package cn.elevator.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.Gravity;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.elevator.R;
import cn.elevator.bean.MenuData;
import q.rorbin.badgeview.QBadgeView;

/**
 * @anthor DamoJiang
 * @date 2018/8/15
 * @describe 首页菜单
 *
 **/
public class HomeMenuAdapter extends BaseQuickAdapter<Pair<String, MenuData>, BaseViewHolder> {
    public HomeMenuAdapter(@Nullable List<Pair<String, MenuData>> data) {
        super(R.layout.item_home_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Pair<String, MenuData> item) {
        helper.setText(R.id.titleTextView, item.first);
        ImageView imageView = helper.getView(R.id.iconImageView);
        imageView.setImageResource(item.second.getIcon());
        QBadgeView badgeView = new QBadgeView(mContext);
        badgeView.bindTarget(imageView);
        badgeView.setBadgeNumber(item.second.getCout());
        badgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
    }
}
