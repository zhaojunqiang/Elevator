package cn.elevator.ui.mvp.setting.secret;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.HashMap;
import java.util.Map;

import cn.elevator.R;
import cn.elevator.bean.AboutInfo;
import cn.elevator.config.Constant;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.widget.ToolBar;

public class SecretActivity extends AppCompatActivity implements SecretContact.View {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private SecretPresenter presenter;
    private TextView secret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        presenter = new SecretPresenter(this);
        setContentView(R.layout.activity_secret);
        QMUIStatusBarHelper.translucent(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        secret = findViewById(R.id.id_tv_secret);

        Map<String, String> types = new HashMap<>();
        types.put("UserId",SharedPrefUtils.getObj(Constant.USERID));
        types.put("TypeID", "Privacy");
        presenter.getAboutInfo(types);
    }

    @Override
    public boolean isActive() {
        return activityState;
    }

    @Override
    public void showAboutInfo(AboutInfo aboutInfo) {
        if(aboutInfo!= null && aboutInfo.getData()!=null && aboutInfo.getData().size()>0){
            secret.setText(aboutInfo.getData().get(0).getCodeName());
        }
    }

    @Override
    public void initViews(View view) {

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
}
