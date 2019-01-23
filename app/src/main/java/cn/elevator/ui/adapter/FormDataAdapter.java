package cn.elevator.ui.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.List;

import cn.elevator.R;
import cn.elevator.bean.FormListData;

/**
 * Created by Yangzb on 2019/1/23 14:22
 * E-mail：yangzongbin@si-top.com
 * Describe:
 */
public class FormDataAdapter extends BaseMultiItemQuickAdapter<FormListData, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private String[] means = {"符合", "不符合", "无此项"};
    private String[] results = {"合格","不合格","——"};
    private TextView check;
    private TextView result;
    public FormDataAdapter(List<FormListData> data) {
        super(data);
        addItemType(FormListData.TEXT_BG_BLUE, R.layout.item_text_bg_blue);
        addItemType(FormListData.TEXT, R.layout.item_text);
        addItemType(FormListData.ITEM_EDIT, R.layout.item_edit);
    }

    @Override
    protected void convert(BaseViewHolder helper, FormListData item) {
        switch (helper.getItemViewType()) {
            case FormListData.TEXT_BG_BLUE:
                helper.setText(R.id.id_tv_title, item.getCheckListName());
                LinearLayout show1 = helper.getView(R.id.id_ll_show);
                show1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTip(item.getInspectionItem());
                    }
                });
                break;
            case FormListData.TEXT:
                helper.setText(R.id.id_tv_title, item.getCheckListName());
                LinearLayout show2 = helper.getView(R.id.id_ll_show);
                show2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTip(item.getInspectionItem());
                    }
                });
                break;
            case FormListData.ITEM_EDIT:
                helper.setText(R.id.id_tv_title, item.getCheckListName());
                helper.setText(R.id.id_tv_type,item.getListType());
                check = helper.getView(R.id.id_tv_check);
                result = helper.getView(R.id.id_tv_result);
                check.setText(item.getDefaultResult());
                result.setText(item.getDefaultConclusion());
                LinearLayout show3 = helper.getView(R.id.id_ll_show);
                show3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTip(item.getInspectionItem());
                    }
                });
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(item.getListType().equals("C")){
                            showMeans(item);
                        }
                    }
                });
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showResults(item);
                    }
                });
                break;
        }
    }

    private void showMeans(FormListData item) {
        QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(mContext).
                addItems(means, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +types[which], Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    check.setText(means[which]);
                    item.setDefaultResult(means[which]);
                });
        QMUIDialog typeDialog = typeBuilder.create();
        typeDialog.show();
    }

    private void showResults(FormListData item) {
        QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(mContext).
                addItems(results, (dialog, which) -> {
//                    Toast.makeText(CheckActivity.this, "你选择了 " +types[which], Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    result.setText(results[which]);
                    item.setDefaultResult(results[which]);
                });
        QMUIDialog typeDialog = typeBuilder.create();
        typeDialog.show();
    }

    private void showTip(String inspectionItem) {
        new QMUIDialog.MessageDialogBuilder(mContext)
                .setTitle("描述")
                .setMessage(inspectionItem)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
