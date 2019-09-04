package net36px.passport.app.task.qna;

import android.os.Handler;

public class TaskProxyResponder implements Responder {

    private final Handler handler;
    private final Responder responder;

    public TaskProxyResponder(Responder responder) {
        this.handler = new Handler();
        this.responder = responder;
    }

    public void handle(Question question) {
        Asking asking = new Asking();
        asking.setQuestion(question);
        this.handle(asking);
    }

    public void handle(final Asking asking) {
        Task task = new Task();
        asking.setAsker(task);
        this.handler.post(new Runnable() {
            @Override
            public void run() {
                TaskProxyResponder.this.responder.handle(asking);
            }
        });
        task.waitForAnswer(asking.getTimeout());
    }

    private static class Task implements Asker {

        private boolean done;
        private boolean hasReject;

        public Task() {
        }

        @Override
        public void commit(Asking asking) {
            this.done = true;
        }

        @Override
        public void close(Asking asking) {
            this.done = true;
        }

        @Override
        public void reject(Asking asking) {
            this.hasReject = true;
            this.done = true;
        }

        public void waitForAnswer(int timeout) {

            final int step = 1000;

            for (; ; ) {
                if (this.done) {
                    break;
                } else if (timeout <= 0) {
                    throw new RuntimeException("timeout");
                } else {
                    timeout -= step;
                    this.sleep(step);
                }
            }

            if (this.hasReject) {
                throw new RuntimeException("reject");
            }

        }

        private void sleep(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
