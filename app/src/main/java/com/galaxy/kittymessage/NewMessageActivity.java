package com.galaxy.kittymessage;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class NewMessageActivity extends BaseActivity {

    private EditText messageBox;
    private EditText receiverBox;
    private SmsManager smsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        setTitleText(R.string.new_conversation);

        messageBox = findViewById(R.id.messageBox);
        receiverBox = findViewById(R.id.receiverBox);

        findViewById(R.id.backButton).setOnClickListener(this);
        findViewById(R.id.clipButton).setOnClickListener(this);
        findViewById(R.id.sendButton).setOnClickListener(this);

        smsManager = SmsManager.getDefault();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.sendButton) {
            String phoneNumber = receiverBox.getText().toString();
            String message = messageBox.getText().toString();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
    }
}
