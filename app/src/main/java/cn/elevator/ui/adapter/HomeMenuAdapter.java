package cn.elevator.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Pair;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.elevator.R;

/**
 * @anthor DamoJiang
 * @date 2018/8/15
 * @describe 首页菜单
 *
 **/
public class HomeMenuAdapter extends BaseQuickAdapter<Pair<String, Integer>, BaseViewHolder> {
    public HomeMenuAdapter(@Nullable List<Pair<String, Integer>> data) {
        super(R.layout.item_home_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Pair<String, Integer> item) {
        helper.setText(R.id.titleTextView, item.first)
                .setImageResource(R.id.iconImageView, item.second);
    }
}
