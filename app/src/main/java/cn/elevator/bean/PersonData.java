package cn.elevator.bean;

import java.util.List;

/**
 * Created by Yangzb on 2018/12/29 15:44
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class PersonData {
    private String Code;
    private List<PersonListData> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<PersonListData> getData() {
        return Data;
    }

    public void setData(List<PersonListData> data) {
        Data = data;
    }

    public class PersonListData {

        /**
         * UserID : u0001
         * UserCode : XB001
         * UserName : 李明
         * DepartmentID : BM001
         */

        private String UserID;
        private String UserCode;
        private String UserName;
        private String DepartmentID;

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String UserCode) {
            this.UserCode = UserCode;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getDepartmentID() {
            return DepartmentID;
        }

        public void setDepartmentID(String DepartmentID) {
            this.DepartmentID = DepartmentID;
        }
    }
}
