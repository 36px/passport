package net36px.passport.app.data.service.impl;

import net36px.passport.api.BaseRestHead;
import net36px.passport.api.body.SignInBody;
import net36px.passport.api.document.SignInDoc;
import net36px.passport.api.document.UnlockDoc;
import net36px.passport.app.context.SessionContext;
import net36px.passport.app.data.service.SignInService;
import net36px.passport.app.net.rest.RestSignIn;
import net36px.passport.app.net.rest.RestUnlock;
import net36px.passport.app.task.qna.Responder;

public class SignInServiceImpl implements SignInService {

    private final SessionContext context;

    public SignInServiceImpl(SessionContext context) {
        this.context = context;
    }

    @Override
    public void login(String email, char[] verification, Responder responder) {

        String verificationStr = null;

        if (verification != null) {
            String str = new String(verification);
            str = str.trim();
            if (str.length() > 0) {
                verificationStr = str;
            }
        }

        RestSignIn rest = context.getRestServiceSet().getSignInService();
        SignInDoc doc = new SignInDoc();
        SignInBody body = new SignInBody();
        BaseRestHead head = new BaseRestHead();


        body.setName(email);
        body.setVerification(verificationStr);

        doc.setHead(head);
        doc.setSignIn(body);

        rest.doPost(doc);

    }

    @Override
    public void unlock(String email, char[] pin, Responder responder) {

        RestUnlock rest = context.getRestServiceSet().getUnlockService();
        UnlockDoc doc = new UnlockDoc();
        rest.doPost(doc);

    }

}
