package cn.elevator.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.Arrays;

import cn.elevator.base.tab.INavigator;

/**
 * @anthor DamoJiang
 * @date 2018/8/13
 * @describe describe
 **/
public class TabRegularHelper {
    private Context mContext;
    private FragmentManager mFragmentManager;
    private QMUITabSegment mTabSegment;
    private int mContentId;
    private String[] mNavTitles;
    private int[] mTabIcons;
    private Fragment[] mFragments;
    private INavigator.OnNavigatorChangedListener mListener;

    private FragmentHelper helper;
    public TabRegularHelper(Builder builder) {
        this.mContext = builder.context;
        this.mFragmentManager = builder.fragmentManager;
        this.mTabSegment = builder.tabSegment;
        this.mContentId = builder.contentId;
        this.mNavTitles = builder.navTitles;
        this.mTabIcons = builder.tabIcons;
        this.mFragments = builder.fragments;
        this.mListener = builder.listener;
        this.helper=new FragmentHelper(this.mFragmentManager,this.mContentId,Arrays.asList(this.mFragments));
    }


    /**
     * 装在布局
     */
    public void installContentView() {
        if (mFragments == null || mFragments.length == 0) {
            return;
        }
        installContent();
        installBottomTab();
    }


    /**
     * 装载内容区域
     */
    private void installContent() {
        helper.addAndShowFragment(0);
    }


    /**
     * 装载底部导航布局
     */
    private void installBottomTab() {

        for (int i = 0; i < mNavTitles.length; i++) {

            QMUITabSegment.Tab tab = null;

            if (null == mTabIcons || mTabIcons.length == 0) {
                tab = new QMUITabSegment.Tab(mNavTitles[i]);
            } else {
                tab = new QMUITabSegment.Tab(
                        ContextCompat.getDrawable(mContext, mTabIcons[i]),
                        null,
                        mNavTitles[i], true
                );
                tab.setIconPosition(QMUITabSegment.ICON_POSITION_TOP);
            }

            mTabSegment.addTab(tab);
        }
        mTabSegment.selectTab(0);
        mTabSegment.setOnTabClickListener(index -> {
            if (mListener != null) {
                mListener.onPageSelected(index);
            }
            helper.showCurrentFragment(index);
        });
    }


    public static class Builder {
        private Context context;
        private FragmentManager fragmentManager;
        /*QMUITabSegment布局*/
        private QMUITabSegment tabSegment;
        /*需添加内容的容器布局*/
        private int contentId;
        /*标题列表*/
        private String[] navTitles;
        /*图标列表*/
        private int[] tabIcons;
        /*fragment列表*/
        private Fragment[] fragments;

        private INavigator.OnNavigatorChangedListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder fragmentManager(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
            return this;
        }

        public Builder tabSegment(QMUITabSegment tabSegment) {
            this.tabSegment = tabSegment;
            return this;
        }

        public Builder contentId(int contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder titles(String[] navTitles) {
            this.navTitles = navTitles;
            return this;
        }

        public Builder icons(int[] tabIcons) {
            this.tabIcons = tabIcons;
            return this;
        }


        public Builder fragments(Fragment[] fragments) {
            this.fragments = fragments;
            return this;
        }

        public Builder listener(INavigator.OnNavigatorChangedListener listener) {
            this.listener = listener;
            return this;
        }

        public TabRegularHelper build() {
            return new TabRegularHelper(this);
        }

    }
}
