package com.example.customerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.FormatterClosedException;

public class ReservateHotel extends AppCompatActivity {

    //시간관련
    TimePicker timePicker;
    CalendarView calendarView;


    //날짜를 저장하는 곳
    int year,month,dayofmonth;
    String totaldate;
    String reservationTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservate_hotel);

        timePicker = findViewById(R.id.timePicker);
        calendarView = findViewById(R.id.calendarView);

        //시간 받아오기
        reservationTime = Integer.toString(timePicker.getHour()) + ":" + Integer.toString(timePicker.getMinute());


        //날짜받아오기
        Calendar cal =Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH);
        dayofmonth = cal.get(cal.DATE);
        totaldate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(dayofmonth);







    }


}








