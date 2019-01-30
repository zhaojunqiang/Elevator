package cn.elevator.bean;

import java.util.List;

/**
 * Created by Yangzb on 2018/12/29 15:44
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class EquipmentData {

    /**
     * Code : 200
     * Message : OK
     * Data : [{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"18","CodeName":"计时器"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"19","CodeName":"便携式检验灯"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"20","CodeName":"常用电工工具"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"21","CodeName":"限速器测试设备"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"22","CodeName":"钢丝绳探伤仪"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"01","CodeName":"万用钳表"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"02","CodeName":"声级计"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"03","CodeName":"点温计"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"04","CodeName":"照度计"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"05","CodeName":"温湿度计"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"06","CodeName":"推拉力计"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"07","CodeName":"转速表"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"08","CodeName":"绝缘电阻测量仪"},{"TypeID":"DT_Instrumentation","TypeName":"电梯检验仪器","CodeID":"20","CodeName":"常用电工工具"}]
     * Count : 14
     */

    private String Code;
    private String Message;
    private int Count;
    private List<DataBean> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * TypeID : DT_Instrumentation
         * TypeName : 电梯检验仪器
         * CodeID : 18
         * CodeName : 计时器
         */

        private String TypeID;
        private String TypeName;
        private String CodeID;
        private String CodeName;

        public String getTypeID() {
            return TypeID;
        }

        public void setTypeID(String TypeID) {
            this.TypeID = TypeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public String getCodeID() {
            return CodeID;
        }

        public void setCodeID(String CodeID) {
            this.CodeID = CodeID;
        }

        public String getCodeName() {
            return CodeName;
        }

        public void setCodeName(String CodeName) {
            this.CodeName = CodeName;
        }
    }
}
