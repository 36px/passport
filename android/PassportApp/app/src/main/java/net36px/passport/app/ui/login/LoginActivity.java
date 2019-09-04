package net36px.passport.app.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;
import net36px.passport.app.client.PassportClient;
import net36px.passport.app.client.PassportSession;
import net36px.passport.app.task.qna.Responder;

public class LoginActivity extends Activity {

    private ClientAgent clientAgent;
    private EditText editVerification;
    private EditText editEmail;
    private Button buttonRequestVerification;
    private LoginContext loginContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setupButton(R.id.button_request_verification);
        this.setupButton(R.id.button_sign_in);
        this.editEmail = (EditText) this.findViewById(R.id.input_email);
        this.editVerification = (EditText) this.findViewById(R.id.input_verification);
        this.buttonRequestVerification = (Button) this.findViewById(R.id.button_request_verification);

        clientAgent = ClientUtils.getClientAgent(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loginContext = new LoginContext();
    }

    private void setupButton(int id) {
        this.findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.onClickButton(view.getId());
            }
        });
    }

    private void onClickButton(int id) {
        if (id == R.id.button_sign_in) {
            this.doSignIn();
        } else if (id == R.id.button_request_verification) {
            this.doRequestVerification();
        }
    }

    private void doRequestVerification() {
        String email = this.editEmail.getText().toString();
        if (this.verifyEmailText(email)) {
            (new LoginTaskStep1()).execute(this.loginContext);
        }
    }

    private boolean verifyEmailText(String text) {
        if (text == null) {
        } else if (text.contains("@")) {
            return true;
        } else {
        }
        String message = "请输入正确的 E-mail 地址。";
        this.alert(message);
        return false;
    }

    private boolean verifyVerificationText(String text) {
        if (text == null) {
        } else if (text.trim().length() >= 4) {
            return true;
        } else {
        }
        String message = "请输入正确的验证码。";
        this.alert(message);
        return false;
    }

    private void doSignIn() {
        String email = this.editEmail.getText().toString();
        String verification = this.editVerification.getText().toString();
        if (this.verifyEmailText(email) && this.verifyVerificationText(verification)) {
            this.loginContext.email = email;
            this.loginContext.verification = verification;
            (new LoginTaskStep2()).execute(this.loginContext);
        }
    }

    private void alert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private class LoginContext {

        String email;
        String verification;
        String ticket;
        String requestVerificationButtonText;
        boolean step1ok;
        boolean step2ok;
        Exception error;
        Responder responder;

    }


    private class LoginTaskStep1 extends AsyncTask<LoginContext, LoginContext, LoginContext> {

        String theRequestVerificationButtonText;

        @Override
        protected LoginContext doInBackground(LoginContext... param) {
            LoginContext context = param[0];
            try {
                String email = context.email;
                this.sendVerifyRequest(context, email);
                this.runCountdownTimer(context, 60);
                context.step1ok = true;
            } catch (Exception e) {
                e.printStackTrace();
                context.error = e;
            }
            return context;
        }

        private void sendVerifyRequest(LoginContext context, String email) {
            PassportSession session = LoginActivity.this.clientAgent.getSession(email);
            session.getSignInService().login(email, null, context.responder);
            this.publishProgress(context);
        }

        private void runCountdownTimer(LoginContext context, int sec) {
            for (; sec > 0; sec--) {
                String text = sec + "秒后重试";
                context.requestVerificationButtonText = text;
                this.publishProgress(context);
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
        protected void onProgressUpdate(LoginContext... values) {
            super.onProgressUpdate(values);
            String text = values[0].requestVerificationButtonText;
            LoginActivity.this.buttonRequestVerification.setText(text);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Button button = LoginActivity.this.buttonRequestVerification;
            this.theRequestVerificationButtonText = button.getText().toString();
            button.setEnabled(false);
        }

        @Override
        protected void onPostExecute(LoginContext context) {
            super.onPostExecute(context);
            // restore the verify button
            Button button = LoginActivity.this.buttonRequestVerification;
            button.setText(this.theRequestVerificationButtonText);
            button.setEnabled(true);
            // show error
            this.showError(context.error);
        }

        private void showError(Exception error) {
            if (error == null) {
                return;
            }
            String text = "Error: " + error;
            LoginActivity.this.alert(text);
        }
    }

    private class LoginTaskStep2 extends AsyncTask<LoginContext, LoginContext, LoginContext> {

        @Override
        protected LoginContext doInBackground(LoginContext... param) {
            LoginContext context = param[0];
            try {
                String email = context.email;
                String verification = context.verification;
                ClientAgent agent = LoginActivity.this.clientAgent;
                PassportSession session = agent.getSession(email);
                session.getSignInService().login(email, verification.toCharArray(), context.responder);
                context.step2ok = true;
            } catch (Exception e) {
                e.printStackTrace();
                context.error = e;
            }
            return context;
        }

        @Override
        protected void onPostExecute(LoginContext context) {
            super.onPostExecute(context);
            if (context.step2ok) {
                LoginActivity.this.alert("OK");
            } else {
                String text = "Error: " + context.error;
                LoginActivity.this.alert(text);
            }
        }
    }

}
