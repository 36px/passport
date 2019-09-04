package net36px.passport.app.data.service;

public interface SessionService {

    void logout();

    void lock();

    void unlock(char[] pass_code);

}
