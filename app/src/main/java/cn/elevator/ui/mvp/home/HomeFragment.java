package cn.elevator.ui.mvp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.elevator.R;
import cn.elevator.bean.BannerData;
import cn.elevator.bean.MenuData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import cn.elevator.config.Constant;
import cn.elevator.helper.GlideLoaderHelper;
import cn.elevator.ui.adapter.HomeMenuAdapter;
import cn.elevator.ui.mvp.home.check.CheckActivity;
import cn.elevator.ui.mvp.home.verify.VerifyActivity;
import cn.elevator.utils.SharedPrefUtils;
import cn.elevator.utils.ToastUtil;

/**
 * author: DamonJiang
 * date:   2018/8/13 0013
 * description: 首页视图
 */
public class HomeFragment extends Fragment implements HomeContact.View {

    private HomePresenter presenter;
    private Banner banner;
    private RecyclerView mRecycleView;
    private HomeMenuAdapter menuAdapter;

    private Pair<String, MenuData>[] menus = new Pair[6];

    private String[] bannerUrls = new String[]{
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg",
            "http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg"
    };
    private String mUid;
    private String dataFields;
    private List<TaskListData> mDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    /**
     * 初始化 UI
     */
    @Override
    public void initViews(View view) {
        presenter = new HomePresenter(this);

        banner = (Banner) getActivity().getLayoutInflater().inflate(R.layout.container_banner, null);
        banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getContext().getResources().getDisplayMetrics().heightPixels / 4));
        mRecycleView = view.findViewById(R.id.id_rv);
        initMenuRecycler();
        initDatas();
        initBanners();
        initTaskData();
        initTaskCount();
        //initFormData();
    }


    private void initDatas() {
        menus[0] = new Pair("检验任务", new MenuData(R.drawable.test, 0,0));
        menus[1] = new Pair("报告审核", new MenuData(R.drawable.report, 0,1));
        menus[2] = new Pair("报告批准", new MenuData(R.drawable.approve, 0,2));
        menus[3] = new Pair("数据查询", new MenuData(R.drawable.data, 0,3));
        menus[4] = new Pair("检验配置", new MenuData(R.drawable.configuration, 0,4));
        menus[5] = new Pair("系统配置", new MenuData(R.drawable.system, 0,5));
        menuAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化 recycleview 菜单
     */
    private void initMenuRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        mRecycleView.setLayoutManager(layoutManager);

        menuAdapter = new HomeMenuAdapter(Arrays.asList(menus));
        menuAdapter.setHeaderView(banner);

        mRecycleView.setAdapter(menuAdapter);

        menuAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), CheckActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(),VerifyActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(),VerifyActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(),VerifyActivity.class));
                    break;
            }
        });
    }


    /**
     * 当前 fragment 是否已经添加并显示
     *
     * @return
     */
    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    /**
     * 加载轮播 banner
     */
    @Override
    public void initBanners() {
        presenter.getBannersData();
//        showBanners();
    }

    @Override
    public void initTaskData() {
        mUid = SharedPrefUtils.getObj(Constant.USERID);
        dataFields = "CraneRecordListID,CraneRecordCode,CheckRecordID,ReportClassID,CheckYear,ReportID," +
                "CheckType,APPRecordState,RecordTime,RecordState,SurveyConclusions,CheckStartData,SurveyDate," +
                "TendingOrganize,TendingLinkMan,TendingTel,UseOrganize,UseOrganizeAdd,UseOrganizeCode,UserRegeditCode,UseOrganizeTel,MadeCode," +
                "EquipmentCode,RegistCode,UnitNumber,ElevatorType,NextSurveyDate,Checker1,Checker2,CheckerOut,EquipmentVarieties,Specification,"+
                "Specification,ProductCode,MakeDate,MakeOrganize,UserSite,SafeAdmin,ReformDate,Reform,Builder,ConstructLicence,ConstructType,"+
                "RatedLoad,RatedSpeed,LayerStations,Ladderwidth,Angle,TransmissionCapacity,LiftingHeight,SegmentLength,Instrument,InstallationSite,"+
                "Control,RecordRemark";
        presenter.getTaskData(mUid, dataFields);
    }
    /**
     * 加载检验任务红点个数数据
     */
    @Override
    public void initTaskCount() {
        presenter.getTaskCount();
    }

    @Override
    public void initFormData() {
        for(TaskListData data:mDatas){
            presenter.getFormData(mUid,data.getCraneRecordListID());
        }
    }

    /**
     * 显示轮播 banner
     */
    @Override
    public void showBanners(BannerData data) {
        banner.setImages(data.getData());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideLoaderHelper());
        banner.isAutoPlay(true);
        banner.setDelayTime(3500);
//        banner.setOnBannerListener(position ->
//                ToastUtil.showToast(getActivity(), "你点击了第 " + position + " 条广告")
//        );

        banner.start();
    }

    /**
     * 显示检验任务红点个数
     */
    @Override
    public void showTaskCount(List<TaskListData> dataList) {
        mDatas.clear();
        mDatas.addAll(dataList);
        int count = dataList.size();
        menus[0] = new Pair("检验任务", new MenuData(R.drawable.test, count,0));
        menus[1] = new Pair("报告审核", new MenuData(R.drawable.report, count,1));
        menus[2] = new Pair("报告批准", new MenuData(R.drawable.approve, count,2));
        menus[3] = new Pair("数据查询", new MenuData(R.drawable.data, count,3));
        menuAdapter.notifyDataSetChanged();

        if(mDatas.size()>0){
            initFormData();
        }

    }

    @Override
    public void showNetWorkError() {
        ToastUtil.showToast(getActivity(), getString(R.string.http_error));
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
}
