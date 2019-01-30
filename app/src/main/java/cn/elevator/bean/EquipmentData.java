package cn.elevator.bean;

import java.util.List;

/**
 * Created by Yangzb on 2018/12/29 15:44
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class EquipmentData {
    private String Code;
    private List<EquipListData> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<EquipListData> getData() {
        return Data;
    }

    public void setData(List<EquipListData> data) {
        Data = data;
    }

    public class EquipListData {

    }
}
