package cn.xuexuan.newui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CoorLayoutActivity3 extends Activity implements BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView rlv;
    private AppBarLayout mAppBarLayout;

    private ArticleListAdapter mArticleListAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cor3);

        findView();
        initRecycle();
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
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
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
