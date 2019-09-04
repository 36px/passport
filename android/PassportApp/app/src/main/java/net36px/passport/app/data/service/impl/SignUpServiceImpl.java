package net36px.passport.app.data.service.impl;

import net36px.passport.api.body.SignUpBody;
import net36px.passport.api.document.SignUpDoc;
import net36px.passport.app.context.ClientContext;
import net36px.passport.app.data.service.SignUpService;
import net36px.passport.app.net.rest.RestSignUp;

public class SignUpServiceImpl implements SignUpService {

    private final ClientContext context;

    public SignUpServiceImpl(ClientContext context) {
        this.context = context;
    }

    @Override
    public void signUp(String email, char[] verification) {

        SignUpDoc doc = new SignUpDoc();
        SignUpBody body = new SignUpBody();

        doc.setSignUp(body);
        body.setEmail(email);

        if (verification != null) {
            body.setVerification(new String(verification));
        }

        RestSignUp rest = context.getRestServiceSet().getSignUpService();
        rest.doPost(doc);

    }
}
