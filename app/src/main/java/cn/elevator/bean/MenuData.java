package cn.elevator.bean;

/**
 * Created by Yangzb on 2018/12/14 09:54
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class MenuData {
    int icon;
    int cout;
    int position;

    public MenuData(int icon, int cout,int position) {
        this.icon = icon;
        this.cout = cout;
        this.position = position;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
