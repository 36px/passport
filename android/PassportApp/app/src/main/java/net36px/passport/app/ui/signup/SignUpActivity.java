package net36px.passport.app.ui.signup;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;
import net36px.passport.app.client.PassportClient;

public class SignUpActivity extends Activity {

    private ClientAgent clientAgent;
    private EditText editVerification;
    private EditText editEmail;
    private Button buttonRequestVerification;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setupButton(R.id.button_request_verification);
        this.setupButton(R.id.button_sign_up);
        this.editEmail = (EditText) this.findViewById(R.id.edit_email);
        this.editVerification = (EditText) this.findViewById(R.id.edit_verification);
        this.buttonRequestVerification = (Button) this.findViewById(R.id.button_request_verification);

        clientAgent = ClientUtils.getClientAgent(this);
    }

    private void setupButton(int id) {
        this.findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.this.onClickButton(view.getId());
            }
        });
    }

    private void onClickButton(int id) {
        if (id == R.id.button_sign_up) {
            this.doSignUp();
        } else if (id == R.id.button_request_verification) {
            this.doRequestVerification();
        }
    }

    private void doRequestVerification() {
        String email = this.editEmail.getText().toString();
        if (this.verifyEmailText(email)) {
            (new RequestVerificationTask()).execute(email);
        }
    }

    private boolean verifyEmailText(String text) {
        if (text == null) {
        } else if (text.contains("@")) {
            return true;
        } else {
        }
        String message = "请输入正确的 E-mail 地址。";
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean verifyVerificationText(String text) {
        if (text == null) {
        } else if (text.trim().length() >= 4) {
            return true;
        } else {
        }
        String message = "请输入正确的验证码。";
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
        return false;
    }

    private void doSignUp() {
        String email = this.editEmail.getText().toString();
        String verification = this.editVerification.getText().toString();
        if (this.verifyEmailText(email) && this.verifyVerificationText(verification)) {
            (new SignUpTask()).execute(email, verification);
        }
    }

    private class RequestVerificationTask extends AsyncTask<String, String, String> {
        private String theRequestVerificationButtonText;
        private boolean success;
        private Exception error;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String email = strings[0];
                this.sendVerifyRequest(email);
                this.runCountdownTimer(60);
                this.success = true;
                return "OK";
            } catch (Exception e) {
                e.printStackTrace();
                this.error = e;
                return "Error";
            }
        }

        private void sendVerifyRequest(String email) {
            PassportClient client = SignUpActivity.this.clientAgent.getClient();
            client.getSignUpService().signUp(email, null);
            this.publishProgress("OK");
        }

        private void runCountdownTimer(int sec) {
            for (; sec > 0; sec--) {
                String text = sec + "秒后重试";
                this.publishProgress(text);
                this.sleep(1000);
            }
        }

        private void sleep(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String text = values[0];
            SignUpActivity.this.buttonRequestVerification.setText(text);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Button button = SignUpActivity.this.buttonRequestVerification;
            this.theRequestVerificationButtonText = button.getText().toString();
            button.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // restore the verify button
            Button button = SignUpActivity.this.buttonRequestVerification;
            button.setText(this.theRequestVerificationButtonText);
            button.setEnabled(true);
            // show error
            this.showError(this.error);
        }

        private void showError(Exception error) {
            if (error == null) {
                return;
            }
            String text = "Error: " + error;
            Toast.makeText(SignUpActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }

    private class SignUpTask extends AsyncTask<String, String, String> {
        private boolean success;
        private Exception error;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String email = strings[0];
                String verification = strings[1];
                PassportClient client = SignUpActivity.this.clientAgent.getClient();
                client.getSignUpService().signUp(email, verification.toCharArray());
                this.success = true;
                return "OK";
            } catch (Exception e) {
                e.printStackTrace();
                this.error = e;
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (this.success) {
                Toast.makeText(SignUpActivity.this, "OK", Toast.LENGTH_LONG).show();
            } else {
                String text = "Error: " + this.error;
                Toast.makeText(SignUpActivity.this, text, Toast.LENGTH_LONG).show();
            }
        }
    }

}
