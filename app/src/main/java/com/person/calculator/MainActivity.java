package com.person.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_clear;
    private Button btn_cc;
    private Button btn_chu;
    private Button btn_cheng;
    private Button btn_jian;
    private Button btn_jia;
    private Button btn_equal;
    private Button btn_point;
    private String  str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_input);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_chu = (Button) findViewById(R.id.btn_chu);
        btn_cheng = (Button) findViewById(R.id.btn_cheng);
        btn_jian = (Button) findViewById(R.id.btn_jian);
        btn_jia = (Button) findViewById(R.id.btn_jia);
        btn_clear = (Button) findViewById(R.id.clear);
        btn_cc = (Button) findViewById(R.id.c_current);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_point = (Button) findViewById(R.id.dian);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
        btn_cheng.setOnClickListener(this);
        btn_chu.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_cc.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        str = editText.getText().toString();
        switch (v.getId()){
            case R.id.btn_9 :
            case R.id.btn_1 :
            case R.id.btn_2 :
            case R.id.btn_3 :
            case R.id.btn_4 :
            case R.id.btn_5 :
            case R.id.btn_6 :
            case R.id.btn_7 :
            case R.id.btn_8 :
            case R.id.btn_0 :
            case R.id.dian :
                editText.setText(str + ((Button)v).getText());break;
            case R.id.btn_jia :
            case R.id.btn_jian :
            case R.id.btn_cheng :
            case R.id.btn_chu :
                if(!editText.getText().equals("")){
                    editText.setText(str + " " + ((Button)v).getText() + " ");break;
                }
            case R.id.clear :
                editText.setText("");break;
            case R.id.c_current :
                editText.setText(str.substring(0 ,str.length() - 2));break;
            case R.id.btn_equal :
                if(!editText.getText().equals("")){
                    getResult();break;
                }
            default :
                break;
        }
        editText.setSelection(editText.getText().length());
    }

    private void getResult() {
        String transform = editText.getText().toString();
        double result = 0;
        double n1;
        double n2;
        int len1 = 0;
        int len2 = 0;
        int k = 0;
        String s1,op,s2;
        for(int i = 0;i < transform.length();i++){
            if(transform.charAt(i)=='+'||transform.charAt(i)=='-'||transform.charAt(i)=='*'
                    ||transform.charAt(i)=='/'){
                k++;
            }
        }
        if(k == 1)
        {
            s1 = transform.substring(0,transform.indexOf(" "));
            op = transform.substring(transform.indexOf(" ") + 1 , transform.indexOf(" ") + 2);
            s2 = transform.substring(transform.indexOf(" ") + 3);
            if(!s1.equals("") && !s2.equals("")){
                n1 = Double.parseDouble(s1);
                n2 = Double.parseDouble(s2);

                if(op.equals("+"))
                {
                    result = n1 + n2;
                }
                else if(op.equals("-"))
                {
                    result = n1 - n2;
                }
                else if(op.equals("*"))
                {
                    result = n1 * n2;
                }
                else if (op.equals("/"))
                {
                    if(n2 == 0)
                    {
                        result = 0;
                    }
                    else
                    {
                        result = n1 / n2;
                    }
                }
                if(!s1.contains(".") && !s2.contains(".") && !op.equals("/")){
                    int r = (int) result;
                    editText.setText(r + "");
                }
                else{
                    editText.setText(result + "");
                }
            }
        }
        else{
            //第一个数据字符
            s1 = transform.substring(0,transform.indexOf(" "));
            len1 = s1.length();
            for(int i = 0;i < k;i++) {
                //运算符
                op = transform.substring(transform.indexOf(" ")+1,transform.indexOf(" ")+2);
                //第二个数据字符
                if(i==k - 1){
                    s2 = transform.substring(transform.indexOf(" ")+3);
                }
                else{
                    //逻辑未考虑小数情况，需修改？？？？
                    String flag = null;
                    if(i == 0){
                        flag = transform.substring(len1 - 1 + 4);
                    }
                    else{
                        flag = transform.substring(3);
                    }
                    s2 = flag.substring(0,flag.indexOf(" "));
                    len2 = s2.length();
                }
                if(i==0) {
                    n1 = Double.parseDouble(s1);
                }
                else{
                    n1 = result;
                }
                n2 = Double.parseDouble(s2);
                if(op.equals("+")){
                    result = n1 + n2;
                }
                else if(op.equals("-")){
                    result = n1 - n2;
                }
                else if(op.equals("*")){
                    result = n1 * n2;
                }
                else if(op.equals("/")){
                    if(n2 == 0){
                        result = 0;
                    }
                    else{
                        result = n1 / n2;
                    }
                }
                //第一轮计算过后然后去掉之前的部分,这里只考虑了前面是整数情况
                if(i == 0){
                    transform = transform.substring(len1 - 1 + len2 + 4);
                }
                //如果是最后一个数，那么就只用去掉之前的部分，可能是整数或者小数
                else if(i != k-1)
                {
                    transform = transform.substring(len2 - 1 + 4);
                }
            }
            if(result % 1 == 0) {
                int r_int = (int) result;
                editText.setText(r_int + "");
            }
            else{
                editText.setText(result + "");
            }
        }
    }
}
