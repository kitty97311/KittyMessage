package com.galaxy.kittymessage;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageDetailActivity extends BaseActivity{

    private static long threadId = 0;
    private static List<MessageModel> mList;
    private static ContentResolver contentResolver;
    private static RecyclerView mListView;
    private static Context context;
    private String number;
    private EditText messageBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        context = this;
        findViewById(R.id.sendButton).setOnClickListener(this);

        mListView = findViewById(R.id.messageListView);
        messageBox = findViewById(R.id.messageBox);

        contentResolver = getContentResolver();
        threadId = getIntent().getLongExtra(AppConstant.THREAD_ID_KEY, 0);
        String contactName = getIntent().getStringExtra(AppConstant.CONTACT_NAME_KEY);
        number = getIntent().getStringExtra(AppConstant.NUMBER_KEY);

        if (contactName == null) {
            hideSubTitle();
            setTitleText(number);
        } else {
            setTitleText(contactName);
            setSubTitleText(number);
        }

        LinearLayoutManager rlm = new LinearLayoutManager(context);
        mListView.setLayoutManager(rlm);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshList();
    }

    public static void refreshList() {
        mList = new ArrayList<>();
        Uri uri = Telephony.Sms.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, Telephony.Sms.THREAD_ID + " = ?", new String[]{String.valueOf(threadId)}, null);
        String dateString = "";
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE)));
                if (!dateString.equals(AppHelper.GetMessageDetailDate(context, calendar))) {
                    dateString = AppHelper.GetMessageDetailDate(context, calendar);
                    MessageModel dateModel = new MessageModel();
                    dateModel.dateFormatString = dateString;
                    mList.add(dateModel);
                }
                MessageModel mm = new MessageModel();
                mm.body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                mm.date = calendar;
                mm.isRead = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.READ)) == 1;
                mm.type = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE));
                mm.status = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.STATUS));
                mm.dateFormatString = null;
                mList.add(mm);
            } while (cursor.moveToNext());
            cursor.close();
        }
        SmsListAdapter sla = new SmsListAdapter();
        mListView.setAdapter(sla);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.sendButton) {
            SmsManager smsManager = SmsManager.getDefault();
            String message = messageBox.getText().toString();
            for (String n : number.split(";")) {
                Intent sentIntent = new Intent(this, SmsSentReceiver.class);
                PendingIntent sentPendingIntent;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                    sentPendingIntent = PendingIntent.getBroadcast(this, AppConstant.SENT_PENDING_INTENT_REQUEST_CODE, sentIntent, PendingIntent.FLAG_MUTABLE);
                } else {
                    sentPendingIntent = PendingIntent.getBroadcast(this, AppConstant.SENT_PENDING_INTENT_REQUEST_CODE, sentIntent, 0);
                }
                Intent deliveryIntent = new Intent(this, SmsDeliveryReceiver.class);
                PendingIntent deliveryPendingIntent;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                    deliveryPendingIntent = PendingIntent.getBroadcast(this, AppConstant.DELIVERY_PENDING_INTENT_REQUEST_CODE, deliveryIntent, PendingIntent.FLAG_MUTABLE);
                } else {
                    deliveryPendingIntent = PendingIntent.getBroadcast(this, AppConstant.DELIVERY_PENDING_INTENT_REQUEST_CODE, deliveryIntent, 0);
                }
                smsManager.sendTextMessage(n, null, message, sentPendingIntent, deliveryPendingIntent);
            }
            refreshList();
        }
    }

    private static class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.SmsDetailHolder> {

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @NonNull
        @Override
        public SmsDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MessageModel item = mList.get(viewType);
            SmsDetailHolder sdh;
            if (item.dateFormatString == null) {
                sdh = new MessageDetailActivity.SmsListAdapter
                        .SmsDetailHolder(LayoutInflater.from(context).inflate(item.type == 1 ? R.layout.item_received_message : R.layout.item_sent_message, parent, false));
            } else {
                sdh = new MessageDetailActivity.SmsListAdapter
                        .SmsDetailHolder(LayoutInflater.from(context).inflate(R.layout.item_message_date, parent, false));
            }
            return sdh;
        }

        @Override
        public void onBindViewHolder(@NonNull SmsDetailHolder holder, int position) {
            MessageModel item = mList.get(position);
            if (item.dateFormatString != null) {
                holder.messageTimeView.setText(item.dateFormatString);
            } else {
                holder.messageContentView.setText(item.body);
                holder.messageTimeView.setText(AppHelper.GetMessageDetailTime(context, item.date));
                if (item.type == 2) {
                    if (item.status == Telephony.Sms.STATUS_COMPLETE) {
                        holder.messageStateView.setText(R.string.received);
                    } else if (item.status == Telephony.Sms.STATUS_FAILED) {
                        holder.messageStateView.setText(R.string.failed);
                    } else if (item.status == Telephony.Sms.STATUS_PENDING) {
                        holder.messageStateView.setText(R.string.pending);
                    } else if (item.status == Telephony.Sms.STATUS_NONE) {
                        holder.messageStateView.setVisibility(View.GONE);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        private static class SmsDetailHolder extends RecyclerView.ViewHolder {

            public TextView messageContentView;
            public TextView messageTimeView;
            public TextView messageStateView;

            public SmsDetailHolder(@NonNull View itemView) {
                super(itemView);
                messageContentView = itemView.findViewById(R.id.messageContentView);
                messageTimeView = itemView.findViewById(R.id.messageTimeView);
                messageStateView = itemView.findViewById(R.id.messageStateView);
            }
        }
    }
}
