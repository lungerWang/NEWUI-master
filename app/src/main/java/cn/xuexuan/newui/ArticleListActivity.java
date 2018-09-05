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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArticleListActivity extends Activity implements BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView rlv;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LinearLayout ll_toolbar_content;
    private LinearLayout ll_main_content;

    private ArticleListAdapter mArticleListAdapter;
    private List<String> mList;
    float screenW;
    float toolbarHeight;
    float initHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        screenW = getResources().getDisplayMetrics().widthPixels;
        toolbarHeight = getResources().getDimension(R.dimen.tool_bar_height);
        initHeight = getResources().getDimension(R.dimen.app_bar_height);

        findView();
        initRecycle();
        initListener();
        ll_toolbar_content.setAlpha(0);
    }

    private void initListener() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float scale = 1.0f - (-verticalOffset) / (initHeight - toolbarHeight);
                Log.d("wbl", "scale :" + scale);
                ll_toolbar_content.setAlpha(1 - scale);
                ll_main_content.setAlpha(scale);
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
        mArticleListAdapter.setOnLoadMoreListener(this, rlv);
    }

    private void findView() {
        rlv = (RecyclerView) findViewById(R.id.recyclerView);
        //iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_layout);
        ll_toolbar_content = (LinearLayout) findViewById(R.id.ll_toolbar_content);
        ll_main_content = (LinearLayout) findViewById(R.id.ll_main_content);
    }

    @Override
    public void onLoadMoreRequested() {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mArticleListAdapter.loadMoreComplete();
                        for (int i = 0; i < 5; i++) {
                            mList.add(null);
                        }
                        mArticleListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
