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
        private String UserId;
        private String UserCode;
        private String UserName;
        private String DepartmentID;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String userCode) {
            UserCode = userCode;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getDepartmentID() {
            return DepartmentID;
        }

        public void setDepartmentID(String departmentID) {
            DepartmentID = departmentID;
        }
    }
}
