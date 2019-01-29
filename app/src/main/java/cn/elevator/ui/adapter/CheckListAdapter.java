package cn.elevator.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;

import java.util.List;

import cn.elevator.R;
import cn.elevator.bean.MenuData;
import cn.elevator.bean.TaskData;
import cn.elevator.bean.TaskListData;
import q.rorbin.badgeview.QBadgeView;

/**
 * @anthor DamoJiang
 * @date 2018/8/15
 * @describe 首页菜单
 *
 **/
public class CheckListAdapter extends BaseQuickAdapter<TaskListData, BaseViewHolder> {
    public CheckListAdapter(@Nullable List<TaskListData> data) {
        super(R.layout.item_check_list, data);
    }
    private onClick onClick;
    //接口回调
    public interface onClick{
        void myClick(TaskListData listData);
    }
    public void setOnClick(onClick onClick) {
        this.onClick = onClick;
    }
    private onRectClick onRect;
    public interface onRectClick{
        void myRectClick(TaskListData listData);
    }
    public void setRectClick(onRectClick onRect){
        this.onRect = onRect;
    }
    @Override
    protected void convert(BaseViewHolder helper, TaskListData item) {
       helper.setText(R.id.id_tv_num,"流  水  号："+item.getCraneRecordListID())
               .setText(R.id.id_tv_type,"检 验 类 别："+getTypeStr(item.getCheckType()))
               .setText(R.id.id_tv_report_num,"报 告 编 号："+item.getReportID())
               .setText(R.id.id_tv_com_devicecode,"单位设备编号："+item.getUnitNumber())
               .setText(R.id.id_tv_eng,"维 保 单 位："+item.getTendingOrganize())
               .setText(R.id.id_tv_device_code,"设 备 代 码："+item.getEquipmentCode())
               .setText(R.id.id_tv_made_code,"出 厂 编 号："+item.getMadeCode())
               .setText(R.id.id_tv_state,"任 务 状 态："+getStateStr(item.getAPPRecordState()));
        SuperTextView textView = helper.getView(R.id.id_tv_oper);
        textView.setOnClickListener(v -> onClick.myClick(item));
        SuperTextView textRect = helper.getView(R.id.id_tv_rectify);
        textRect.setOnClickListener(v -> onRect.myRectClick(item));//整改
    }

    private String getStateStr(int appRecordState) {
        String state = "";
        switch (appRecordState){
            case 1:
                state = "未编制";
                break;
            case 2:
                state = " 编制中";
                break;
            case 3:
                state = "已提交";
                break;
        }
        return state;
    }

    private String getTypeStr(int checkType) {
        String type = "";
        switch (checkType){
            case 1:
                type = "首检";
                break;
            case 2:
                type = "定检";
                break;
            case 3:
                type = "监检";
                break;
        }
        return type;
    }

}
