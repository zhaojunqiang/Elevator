package cn.elevator.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.Gravity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.elevator.R;
import cn.elevator.bean.MenuData;
import cn.elevator.bean.TaskData;
import q.rorbin.badgeview.QBadgeView;

/**
 * @anthor DamoJiang
 * @date 2018/8/15
 * @describe 首页菜单
 *
 **/
public class CheckListAdapter extends BaseQuickAdapter<TaskData.DataBean, BaseViewHolder> {
    public CheckListAdapter(@Nullable List<TaskData.DataBean> data) {
        super(R.layout.item_check_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskData.DataBean item) {
       helper.setText(R.id.id_tv_num,item.getCraneRecordListID())
               .setText(R.id.id_tv_type,getTypeStr(item.getCheckType()))
               .setText(R.id.id_tv_report_num,item.getReportID())
               .setText(R.id.id_tv_use,item.getUseOrganize())
               .setText(R.id.id_tv_eng,item.getTendingOrganize())
               .setText(R.id.id_tv_device_code,item.getEquipmentCode())
               .setText(R.id.id_tv_time,item.getSurveyDate())
               .setText(R.id.id_tv_state,getStateStr(item.getAPPRecordState()));
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
