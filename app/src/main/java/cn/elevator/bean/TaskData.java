package cn.elevator.bean;

/**
 * @author: DamonJiang
 * @date: 2018/8/15 0015
 * @description: 检验任务列表数据
 */
public class TaskData {
    private String CraneRecordListID;//流水号
    private String UseOrganize;//使用单位
    private String MadeCode;//出厂编号

    public String getCraneRecordListID() {
        return CraneRecordListID;
    }

    public void setCraneRecordListID(String craneRecordListID) {
        CraneRecordListID = craneRecordListID;
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
}
