package cn.xuexuan.newui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goMoveView(View view) {
        startActivity(new Intent(this, CoordinatorActivity.class));
    }

    public void goCoordinatorLayout1(View view) {
        startActivity(new Intent(this, CoorLayoutActivity1.class));
    }

    public void goCoordinatorLayout2(View view) {
        startActivity(new Intent(this, CoorLayoutActivity2.class));
    }
}
