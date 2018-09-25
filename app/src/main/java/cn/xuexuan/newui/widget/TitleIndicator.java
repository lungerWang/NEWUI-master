package cn.xuexuan.newui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.xuexuan.newui.R;

/**
 * Created by Allen on 2018/9/25 0025.
 */

public class TitleIndicator extends RelativeLayout {

    private Context mContext;
    private LinearLayout ll_container;
    private List<String> mData;

    public TitleIndicator(Context context) {
        this(context, null);
    }

    public TitleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.title_indicator, this);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ll_container.setBackgroundColor(Color.parseColor("#55000000"));
    }

    public void setData(List<String> data) {
        mData = data;
        refreshUI();
    }

    private void refreshUI() {
        if (mData == null) {
            return;
        }
        ll_container.removeAllViews();
        TextView tv;
        for (int i = 0; i < mData.size(); i++) {
            tv = new TextView(mContext);
            tv.setText(mData.get(i));
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setTextSize(30);
            tv.setPadding(20,5, 20, 5);
            //标记position
            tv.setTag(i);
            final TextView finalTv = tv;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitleClick((Integer) finalTv.getTag());
                }
            });
            ll_container.addView(tv);
        }
    }

    private void onTitleClick(int position){
        Log.d("wbl", "position = " + position);
    }

}
