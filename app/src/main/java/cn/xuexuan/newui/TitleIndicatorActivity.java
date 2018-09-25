package cn.xuexuan.newui;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xuexuan.newui.widget.TitleIndicator;

public class TitleIndicatorActivity extends Activity {

    TitleIndicator title_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_indicator);
        title_indicator = (TitleIndicator) findViewById(R.id.title_indicator);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("title" + i);
        }
        title_indicator.setData(data);

        final TextView tv = (TextView) findViewById(R.id.tv);
        tv.setTextColor(Color.parseColor("#ff0000"));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setTextColor(Color.parseColor("#00ff00"));
            }
        });
    }
}
