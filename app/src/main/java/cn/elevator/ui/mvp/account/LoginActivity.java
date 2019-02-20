package cn.elevator.ui.mvp.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;


import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

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
    private ImageView mSeePass;
    private ProgressDialog mProgressD;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        mProgressD = new ProgressDialog(this);
        mProgressD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressD.setMessage("登录中...");
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
        mSeePass = findViewById(R.id.iv_see_password);
        mSeePass.setOnClickListener(this);

    }

    /**
     * 取本地保存的用户名和密码
     */
    private void initData() {
        mUserName = SharedPrefUtils.getObj(Constant.USER_NAME);
        mPassword = SharedPrefUtils.getObj(Constant.USER_PASSWORD);

        mUserNameView.setText(TextUtils.isEmpty(mUserName) ? "" : mUserName);
        mPassWordView.setText(TextUtils.isEmpty(mPassword) ? "" : mPassword);
        if(!TextUtils.isEmpty(mUserName) && !TextUtils.isEmpty(mPassword)){
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
//            presenter.login(mUserName, mPassword, "ZX09XNS0819");
        }
    }

    /**
     * 显示登录错误提示
     *
     * @param errorMsg
     */
    @Override
    public void onLoginError(String errorMsg) {
        ToastUtil.showToast(this, errorMsg);
        if (mProgressD != null) {
            mProgressD.dismiss();
        }
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
        if (mProgressD != null) {
            mProgressD.dismiss();
        }
        finish();
    }

    /**
     * 展示网络错误
     */
    @Override
    public void showNetworkError() {
        ToastUtil.showToast(this, "网络错误，请检查您的网络");
        if (mProgressD != null) {
            mProgressD.dismiss();
        }
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
    boolean show = false;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkEnter()) {
//                     presenter.login("李明", "123", "ZX09XNS0819");
                    mProgressD.show();
                    presenter.login(mUserName, mPassword, "ZX09XNS0819");
                }
                break;
            case R.id.iv_see_password:
                if (!show) {
                    // 显示密码
                    mPassWordView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mSeePass.setImageDrawable(this.getResources().getDrawable(R.drawable.open_eye));
                    show = true;
                } else {
                    // 隐藏密码
                    mPassWordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mSeePass.setImageDrawable(this.getResources().getDrawable(R.drawable.close_eye));
                    show = false;
                }
                //设置光标位置
                mPassWordView.setSelection(mPassWordView.getText().toString().length());
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
