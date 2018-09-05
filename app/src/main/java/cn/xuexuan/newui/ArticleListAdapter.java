package cn.xuexuan.newui;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Allen on 2018/8/16 0016.
 * 文章列表适配器
 */

public class ArticleListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ArticleListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        View view = helper.getView(R.id.span);
        if(helper.getLayoutPosition() == 0){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }

}
