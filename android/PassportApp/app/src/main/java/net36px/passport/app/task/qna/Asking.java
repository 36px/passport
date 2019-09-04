package net36px.passport.app.task.qna;

public class Asking {

    private int timeout; // in ms
    private Question question;
    private Asker asker;
    private Answer answer;

    public Asking() {
        this.timeout = 60 * 1000;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Asker getAsker() {
        return asker;
    }

    public void setAsker(Asker asker) {
        this.asker = asker;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
