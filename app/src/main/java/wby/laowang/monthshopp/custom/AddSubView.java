package wby.laowang.monthshopp.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import wby.laowang.monthshopp.R;


public class AddSubView extends LinearLayout implements View.OnClickListener {

    private TextView add;
    private TextView sub;
    private EditText count;
    private OnAddSubClick onAddClick;

    public AddSubView(Context context) {
        this(context,null);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.add_layout, this);
        add = view.findViewById(R.id.add);
        sub = view.findViewById(R.id.sub);
        count = view.findViewById(R.id.count);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                add();
                onAddClick.onAddSubClick();
                break;
            case R.id.sub:
                sub();
                onAddClick.onAddSubClick();
                break;
        }

    }

    //添加
    public void add(){
        String s = count.getText().toString();
        int i = Integer.parseInt(s);
        i++;
        count.setText(i+"");

    }

    //减少
    public void sub(){
        String s = count.getText().toString();
        int i = Integer.parseInt(s);
        if (i>1){
            i--;
            count.setText(i+"");
        }
    }
    //获取数量
    public String getCount(){
        return count.getText().toString();
    }
    //设置数量
    public void setCount(int cousize){
        count.setText(cousize+"");
    }

    public interface OnAddSubClick{
        void onAddSubClick();
    }
    public void setOnAddClick(OnAddSubClick onAddClick){
        this.onAddClick = onAddClick;
    }
}
