package cn.elevator.bean;


/**
 * 登录成功后返回的数据模型
 * Created by DamonJiang on 2018/8/13 0013.
 */
public class LoginData {


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

        private String UserId;
        private boolean Result;
        private String Token;
        private String LoginState;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public boolean isResult() {
            return Result;
        }

        public void setResult(boolean Result) {
            this.Result = Result;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getLoginState() {
            return LoginState;
        }

        public void setLoginState(String LoginState) {
            this.LoginState = LoginState;
        }
    }
}
