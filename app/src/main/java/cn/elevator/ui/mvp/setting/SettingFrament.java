package cn.elevator.ui.mvp.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.elevator.BuildConfig;
import cn.elevator.R;
import cn.elevator.ui.mvp.account.LoginActivity;
import cn.elevator.ui.mvp.setting.about.AboutActivity;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 设置视图
 */
public class SettingFrament extends Fragment implements View.OnClickListener{
    private TextView mVersion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        view.findViewById(R.id.id_tv_about).setOnClickListener(this);
        view.findViewById(R.id.btn_exit).setOnClickListener(this);
        mVersion = view.findViewById(R.id.id_tv_version);
        mVersion.setText("版本号V"+BuildConfig.VERSION_NAME);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tv_about:
                startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
            case R.id.btn_exit:
                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
}
