package cn.elevator.ui.mvp.home.check.chekinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import cn.elevator.R;

public class CheckInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_check_info);
    }
}
