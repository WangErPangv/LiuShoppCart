package wby.laowang.monthshopp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import wby.laowang.monthshopp.bean.ShoppCartBean;
import wby.laowang.monthshopp.custom.AddSubView;
import wby.laowang.monthshopp.model.ModelFusion;
import wby.laowang.monthshopp.presenter.PresenterFusion;
import wby.laowang.monthshopp.view.IDelete;


public class MyExpanAdapter extends BaseExpandableListAdapter implements IDelete {

    private Context context;
    private List<ShoppCartBean.DataBean> slist;
    private int depid;

    public MyExpanAdapter(Context context, List<ShoppCartBean.DataBean> slist) {
        this.context = context;
        this.slist = slist;
    }

    @Override
    public int getGroupCount() {
        return slist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return slist.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return slist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return slist.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final ParentViewHolder parentViewHolder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_parent, null);
            parentViewHolder = new ParentViewHolder(convertView);

            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }

        parentViewHolder.parentTitle.setText(slist.get(groupPosition).getSellerName());
        parentViewHolder.parentBox.setChecked(slist.get(groupPosition).isParent_flag());

        parentViewHolder.parentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked = parentViewHolder.parentBox.isChecked();
                slist.get(groupPosition).setParent_flag(ischecked);

                List<ShoppCartBean.DataBean.ListBean> list = slist.get(groupPosition).getList();
                for (int i = 0; i < list.size(); i++) {
                    ShoppCartBean.DataBean.ListBean listBean = list.get(i);
                    listBean.setChild_flag(ischecked);
                }
                notifyDataSetChanged();

            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildViewHolder childViewHolder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_child, null);
            childViewHolder = new ChildViewHolder(convertView);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        depid = slist.get(groupPosition).getList().get(childPosition).getPid();
        Log.d("pidkkkk", depid + "我的pid");

        final List<ShoppCartBean.DataBean.ListBean> list = slist.get(groupPosition).getList();
        childViewHolder.childTitle.setText(list.get(childPosition).getTitle());
        childViewHolder.childPrice.setText("￥: " + list.get(childPosition).getPrice());
        childViewHolder.childBox.setChecked(list.get(childPosition).isChild_flag());

        String[] imags = list.get(childPosition).getImages().split("\\|");
        Uri parse = Uri.parse(imags[0]);
        childViewHolder.childImg.setImageURI(parse);

        childViewHolder.addsub.setCount(list.get(childPosition).getNum());
        childViewHolder.addsub.setOnAddClick(new AddSubView.OnAddSubClick() {
            @Override
            public void onAddSubClick() {
                String count = childViewHolder.addsub.getCount();
                list.get(childPosition).setNum(Integer.parseInt(count));
                notifyDataSetChanged();
            }
        });
        childViewHolder.childBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean child_checked = childViewHolder.childBox.isChecked();
                list.get(childPosition).setChild_flag(child_checked);

                boolean ch_flag = true;//一个开关标记
                for (int i = 0; i < list.size(); i++) {
                    boolean child_flag = list.get(i).isChild_flag();
                    if (child_flag == false) {
                        ch_flag = false;
                    }
                }
                slist.get(groupPosition).setParent_flag(ch_flag);

                notifyDataSetChanged();
            }
        });


        childViewHolder.childDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //列表删除，刷新之后还会有
                /*slist.remove(groupPosition);
                list.remove(childPosition);*/

                initDelete();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void showDeleteCart(DeleteBean deleteBean) {
        Toast.makeText(context, deleteBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String duid() {
        return "10756";
    }

    @Override
    public int dpid() {
        return depid;
    }

    static class ParentViewHolder {

        @BindView(R.id.parent_box)
        CheckBox parentBox;
        @BindView(R.id.parent_title)
        TextView parentTitle;

        public ParentViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    static class ChildViewHolder {

        @BindView(R.id.child_box)
        CheckBox childBox;
        @BindView(R.id.child_img)
        SimpleDraweeView childImg;
        @BindView(R.id.child_title)
        TextView childTitle;
        @BindView(R.id.child_price)
        TextView childPrice;
        @BindView(R.id.addsub)
        AddSubView addsub;
        @BindView(R.id.child_delete)
        TextView childDelete;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    public void initDelete() {
        PresenterFusion presenterFusion = new PresenterFusion();
        presenterFusion.showDeleteCartToView(new ModelFusion(presenterFusion), this);
    }

}
