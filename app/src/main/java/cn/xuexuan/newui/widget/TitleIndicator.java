package cn.xuexuan.newui.widget;

import android.content.Context;
import android.content.res.TypedArray;
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
    private int size;
    float textSize;
    int textNormalColor;
    int textSelectedColor;
    int textNormalBgColor;
    int textSelectedBgColor;

    public TitleIndicator(Context context) {
        this(context, null);
    }

    public TitleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.title_indicator, this);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TitleIndicator);
            textSize = ta.getDimension(R.styleable.TitleIndicator_textSize, 30);
            textNormalColor = ta.getColor(R.styleable.TitleIndicator_textNormalColor, Color.parseColor("#000000"));
            textSelectedColor = ta.getColor(R.styleable.TitleIndicator_textSelectedColor, Color.parseColor("#ff0000"));
            textNormalBgColor = ta.getColor(R.styleable.TitleIndicator_textNormalBgColor, Color.parseColor("#55000000"));
            textSelectedBgColor = ta.getColor(R.styleable.TitleIndicator_textSelectedBgColor, Color.parseColor("#ffffff"));
        }
    }

    public void setData(List<String> data) {
        mData = data;
        if (mData != null) {
            size = mData.size();
        } else {
            size = 0;
        }
        refreshUI();
    }

    private void refreshUI() {
        ll_container.removeAllViews();
        if (mData == null || size == 0) {
            return;
        }
        TextView tv;
        for (int i = 0; i < size; i++) {
            tv = new TextView(mContext);
            tv.setText(mData.get(i));
            tv.setTextColor(textNormalColor);
            tv.setBackgroundColor(textNormalBgColor);
            tv.setTextSize(textSize);
            tv.setPadding(20, 5, 20, 5);
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

        //选中第一个
        onTitleClick(0);
    }

    private void onTitleClick(int position) {
        TextView childAt;
        for (int i = 0; i < size; i++) {
            childAt = (TextView) ll_container.getChildAt(position);
//            if(i == position) {
//                childAt.setEnabled(false);
//                childAt.setTextColor(textSelectedColor);
//                childAt.setBackgroundColor(textSelectedBgColor);
//            }else{
//                childAt.setEnabled(true);
//                childAt.setTextColor(textNormalColor);
//                childAt.setBackgroundColor(textNormalBgColor);
//            }
            if(i == position) {
                childAt.setEnabled(false);
                childAt.setTextColor(Color.parseColor("#ff0000"));
                childAt.setBackgroundColor(Color.parseColor("#ffffff"));
            }else{
                childAt.setEnabled(true);
                childAt.setTextColor(Color.parseColor("#000000"));
                childAt.setBackgroundColor(Color.parseColor("#55000000"));
            }
        }
        Log.d("wbl", "position = " + position);
    }

}
