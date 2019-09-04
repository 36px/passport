package net36px.passport.app.task.qna;

public interface Asker {

    void commit(Asking asking);

    void close(Asking asking);

    void reject(Asking asking);

}
