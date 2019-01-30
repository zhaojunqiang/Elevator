package cn.elevator.http;

/**
 * 接口地址
 * Created by DamonJiang on 2018/8/13 0013.
 */
public class Api {
    //Base URL.
    public static final String BASE_URL = "http://app.xa9c.cn/api/";

    // 登录
    public static final String LOGIN = BASE_URL + "Account/Login";

    // 广告
    public static final String Banner = BASE_URL + "Image/GetSysTopImage";

    // 检验任务列表
    public static final String TASK_LIST = BASE_URL + "Task/GetCheckTaskList";

    // 检验项目数据
    public static final String PROJECT_DATA = BASE_URL + "Task/GetCheckProjectList";

    //获取权限人员
    public static final String CHECK_PERSON = BASE_URL + "User/GetUserList";

    //保存检验数据
    public static final String SAVE_TASK = BASE_URL + "Task/SaveCheckTask";

    //保存检验项数据
    public static final String SAVE_FORM = BASE_URL + "Task/SaveCheckProject";

    //保存检验整改单
    public static final String SAVE_RECTIFY = BASE_URL + "Task/SaveRectify";

    //基本代码
    public static final String BASE_CODE = BASE_URL + "Task/GetBaseCode";
}
