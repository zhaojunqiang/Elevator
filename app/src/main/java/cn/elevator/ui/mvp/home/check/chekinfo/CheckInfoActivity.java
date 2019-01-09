package cn.elevator.ui.mvp.home.check.chekinfo;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import cn.elevator.bean.PersonData;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.ui.mvp.home.check.CheckContact;
import cn.elevator.ui.mvp.home.check.CheckPresenter;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.widget.DateDialog;
import cn.elevator.widget.ExpendRecycleView;
import cn.elevator.widget.ToolBar;

public class CheckInfoActivity extends AppCompatActivity implements CheckInfoContact.View, View.OnClickListener {
    // 记录当前 activity 是否是显示状态
    private boolean activityState = false;
    private CheckInfoPresenter presenter;
    private TaskListData mData;
    private long mId;

    //文件编号
    private TextView mNumber;
    //使用单位
    private EditText mUser;
    //设备代码
    private EditText mDeviceCode;
    //设备类别
    private EditText mDeviceType;
    //开始时间
    private EditText mStartTime;
    //结束时间
    private EditText mEedTime;
    //下次检验日期
    private EditText mNextTime;
    //检验结论
    private EditText mResult;
    //检验人员
    private EditText mCheckPerson;
    //验核人员
    private EditText mVerifyPerson;
    //设备品种
    private EditText mDeviceBreed;
    //设备类型
    private EditText mDeviceModel;
    //产品编号
    private EditText mProductNumber;
    //生产日期
    private EditText mProductTime;
    //制造单位
    private EditText mProduct;
    //使用单位地址
    private EditText mUserAddress;
    //设备使用地点
    private EditText mDeviceAddress;
    //使用单位代码
    private EditText mUserNumber;
    //使用登记证编号
    private EditText mRegistNumber;
    //安全管理人员
    private EditText mSafeManager;
    //使用单位联系电话
    private EditText mUserPhone;
    //单位内编号
    private EditText mUserInterNumber;
    //改造时间
    private EditText mTransTime;
    //改造单位名称
    private EditText mTransName;
    //维保单位名称
    private EditText mRepairName;
    //维保联系人
    private EditText mRepairPerson;
    //应急电话
    private EditText mRescuePhone;
    //施工单位名称
    private EditText mWorkName;
    //施工许可证编号
    private EditText mPermintNumber;
    //施工类别
    private EditText mWorkType;
    //额定载重量
    private EditText mPowerRate;
    //额定速度
    private EditText mRateSpeed;
    //层
    private EditText mFloorNum;
    //站
    private EditText mStationNum;
    //门
    private EditText mDoorNum;
    //控制方式
    private EditText mControlType;
    //确认年月
    private EditText mConfirmYear;
    //制动试验日期
    private EditText mTestYear;
    //名义速度
    private EditText mNormalSpeed;
    //名义宽度
    private EditText mNormalWidth;
    //倾斜角
    private EditText mNormalDip;
    //输送能力
    private EditText mNormalDeliver;
    //提升高度
    private EditText mNormalHoist;
    //使用长度
    private EditText mNormalLength;
    //选择仪器
    private TextView mTvEquipment;
    //仪器列表
    private ExpendRecycleView mRecycleView;

    private String[] deviceTypes = {"自动扶梯与自动人行道", "直梯"};
    private String[] resultTypes = {"合格", "不合格", "复检合格", "复检不合格"};
    private String[] breedTypes = {"曳引驱动乘客电梯", "曳引驱动载客电梯"};
    private String[] workTypes = {"安装", "移装", "改造", "重大修理"};
    private String[] controlTypes = {"集选", "并联", "其他"};
    private Calendar mCurrentCalendar;
    private String mUid;

    private List<PersonData.PersonListData> mCheckList = new ArrayList<>();
    private List<String> mCheckNames = new ArrayList<>();
    private List<PersonData.PersonListData> mVerifyList = new ArrayList<>();
    private List<String> mVerifiNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = true;
        mId = getIntent().getLongExtra("_id", 0);
        QMUIStatusBarHelper.translucent(this);
        mCurrentCalendar = Calendar.getInstance(Locale.CHINA);
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_check_info, null);
        setContentView(view);
        initViews(view);
        initTaskData();
        initCheckPersonData();
    }

    private void initCheckPersonData() {
        mUid = SharedPrefUtils.getObj(Constant.USERID);
        Map<String, String> checks = new HashMap<>();
        checks.put("UserId", mUid);
        checks.put("RoleID", "1011");
        presenter.getCheckPersonList(checks);

        Map<String, String> verifys = new HashMap<>();
        verifys.put("UserId", mUid);
        verifys.put("RoleID", "1019");
        presenter.getCheckPersonList(verifys);
    }


    private void initTaskData() {
        presenter.getTaskById(mId);
    }

    @Override
    public boolean isActive() {
        return activityState;
    }

    @Override
    public void showTaskData(TaskListData listData) {
        mData = listData;

        mNumber.setText("记录编号：" + mData.getCraneRecordCode());
        mUser.setText(mData.getUseOrganize());
        mDeviceCode.setText(mData.getEquipmentCode());
        mDeviceType.setText(getElevator(mData.getElevatorType()));
        mStartTime.setText(mData.getSurveyDate());
        mNextTime.setText(mData.getNextSurveyDate());
        mResult.setText(getResult(mData.getSurveyConclusions()));
        mCheckPerson.setText(mData.getChecker1()+","+mData.getChecker2());
        mVerifyPerson.setText(mData.getCheckerOut());
        mDeviceBreed.setText(mData.getEquipmentVarieties());
        mDeviceModel.setText(mData.getSpecification());
        mProductNumber.setText(mData.getProductCode());
        mProductTime.setText(mData.getMakeDate());
        mProduct.setText(mData.getMakeOrganize());
        mUserAddress.setText(mData.getUseOrganizeAdd());
        mDeviceAddress.setText(mData.getInstallationSite());
        mUserNumber.setText(mData.getUseOrganizeCode());
        mRegistNumber.setText(mData.getUserRegeditCode());
        mSafeManager.setText(mData.getSafeAdmin());
        mUserPhone.setText(mData.getUseOrganizeTel());
        mUserInterNumber.setText(mData.getUnitNumber());
        mTransTime.setText(mData.getReformDate());
        mTransName.setText(mData.getReform());
        mRepairName.setText(mData.getTendingOrganize());
        mRepairPerson.setText(mData.getTendingLinkMan());
        mRescuePhone.setText(mData.getTendingTel());
        mWorkName.setText(mData.getBuilder());
        mPermintNumber.setText(mData.getConstructLicence());
        mWorkType.setText(mData.getConstructType());
        mPowerRate.setText(mData.getRatedLoad());
        mRateSpeed.setText(mData.getRatedSpeed());
        mFloorNum.setText(mData.getLayerStations());
        mControlType.setText("");
        mConfirmYear.setText("");
        mTestYear.setText("");
        mNormalSpeed.setText(mData.getRatedSpeed());
        mNormalWidth.setText(mData.getLadderwidth());
        mNormalDip.setText(mData.getAngle());
        mNormalDeliver.setText(mData.getTransmissionCapacity());
        mNormalHoist.setText(mData.getLiftingHeight());
        mNormalLength.setText(mData.getSegmentLength());
    }

    //获取结论
    private String getResult(String type) {
        switch (type) {
            case "01":
                return "合格";
            case "02":
                return "不合格";
            case "03":
                return "复检合格";
            case "04":
                return "复检不合格";
            default:
                return "合格";
        }
    }

    /**
     * 获取电梯类型
     *
     * @param type
     * @return
     */
    private String getElevator(int type) {
        switch (type) {
            case 1:
                return "自动直梯";
            case 2:
                return "自动扶梯";
            default:
                return "自动扶梯";

        }
    }

    @Override
    public void showCheckPersonData(PersonData personData) {
        mCheckList.clear();
        mCheckNames.clear();
        if (personData.getData() != null && personData.getData().size() > 0) {
            mCheckList.addAll(personData.getData());
        }
        for (PersonData.PersonListData data : mCheckList) {
            mCheckNames.add(data.getUserName());
        }
    }

    @Override
    public void showVerifyData(PersonData personData) {
        mVerifyList.clear();
        mVerifiNames.clear();
        if (personData.getData() != null && personData.getData().size() > 0) {
            mVerifyList.addAll(personData.getData());
        }
        for (PersonData.PersonListData data : mVerifyList) {
            mVerifiNames.add(data.getUserName());
        }
    }

    @Override
    public void initViews(View view) {
        presenter = new CheckInfoPresenter(this);
        ToolBar toolBar = findViewById(R.id.titlebar);
        toolBar.setLeftButtonOnClick(v -> finish());

        mNumber = findViewById(R.id.id_tv_number);
        mUser = findViewById(R.id.id_et_user);
        mDeviceCode = findViewById(R.id.id_et_device_code);
        mDeviceType = findViewById(R.id.id_et_device_type);
        mDeviceType.setOnClickListener(this);
        mStartTime = findViewById(R.id.id_et_start_time);
        mStartTime.setOnClickListener(this);
        mEedTime = findViewById(R.id.id_et_end_time);
        mEedTime.setOnClickListener(this);
        mNextTime = findViewById(R.id.id_et_next_time);
        mNextTime.setOnClickListener(this);
        mResult = findViewById(R.id.id_et_result);
        mResult.setOnClickListener(this);
        mCheckPerson = findViewById(R.id.id_et_check_person);
        mCheckPerson.setOnClickListener(this);
        mVerifyPerson = findViewById(R.id.id_et_verify_person);
        mVerifyPerson.setOnClickListener(this);
        mDeviceBreed = findViewById(R.id.id_et_device_breed);
//        mDeviceBreed.setOnClickListener(this);
        mDeviceModel = findViewById(R.id.id_et_device_model);
        mProductNumber = findViewById(R.id.id_et_product_number);
        mProductTime = findViewById(R.id.id_et_product_time);
        mProductTime.setOnClickListener(this);
        mProduct = findViewById(R.id.id_et_product);
        mUserAddress = findViewById(R.id.id_et_user_addr);
        mDeviceAddress = findViewById(R.id.id_et_device_addr);
        mUserNumber = findViewById(R.id.id_et_user_number);
        mRegistNumber = findViewById(R.id.id_et_regist_number);
        mSafeManager = findViewById(R.id.id_et_safe_manager);
        mUserPhone = findViewById(R.id.id_et_user_phone);
        mUserInterNumber = findViewById(R.id.id_et_user_inter_number);
        mTransTime = findViewById(R.id.id_et_trans_time);
        mTransTime.setOnClickListener(this);
        mTransName = findViewById(R.id.id_et_trans_name);
        mRepairName = findViewById(R.id.id_et_repair_name);
        mRepairPerson = findViewById(R.id.id_et_repair_person);
        mRescuePhone = findViewById(R.id.id_et_rescue_phone);
        mWorkName = findViewById(R.id.id_et_work_name);
        mPermintNumber = findViewById(R.id.id_et_permint_number);
        mWorkType = findViewById(R.id.id_et_work_type);
        mWorkType.setOnClickListener(this);
        mPowerRate = findViewById(R.id.id_et_power_rate);
        mRateSpeed = findViewById(R.id.id_et_rate_speed);
        mFloorNum = findViewById(R.id.id_et_floor_num);
        mStationNum = findViewById(R.id.id_et_station_num);
        mDoorNum = findViewById(R.id.id_et_door_num);
        mControlType = findViewById(R.id.id_et_control_type);
        mControlType.setOnClickListener(this);
        mConfirmYear = findViewById(R.id.id_et_confirm_year);
        mConfirmYear.setOnClickListener(this);
        mTestYear = findViewById(R.id.id_et_test_year);
        mTestYear.setOnClickListener(this);
        mNormalSpeed = findViewById(R.id.id_et_normal_speed);
        mNormalWidth = findViewById(R.id.id_et_normal_width);
        mNormalDip = findViewById(R.id.id_et_normal_dip);
        mNormalDeliver = findViewById(R.id.id_et_normal_deliver);
        mNormalHoist = findViewById(R.id.id_et_normal_hoist);
        mNormalLength = findViewById(R.id.id_et_normal_length);
        mTvEquipment = findViewById(R.id.id_tv_equipment);
        mRecycleView = findViewById(R.id.id_rv);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        //提交
        findViewById(R.id.id_tv_submit).setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_et_device_type:
                QMUIDialog.MenuDialogBuilder deviceTypeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(deviceTypes, (dialog, which) -> {
                            dialog.dismiss();
                            mDeviceType.setText(deviceTypes[which]);
                        });
                QMUIDialog deviceTypeDialog = deviceTypeBuilder.create();
                deviceTypeDialog.show();
                break;
            case R.id.id_et_start_time:
                final DateDialog startDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                startDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = startDlg.getDate();
                    mStartTime.setText(getDate(mCurrentCalendar));
                });
                startDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> startDlg.cancel());
                startDlg.show();
                break;
            case R.id.id_et_end_time:
                final DateDialog endDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                endDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = endDlg.getDate();
                    mEedTime.setText(getDate(mCurrentCalendar));
                });
                endDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> endDlg.cancel());
                endDlg.show();
                break;
            case R.id.id_et_next_time:
                final DateDialog nextDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                nextDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = nextDlg.getDate();
                    mNextTime.setText(getDate(mCurrentCalendar));
                });
                nextDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> nextDlg.cancel());
                nextDlg.show();
                break;
            case R.id.id_et_result:
                QMUIDialog.MenuDialogBuilder rusultTypeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(resultTypes, (dialog, which) -> {
                            dialog.dismiss();
                            mResult.setText(resultTypes[which]);
                            mData.setSurveyConclusions("0"+(which+1));
                        });
                QMUIDialog resultTypeDialog = rusultTypeBuilder.create();
                resultTypeDialog.show();
                break;
            case R.id.id_et_check_person:
                final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6"};
                final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(this)
                        .setCheckedItems(new int[]{-1})
                        .addItems(items, (dialog, which) -> {
                        });
                builder.addAction("取消", (dialog, index) -> dialog.dismiss());
                builder.addAction("确认", (dialog, index) -> {
                    if (builder.getCheckedItemIndexes().length != 2) {
                        Toast.makeText(this, "只能选择两个检验员", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < builder.getCheckedItemIndexes().length; i++) {
                        result.append(items[builder.getCheckedItemIndexes()[i]]).append(",");
                    }
                    mCheckPerson.setText(result);
                    dialog.dismiss();
                });
                builder.create().show();
                break;
            case R.id.id_et_verify_person:
                QMUIDialog.MenuDialogBuilder verifyTypeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(resultTypes, (dialog, which) -> {
                            dialog.dismiss();
                            mVerifyPerson.setText(resultTypes[which]);
                        });
                QMUIDialog verifyTypeDialog = verifyTypeBuilder.create();
                verifyTypeDialog.show();
                break;
            case R.id.id_et_device_breed:
                QMUIDialog.MenuDialogBuilder breedTypeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(breedTypes, (dialog, which) -> {
                            dialog.dismiss();
                            mDeviceBreed.setText(breedTypes[which]);
                        });
                QMUIDialog breedTypeDialog = breedTypeBuilder.create();
                breedTypeDialog.show();
                break;
            case R.id.id_et_product_time:
                final DateDialog productDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                productDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = productDlg.getDate();
                    mProductTime.setText(getDate(mCurrentCalendar));
                });
                productDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> productDlg.cancel());
                productDlg.show();
                break;
            case R.id.id_et_trans_time:
                final DateDialog transDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                transDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = transDlg.getDate();
                    mTransTime.setText(getDate(mCurrentCalendar));
                });
                transDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> transDlg.cancel());
                transDlg.show();
                break;
            case R.id.id_et_work_type:
                QMUIDialog.MenuDialogBuilder workTypeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(workTypes, (dialog, which) -> {
                            dialog.dismiss();
                            mWorkType.setText(workTypes[which]);
                        });
                QMUIDialog workTypeDialog = workTypeBuilder.create();
                workTypeDialog.show();
                break;
            case R.id.id_et_control_type:
                QMUIDialog.MenuDialogBuilder controlTypeBuilder = new QMUIDialog.MenuDialogBuilder(this).
                        addItems(controlTypes, (dialog, which) -> {
                            dialog.dismiss();
                            mControlType.setText(controlTypes[which]);
                        });
                QMUIDialog controlTypeDialog = controlTypeBuilder.create();
                controlTypeDialog.show();
                break;
            case R.id.id_et_confirm_year:
                final DateDialog confirmDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                confirmDlg.pickMonth();
                confirmDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = confirmDlg.getDate();
                    mConfirmYear.setText(getCDate(mCurrentCalendar));
                });
                confirmDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> confirmDlg.cancel());
                confirmDlg.show();
                break;
            case R.id.id_et_test_year:
                final DateDialog testDlg = new DateDialog(this, R.style.MyDateDialog, mCurrentCalendar.get(Calendar.YEAR)
                        , mCurrentCalendar.get(Calendar.MONTH) + 1
                        , mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
                testDlg.pickYear();
                testDlg.setConfirmButton(getString(R.string.ok), (dialogInterface, i) -> {
                    mCurrentCalendar = testDlg.getDate();
                    mTestYear.setText(String.valueOf(mCurrentCalendar.get(Calendar.YEAR)));
                });
                testDlg.setBackButton(getString(R.string.cancel), (dialog, which) -> testDlg.cancel());
                testDlg.show();
                break;
            case R.id.id_tv_submit:

                break;
        }
    }

    //拼接日期
    private String getDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(year)).append("-");
        if (monthOfYear < 10) {
            sb.append("0").append(String.valueOf(monthOfYear));
        } else {
            sb.append(String.valueOf(monthOfYear));
        }
        sb.append("-");
        if (day < 10) {
            sb.append("0").append(String.valueOf(day));
        } else {
            sb.append(String.valueOf(day));
        }
        return sb.toString();
    }

    //拼接日期
    private String getCDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH) + 1;
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(year)).append("年");
        if (monthOfYear < 10) {
            sb.append("0").append(String.valueOf(monthOfYear));
        } else {
            sb.append(String.valueOf(monthOfYear));
        }
        sb.append("月");
        return sb.toString();
    }
}
