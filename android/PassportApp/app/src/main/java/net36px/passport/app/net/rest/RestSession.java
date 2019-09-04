package net36px.passport.app.net.rest;

import net36px.passport.api.document.SessionDoc;

public interface RestSession {

    /****************
     * login
     * */

    SessionDoc doPost(SessionDoc doc);


    /***************
     * logout
     * */

    SessionDoc doDelete(String id);

    /***************
     * lock/unlock
     * */

    SessionDoc doPut(String id, SessionDoc doc);

}
