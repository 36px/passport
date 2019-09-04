package net36px.passport.app.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;


import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;
import net36px.passport.app.client.PassportSession;
import net36px.passport.app.client.SessionState;
import net36px.passport.app.ui.login.LoginActivity;
import net36px.passport.app.ui.query.QueryActivity;
import net36px.passport.app.task.GetSessionStateTask;

public class MainActivity extends Activity {

    private ClientAgent clientAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientAgent = ClientUtils.getClientAgent(this);

        //  this.setupButton(R.id.textPassport);
        //  this.setupButton(R.id.textPassword);
    }

    private void setupButton(int id) {
        this.findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onClickButton(view.getId());
            }
        });
    }

    private void onClickButton(int id) {
        if (id == R.id.textPassword) {
            this.startActivity(LoginActivity.class);
        } else if (id == R.id.textPassport) {
            this.startActivity(QueryActivity.class);
        }
    }

    private void startActivity(Class<?> activity) {
        this.startActivity(new Intent(this, activity));
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetSessionState context = new GetSessionState();
        GetSessionStateTask task = new GetSessionStateTask();
        task.execute(context);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private static class GetSessionState {

        SessionState state;
    }

    private class GetSessionStateTask extends AsyncTask<GetSessionState, GetSessionState, GetSessionState> {

        @Override
        protected GetSessionState doInBackground(GetSessionState... params) {
            GetSessionState context = params[0];
            try {
                String account = clientAgent.getClient().getCurrentService().getCurrentAccount();
                if (account == null) {
                    context.state = SessionState.INIT;
                } else {
                    context.state = clientAgent.getSession(account).getState();
                }
            } catch (Exception e) {
                e.printStackTrace();
                context.state = SessionState.ERROR;
            }
            return context;
        }

        @Override
        protected void onPostExecute(GetSessionState context) {
            super.onPostExecute(context);
            MainActivity.this.onSessionState(context);
        }
    }

    private void onSessionState(GetSessionState context) {
        SessionState state = context.state;
        if (state == SessionState.LOCKED || state == SessionState.READY) {
            this.startActivity(QueryActivity.class);
        } else {
            this.startActivity(LoginActivity.class);
        }
    }

}
