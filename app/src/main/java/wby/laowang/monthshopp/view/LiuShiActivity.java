package wby.laowang.monthshopp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wby.laowang.monthshopp.R;

public class LiuShiActivity extends AppCompatActivity {

    @BindView(R.id.sou_finish)
    ImageView souFinish;
    @BindView(R.id.sou_edtext)
    EditText souEdtext;
    @BindView(R.id.sou_text)
    TextView souText;
    @BindView(R.id.shopp_liushi)
    FlowLayout shoppLiushi;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liu_shi);
        ButterKnife.bind(this);
        initLiushi();
    }

    @OnClick({R.id.sou_finish, R.id.sou_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sou_finish:
                finish();
                break;
            case R.id.sou_text:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    public void initLiushi(){

        list = new ArrayList<>();
        list.add("手机");list.add("火腿");
        list.add("连衣裙");list.add("坚果");
        list.add("零食");list.add("U盘64G");
        list.add("电脑");list.add("野马福特");

        for (int i = 0; i < list.size(); i++) {

            TextView textView = buildLabel(list.get(i));
            shoppLiushi.addView(textView);
        }
    }

    private TextView buildLabel(final String text){

        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        textView.setPadding((int) dpToPx(16),(int) dpToPx(8),(int) dpToPx(16),(int) dpToPx(8));
        textView.setBackgroundResource(R.drawable.liu_shaape);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                souEdtext.setText(text);
            }
        });

        return textView;
    }

    private float dpToPx(float dp){

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
