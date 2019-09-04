package net36px.passport.app.ui.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import net36px.passport.app.ui.login.LoginActivity;
import net36px.passport.app.ui.main.MainActivity;
import net36px.passport.app.ui.password.PasswordDisplayActivity;
import net36px.passport.app.ui.query.QueryActivity;
import net36px.passport.app.ui.scan.CodeScannerActivity;
import net36px.passport.app.ui.signup.SignUpActivity;
import net36px.passport.app.ui.unlock.UnlockWithGraphActivity;
import net36px.passport.app.ui.unlock.UnlockWithPinActivity;

public class DebugActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<Class<?>> activity_type_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        this.listView = this.findViewById(R.id.listView1);

        this.activity_type_list = this.createActivityTypeList();
        List<String> list = this.toStringList(this.activity_type_list);

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list);
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(this);
    }

    private List<String> toStringList(List<Class<?>> set) {
        List<String> list = new ArrayList<>();
        for (Class<?> type : set) {
            list.add(type.getSimpleName());
        }
        return list;
    }

    private List<Class<?>> createActivityTypeList() {
        List<Class<?>> set = new ArrayList<>();

        set.add(LoginActivity.class);
        set.add(QueryActivity.class);
        set.add(CodeScannerActivity.class);
        set.add(PasswordDisplayActivity.class);
        set.add(UnlockWithPinActivity.class);
        set.add(UnlockWithGraphActivity.class);
        set.add(MainActivity.class);
        set.add(SignUpActivity.class);

        return set;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Class<?> type = this.activity_type_list.get(i);
        this.startActivity(new Intent(this, type));
    }

}
