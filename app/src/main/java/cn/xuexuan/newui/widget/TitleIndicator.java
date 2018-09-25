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
import android.widget.Toast;

import java.util.List;

import cn.xuexuan.newui.R;

/**
 * Created by Allen on 2018/9/25 0025.
 * 首页标题选择控件
 */

public class TitleIndicator<T> extends RelativeLayout {

    private Context mContext;
    //主容器
    private LinearLayout ll_container;
    //数据集合
    private List<T> mData;
    private int size;
    //选中回调
    private OnTitleSelectListener mOnTitleSelectListener;
    //分类名解析
    private Parser mParser;
    float textSize;
    //未选中文字颜色
    int textNormalColor;
    //选中文字颜色
    int textSelectedColor;
    //未选中背景颜色
    int textNormalBgColor;
    //选中背景颜色
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
        //获取xml自定义属性
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TitleIndicator);
            textSize = ta.getDimension(R.styleable.TitleIndicator_textSize, 30);
            textNormalColor = ta.getColor(R.styleable.TitleIndicator_textNormalColor, Color.parseColor("#000000"));
            textSelectedColor = ta.getColor(R.styleable.TitleIndicator_textSelectedColor, Color.parseColor("#ff0000"));
            textNormalBgColor = ta.getColor(R.styleable.TitleIndicator_textNormalBgColor, Color.parseColor("#55000000"));
            textSelectedBgColor = ta.getColor(R.styleable.TitleIndicator_textSelectedBgColor, Color.parseColor("#ffffff"));
        }
    }

    /**
     * 设置选中监听器
     * @param onTitleSelectListener 监听器
     */
    public void setOnTitleSelectListener(OnTitleSelectListener onTitleSelectListener) {
        mOnTitleSelectListener = onTitleSelectListener;
    }

    /**
     * 设置数据源，解析器
     * @param data 数据源
     * @param parser 从数据源解析出分类名称的解析器
     */
    public void setData(List<T> data, Parser parser) {
        mData = data;
        mParser = parser;
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
            if(mData.get(i) != null){
                tv.setText(mParser.getName(mData.get(i)));
            }else{
                tv.setText("");
            }
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

    /**
     * 选中后的处理
     * @param position
     */
    private void onTitleClick(int position) {
        TextView childAt;
        for (int i = 0; i < size; i++) {
            childAt = (TextView) ll_container.getChildAt(i);
            //设置字色，背景色
            if(i == position) {
                childAt.setEnabled(false);
                childAt.setTextColor(textSelectedColor);
                childAt.setBackgroundColor(textSelectedBgColor);
            }else{
                childAt.setEnabled(true);
                childAt.setTextColor(textNormalColor);
                childAt.setBackgroundColor(textNormalBgColor);
            }
        }
        //回调position给调用者
        if(mOnTitleSelectListener != null){
            mOnTitleSelectListener.onSelect(position);
        }
    }

    public interface OnTitleSelectListener{
        void onSelect(int position);
    }

    public interface Parser<T>{
        String getName(T t);
    }

}
