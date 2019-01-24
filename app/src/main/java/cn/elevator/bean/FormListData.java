package cn.elevator.bean;

import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Yangzb on 2019/1/23 13:57
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
@Entity
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

    @Id(assignable = true)
    private long id;
    private String CheckProjectListID;
    private String CheckRecordID;
    private String ReportClassID;
    private String FatherId;
    private String CheckListCode;
    private String CheckListName;
    private String ListType;
    private String InspectionItem;
    private String DefaultResult;
    private String DefaultConclusion;
    private int IFMergerConclusions;
    private int IFMergerConclusionsDefault;
    private String Remark;
    private int ord;

    @Override
    public int getItemType() {
        if(FatherId.length()==4){
            return TEXT_BG_BLUE;
        }else if(!TextUtils.isEmpty(DefaultResult)){
            return ITEM_EDIT;
        }
        return TEXT;
    }

    public long getId() {
        return Long.valueOf(this.CheckProjectListID);
    }

    public void setId(long id) {
        this.id = id;
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

    public String getInspectionItem() {
        return InspectionItem;
    }

    public void setInspectionItem(String InspectionItem) {
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

    public int getIFMergerConclusions() {
        return IFMergerConclusions;
    }

    public void setIFMergerConclusions(int IFMergerConclusions) {
        this.IFMergerConclusions = IFMergerConclusions;
    }

    public int getIFMergerConclusionsDefault() {
        return IFMergerConclusionsDefault;
    }

    public void setIFMergerConclusionsDefault(int IFMergerConclusionsDefault) {
        this.IFMergerConclusionsDefault = IFMergerConclusionsDefault;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }
}
