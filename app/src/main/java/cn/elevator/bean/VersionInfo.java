package cn.elevator.bean;

/**
 * Created by Yangzb on 2019/1/10 17:02
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class VersionInfo {
    private String Code;
    private DataBean Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String TypeID;
        private String TypeName;
        private String CodeID;
        private String CodeName;

        public String getTypeID() {
            return TypeID;
        }

        public void setTypeID(String typeID) {
            TypeID = typeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String typeName) {
            TypeName = typeName;
        }

        public String getCodeID() {
            return CodeID;
        }

        public void setCodeID(String codeID) {
            CodeID = codeID;
        }

        public String getCodeName() {
            return CodeName;
        }

        public void setCodeName(String codeName) {
            CodeName = codeName;
        }
    }
}
