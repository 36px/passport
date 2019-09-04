package net36px.passport.app.ui.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;
import net36px.passport.app.data.service.SignUpService;
import net36px.passport.app.task.qna.ActivityAskingRouter;
import net36px.passport.app.task.qna.Responder;
import net36px.passport.app.task.qna.TaskProxyResponder;
import net36px.passport.app.ui.login.LoginActivity;
import net36px.passport.app.ui.main.MainActivity;
import net36px.passport.app.ui.password.PasswordDisplayActivity;
import net36px.passport.app.ui.query.QueryActivity;
import net36px.passport.app.ui.scan.CodeScannerActivity;
import net36px.passport.app.ui.signup.SignUpActivity;
import net36px.passport.app.ui.unlock.UnlockWithGraphActivity;
import net36px.passport.app.ui.unlock.UnlockWithPinActivity;

public class DebugRestActivity extends Activity implements Button.OnClickListener {

    private ActivityAskingRouter askingRouter;
    private Responder bgResponder;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_rest);

        // Q&A
        this.askingRouter = new ActivityAskingRouter(this);
        this.bgResponder = new TaskProxyResponder(this.askingRouter);

        // buttons
        this.setupButton(R.id.button_debug_rest_login);
        this.setupButton(R.id.button_debug_rest_unlock);
        this.setupButton(R.id.button_debug_rest_query);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.askingRouter.onActivityResult(requestCode, resultCode, data);
    }

    private void setupButton(int button_id) {
        this.findViewById(button_id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_debug_rest_login:
                break;
            case R.id.button_debug_rest_unlock:
                break;
            case R.id.button_debug_rest_query:
                break;
            default:
                break;
        }
    }
}
