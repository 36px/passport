package net36px.passport.app.task.qna;

import android.app.Activity;
import android.content.Intent;

public class ActivityAskingRouter implements Responder {

    private final Activity activity;

    public ActivityAskingRouter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void handle(Asking asking) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

}
