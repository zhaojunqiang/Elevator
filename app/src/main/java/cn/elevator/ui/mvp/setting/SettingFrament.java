package cn.elevator.ui.mvp.setting;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.elevator.BuildConfig;
import cn.elevator.R;
import cn.elevator.bean.VersionInfo;
import cn.elevator.config.Constant;
import cn.elevator.ui.mvp.account.LoginActivity;
import cn.elevator.ui.mvp.setting.about.AboutActivity;
import cn.elevator.utils.DownloadAppUtils;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 设置视图
 */
public class SettingFrament extends Fragment implements SettingContact.View,View.OnClickListener,EasyPermissions.PermissionCallbacks{
    private TextView mVersion;
    private TextView mUserName;
    private SettingPresenter presenter;
    private VersionInfo.DataBean dataBean;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new SettingPresenter(this);
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        view.findViewById(R.id.id_tv_about).setOnClickListener(this);
        view.findViewById(R.id.btn_exit).setOnClickListener(this);
        mUserName = view.findViewById(R.id.id_tv_userName);
        mUserName.setText("下午好，"+SharedPrefUtils.getObj(Constant.USER_NAME));
        mVersion = view.findViewById(R.id.id_tv_version);
        mVersion.setText("版本号V" + BuildConfig.VERSION_NAME);
        mVersion.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tv_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.id_tv_version:

                Map<String, String> types = new HashMap<>();
                types.put("UserId",SharedPrefUtils.getObj(Constant.USERID));
                types.put("TypeID", "VersionUpgrade");
                presenter.getVersionInfo(types);
                break;
            case R.id.btn_exit:
                new QMUIDialog.MessageDialogBuilder(getActivity())
                        .setTitle("提示")
                        .setMessage("确定要退出登录吗？")
                        .addAction("取消", (dialog, index) -> dialog.dismiss())
                        .addAction("确定", (dialog, index) -> {
                            dialog.dismiss();
                            SharedPrefUtils.clear();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                        }).show();
                break;
        }
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showVersionInfo(VersionInfo versionInfo) {
        if(versionInfo!=null && versionInfo.getData()!=null && versionInfo.getData().size()>0){
            dataBean = versionInfo.getData().get(0);
            if(Integer.valueOf(dataBean.getCodeID())>BuildConfig.VERSION_CODE){//有新版本
                //下载app
                downLoadApp();
            }else{
                ToastUtil.showToast(getActivity(),"当前已是最新版本");
            }
        }
    }

    private void downLoadApp() {
        checkPermission();
    }
    @AfterPermissionGranted(REQUEST_EXTERNAL)
    private void checkPermission() {
        if (EasyPermissions.hasPermissions(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            DownloadAppUtils.DownLoad(getActivity(), dataBean.getCodeName(), "elevator");
        } else {
            EasyPermissions.requestPermissions(this, "需要请求内存卡权限",
                    REQUEST_EXTERNAL, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }
    public static final int REQUEST_EXTERNAL = 10;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
