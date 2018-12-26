package cn.elevator.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

/**
 * Created by Yangzb on 2018/12/24 09:45
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
@Entity
public class TaskListData {
    /**
     * CraneRecordListID : CCR201812110002
     * CraneRecordCode : 电子记录编号2
     * CheckRecordID : 报告ID2
     * ReportClassID : 0110
     * CheckYear : 2018
     * ReportID : JD2-DT-D-18-002
     * CheckType : 2
     * APPRecordState : 1
     * RecordTime : 11 22 2018  3:46PM
     * RecordState : 1
     * SurveyConclusions : 01
     * SurveyDate : 2018-05-21
     * TendingOrganize : 维护保养养单位
     * UseOrganize : 使用单位
     * MadeCode : 出厂编号
     * EquipmentCode : 设备代码
     * RegistCode : 注册代码
     * InspectionID : null
     * UnitNumber : 使用单位设备编号
     */
    @Id(assignable = true)
    private long id;
    private String CraneRecordListID;
    private String CraneRecordCode;
    private String CheckRecordID;
    private String ReportClassID;
    private int CheckYear;
    private String ReportID;
    private int CheckType;
    private int APPRecordState;
    private String RecordTime;
    private int RecordState;
    private String SurveyConclusions;
    private String SurveyDate;
    private String TendingOrganize;
    private String UseOrganize;
    private String MadeCode;
    private String EquipmentCode;
    private String RegistCode;
    private String UnitNumber;
    @Transient
    private Object InspectionID;

    public long getId() {
        return Long.valueOf(this.CraneRecordListID);
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCraneRecordListID() {
        return CraneRecordListID;
    }

    public void setCraneRecordListID(String craneRecordListID) {
        CraneRecordListID = craneRecordListID;
    }

    public String getCraneRecordCode() {
        return CraneRecordCode;
    }

    public void setCraneRecordCode(String craneRecordCode) {
        CraneRecordCode = craneRecordCode;
    }

    public String getCheckRecordID() {
        return CheckRecordID;
    }

    public void setCheckRecordID(String checkRecordID) {
        CheckRecordID = checkRecordID;
    }

    public String getReportClassID() {
        return ReportClassID;
    }

    public void setReportClassID(String reportClassID) {
        ReportClassID = reportClassID;
    }

    public int getCheckYear() {
        return CheckYear;
    }

    public void setCheckYear(int checkYear) {
        CheckYear = checkYear;
    }

    public String getReportID() {
        return ReportID;
    }

    public void setReportID(String reportID) {
        ReportID = reportID;
    }

    public int getCheckType() {
        return CheckType;
    }

    public void setCheckType(int checkType) {
        CheckType = checkType;
    }

    public int getAPPRecordState() {
        return APPRecordState;
    }

    public void setAPPRecordState(int APPRecordState) {
        this.APPRecordState = APPRecordState;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }

    public int getRecordState() {
        return RecordState;
    }

    public void setRecordState(int recordState) {
        RecordState = recordState;
    }

    public String getSurveyConclusions() {
        return SurveyConclusions;
    }

    public void setSurveyConclusions(String surveyConclusions) {
        SurveyConclusions = surveyConclusions;
    }

    public String getSurveyDate() {
        return SurveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        SurveyDate = surveyDate;
    }

    public String getTendingOrganize() {
        return TendingOrganize;
    }

    public void setTendingOrganize(String tendingOrganize) {
        TendingOrganize = tendingOrganize;
    }

    public String getUseOrganize() {
        return UseOrganize;
    }

    public void setUseOrganize(String useOrganize) {
        UseOrganize = useOrganize;
    }

    public String getMadeCode() {
        return MadeCode;
    }

    public void setMadeCode(String madeCode) {
        MadeCode = madeCode;
    }

    public String getEquipmentCode() {
        return EquipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        EquipmentCode = equipmentCode;
    }

    public String getRegistCode() {
        return RegistCode;
    }

    public void setRegistCode(String registCode) {
        RegistCode = registCode;
    }

    public String getUnitNumber() {
        return UnitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        UnitNumber = unitNumber;
    }

    public Object getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(Object inspectionID) {
        InspectionID = inspectionID;
    }
}
