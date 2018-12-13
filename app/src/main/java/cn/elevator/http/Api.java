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
    public static final String Banner = BASE_URL + "";

    // 检验任务列表
    public static final String TESK_LIST = BASE_URL + "Task/GetMyTask";

    // 检验项目数据
    public static final String PROJECT_DATA = BASE_URL + "Task/GetCheckProject";

}
