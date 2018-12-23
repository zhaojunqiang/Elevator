package cn.elevator.bean;

import java.util.List;

/**
 * @author: DamonJiang
 * @date: 2018/8/15 0015
 * @description: 检验任务列表数据
 */
public class TaskData {

    /**
     * Code : 200
     * Data : [{"CraneRecordListID":"CCR201812110002","CraneRecordCode":"电子记录编号2","CheckRecordID":"报告ID2","ReportClassID":"0110","CheckYear":2018,"ReportID":"JD2-DT-D-18-002","CheckType":2,"APPRecordState":1,"RecordTime":"11 22 2018  3:46PM","RecordState":1,"SurveyConclusions":"01","SurveyDate":"2018-05-21","TendingOrganize":"维护保养养单位","UseOrganize":"使用单位","MadeCode":"出厂编号","EquipmentCode":"设备代码","RegistCode":"注册代码","InspectionID":null},{"CraneRecordListID":"CCR201812110003","CraneRecordCode":"电子记录编号2","CheckRecordID":"报告ID2","ReportClassID":"0110","CheckYear":2018,"ReportID":"JD2-DT-D-18-003","CheckType":2,"APPRecordState":1,"RecordTime":"11 22 2018  3:46PM","RecordState":1,"SurveyConclusions":"01","SurveyDate":"2018-05-21","TendingOrganize":"维护保养养单位","UseOrganize":"使用单位","MadeCode":"出厂编号","EquipmentCode":"设备代码","RegistCode":"注册代码","InspectionID":null},{"CraneRecordListID":"CCR201812110004","CraneRecordCode":"电子记录编号2","CheckRecordID":"报告ID2","ReportClassID":"0110","CheckYear":2018,"ReportID":"JD2-DT-D-18-002","CheckType":2,"APPRecordState":1,"RecordTime":"11 22 2018  3:46PM","RecordState":1,"SurveyConclusions":"01","SurveyDate":"2018-05-21","TendingOrganize":"维护保养养单位","UseOrganize":"使用单位","MadeCode":"出厂编号","EquipmentCode":"设备代码","RegistCode":"注册代码","InspectionID":null},{"CraneRecordListID":"CCR201812110005","CraneRecordCode":"电子记录编号2","CheckRecordID":"报告ID2","ReportClassID":"0110","CheckYear":2018,"ReportID":"JD2-DT-D-18-004","CheckType":2,"APPRecordState":1,"RecordTime":"11 22 2018  3:46PM","RecordState":1,"SurveyConclusions":"01","SurveyDate":"2018-05-21","TendingOrganize":"维护保养养单位","UseOrganize":"使用单位","MadeCode":"出厂编号","EquipmentCode":"设备代码","RegistCode":"注册代码","InspectionID":null}]
     */

    private String Code;
    private List<DataBean> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
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
         */

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
        private Object InspectionID;

        public String getCraneRecordListID() {
            return CraneRecordListID;
        }

        public void setCraneRecordListID(String CraneRecordListID) {
            this.CraneRecordListID = CraneRecordListID;
        }

        public String getCraneRecordCode() {
            return CraneRecordCode;
        }

        public void setCraneRecordCode(String CraneRecordCode) {
            this.CraneRecordCode = CraneRecordCode;
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

        public int getCheckYear() {
            return CheckYear;
        }

        public void setCheckYear(int CheckYear) {
            this.CheckYear = CheckYear;
        }

        public String getReportID() {
            return ReportID;
        }

        public void setReportID(String ReportID) {
            this.ReportID = ReportID;
        }

        public int getCheckType() {
            return CheckType;
        }

        public void setCheckType(int CheckType) {
            this.CheckType = CheckType;
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

        public void setRecordTime(String RecordTime) {
            this.RecordTime = RecordTime;
        }

        public int getRecordState() {
            return RecordState;
        }

        public void setRecordState(int RecordState) {
            this.RecordState = RecordState;
        }

        public String getSurveyConclusions() {
            return SurveyConclusions;
        }

        public void setSurveyConclusions(String SurveyConclusions) {
            this.SurveyConclusions = SurveyConclusions;
        }

        public String getSurveyDate() {
            return SurveyDate;
        }

        public void setSurveyDate(String SurveyDate) {
            this.SurveyDate = SurveyDate;
        }

        public String getTendingOrganize() {
            return TendingOrganize;
        }

        public void setTendingOrganize(String TendingOrganize) {
            this.TendingOrganize = TendingOrganize;
        }

        public String getUseOrganize() {
            return UseOrganize;
        }

        public void setUseOrganize(String UseOrganize) {
            this.UseOrganize = UseOrganize;
        }

        public String getMadeCode() {
            return MadeCode;
        }

        public void setMadeCode(String MadeCode) {
            this.MadeCode = MadeCode;
        }

        public String getEquipmentCode() {
            return EquipmentCode;
        }

        public void setEquipmentCode(String EquipmentCode) {
            this.EquipmentCode = EquipmentCode;
        }

        public String getRegistCode() {
            return RegistCode;
        }

        public void setRegistCode(String RegistCode) {
            this.RegistCode = RegistCode;
        }

        public Object getInspectionID() {
            return InspectionID;
        }

        public void setInspectionID(Object InspectionID) {
            this.InspectionID = InspectionID;
        }
    }
}
