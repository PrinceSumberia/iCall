package com.example.prince.icall;

import android.Manifest;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class sms extends Fragment {
    EditText phone_number, message;
    Button send_btn;

    private static final int REQUEST_SMS = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sms, container, false);

        phone_number = view.findViewById(R.id.phone_number);
        message = view.findViewById(R.id.sms_message);
        send_btn = view.findViewById(R.id.send_sms);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });
        return view;
    }

    private void sendSMS() {
        String number = phone_number.getText().toString();
        String sms_message = message.getText().toString();

        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
            } else {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getActivity(), 0, intent, 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, sms_message, pi, null);
                Toast.makeText(getActivity(), "Message Sent Successfully", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please Enter a Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMS();
            }
        } else {
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
}
