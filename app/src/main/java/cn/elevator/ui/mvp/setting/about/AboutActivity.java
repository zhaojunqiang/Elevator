package cn.elevator.ui.mvp.setting.about;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.HashMap;
import java.util.Map;

import cn.elevator.BuildConfig;
import cn.elevator.R;
import cn.elevator.bean.AboutInfo;
import cn.elevator.ui.mvp.setting.agreement.AgreementActivity;
import cn.elevator.ui.mvp.setting.secret.SecretActivity;
import cn.elevator.widget.ToolBar;

public class AboutActivity extends AppCompatActivity implements AboutContact.View {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private AboutPresenter presenter;
    private TextView about;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        presenter = new AboutPresenter(this);
        setContentView(R.layout.activity_about);
        QMUIStatusBarHelper.translucent(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        TextView vesion = findViewById(R.id.id_tv_version);
        vesion.setText("九州电子V"+BuildConfig.VERSION_NAME);
        TextView agreement = findViewById(R.id.id_tv_agreement);
        agreement.setOnClickListener(v -> {
            startActivity(new Intent(AboutActivity.this,AgreementActivity.class));
        });
        TextView secret = findViewById(R.id.id_tv_secret);
        secret.setOnClickListener(v -> startActivity(new Intent(AboutActivity.this,SecretActivity.class)));
        about = findViewById(R.id.id_tv_about);
        Map<String, String> types = new HashMap<>();
        types.put("TypeID", "AboutUS");
        presenter.getAboutInfo(types);
    }

    @Override
    public boolean isActive() {
        return activityState;
    }

    @Override
    public void showAboutInfo(AboutInfo aboutInfo) {
            if(aboutInfo!= null && aboutInfo.getData()!=null && !TextUtils.isEmpty(aboutInfo.getData().getCodeName())){
                about.setText(aboutInfo.getData().getCodeName());
            }
    }

    @Override
    public void initViews(View view) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = false;
    }
}
