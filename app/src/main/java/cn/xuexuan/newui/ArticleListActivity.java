package cn.xuexuan.newui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends Activity {

    private RecyclerView rlv;
    private ImageView iv_avatar;

    private ArticleListAdapter mArticleListAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        findView();
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
        iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
    }
}
