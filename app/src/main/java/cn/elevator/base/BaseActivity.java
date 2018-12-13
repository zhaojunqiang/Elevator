package cn.elevator.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import cn.elevator.R;
import cn.elevator.app.App;
import cn.elevator.base.title.TitleBuilder;
import cn.elevator.helper.ResourceHelper;

/**
 * author: DamonJiang
 * date:   2018/8/15 0015
 * description: 基类 activity
 */
public abstract class BaseActivity extends BaseAppActivity {
    //自定义标题栏
    protected CommonTitleBar commonTitleBar;
    // 所有自类的父布局
    private LinearLayout contentPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        LinearLayout parentView = (LinearLayout) View.inflate(this, R.layout.activity_base, null);
        setContentView(parentView);
        commonTitleBar = findViewById(R.id.commonTitleBar);
        commonTitleBar.getLeftImageButton().setImageDrawable(ResourceHelper.getDrawable(R.drawable.left));
        commonTitleBar.setBackgroundColor(ResourceHelper.getColor(R.color.colorAccent));
        commonTitleBar.getCenterTextView().setTextColor(ResourceHelper.getColor(R.color.colorWhite));
        initToolbar();
        contentPanel = findViewById(R.id.contentPanel);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            View.inflate(this, layoutId, contentPanel);
        }

        setInitContentView(true);
        super.onCreate(savedInstanceState);
    }



    /**
     * toolbar相关设置 增加标题名字
     */
    private void initToolbar() {

        initTitleBar();
        //标题监听处理
        commonTitleBar.setListener((v, action, extra) -> {
            if (action == CommonTitleBar.ACTION_LEFT_BUTTON || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                onGoBack();
            } else if (action == CommonTitleBar.ACTION_RIGHT_TEXT || action == CommonTitleBar.ACTION_RIGHT_BUTTON) {
                onCommitClick();
            } else if (action == CommonTitleBar.ACTION_CENTER_TEXT) {
                onCenterTextClick();
            } else if (action == CommonTitleBar.ACTION_SEARCH_VOICE) {
                onSearchVoiceClick();
            } else if (action == CommonTitleBar.ACTION_SEARCH_DELETE) {
                onSearchDeleteClick();
            }

        });
    }

    /**
     * 初始化标题栏
     */
    protected void initTitleBar() {
        TitleBuilder titleBuilder = createTitleBuilder();
        if (null == titleBuilder) return;
        commonTitleBar.setVisibility(titleBuilder.isShowingTitleBar() ? View.VISIBLE : View.GONE);
        commonTitleBar.getLeftImageButton().setVisibility(titleBuilder.isShowingBack() ? View.VISIBLE : View.GONE);

        commonTitleBar.getCenterTextView().setText(TextUtils.isEmpty(titleBuilder.getTitle()) ? "" : titleBuilder.getTitle());

        commonTitleBar.getCenterSubTextView().setVisibility(titleBuilder.isShowingSubTitle() ? View.VISIBLE : View.GONE);
        commonTitleBar.getCenterSubTextView().setText(TextUtils.isEmpty(titleBuilder.getSubTitle()) ? "" : titleBuilder.getSubTitle());

        commonTitleBar.getRightTextView().setVisibility(titleBuilder.isShowingRightText() ? View.VISIBLE : View.GONE);
        commonTitleBar.getRightTextView().setText(TextUtils.isEmpty(titleBuilder.getRightText()) ? "" : titleBuilder.getRightText());
    }
    /**
     * 返回结束
     */
    protected void onGoBack() {
        App.getActiveActivity().finish();
    }

    /**
     * 创建标题栏参数
     *
     * @return
     */
    protected TitleBuilder createTitleBuilder() {
        return null;
    }

    protected void setCurrentTitle(String title) {
        commonTitleBar.getCenterTextView().setText(title);
    }


    /**
     * 标题栏右边提交等操作
     */
    protected void onCommitClick() {

    }

    /**
     * 中间文本点击监听
     */
    protected void onCenterTextClick() {

    }

    /**
     * 搜索栏点击声音操作监听
     */
    protected void onSearchVoiceClick() {

    }

    /**
     * 搜索栏点击删除监听
     */
    protected void onSearchDeleteClick() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onGoBack();
    }

    /**
     * 添加布局
     *
     * @param view
     */
    protected void installContentView(View view) {
        if (view == null) {
            return;
        }
        contentPanel.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        contentPanel.addView(view, 0, params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 要使用长文本LengthInputTextActivity子类必须调用super方法
     *
     * @param savedInstanceState
     */
    @Override
    public void initViews(Bundle savedInstanceState) {
        //设置activity启动的时候输入法默认不开启
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    /**
     * 显示隐藏标题栏
     *
     * @param isShowing
     */
    protected void showTitleBar(boolean isShowing) {
        commonTitleBar.setVisibility(isShowing ? View.VISIBLE : View.GONE);
    }
}
