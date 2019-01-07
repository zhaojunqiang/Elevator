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
     * TendingLinkMan : 维保单位联系人
     * TendingTel ： 应急救援电话
     * UseOrganize : 使用单位
     * UseOrganizeAdd : 使用单位地址
     * UseOrganizeCode : 使用单位代码
     * UserRegeditCode : 使用登记证编号
     * UseOrganizeTel : 使用单位联系电话
     * MadeCode : 出厂编号
     * EquipmentCode : 设备代码
     * RegistCode : 注册代码
     * InspectionID : null
     * UnitNumber : 使用单位设备编号
     * ElevatorType : 直梯
     * NextSurveyDate : 下次检验日期
     * Checker1 : 检验人员1
     * Checker2 : 检验人员2
     * CheckerOut : 校核人员
     * EquipmentVarieties : 设备品种
     * Specification : 设备型号
     * ProductCode : 产品编号
     * MakeDate : 制造日期
     * MakeOrganize : 制造单位
     * UserSite: 使用地点
     * SafeAdmin : 安全管理人员
     * ReformDate : 改造日期
     * Reform : 改造单位
     * Builder : 施工单位
     * ConstructLicence : 施工许可证编号
     * ConstructType : 施工类别
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
    private String TendingLinkMan;
    private String TendingTel;
    private String UseOrganize;
    private String UseOrganizeAdd;
    private String UseOrganizeCode;
    private String UserRegeditCode;
    private String UseOrganizeTel;
    private String MadeCode;
    private String EquipmentCode;
    private String RegistCode;
    private String UnitNumber;
    private int ElevatorType;
    private String NextSurveyDate;
    private String Checker1;
    private String Checker2;
    private String CheckerOut;
    private String EquipmentVarieties;
    private String Specification;
    private String ProductCode;
    private String MakeDate;
    private String MakeOrganize;
    private String UserSite;
    private String SafeAdmin;
    private String ReformDate;
    private String Reform;
    private String Builder;
    private String ConstructLicence;
    private String ConstructType;
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

    public String getTendingLinkMan() {
        return TendingLinkMan;
    }

    public void setTendingLinkMan(String tendingLinkMan) {
        TendingLinkMan = tendingLinkMan;
    }

    public String getTendingTel() {
        return TendingTel;
    }

    public void setTendingTel(String tendingTel) {
        TendingTel = tendingTel;
    }

    public String getUseOrganize() {
        return UseOrganize;
    }

    public void setUseOrganize(String useOrganize) {
        UseOrganize = useOrganize;
    }

    public String getUseOrganizeAdd() {
        return UseOrganizeAdd;
    }

    public void setUseOrganizeAdd(String useOrganizeAdd) {
        UseOrganizeAdd = useOrganizeAdd;
    }

    public String getUseOrganizeCode() {
        return UseOrganizeCode;
    }

    public void setUseOrganizeCode(String useOrganizeCode) {
        UseOrganizeCode = useOrganizeCode;
    }

    public String getUserRegeditCode() {
        return UserRegeditCode;
    }

    public void setUserRegeditCode(String userRegeditCode) {
        UserRegeditCode = userRegeditCode;
    }

    public String getUseOrganizeTel() {
        return UseOrganizeTel;
    }

    public void setUseOrganizeTel(String useOrganizeTel) {
        UseOrganizeTel = useOrganizeTel;
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

    public int getElevatorType() {
        return ElevatorType;
    }

    public void setElevatorType(int elevatorType) {
        ElevatorType = elevatorType;
    }

    public String getNextSurveyDate() {
        return NextSurveyDate;
    }

    public void setNextSurveyDate(String nextSurveyDate) {
        NextSurveyDate = nextSurveyDate;
    }

    public String getChecker1() {
        return Checker1;
    }

    public void setChecker1(String checker1) {
        Checker1 = checker1;
    }

    public String getChecker2() {
        return Checker2;
    }

    public void setChecker2(String checker2) {
        Checker2 = checker2;
    }

    public String getCheckerOut() {
        return CheckerOut;
    }

    public void setCheckerOut(String checkerOut) {
        CheckerOut = checkerOut;
    }

    public String getEquipmentVarieties() {
        return EquipmentVarieties;
    }

    public void setEquipmentVarieties(String equipmentVarieties) {
        EquipmentVarieties = equipmentVarieties;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getMakeDate() {
        return MakeDate;
    }

    public void setMakeDate(String makeDate) {
        MakeDate = makeDate;
    }

    public String getMakeOrganize() {
        return MakeOrganize;
    }

    public void setMakeOrganize(String makeOrganize) {
        MakeOrganize = makeOrganize;
    }

    public String getUserSite() {
        return UserSite;
    }

    public void setUserSite(String userSite) {
        UserSite = userSite;
    }

    public String getSafeAdmin() {
        return SafeAdmin;
    }

    public void setSafeAdmin(String safeAdmin) {
        SafeAdmin = safeAdmin;
    }

    public String getReformDate() {
        return ReformDate;
    }

    public void setReformDate(String reformDate) {
        ReformDate = reformDate;
    }

    public String getReform() {
        return Reform;
    }

    public void setReform(String reform) {
        Reform = reform;
    }

    public String getBuilder() {
        return Builder;
    }

    public void setBuilder(String builder) {
        Builder = builder;
    }

    public String getConstructLicence() {
        return ConstructLicence;
    }

    public void setConstructLicence(String constructLicence) {
        ConstructLicence = constructLicence;
    }

    public String getConstructType() {
        return ConstructType;
    }

    public void setConstructType(String constructType) {
        ConstructType = constructType;
    }

    public Object getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(Object inspectionID) {
        InspectionID = inspectionID;
    }
}
