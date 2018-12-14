package cn.elevator.ui.mvp.setting.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.elevator.BuildConfig;
import cn.elevator.R;
import cn.elevator.widget.ToolBar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        TextView vesion = findViewById(R.id.id_tv_version);
        vesion.setText("九州电子V"+BuildConfig.VERSION_NAME);
    }
}
