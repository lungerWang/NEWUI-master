package cn.xuexuan.newui;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.xuexuan.newui.bean.CategoryBean;
import cn.xuexuan.newui.widget.TitleIndicator;

public class TitleIndicatorActivity extends Activity {

    //主分类控件
    TitleIndicator<CategoryBean> title_indicator;
    //子分类控件
    TitleIndicator<String> title_indicator_sub;
    private List<CategoryBean> mCategoryBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_indicator);
        findView();
        fakeData();
        setListener();
    }

    private void findView() {
        title_indicator = (TitleIndicator) findViewById(R.id.title_indicator);
        title_indicator_sub = (TitleIndicator) findViewById(R.id.title_indicator_sub);
    }

    private void fakeData() {
        //创建模拟数据
        CategoryBean categoryBean;
        for (int i = 0; i < 9; i++) {
            categoryBean = new CategoryBean();
            categoryBean.setName("First" + i);
            double v = Math.random() * 10;
            List<String> subList = new ArrayList<>();
            for (int j = 0; j < v; j++) {
                subList.add(i + "Second" + j);
            }
            categoryBean.setSubCategories(subList);
            mCategoryBeans.add(categoryBean);
        }
        //设置一个没有子分类的主分类
        CategoryBean categoryBeanLast = new CategoryBean();
        categoryBeanLast.setName("First9");

        mCategoryBeans.add(categoryBeanLast);
    }
    
    private void setListener() {
        title_indicator.setOnTitleSelectListener(new TitleIndicator.OnTitleSelectListener() {
            @Override
            public void onSelect(int position) {
                Log.d("wbl", "first position = " + position);
                title_indicator_sub.setData(mCategoryBeans.get(position).getSubCategories(), new TitleIndicator.Parser<String>() {
                    @Override
                    public String getName(String o) {
                        return o;
                    }
                });
            }
        });

        title_indicator_sub.setOnTitleSelectListener(new TitleIndicator.OnTitleSelectListener() {
            @Override
            public void onSelect(int position) {
                Log.d("wbl", "second position = " + position);

            }
        });

        title_indicator.setData(mCategoryBeans, new TitleIndicator.Parser<CategoryBean>() {
            @Override
            public String getName(CategoryBean o) {
                return o.getName();
            }
        });
    }
}
