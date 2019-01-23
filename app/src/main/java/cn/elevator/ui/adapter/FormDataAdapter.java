package cn.elevator.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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
                break;
            case FormListData.TEXT:
                helper.setText(R.id.id_tv_title, item.getCheckListName());
                break;
            case FormListData.ITEM_EDIT:
                helper.setText(R.id.id_tv_title, item.getCheckListName());
                helper.setText(R.id.id_tv_type,item.getListType());
                helper.setText(R.id.id_tv_check,item.getDefaultResult());
                helper.setText(R.id.id_tv_result,item.getDefaultConclusion());
                break;
        }
    }
}
