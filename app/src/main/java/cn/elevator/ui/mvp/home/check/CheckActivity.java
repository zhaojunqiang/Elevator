package cn.elevator.ui.mvp.home.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import cn.elevator.R;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.ui.adapter.CheckListAdapter;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;
import cn.elevator.widget.ExpendRecycleView;
import cn.elevator.widget.ToolBar;

public class CheckActivity extends AppCompatActivity implements CheckContact.View {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private CheckPresenter presenter;
    private String mUid;
    private String dataFields;
    private ExpendRecycleView mRecycleView;
    private List<TaskListData> dataBeans;
    private CheckListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check);
        QMUIStatusBarHelper.translucent(this);  LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_check, null);
        setContentView(view);
        initViews(view);
        initTaskData();
    }

    private void initTaskData() {
        mUid = SharedPrefUtils.getObj(Constant.USERID);
        dataFields = "CraneRecordListID,InspectionID,CraneRecordCode,UseOrganize,MadeCode," +
                "RegistCode,CheckRecordID,ReportClassID,CheckYear,CheckType,APPRecordState," +
                "RecordTime,SurveyConclusions,SurveyDate,TendingOrganize,ReportID,EquipmentCode";
        presenter.getTaskData(mUid,dataFields);
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
    @Override
    public boolean isActive() {
        return activityState;
    }

    @Override
    public void showTaskData(TaskData taskData) {
        dataBeans.clear();
        dataBeans.addAll(taskData.getData());
        mRecycleView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showNetWorkError() {
        ToastUtil.showToast(this, getString(R.string.http_error));
    }

    @Override
    public void initViews(View view) {
        presenter = new CheckPresenter(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        mRecycleView = findViewById(R.id.id_rv);

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        dataBeans = new ArrayList<>();
        mAdapter = new CheckListAdapter(dataBeans);
        mRecycleView.setAdapter(mAdapter);
    }
}
