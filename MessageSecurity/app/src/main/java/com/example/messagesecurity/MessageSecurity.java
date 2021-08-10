package com.example.messagesecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MessageSecurity extends AppCompatActivity {

    float x1,x2,y1,y2;
    Boolean kt=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_security);

        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);

        EditText editTextKey=findViewById(R.id.edtxKey);
        EditText editText1=findViewById(R.id.edtx1);
        EditText editText2=findViewById(R.id.edtx2);

        TextView textViewKey=findViewById(R.id.txvKey);
        TextView textView1=findViewById(R.id.txv1);
        TextView textView2=findViewById(R.id.txv2);

        //boolean kt=true;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=editTextKey.getText().toString();
                String message=editText1.getText().toString();
                if (message.length()==0) Toast.makeText(MessageSecurity.this,"Vui lòng nhập văn bản vào!", Toast.LENGTH_SHORT).show();
                int s=0;
                for (int i=0;i<key.length();i++) s+=key.charAt(i);
                s %= 94 - 46;
                if (s==0) s=13;
                String messageSecurity="";
                if (kt)
                {
                    for (int i = 0; i < message.length(); i++)
                    {
                        int k = message.charAt(i);
                        k += s;
                        if (k < 32)
                        {
                            int t = 32 - k;
                            k = 127 - t;
                        }
                        else if (k > 126)
                        {
                            int t = k - 126;
                            k = 31 + t;
                        }
                        char ch = (char)k;
                        messageSecurity += ch;
                    }
                    editText2.setText(messageSecurity);
                }
                else
                {
                    for (int i = 0; i < message.length(); i++)
                    {
                        int k = message.charAt(i);
                        k -= s;
                        if (k < 32)
                        {
                            int t = 32 - k;
                            k = 127 - t;
                        }
                        else if (k > 126)
                        {
                            int t = k - 126;
                            k = 31 + t;
                        }
                        char ch = (char)k;
                        messageSecurity += ch;
                    }
                    editText2.setText(messageSecurity);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText("");
                editText2.setText("");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kt)
                {
                    textView1.setText("  Mã Hóa:");
                    textView2.setText("Tin Nhắn:");
                    editText1.setHint("Tin Nhắn Đã Mã Hóa");
                    editText2.setHint("Tin Nhắn Đã Giải Hóa");
                    button1.setText("Giải Hóa");
                    button3.setText("Chuyển Sang Mã Hóa");

                }
                else
                {
                    textView1.setText("Tin Nhắn:");
                    textView2.setText("  Mã Hóa:");
                    editText1.setHint("Tin Nhắn Cần Mã Hóa");
                    editText2.setHint("Tin Nhắn Đã Mã Hóa");
                    button1.setText("Mã Hóa");
                    button3.setText("Chuyển Sang Giải Hóa");
                }
                editText1.setText("");
                editText2.setText("");
                kt=!kt;
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(MessageSecurity.this,MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    this.finish();
                }
                break;
        }
        return false;
    }
}