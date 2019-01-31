package cn.elevator.ui.adapter;

import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.List;

import cn.elevator.R;
import cn.elevator.app.App;
import cn.elevator.bean.FormListData;
import cn.elevator.bean.FormListData_;
import cn.elevator.bean.TaskListData;
import cn.elevator.utils.ToastUtil;
import io.objectbox.Box;

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
    private String[] means = {"符合", "不符合", "无此项","资料确认符合"};
    private String[] results = {"合格", "不合格", "——"};

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
                helper.setText(R.id.id_tv_type, item.getListType());
                TextView check = helper.getView(R.id.id_tv_check);
                TextView result = helper.getView(R.id.id_tv_result);
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
                        showMeans(check, item);

                    }
                });
                check.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //长按填写
                        showEdit(check, item);
                        return true;
                    }
                });
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showResults(result, item);
                    }
                });
                break;
        }
    }

    private void showEdit(TextView check, FormListData item) {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(mContext);
        builder.setTitle("输入").setPlaceholder("在此输入检验结果").
                setInputType(InputType.TYPE_CLASS_TEXT).
                addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            item.setDefaultResult(text.toString());
                            Box<FormListData> listDataBox = App.getInstance().
                                    getBoxStore().boxFor(FormListData.class);
                            listDataBox.put(item);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } else {
                            ToastUtil.showToast(mContext,"请输入检验结果");
                        }
                    }
                }).show();
    }


    private void showMeans(TextView v, FormListData item) {
        QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(mContext).
                addItems(means, (dialog, which) -> {
                    dialog.dismiss();
//                    v.setText(means[which]);
                    if(!item.getListType().equals("C") && which==3){
                        ToastUtil.showToast(mContext,"此项不能选择资料确认符合");
                        return;
                    }
                    item.setDefaultResult(means[which]);
                    if (item.getIFMergerConclusions() == 0) {//不是合并项
                        if(item.getListType().equals("C")){
                            if(which==0 || which==3){
                                item.setDefaultConclusion(results[0]);
                            }else {
                                item.setDefaultConclusion(results[which]);
                            }
                        }else {
                            item.setDefaultConclusion(results[which]);
                        }
                        Box<FormListData> listDataBox = App.getInstance().
                                getBoxStore().boxFor(FormListData.class);
                        listDataBox.put(item);
                        notifyDataSetChanged();
                    } else {//是合并项
                        Box<FormListData> listDataBox = App.getInstance().
                                getBoxStore().boxFor(FormListData.class);
                        listDataBox.put(item);
                        notifyDataSetChanged();
                        List<FormListData> listDatas = listDataBox.find(FormListData_.FatherId, item.getFatherId());
                        int index = -1;//合格/不合格
//                        int position = 2;//无此项
                        for (FormListData data : listDatas){
                            if(data.getDefaultResult().equals(means[1])){
                                index = 1;//不合格
                                break;
                            }else if(data.getDefaultResult().equals(means[0]) || data.getDefaultResult().equals(means[3])){
                                index = 0;//合格
                            }
//                            else if(data.getDefaultResult().equals(means[2])){
//                                position = 2;//无此项
//                            }
                        }
                        for (FormListData data:listDatas){
                            if(data.getIFMergerConclusionsDefault()==1){//显示合并结论
                                if(index > -1){
                                    data.setDefaultConclusion(results[index]);
                                }else {
                                    data.setDefaultConclusion(results[2]);
                                }
                                listDataBox.put(data);
                                notifyDataSetChanged();
                                break;
                            }
                        }
                    }
//

                });
        QMUIDialog typeDialog = typeBuilder.create();
        typeDialog.show();
    }

    private void showResults(TextView v, FormListData item) {
        QMUIDialog.MenuDialogBuilder typeBuilder = new QMUIDialog.MenuDialogBuilder(mContext).
                addItems(results, (dialog, which) -> {
                    dialog.dismiss();
//                    v.setText(results[which]);
                    item.setDefaultConclusion(results[which]);
                    Box<FormListData> listDataBox = App.getInstance().
                            getBoxStore().boxFor(FormListData.class);
                    listDataBox.put(item);
                    notifyDataSetChanged();
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
