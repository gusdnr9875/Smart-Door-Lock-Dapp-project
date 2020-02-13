package com.example.customerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    static final int GET_STRING =1; //전역변수를 상수화 합니다 GET_STRING =1
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainMenu.this, ReservateHotel.class);
                startActivityForResult(in,GET_STRING);

            }
        });


    }


    public void sendTransition(View view){
        Intent in = new Intent(MainMenu.this, SignContract.class);
        startActivity(in);
    }
    public void openDoorLock(View view){
        Intent in = new Intent(MainMenu.this, OpenDoorLock.class);
        startActivity(in);
    }

    @Override
    protected  void onActivityResult(int requsetCode,int resultCode,Intent data ){ //만약 값이 전달 될때

        super.onActivityResult(1, resultCode, data);
        if(requsetCode==GET_STRING) //요청코드가 1이고
            if(resultCode==RESULT_OK){ //결과코드가 RESUL_OK값이면
                textView.setText(data.getStringExtra("INPUT_TEXT")); // INPUT_TEXT라는 이름값을 가진 문자열을 받아와서 텍스트뷰로 보여줍니다.
                Log.i("ads",data.getStringExtra("INPUT_TEXT"));
            }

    }



}
