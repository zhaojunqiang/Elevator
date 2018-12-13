package cn.elevator.base.title;

/**
 * @company 上海道枢信息科技-->
 * @anthor created by xuebing
 * @date 2018/4/11
 * @change
 * @describe describe
 * 标题栏参数
 **/
public class TitleBuilder {
    private boolean isShowingTitleBar = true;
    private boolean isShowingBack = true;
    private String leftText;
    private String title;
    private boolean isShowingSubTitle;
    private String subTitle;
    private boolean isShowingRightText;
    private String rightText;
    private int leftIconRes;
    private int rightIconRes;
    private int searchVoiceIconRes;
    private int searchDeleteIconRes;

    public TitleBuilder() {
    }

    public TitleBuilder(String title) {
        this.title = title;
    }

    public String getLeftText() {
        return leftText;
    }

    public TitleBuilder leftText(String leftText) {
        this.leftText = leftText;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TitleBuilder title(String title) {
        this.title = title;
        return this;
    }

    public boolean isShowingSubTitle() {
        return isShowingSubTitle;
    }

    public TitleBuilder showingSubTitle(boolean isShowingSubTitle) {
        this.isShowingSubTitle = isShowingSubTitle;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public TitleBuilder subTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getRightText() {
        return rightText;
    }

    public TitleBuilder rightText(String rightText) {
        this.rightText = rightText;
        return this;
    }

    public int getLeftIconRes() {
        return leftIconRes;
    }

    public TitleBuilder leftIconRes(int leftIconRes) {
        this.leftIconRes = leftIconRes;
        return this;
    }

    public int getRightIconRes() {
        return rightIconRes;
    }

    public TitleBuilder rightIconRes(int rightIconRes) {
        this.rightIconRes = rightIconRes;
        return this;
    }

    public int getSearchVoiceIconRes() {
        return searchVoiceIconRes;
    }

    public TitleBuilder searchVoiceIconRes(int searchVoiceIconRes) {
        this.searchVoiceIconRes = searchVoiceIconRes;
        return this;
    }

    public int getSearchDeleteIconRes() {
        return searchDeleteIconRes;
    }

    public TitleBuilder searchDeleteIconRes(int searchDeleteIconRes) {
        this.searchDeleteIconRes = searchDeleteIconRes;
        return this;
    }

    public boolean isShowingRightText() {
        return isShowingRightText;
    }

    public TitleBuilder showingRightText(boolean showingRightText) {
        isShowingRightText = showingRightText;
        return this;
    }

    public boolean isShowingBack() {
        return isShowingBack;
    }

    public TitleBuilder showingBack(boolean showingBack) {
        isShowingBack = showingBack;
        return this;
    }


    public boolean isShowingTitleBar() {
        return isShowingTitleBar;
    }

    public TitleBuilder showingTitleBar(boolean showingTitleBar) {
        isShowingTitleBar = showingTitleBar;
        return this;
    }
}
