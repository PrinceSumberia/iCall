package com.example.prince.icall;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Email extends Fragment {
    EditText recipient, subject, message;
    Button send_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        recipient = view.findViewById(R.id.recipient_email_address);
        subject = view.findViewById(R.id.email_subject);
        message = view.findViewById(R.id.email_message);
        send_btn = view.findViewById(R.id.send_email);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipient_email = recipient.getText().toString();
                String email_subject = subject.getText().toString();
                String email_message = message.getText().toString();

                if (recipient_email.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter Recipient Email Address", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient_email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, email_subject);
                    intent.putExtra(Intent.EXTRA_TEXT, email_message);

                    intent.setType("message/rfc822");

                    startActivity(Intent.createChooser(intent, "Choose an Email Client:"));
                }

            }
        });
        return view;
    }
}
