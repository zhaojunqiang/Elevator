package cn.elevator.bean;

/**
 * Created by Yangzb on 2019/1/10 17:02
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class SaveFormResult {
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

        private boolean Result;
        private String ResultMessage;
        private String CheckProjectListID;

        public boolean isResult() {
            return Result;
        }

        public void setResult(boolean result) {
            Result = result;
        }

        public String getResultMessage() {
            return ResultMessage;
        }

        public void setResultMessage(String resultMessage) {
            ResultMessage = resultMessage;
        }

        public String getCheckProjectListID() {
            return CheckProjectListID;
        }

        public void setCheckProjectListID(String checkProjectListID) {
            CheckProjectListID = checkProjectListID;
        }
    }
}
