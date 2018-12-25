package cn.elevator.ui.mvp.home.check;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.ArrayList;
import java.util.List;

import cn.elevator.R;
import cn.elevator.app.App;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.ui.adapter.CheckListAdapter;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;
import cn.elevator.widget.ExpendRecycleView;
import cn.elevator.widget.ToolBar;
import io.objectbox.Box;

public class CheckActivity extends AppCompatActivity implements CheckContact.View,View.OnClickListener {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private CheckPresenter presenter;
    private String mUid;
    private String dataFields;
    private ExpendRecycleView mRecycleView;
    private List<TaskListData> dataBeans;
    private CheckListAdapter mAdapter;
    private List<String> mYears;
    private String[] years;
    private String[] types = {"首检","定检","监检"};
    private List<String> mUsers;
    private String[] users;

    private ImageView mTime;
    private ImageView mType;
    private ImageView mUser;
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
//        presenter.getTaskData(mUid,dataFields);
        presenter.getTaskFromDataBase();
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
    public void showTaskList(List<TaskListData> taskListData) {
        dataBeans.clear();
        dataBeans.addAll(taskListData);
        mRecycleView.getAdapter().notifyDataSetChanged();
        mYears.clear();
        mUsers.clear();
        for (TaskListData data:taskListData){
            if(!mYears.contains(String.valueOf(data.getCheckYear()))){
                mYears.add(String.valueOf(data.getCheckYear()));
            }
            if(!mUsers.contains(data.getUseOrganize())){
                mUsers.add(data.getUseOrganize());
            }
        }
        String[] arrayYear = new String[mYears.size()];
        years = mYears.toArray(arrayYear);
        String[] arrayUser = new String[mUsers.size()];
        users = mUsers.toArray(arrayUser);
    }

    @Override
    public void showSelectList(List<TaskListData> taskListData) {
        dataBeans.clear();
        dataBeans.addAll(taskListData);
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
        view.findViewById(R.id.id_ll_time).setOnClickListener(this);
        view.findViewById(R.id.id_ll_type).setOnClickListener(this);
        view.findViewById(R.id.id_ll_company).setOnClickListener(this);

        mTime = view.findViewById(R.id.id_img_time);
        mType = view.findViewById(R.id.id_img_type);
        mUser = view.findViewById(R.id.id_img_user);
        mRecycleView = findViewById(R.id.id_rv);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        dataBeans = new ArrayList<>();
        mYears = new ArrayList<>();
        mUsers = new ArrayList<>();
        mAdapter = new CheckListAdapter(dataBeans);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnClick(listData -> {
            listData.setAPPRecordState(2);
            Box<TaskListData> listDataBox = App.getInstance().
                    getBoxStore().boxFor(TaskListData.class);
            listDataBox.put(listData);
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_ll_time:
                mTime.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
                QMUIDialog.MenuDialogBuilder timeBuilder = new QMUIDialog.MenuDialogBuilder(this).addItems(years, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +years[which], Toast.LENGTH_SHORT).show();
                    presenter.getTaskByParam(years[which],1);
                    dialog.dismiss();
                    mTime.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
                });
                QMUIDialog timeDialog = timeBuilder.create();
                timeDialog.setOnDismissListener(dialog -> mTime.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
                timeDialog.show();
                break;
            case R.id.id_ll_type:
                mType.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
                QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(types, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +types[which], Toast.LENGTH_SHORT).show();
                    presenter.getTaskByParam(String.valueOf(which+1),2);
                    dialog.dismiss();
                    mType.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
                });
                QMUIDialog typeDialog = typeBuilder.create();
                typeDialog.setOnDismissListener(dialog -> mType.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
                typeDialog.show();
                break;
            case R.id.id_ll_company:
                mUser.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
                QMUIDialog.MenuDialogBuilder userBuilder =  new QMUIDialog.MenuDialogBuilder(this).addItems(users, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +users[which], Toast.LENGTH_SHORT).show();
                    presenter.getTaskByParam(users[which],3);
                    dialog.dismiss();
                    mUser.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
                });
                QMUIDialog userDialog = userBuilder.create();
                userDialog.setOnDismissListener(dialog -> mUser.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
                userDialog.show();
                break;
        }
    }
}
