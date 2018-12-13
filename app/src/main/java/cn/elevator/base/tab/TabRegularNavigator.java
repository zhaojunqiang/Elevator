package cn.elevator.base.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;

import cn.elevator.helper.TabRegularHelper;


/**
 * @anthor DamonJiang
 * @date 2018/8/15
 * @describe describe
 **/
public class TabRegularNavigator implements INavigator {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private View mContentParent;
    private QMUITabSegment mTabSegment;
    private int mContentId;
    private String[] mNavTitles;
    private int[] mTabIcons;
    private Fragment[] mFragments;

    private OnNavigatorChangedListener mListener;

    private TabRegularNavigator(Builder builder) {
        this.mContext = builder.context;
        this.mFragmentManager = builder.fragmentManager;
        this.mContentParent = builder.contentParent;
        this.mTabSegment = builder.tabSegment;
        this.mContentId = builder.contentId;
        this.mNavTitles = builder.navTitles;
        this.mTabIcons = builder.tabIcons;
        this.mFragments = builder.fragments;
        this.mListener = builder.listener;

    }


    @Override
    public void attachToTab() {
        new TabRegularHelper.Builder(mContext)
                .fragmentManager(mFragmentManager)
                .fragments(mFragments)
                .contentId(mContentId)
                .tabSegment(mTabSegment)
                .titles(mNavTitles)
                .icons(mTabIcons)
                .listener(mListener)
                .build().installContentView();
    }

    @Override
    public View getContentView() {
        return mContentParent;
    }

    @Override
    public void destroy() {

    }


    public static class Builder {
        private Context context;
        private FragmentManager fragmentManager;
        /*父容器*/
        private View contentParent;
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
        /*选中监听*/
        private OnNavigatorChangedListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder fragmentManager(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
            return this;
        }

        public Builder contentParent(View contentParent) {
            this.contentParent = contentParent;
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

        public Builder listener(OnNavigatorChangedListener listener) {
            this.listener = listener;
            return this;
        }

        public TabRegularNavigator build() {
            return new TabRegularNavigator(this);
        }


    }


}
