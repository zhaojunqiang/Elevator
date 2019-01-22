package cn.elevator.ui.mvp.home.check.form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import cn.elevator.R;
import cn.elevator.bean.FormData;
import cn.elevator.widget.ToolBar;

public class FormActivity extends AppCompatActivity implements FormContact.View{
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private long mId;
    private FormPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        mId = getIntent().getLongExtra("_id", 0);
        QMUIStatusBarHelper.translucent(this);
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_form, null);
        setContentView(view);
        initViews(view);
        initFormData();
    }

    private void initFormData() {
        presenter.getFormData(String.valueOf(mId));
    }

    @Override
    public boolean isActive() {
        return activityState;
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = false;
    }
    @Override
    public void showFormData(FormData formData) {

    }

    @Override
    public void initViews(View view) {
        presenter = new FormPresenter(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
    }
}
