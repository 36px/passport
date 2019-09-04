package net36px.passport.app.data.service;

import net36px.passport.app.task.qna.Responder;

public interface SignInService {

    void login(String email, char[] verification, Responder responder);

    void unlock(String email, char[] pin, Responder responder);

}
