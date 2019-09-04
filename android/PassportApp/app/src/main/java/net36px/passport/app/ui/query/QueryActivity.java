package net36px.passport.app.ui.query;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;
import net36px.passport.app.ui.unlock.UnlockWithGraphActivity;


public class QueryActivity extends Activity {

    private final static int UNLOCK_REQUEST_CODE = 0x9874123;

    private ClientAgent clientAgent;

    private String unlockCode;
    private String credentialUrl;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        clientAgent = ClientUtils.getClientAgent(this);
        this.setupScanCodeButton();
        this.setupQueryButton();
    }

    private void setupQueryButton() {
        this.findViewById(R.id.buttonQuery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryActivity.this.query();
            }
        });
    }


    private void setupScanCodeButton() {
        this.findViewById(R.id.imageButtonScanCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryActivity.this.scanCode();
            }
        });
    }

    private void scanCode() {
        (new IntentIntegrator(this)).initiateScan();
    }

    private void unlock() {
        Intent intent = new Intent(this, UnlockWithGraphActivity.class);
        this.startActivityForResult(intent, UNLOCK_REQUEST_CODE);
    }

    private void query() {

        // send query rest request

        this.clientAgent.getSession().getCredentialService().get("");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UNLOCK_REQUEST_CODE) {
            this.onUnlockResult(requestCode, resultCode, data);
        } else {
            this.onScanResult(requestCode, resultCode, data);
        }
    }

    private void onUnlockResult(int requestCode, int resultCode, Intent data) {

        this.unlockCode = "undefine";

        this.query();

    }

    private void onScanResult(int requestCode, int resultCode, Intent data) {


        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                this.unlock();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
