package com.galaxy.kittymessage;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagingActivity extends BaseActivity {

    private List<MessageModel> smsGroupList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        setTitleText(R.string.messaging);

        findViewById(R.id.startChatButton).setOnClickListener(this);

        RecyclerView smsGroupListView = findViewById(R.id.messageList);

        Cursor cursor = getContentResolver().query(Telephony.Sms.CONTENT_URI,
                null, null, null, null);
        List<MessageModel> smsList = new ArrayList<>();
        int size = cursor.getCount();
        while (cursor.moveToNext()) {
            String[] columnList = cursor.getColumnNames();
            MessageModel mm = new MessageModel();
            mm._id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));;
            String contactName = null;
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
            Cursor contactCursor = getContentResolver().query(uri, null,
                    ContactsContract.PhoneLookup.NUMBER + "='" + number + "'",null,null);
            if(contactCursor.getCount() > 0)
            {
                contactCursor.moveToFirst();
                contactName = contactCursor.getString(contactCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup.DISPLAY_NAME));
                long contactId = contactCursor.getLong(contactCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
                mm.contactId = contactId;
                // Query the ContactsContract.Data content URI to retrieve the contact photo
                Uri photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
                InputStream photoStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), photoUri);

                // Use the contact photo as needed
                if (photoStream != null) {
                    // Display or process the contact photo
                    mm.photo = BitmapFactory.decodeStream(photoStream);
                }
            }
            mm.number = number;
            mm.contactName = contactName;
            mm.body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.ThreadsColumns.DATE)));
            mm.date = cal;
            smsList.add(mm);
        }
        smsGroupList = smsList;

        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        smsGroupListView.setLayoutManager(rlm);
        smsGroupListView.setAdapter(new SmsListAdapter());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.startChatButton) {
            startActivity(new Intent(this, NewMessageActivity.class));
        }
    }

    private class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.SmsGroupViewHolder> {

        @NonNull
        @Override
        public SmsGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SmsGroupViewHolder(LayoutInflater.from(MessagingActivity.this).inflate(R.layout.item_message_group, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SmsGroupViewHolder holder, int position) {
            MessageModel item = smsGroupList.get(position);
            holder.contentView.setText(item.body);
            if (item.contactName == null) {
                holder.nameView.setText(item.number);
                holder.defaultView.setVisibility(View.VISIBLE);
            } else {
                holder.nameView.setText(item.contactName);
                if (item.photo == null) {
                    holder.contactView.setVisibility(View.VISIBLE);
                    holder.contactView.setText(String.valueOf(Character.toUpperCase(item.contactName.charAt(0))));
                } else {
                    holder.photoView.setVisibility(View.VISIBLE);
                    holder.photoView.setImageBitmap(item.photo);
                }
            }
            holder.contentView.setText(item.body);
        }

        @Override
        public int getItemCount() {
            return smsGroupList.size();
        }

        private class SmsGroupViewHolder extends RecyclerView.ViewHolder {

            public CircleImageView photoView;
            public ImageView defaultView;
            public  TextView contactView;
            public TextView nameView;
            public TextView contentView;
            public TextView dateView;

            public SmsGroupViewHolder(@NonNull View itemView) {
                super(itemView);
                photoView = itemView.findViewById(R.id.photoImageView);
                defaultView = itemView.findViewById(R.id.defaultAvatarView);
                contactView = itemView.findViewById(R.id.contactAvatarView);
                nameView = itemView.findViewById(R.id.nameTextView);
                contentView = itemView.findViewById(R.id.contentTextView);
                dateView = itemView.findViewById(R.id.timeView);
            }

        }
    }
}
