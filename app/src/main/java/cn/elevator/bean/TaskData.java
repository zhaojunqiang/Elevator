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
    private List<TaskListData> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<TaskListData> getData() {
        return Data;
    }

    public void setData(List<TaskListData> data) {
        Data = data;
    }
}
