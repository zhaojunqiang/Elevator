package cn.elevator.ui.mvp.home.check;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.elevator.R;
import cn.elevator.app.App;
import cn.elevator.bean.FormListData;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.ui.adapter.CheckListAdapter;
import cn.elevator.ui.mvp.home.check.chekinfo.CheckInfoActivity;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;
import cn.elevator.widget.DateDialog;
import cn.elevator.widget.ExpendRecycleView;
import cn.elevator.widget.RecycleRefreshLoadLayout;
import cn.elevator.widget.ToolBar;
import io.objectbox.Box;

public class CheckActivity extends AppCompatActivity implements CheckContact.View, View.OnClickListener {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private CheckPresenter presenter;
    private String mUid;
    private String dataFields;
    private boolean isRefresh;
    private RelativeLayout mNoDataLayout;
    private ExpendRecycleView mRecycleView;
    private List<TaskListData> dataBeans;
    private CheckListAdapter mAdapter;

    private Calendar mCurrentCalendar;
    private List<String> mYears;
    private String[] years;
    private String[] types = {"首检", "定检", "监检"};
    private String[] status = {"未编制", "已编制"};
    private List<String> mUsers;
    private String[] users;

    private ImageView mTime;
    private ImageView mType;
    private ImageView mState;
    private ImageView mUser;
    private TextView mTvTime;
    private TextView mTvType;
    private TextView mTvState;
    private TextView mTvUser;
    private int mPage = 1;
    private int mPageCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check);
        activityState = true;
        QMUIStatusBarHelper.translucent(this);
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_check, null);
        setContentView(view);
        mCurrentCalendar = Calendar.getInstance(Locale.CHINA);
        initViews(view);
        initTaskData();
    }

    private void initTaskData() {
        mUid = SharedPrefUtils.getObj(Constant.USERID);
        dataFields = "CraneRecordListID,InspectionID,CraneRecordCode,UseOrganize,MadeCode," +
                "RegistCode,CheckRecordID,ReportClassID,CheckYear,CheckType,APPRecordState,RecordTime," +
                "SurveyConclusions,SurveyDate,NextSurveyDate,Checker1,Checker2,ConstructType,Builder,TendingOrganize," +
                "TendingLinkMan,TendingTel,ReportID,EquipmentCode,UnitNumber";
//        presenter.getTaskFromDataBase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
        presenter.getTaskFromDataBase();
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
        if (mNoDataLayout.getVisibility() == View.VISIBLE) {
            mNoDataLayout.setVisibility(View.GONE);
        }
        dataBeans.clear();
        dataBeans.addAll(taskData.getData());
        mRecycleView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void showMoreTaskData(TaskData taskData) {
        dataBeans.addAll(taskData.getData());
        mRecycleView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showTaskList(List<TaskListData> taskListData) {
        dataBeans.clear();
        dataBeans.addAll(taskListData);
        mRecycleView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showSelectList(List<TaskListData> taskListData) {
        if (taskListData != null && taskListData.size() > 0) {
            mNoDataLayout.setVisibility(View.GONE);
        } else {
            mNoDataLayout.setVisibility(View.VISIBLE);
        }
        dataBeans.clear();
        dataBeans.addAll(taskListData);
        mRecycleView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showNetWorkError() {
        ToastUtil.showToast(this, getString(R.string.http_error));
    }

    @Override
    public void noData() {
        dataBeans.clear();
        mRecycleView.getAdapter().notifyDataSetChanged();
        mNoDataLayout.setVisibility(View.VISIBLE);
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
    public void showSaveRecResult(SaveResult saveResult) {
        ToastUtil.showToast(this, saveResult.getData().getResultMessage());
    }

    @Override
    public void initViews(View view) {
        presenter = new CheckPresenter(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        view.findViewById(R.id.id_ll_time).setOnClickListener(this);
        view.findViewById(R.id.id_ll_type).setOnClickListener(this);
        view.findViewById(R.id.id_ll_state).setOnClickListener(this);
        view.findViewById(R.id.id_ll_company).setOnClickListener(this);

        mTime = view.findViewById(R.id.id_img_time);
        mType = view.findViewById(R.id.id_img_type);
        mState = view.findViewById(R.id.id_img_state);
        mUser = view.findViewById(R.id.id_img_user);
        mTvTime = view.findViewById(R.id.id_tv_time);
        mTvType = view.findViewById(R.id.id_tv_type);
        mTvState = view.findViewById(R.id.id_tv_state);
        mTvUser = view.findViewById(R.id.id_tv_company);

        mNoDataLayout = view.findViewById(R.id.layout_no_data);
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
//            mAdapter.notifyDataSetChanged();
            Intent intent = new Intent(CheckActivity.this, CheckInfoActivity.class);
            intent.putExtra("_id", listData.getId());
            startActivity(intent);
        });
        mAdapter.setRectClick(listData -> {
            //调用整改接口
//            ToastUtil.showToast(CheckActivity.this,listData.getCraneRecordListID());
//            final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
//            builder.getEditText().setSingleLine(false);
//            builder.getEditText().setMinLines(3);
//            builder.setTitle("整改意见").setPlaceholder("在此输入整改意见").
//                    setInputType(InputType.TYPE_CLASS_TEXT).
//                    addAction("取消", new QMUIDialogAction.ActionListener() {
//                        @Override
//                        public void onClick(QMUIDialog dialog, int index) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .addAction("确定", new QMUIDialogAction.ActionListener() {
//                        @Override
//                        public void onClick(QMUIDialog dialog, int index) {
//                            CharSequence text = builder.getEditText().getText();
//                            if (text != null && text.length() > 0) {
//                                presenter.saveRecData(listData.getCraneRecordListID(),1,text.toString());
//                                dialog.dismiss();
//                            } else {
//                                ToastUtil.showToast(CheckActivity.this,"在此输入整改意见");
//                            }
//                        }
//                    }).show();
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("整改意见");

            EditText editText = new EditText(this);
            editText.setHint("请填写整改意见");
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setWidth(QMUIDisplayHelper.dpToPx(480));
            editText.setHeight(QMUIDisplayHelper.dpToPx(120));
            editText.setBackground(this.getResources().getDrawable(R.drawable.edit_bg));
            editText.setGravity(Gravity.TOP|Gravity.LEFT);
            editText.setPadding(QMUIDisplayHelper.dpToPx(10),QMUIDisplayHelper.dpToPx(10),QMUIDisplayHelper.dpToPx(10),QMUIDisplayHelper.dpToPx(10));
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, QMUIResHelper.getAttrDimen(this, com.qmuiteam.qmui.R.attr.qmui_dialog_content_message_text_size));
            editText.setHintTextColor(QMUIResHelper.getAttrColor(this, com.qmuiteam.qmui.R.attr.qmui_config_color_gray_3));
            editText.setTextColor(QMUIResHelper.getAttrColor(this, com.qmuiteam.qmui.R.attr.qmui_config_color_black));
            editText.setSingleLine(false);

            LinearLayout layout = new LinearLayout(this);
            layout.addView(editText);
            layout.setPadding(QMUIDisplayHelper.dpToPx(20), QMUIDisplayHelper.dpToPx(10), 0, 0);

            builder.setView(layout);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CharSequence text = editText.getText();
                    if (text != null && text.length() > 0) {
                        presenter.saveRecData(listData.getCraneRecordListID(), 1, text.toString());
                        dialog.dismiss();
                    } else {
                        ToastUtil.showToast(CheckActivity.this, "请输入输入整改意见");
                        return;
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setCancelable(true);

            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        });
    }

    public int findColorById(int color) {
        return getResources().getColor(color);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_time:
//                mTime.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
//                QMUIDialog.MenuDialogBuilder timeBuilder = new QMUIDialog.MenuDialogBuilder(this).addItems(years, (dialog, which) -> {
////                    Toast.makeText(CheckActivity.this, "你选择了 " +years[which], Toast.LENGTH_SHORT).show();
//                    presenter.getTaskByParam(years[which],1);
//                    dialog.dismiss();
//                    mTvTime.setText(years[which]);
//                    mTime.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
//                });
//                QMUIDialog timeDialog = timeBuilder.create();
//                timeDialog.setOnDismissListener(dialog -> mTime.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
//                timeDialog.show();
                final DateDialog dateDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                dateDlg.pickYear();
                dateDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = dateDlg.getDate();
                    mTvTime.setText(String.valueOf(mCurrentCalendar.get(Calendar.YEAR)));
                    mTime.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down));
                    mPage = 1;
                    Map<String, String> params = new HashMap<>();
                    params.put("UserId", mUid);
                    params.put("DataFields", dataFields);
                    params.put("page", String.valueOf(mPage));
                    params.put("limit", String.valueOf(mPageCount));
                    params.put("CheckYear", mTvTime.getText().toString());
                    presenter.getTaskList(params);
                });
                dateDlg.setBackButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dateDlg.cancel();
                    }
                });
                dateDlg.show();
                break;
            case R.id.id_ll_type:
                mType.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
                QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(types, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +types[which], Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            mTvType.setText(types[which]);
                            mType.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
                            presenter.getTaskByParam(String.valueOf(which + 1), 2);
                        });
                QMUIDialog typeDialog = typeBuilder.create();
                typeDialog.setOnDismissListener(dialog -> mType.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
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
                            presenter.getTaskByParam(String.valueOf(which + 1), 4);
                        });
                QMUIDialog stateDialog = stateBuilder.create();
                stateDialog.setOnDismissListener(dialog -> mState.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
                stateDialog.show();
                break;
            case R.id.id_ll_company:
//                mUser.setImageDrawable(this.getResources().getDrawable(R.drawable.up));
//                QMUIDialog.MenuDialogBuilder userBuilder =  new QMUIDialog.MenuDialogBuilder(this).addItems(users, (dialog, which) -> {
////                    Toast.makeText(CheckActivity.this, "你选择了 " +users[which], Toast.LENGTH_SHORT).show();
//                    presenter.getTaskByParam(users[which],3);
//                    dialog.dismiss();
//                    mTvUser.setText(users[which]);
//                    mUser.setImageDrawable(this.getResources().getDrawable(R.drawable.down));
//                });
//                QMUIDialog userDialog = userBuilder.create();
//                userDialog.setOnDismissListener(dialog -> mUser.setImageDrawable(CheckActivity.this.getResources().getDrawable(R.drawable.down)));
//                userDialog.show();
                break;
        }
    }
}
