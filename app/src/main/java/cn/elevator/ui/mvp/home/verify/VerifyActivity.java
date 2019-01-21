package cn.elevator.ui.mvp.home.verify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.elevator.R;
import cn.elevator.app.App;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.ui.adapter.CheckListAdapter;
import cn.elevator.ui.mvp.home.check.CheckActivity;
import cn.elevator.ui.mvp.home.check.CheckPresenter;
import cn.elevator.ui.mvp.home.check.chekinfo.CheckInfoActivity;
import cn.elevator.ui.mvp.home.verify.verifyInfo.VerifyInfoActivity;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.widget.ExpendRecycleView;
import cn.elevator.widget.ToolBar;
import io.objectbox.Box;

public class VerifyActivity extends AppCompatActivity implements VerifyContact.View,View.OnClickListener {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private VerifyPresenter presenter;
    private String mUid;
    private String dataFields;
    private boolean isRefresh;
    private RelativeLayout mNoDataLayout;
    private ExpendRecycleView mRecycleView;
    private List<TaskListData> dataBeans;
    private CheckListAdapter mAdapter;

    private String[] types = {"首检", "定检", "监检"};
    private String[] status = {"未编制","已编制"};
    private ImageView mType;
    private ImageView mState;
    private TextView mTvType;
    private TextView mTvState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        QMUIStatusBarHelper.translucent(this);
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_verify, null);
        setContentView(view);
        initViews(view);
        initTaskData();
    }

    private void initTaskData() {
        mUid = SharedPrefUtils.getObj(Constant.USERID);
        dataFields = "CraneRecordListID,InspectionID,CraneRecordCode,UseOrganize,MadeCode," +
                "RegistCode,CheckRecordID,ReportClassID,CheckYear,CheckType,APPRecordState,RecordTime," +
                "SurveyConclusions,SurveyDate,NextSurveyDate,Checker1,Checker2,ConstructType,Builder,TendingOrganize," +
                "TendingLinkMan,TendingTel,ReportID,EquipmentCode,UnitNumber";
        presenter.getTaskData(mUid,dataFields);
    }

    private void initViews(LinearLayout view) {
        presenter = new VerifyPresenter(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        view.findViewById(R.id.id_ll_type).setOnClickListener(this);
        view.findViewById(R.id.id_ll_state).setOnClickListener(this);
        mType = view.findViewById(R.id.id_img_type);
        mState = view.findViewById(R.id.id_img_state);
        mTvType = view.findViewById(R.id.id_tv_type);
        mTvState = view.findViewById(R.id.id_tv_state);
        mNoDataLayout = view.findViewById(R.id.layout_no_data);
        mRecycleView = findViewById(R.id.id_rv);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        dataBeans = new ArrayList<>();
        mAdapter = new CheckListAdapter(dataBeans);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnClick(listData -> {
            Intent intent = new Intent(VerifyActivity.this,VerifyInfoActivity.class);
            startActivity(intent);
        });
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
    public boolean isActive() {
        return activityState;
    }

    @Override
    public void showTaskData(TaskData taskData) {
        if(mNoDataLayout.getVisibility() == View.VISIBLE){
            mNoDataLayout.setVisibility(View.GONE);
        }
        dataBeans.clear();
        dataBeans.addAll(taskData.getData());
        mRecycleView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void showMoreTaskData(TaskData taskData) {

    }

    @Override
    public void noData() {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void hideLoadingMore() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_type:
                mType.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
                QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(types, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +types[which], Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            mTvType.setText(types[which]);
                            mType.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
                        });
                QMUIDialog typeDialog = typeBuilder.create();
                typeDialog.setOnDismissListener(dialog -> mType.setImageDrawable(VerifyActivity.this.getResources().getDrawable(R.drawable.down)));
                typeDialog.show();
                break;
            case R.id.id_ll_state:
                mState.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
                QMUIDialog.MenuDialogBuilder stateBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(status, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +types[which], Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            mTvState.setText(status[which]);
                            mState.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
                        });
                QMUIDialog stateDialog = stateBuilder.create();
                stateDialog.setOnDismissListener(dialog -> mState.setImageDrawable(VerifyActivity.this.getResources().getDrawable(R.drawable.down)));
                stateDialog.show();
                break;
        }
    }
}
