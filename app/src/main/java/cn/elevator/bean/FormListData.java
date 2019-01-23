package cn.elevator.bean;

import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Yangzb on 2019/1/23 13:57
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class FormListData implements MultiItemEntity {
    public static final int TEXT_BG_BLUE = 1;
    public static final int TEXT = 2;
    public static final int ITEM_EDIT = 3;
    /**
     * CheckProjectListID : 01201812110002-73
     * CheckRecordID : 01201812110002
     * ReportClassID : 1101
     * FatherId : 110104
     * CheckListCode : 11010402
     * CheckListName : 4.2扶手防爬/阻挡/防滑行装置
     * ListType :
     * InspectionItem : null
     * DefaultResult :
     * DefaultConclusion :
     * IFMergerConclusions : null
     * IFMergerConclusionsDefault : null
     * Remark : null
     */

    private String CheckProjectListID;
    private String CheckRecordID;
    private String ReportClassID;
    private String FatherId;
    private String CheckListCode;
    private String CheckListName;
    private String ListType;
    private Object InspectionItem;
    private String DefaultResult;
    private String DefaultConclusion;
    private Object IFMergerConclusions;
    private Object IFMergerConclusionsDefault;
    private Object Remark;

    @Override
    public int getItemType() {
        if(FatherId.length()==4){
            return TEXT_BG_BLUE;
        }else if(!TextUtils.isEmpty(DefaultResult)){
            return ITEM_EDIT;
        }
        return TEXT;
    }

    public String getCheckProjectListID() {
        return CheckProjectListID;
    }

    public void setCheckProjectListID(String CheckProjectListID) {
        this.CheckProjectListID = CheckProjectListID;
    }

    public String getCheckRecordID() {
        return CheckRecordID;
    }

    public void setCheckRecordID(String CheckRecordID) {
        this.CheckRecordID = CheckRecordID;
    }

    public String getReportClassID() {
        return ReportClassID;
    }

    public void setReportClassID(String ReportClassID) {
        this.ReportClassID = ReportClassID;
    }

    public String getFatherId() {
        return FatherId;
    }

    public void setFatherId(String FatherId) {
        this.FatherId = FatherId;
    }

    public String getCheckListCode() {
        return CheckListCode;
    }

    public void setCheckListCode(String CheckListCode) {
        this.CheckListCode = CheckListCode;
    }

    public String getCheckListName() {
        return CheckListName;
    }

    public void setCheckListName(String CheckListName) {
        this.CheckListName = CheckListName;
    }

    public String getListType() {
        return ListType;
    }

    public void setListType(String ListType) {
        this.ListType = ListType;
    }

    public Object getInspectionItem() {
        return InspectionItem;
    }

    public void setInspectionItem(Object InspectionItem) {
        this.InspectionItem = InspectionItem;
    }

    public String getDefaultResult() {
        return DefaultResult;
    }

    public void setDefaultResult(String DefaultResult) {
        this.DefaultResult = DefaultResult;
    }

    public String getDefaultConclusion() {
        return DefaultConclusion;
    }

    public void setDefaultConclusion(String DefaultConclusion) {
        this.DefaultConclusion = DefaultConclusion;
    }

    public Object getIFMergerConclusions() {
        return IFMergerConclusions;
    }

    public void setIFMergerConclusions(Object IFMergerConclusions) {
        this.IFMergerConclusions = IFMergerConclusions;
    }

    public Object getIFMergerConclusionsDefault() {
        return IFMergerConclusionsDefault;
    }

    public void setIFMergerConclusionsDefault(Object IFMergerConclusionsDefault) {
        this.IFMergerConclusionsDefault = IFMergerConclusionsDefault;
    }

    public Object getRemark() {
        return Remark;
    }

    public void setRemark(Object Remark) {
        this.Remark = Remark;
    }
}
