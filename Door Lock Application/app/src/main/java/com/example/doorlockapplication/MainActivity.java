package com.example.doorlockapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //qr code scanner object
    private IntentIntegrator qrScan;
    TextView address;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrScan = new IntentIntegrator(this);
        address = findViewById(R.id.address);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });

    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(MainActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    address.setText(obj.getString("name"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                    address.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



}
