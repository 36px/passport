package net36px.passport.app.ui.scan;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import net36px.passport.app.R;
import net36px.passport.app.client.ClientAgent;
import net36px.passport.app.client.ClientUtils;

public class CodeScannerActivity extends Activity implements SurfaceHolder.Callback {

    private ClientAgent clientAgent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);
        clientAgent = ClientUtils.getClientAgent(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SurfaceView preview_box = (SurfaceView) this.findViewById(R.id.camera_preview_box);
        SurfaceHolder holder = preview_box.getHolder();
        holder.addCallback(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SurfaceView preview_box = (SurfaceView) this.findViewById(R.id.camera_preview_box);
        SurfaceHolder holder = preview_box.getHolder();
        holder.removeCallback(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        // com.google.zxing.client.android.camera.CameraConfigurationUtils.

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
