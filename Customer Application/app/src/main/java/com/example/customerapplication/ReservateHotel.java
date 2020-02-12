package com.example.customerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.FormatterClosedException;

public class ReservateHotel extends AppCompatActivity {
    Intent passedIntent;
    TextView text;
    ImageView image;
    int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservate_hotel);

        text =(TextView) findViewById(R.id.text);
        image = (ImageView)findViewById(R.id.image);

        passedIntent = getIntent();

        String s = "";
        Parcelable[] data = passedIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES); //여분의 배열이 태그에 존재한다.
        Log.i("1", data+"");


        if (data != null) {
            try {

                for (int i = 0; i < data.length; i++) {
                    NdefRecord[] recs = ((NdefMessage) data[i]).getRecords();

                    for (j = 0; j < recs.length; j++) {
                        Log.i("j =", j + "");
                        if (recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT)) {
                            byte[] payload = recs[j].getPayload();
                            String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
                            int langCodeLen = payload[0] & 0077;
                            s += ("\n" + new String(payload, langCodeLen + 1, payload.length - langCodeLen - 1, textEncoding));
                            Log.i("check", s);
                        }
                    }
                    byte[] payload = recs[j].getPayload();
                }
            } catch (Exception e) {
                Log.e("TagDispatch", e.toString());
            }
        }
        text.setText(s);
    }


}








