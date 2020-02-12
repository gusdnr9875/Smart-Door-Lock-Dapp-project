package com.example.customerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
    }

    public void reservate(View view){
        Intent in = new Intent(MainMenu.this, ReservateHotel.class);
        startActivity(in);
    }
    public void sendTransition(View view){
        Intent in = new Intent(MainMenu.this, SignContract.class);
        startActivity(in);
    }
    public void openDoorLock(View view){
        Intent in = new Intent(MainMenu.this, OpenDoorLock.class);
        startActivity(in);
    }


}
