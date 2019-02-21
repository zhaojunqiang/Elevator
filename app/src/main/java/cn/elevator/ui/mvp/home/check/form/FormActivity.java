package cn.elevator.ui.mvp.home.check.form;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coorchice.library.SuperTextView;
import com.google.gson.Gson;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import cn.elevator.R;
import cn.elevator.app.App;
import cn.elevator.bean.FormData;
import cn.elevator.bean.FormListData;
import cn.elevator.bean.SaveFormResult;
import cn.elevator.bean.SaveResult;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.ui.adapter.FormDataAdapter;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;
import cn.elevator.widget.ExpendRecycleView;
import cn.elevator.widget.ToolBar;
import io.objectbox.Box;

public class FormActivity extends AppCompatActivity implements FormContact.View{
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private String mUid;
    private String mId;
    private long Id;
    private FormPresenter presenter;

    private RelativeLayout mNoDataLayout;
    private ExpendRecycleView mRecycleView;
    private List<FormListData> dataBeans;
    private FormDataAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        mId = getIntent().getStringExtra("_id");
        Id = getIntent().getLongExtra("id",0);
        QMUIStatusBarHelper.translucent(this);
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_form, null);
        setContentView(view);
        initViews(view);
        initFormData();
    }

    private void initFormData() {
        mUid = SharedPrefUtils.getObj(Constant.USERID);
        //先从数据库查询 没有 再网络请求
        presenter.getFormDataDB(mId);
//        presenter.getFormData(mUid,mId);
    }

    @Override
    public boolean isActive() {
        return activityState;
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
    public void showFormData(FormData formData) {
        if(mNoDataLayout.getVisibility() == View.VISIBLE){
            mNoDataLayout.setVisibility(View.GONE);
        }
        dataBeans.clear();
        dataBeans.addAll(formData.getData());
        mRecycleView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void noData() {
        dataBeans.clear();
        mRecycleView.getAdapter().notifyDataSetChanged();
        mNoDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFormDataDB(List<FormListData> formListData) {
        if(formListData !=null && formListData.size()>0){
            if(mNoDataLayout.getVisibility() == View.VISIBLE){
                mNoDataLayout.setVisibility(View.GONE);
            }
            dataBeans.clear();
            dataBeans.addAll(formListData);
            mRecycleView.getAdapter().notifyDataSetChanged();
        }else {
            noData();
        }
    }

    @Override
    public void showTaskData(TaskListData listData) {
        //提交
        String json = new Gson().toJson(listData);
        presenter.saveTaskData(json);
    }

    @Override
    public void showSaveTaskResult(SaveResult saveResult) {
//        ToastUtil.showToast(this, saveResult.getData().getResultMessage()); //提交检验项目
            String json = new Gson().toJson(dataBeans);
            presenter.saveFormData(json);

    }

    @Override
    public void showSaveFormResult(SaveFormResult saveFormResult) {
        ToastUtil.showToast(this, saveFormResult.getData().getResultMessage());
        //删除本地记录
        Box<TaskListData> listDataBox = App.getInstance().
                getBoxStore().boxFor(TaskListData.class);
        listDataBox.remove(Id);
        Box<FormListData> formDataBox = App.getInstance().getBoxStore().boxFor(FormListData.class);
        formDataBox.remove(dataBeans);
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();

    }

    @Override
    public void initViews(View view) {
        presenter = new FormPresenter(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());
        SuperTextView superTextView = findViewById(R.id.id_tv_submit);
        superTextView.setOnClickListener(v -> {
            //提交检验任务数据
            presenter.getTaskById(Id);
        });
        mNoDataLayout = view.findViewById(R.id.layout_no_data);
        mRecycleView = findViewById(R.id.id_rv);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        dataBeans = new ArrayList<>();
        mAdapter = new FormDataAdapter(dataBeans);
        mRecycleView.setAdapter(mAdapter);
    }
}
