package net36px.passport.app.net.rest;

import net36px.passport.api.document.SignUpDoc;

public interface RestSignUp {

    SignUpDoc doPost(SignUpDoc doc);

}
