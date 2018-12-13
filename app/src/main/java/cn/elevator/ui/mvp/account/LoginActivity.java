package cn.elevator.ui.mvp.account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;


import cn.elevator.R;
import cn.elevator.bean.LoginData;
import cn.elevator.config.Constant;
import cn.elevator.ui.MainActivity;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 登录视图
 */
public class LoginActivity extends AppCompatActivity implements LoginContact.View, View.OnClickListener {

    private LoginPresenter presenter;
    private EditText mUserNameView, mPassWordView;
    private Button mLogin;
    private String mUserName, mPassword;
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        setContentView(view);
        initViews(view);
        initData();
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    @Override
    public void initViews(View view) {
        presenter = new LoginPresenter(this);

        mUserNameView = findViewById(R.id.et_account);
        mPassWordView = findViewById(R.id.et_password);
        mLogin = findViewById(R.id.btn_login);
        mLogin.setOnClickListener(this);

    }

    /**
     * 取本地保存的用户名和密码
     */
    private void initData() {
        mUserName = SharedPrefUtils.getObj(Constant.USER_NAME);
        mPassword = SharedPrefUtils.getObj(Constant.USER_PASSWORD);

        mUserNameView.setText(TextUtils.isEmpty(mUserName) ? "" : mUserName);
        mPassWordView.setText(TextUtils.isEmpty(mPassword) ? "" : mPassword);
    }

    /**
     * 显示登录错误提示
     *
     * @param errorMsg
     */
    @Override
    public void onLoginError(String errorMsg) {
        ToastUtil.showToast(this, errorMsg);
    }

    /**
     * 当前 activity 是否显示
     *
     * @return
     */
    @Override
    public boolean isActive() {
        return activityState;
    }

    /**
     * 登录成功
     * 保存登录信息并跳转目标页面
     *
     * @param loginData
     */
    @Override
    public void onLoginSuccess(LoginData loginData) {
        SharedPrefUtils.saveObj(Constant.USERID, loginData.getData().getUserId());
        SharedPrefUtils.saveObj(Constant.TOKEN, loginData.getData().getToken());
        SharedPrefUtils.saveObj(Constant.USER_NAME, mUserName);
        SharedPrefUtils.saveObj(Constant.USER_PASSWORD, mPassword);

        ToastUtil.showToast(this, loginData.getData().getLoginState());
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }

    /**
     * 展示网络错误
     */
    @Override
    public void showNetworkError() {
        ToastUtil.showToast(this, "网络错误，请检查您的网络");
    }


    @Override
    protected void onResume() {
        super.onResume();
        activityState = true;
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = false;
        presenter.unSubscribe();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkEnter()) {
//                     presenter.login("李明", "123", "ZX09XNS0819");
                    presenter.login(mUserName, mPassword, "ZX09XNS0819");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 检查用户输入合法性
     *
     * @return
     */
    public boolean checkEnter() {
        // 重置错误。
        mUserNameView.setError(null);
        mPassWordView.setError(null);
        // 存储值时的登录尝试。
        mUserName = mUserNameView.getText().toString().trim();
        mPassword = mPassWordView.getText().toString().trim();
        if (TextUtils.isEmpty(mUserName)) {
            mUserNameView.setError("请输入用户名");
            mUserNameView.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(mPassword)) {
            mPassWordView.setError("请输入密码");
            mPassWordView.requestFocus();
            return false;
        }
        return true;
    }
}
