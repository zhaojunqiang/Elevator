package cn.elevator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.elevator.R;


/**
 * 自定义APP 的titlebar
 *
 */

public class ToolBar extends RelativeLayout {

    private ImageView ivBack;
    private ImageView ivRight;
    private TextView tvTitle;
    private TextView tvRight;
    private View layoutRight;

    private int backResource = R.drawable.left;
    private int rightResource = R.drawable.iv_pop_entrance;
    private int titleColor = getResources().getColor(R.color.colorWhite);
    private float titleSize = getResources().getDimension(R.dimen.title_bar_text_size);
    private float rightTextSize = getResources().getDimension(R.dimen.main_text_size);
    private String title = "";
    private String rightText = "";
    private boolean isShowLeftView = true;
    private boolean isShowRightView = false;
    private boolean isShowRightText = false;

    public ToolBar(Context context) {
        super(context);
        init(context, null);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    public void init(Context context, AttributeSet attrs) {
        //获取到titlebar的布局文件
        LayoutInflater.from(context).inflate(R.layout.app_title_bar, this);
        ivBack = findViewById(R.id.iv_back);
        ivRight = findViewById(R.id.iv_title_right);
        tvTitle = findViewById(R.id.tv_title);
        layoutRight = findViewById(R.id.layout_title_right);
        tvRight = findViewById(R.id.tv_title_right);
        if (attrs != null) {
            //获取自定义的属性
            TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            title = attr.getString(R.styleable.TitleBar_title);
            rightText = attr.getString(R.styleable.TitleBar_rightText);
            backResource = attr.getResourceId(R.styleable.TitleBar_backResource, R.drawable.left);
            titleColor = attr.getColor(R.styleable.TitleBar_titleColor, getResources().getColor(R.color.colorWhite));
            titleSize = attr.getDimension(R.styleable.TitleBar_titleSize, getResources().getDimension(R.dimen.title_bar_text_size));
            rightTextSize = attr.getDimension(R.styleable.TitleBar_titleSize, getResources().getDimension(R.dimen.main_text_size));
            rightResource = attr.getResourceId(R.styleable.TitleBar_rightResource, R.drawable.iv_pop_entrance);
            isShowRightView = attr.getBoolean(R.styleable.TitleBar_showRightView, false);
            isShowLeftView = attr.getBoolean(R.styleable.TitleBar_showLeftView, true);
            isShowRightText = attr.getBoolean(R.styleable.TitleBar_showRightText, false);

            if (backResource <= 0) {
                isShowLeftView = false;
            }
            if (rightResource <= 0) {
                isShowRightView = false;
            }
            //设置值
            setLeftViewResource(backResource);
            setRightViewResource(rightResource);
            setTitleText(title);
            setTitleColor(titleColor);
            setTitleSize(titleSize);

            setRightText(rightText);
            setRightTextSize(rightTextSize);
            showLeftView(isShowLeftView);
            showRightView(isShowRightView);
            showRightText(isShowRightText);
            //释放资源
            attr.recycle();
        }
    }

    public void setLeftViewResource(int res) {
        ivBack.setImageResource(res);
    }

    public void setRightViewResource(int res) {
        ivRight.setImageResource(res);
    }

    public void setTitleText(int res) {
        tvTitle.setText(getResources().getString(res));
    }

    public void setTitleText(String str) {
        tvTitle.setText(str);
    }

    public void setRightText(String str) {
        tvRight.setText(str);
    }

    public void setRightText(int res) {
        tvRight.setText(getResources().getString(res));
    }

    public void setRightTextSize(float size) {
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }


    public void setTitleSize(float size) {
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    public void setLeftButtonOnClick(OnClickListener listener) {
        if (ivBack != null && listener != null) {
            ivBack.setOnClickListener(listener);
        }
    }

    public void setRightButtonOnClick(OnClickListener listener) {
        if (ivRight != null && listener != null) {
            ivRight.setOnClickListener(listener);
        }
    }

    public void setRightTextOnClick(OnClickListener listener) {
        if (tvRight != null && listener != null) {
            tvRight.setOnClickListener(listener);
        }
    }

    public void showRightView(boolean show) {
        if (layoutRight != null && ivRight != null) {
            if (show) {
                layoutRight.setVisibility(View.VISIBLE);
                ivRight.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.GONE);
            } else {
                ivRight.setVisibility(View.GONE);
                if (!isShowRightText) {
                    layoutRight.setVisibility(View.GONE);
                }
            }
        }
    }

    public void showRightText(boolean show) {
        if (layoutRight != null && tvRight != null) {
            if (show) {
                layoutRight.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.VISIBLE);
                ivRight.setVisibility(View.GONE);
            } else {
                tvRight.setVisibility(View.GONE);
                if (!isShowRightView) {
                    layoutRight.setVisibility(View.GONE);
                }
            }
        }
    }

    public void showLeftView(boolean show) {
        if (ivBack != null) {
            ivBack.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public ImageView getRightView() {
        return ivRight;
    }

    public ImageView getLeftView() {
        return ivBack;
    }

    public TextView getTitleView() {
        return tvTitle;
    }

}
