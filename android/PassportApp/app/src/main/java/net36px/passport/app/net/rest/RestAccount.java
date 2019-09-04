package net36px.passport.app.net.rest;

import net36px.passport.api.document.AccountDoc;

public interface RestAccount {

    /***************************
     *  destroy a account
     * */

    AccountDoc  doDelete(AccountDoc data);

}
