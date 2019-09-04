package net36px.passport.app.net.rest.impl;


import net36px.passport.api.PassportAppApi;
import net36px.passport.api.document.SignUpDoc;
import net36px.passport.app.context.ClientContext;
import net36px.passport.app.net.RestClient;
import net36px.passport.app.net.rest.RestSignUp;

public class RestSignUpImpl implements RestSignUp {

    private final ClientContext context;

    public RestSignUpImpl(ClientContext context) {
        this.context = context;
    }

    @Override
    public SignUpDoc doPost(SignUpDoc doc) {
        RestClient client = context.getRestClient();
        return client.post(PassportAppApi.SIGN_UP, null, doc);
    }
}
