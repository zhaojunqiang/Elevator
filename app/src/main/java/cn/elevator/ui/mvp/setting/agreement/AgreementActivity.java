package cn.elevator.ui.mvp.setting.agreement;

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
import cn.elevator.widget.ToolBar;

public class AgreementActivity extends AppCompatActivity implements AgreementContact.View {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private AgreementPresenter presenter;
    private TextView agreement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        presenter = new AgreementPresenter(this);
        setContentView(R.layout.activity_agreement);
        QMUIStatusBarHelper.translucent(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        agreement = findViewById(R.id.id_tv_agreement);
        Map<String, String> types = new HashMap<>();
        types.put("TypeID", "UserProtocol");
        presenter.getAboutInfo(types);
    }

    @Override
    public boolean isActive() {
        return activityState;
    }

    @Override
    public void showAboutInfo(AboutInfo aboutInfo) {
        if(aboutInfo!= null && aboutInfo.getData()!=null && !TextUtils.isEmpty(aboutInfo.getData().getCodeName())){
            agreement.setText(aboutInfo.getData().getCodeName());
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
