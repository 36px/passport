package net36px.passport.app.net.rest;

import net36px.passport.api.document.SignInDoc;

public interface RestSignIn {

    SignInDoc doPost(SignInDoc doc);

}
