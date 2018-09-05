package cn.xuexuan.newui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends Activity {

    private RecyclerView rlv;
    //private ImageView iv_avatar;
    private RelativeLayout rl_top;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private ArticleListAdapter mArticleListAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        findView();
        initRecycle();
        initListener();
    }

    private void initListener() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("wbl", "AppBarLayout verticalOffset :" + verticalOffset);
            }
        });
    }

    private void initRecycle() {
        mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mList.add(null);
        }


        mArticleListAdapter = new ArticleListAdapter(R.layout.item_article_list, mList);
        rlv.setAdapter(mArticleListAdapter);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void findView() {
        rlv = (RecyclerView) findViewById(R.id.recyclerView);
        //iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
        rl_top = (RelativeLayout) findViewById(R.id.top_relative);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_layout);
    }
}
