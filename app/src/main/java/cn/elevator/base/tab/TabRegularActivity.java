package cn.elevator.base.tab;

import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;

import cn.elevator.R;

/**
 * author: DamonJiang
 * date:   2018/8/15 0013
 * description:
 */
public abstract class TabRegularActivity extends BaseTabActivity implements INavigator.OnNavigatorChangedListener {

    /**
     * 是否底部导航
     *
     * @return
     */
    protected boolean isBottomNavigation() {
        return true;
    }

    private QMUITabSegment tabSegment;
    @Override
    protected INavigator generateNavigator() {
        int layoutId = isBottomNavigation() ? R.layout.layout_bottom_tab : R.layout.layout_top_tab;

        View contentParent = View.inflate(this, layoutId, null);
        tabSegment = contentParent.findViewById(R.id.tabLayout);


        INavigator navigator = new TabRegularNavigator.Builder(this)
                .fragmentManager(getSupportFragmentManager())
                .contentParent(contentParent)
                .tabSegment(tabSegment)
                .titles(generateTitles())
                .fragments(generateFragments())
                .icons(generateIcons())
                .contentId(R.id.contentView)
                .listener(this)
                .build();

        return navigator;
    }


    @Override
    public void onPageSelected(int position) {

    }

    /**
     * 是否显示指示器
     * @param hasIndicator
     */
    protected void setHasIndicator(boolean hasIndicator){
        tabSegment.setHasIndicator(hasIndicator);
    }

}
