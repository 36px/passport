package net36px.passport.app.ui.credential;

import android.app.Activity;
import android.os.Bundle;

import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;

public class CredentialSelectActivity  extends Activity {

    private ClientAgent clientAgent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        clientAgent = ClientUtils.getClientAgent(this);
    }

}
