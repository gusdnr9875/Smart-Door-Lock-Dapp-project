package com.example.customerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.util.Log;


/*
    1.    NfcAdapter 변수를 이용하여 NFC 정보 가져오기
    2.    Intent 설정하기
    3.    Intent 값을 PendingIntent 변수를 이용하여 옮겨 넣기
    4.    IntenFilter 변수를 이용하여 Intent 값을 필터링 하기
    5.    String 변수를 이용하여 techlist 설정
    6.    액티비티 순환주기 중 onResume() 에서 전송 메소드 실행
    7.    액티비티 순환주기 중 onPause() 에서 종료 메소드 실행
    8.    NFC 정보들 중 텍스트 정보 받아들여 화면에 뿌리기
 */
public class OpenDoorLock extends AppCompatActivity {

    NfcManager manager;
    NfcAdapter adapter;
    Intent intent;
    PendingIntent pendingIntent;
    IntentFilter ndefIntent;
    IntentFilter[] mlntentFilters;
    String[][] mNFCTechLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_door_lock);
        //   1. NfcAdapter 변수를 이용하여 NFC 정보 가져오기
        manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
        adapter = manager.getDefaultAdapter();

        //현재 액티비티에서 처리하는 방법
        intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //
        ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED); // Intent to start an activity when a tag with NDEF payload is discovered.
        try{
            ndefIntent.addDataType("*/*");
        }
        catch(Exception e)
        {
            Log.e("TagDispatch", e.toString());
        }
        mlntentFilters = new IntentFilter[] {ndefIntent,};
       mNFCTechLists = new String[][] { new String[]{ NfcF.class.getName()}};


    }
    @Override
    protected void onResume() {

        super.onResume();
        if (adapter != null) { //전송 메소드 실행
            adapter.enableForegroundDispatch(this, pendingIntent, mlntentFilters, mNFCTechLists); //null, null : 모든태그 인식
            //이 문장에서 다음 액티비티로 데이터를 전송하게 된다.
        }
    }


    @Override

    protected void onPause() {
        super.onPause();

        if (adapter != null) {
            adapter.disableForegroundDispatch(this); //수신
            finish();
        }
    }

}
