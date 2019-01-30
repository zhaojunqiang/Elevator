package cn.elevator.bean;

import java.util.List;

/**
 * Created by Yangzb on 2019/1/10 17:02
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class VersionInfo {

    /**
     * Code : 200
     * Message : OK
     * Data : [{"TypeID":"VersionUpgrade","TypeName":"版本升级路径","CodeID":"1","CodeName":"http://app.xajc.cn/app/"}]
     * Count : 1
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
         * TypeID : VersionUpgrade
         * TypeName : 版本升级路径
         * CodeID : 1
         * CodeName : http://app.xajc.cn/app/
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
