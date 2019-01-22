package cn.elevator.bean;

import java.util.List;

/**
 * @author: DamonJiang
 * @date: 2018/8/15 0015
 * @description: 首页广告数据
 */
public class BannerData {

    /**
     * Code : 200
     * Data : ["http://app.xa9c.cn/webdata/SysImage/TopImage.jpg","http://app.xa9c.cn/webdata/SysImage/1.jpg","http://app.xa9c.cn/webdata/SysImage/2.jpg"]
     */

    private String Code;
    private List<String> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<String> getData() {
        return Data;
    }

    public void setData(List<String> Data) {
        this.Data = Data;
    }
}
