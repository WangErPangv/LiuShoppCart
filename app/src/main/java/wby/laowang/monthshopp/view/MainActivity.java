package wby.laowang.monthshopp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wby.laowang.monthshopp.MyExpanAdapter;
import wby.laowang.monthshopp.R;
import wby.laowang.monthshopp.bean.ShoppCartBean;
import wby.laowang.monthshopp.model.ModelFusion;
import wby.laowang.monthshopp.presenter.PresenterFusion;

public class MainActivity extends AppCompatActivity implements IShoppCart {

    @BindView(R.id.expan_able)
    ExpandableListView expanAble;
    @BindView(R.id.quan_cbox)
    CheckBox quanCbox;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.shopp_finish)
    ImageView shoppFinish;
    private MyExpanAdapter myExpanAdapter;
    private ShoppCartBean shopplist;
    private String suid = "10756";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initdatas();
    }

    public void initdatas() {
        PresenterFusion presenterFusion = new PresenterFusion();
        presenterFusion.showShoppCartToView(new ModelFusion(presenterFusion), this);

        quanCbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean quan_checked = quanCbox.isChecked();
                List<ShoppCartBean.DataBean> data = shopplist.getData();

                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setParent_flag(quan_checked);

                    List<ShoppCartBean.DataBean.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        list.get(j).setChild_flag(quan_checked);
                    }
                }
                myExpanAdapter.notifyDataSetChanged();

                double sum = 0;

                for (int p = 0; p < data.size(); p++) {

                    List<ShoppCartBean.DataBean.ListBean> plist = shopplist.getData().get(p).getList();

                    for (int o = 0; o < plist.size(); o++) {

                        boolean child_flag = plist.get(o).isChild_flag();
                        if (child_flag) {
                            double price = plist.get(o).getPrice() * plist.get(o).getNum();
                            sum += price;
                        }

                    }
                }

                total.setText("合计:" + sum);
            }
        });
    }

    @Override
    public void showShoppCart(ShoppCartBean shoppCartBean) {
        this.shopplist = shoppCartBean;

        myExpanAdapter = new MyExpanAdapter(MainActivity.this, shoppCartBean.getData());
        expanAble.setAdapter(myExpanAdapter);
        myExpanAdapter.notifyDataSetChanged();

        int count = expanAble.getCount();
        for (int i = 0; i < count; i++) {
            expanAble.expandGroup(i);
        }
    }

    @Override
    public String uid() {
        return suid;
    }

    @OnClick(R.id.shopp_finish)
    public void onViewClicked() {
        finish();
    }

}
