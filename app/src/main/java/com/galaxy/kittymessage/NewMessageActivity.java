package com.galaxy.kittymessage;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewMessageActivity extends BaseActivity {

    private EditText messageBox;
    private EditText receiverBox;
    private SmsManager smsManager;
    private ListView contactListView;
    private List<ContactNumberModel> contactList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        setTitleText(R.string.new_conversation);

        messageBox = findViewById(R.id.messageBox);
        receiverBox = findViewById(R.id.receiverBox);

        contactListView = findViewById(R.id.contactListView);
        receiverBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setContactList(receiverBox.getText().toString());
                } else {
                    setContactList(null);
                }
            }
        });
        receiverBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setContactList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findViewById(R.id.clipButton).setOnClickListener(this);
        findViewById(R.id.sendButton).setOnClickListener(this);

        smsManager = SmsManager.getDefault();
    }

    private void setContactList(String searchKey) {
        contactList = new ArrayList<>();
        if (searchKey != null) {
            Cursor contactCursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    "REPLACE(REPLACE(REPLACE(REPLACE("
                            + ContactsContract.CommonDataKinds.Phone.NUMBER + ", '(', ''), ')', ''), '-', ''), ' ', '')" + " LIKE '%" + searchKey + "%' OR "
                            + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE '%" + searchKey + "%'",
                    null,
                    null
            );

            while (contactCursor.moveToNext()) {
                ContactNumberModel item = new ContactNumberModel();
                item.name = contactCursor.getString(contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                item.phoneNumber = contactCursor.getString(contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                item.phoneType = contactCursor.getInt(contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE));
                long contactId = contactCursor.getLong(contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID));
                item.contactId = contactId;
                // Query the ContactsContract.Data content URI to retrieve the contact photo
                Uri photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
                InputStream photoStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), photoUri);

                // Use the contact photo as needed
                if (photoStream != null) {
                    // Display or process the contact photo
                    item.photo = BitmapFactory.decodeStream(photoStream);
                }
                contactList.add(item);
            }
            contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String curTypedContact = receiverBox.getText().toString();
                    if (!receiverBox.getText().toString().isEmpty()) {
                        curTypedContact += ",";
                    }
                    receiverBox.setText(curTypedContact + contactList.get(position).name);
                    receiverBox.setSelection(receiverBox.getText().toString().length());
                }
            });
        }
        contactListView.setAdapter(new ContactNumberAdapter());
    }

    private class ContactNumberAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return contactList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(NewMessageActivity.this).inflate(R.layout.item_contact_number, parent, false);
            ContactNumberModel item = contactList.get(position);
            convertView.findViewById(R.id.photoImageView);
            String phoneType;
            switch (item.phoneType) {
                case 1:
                    phoneType = getResources().getString(R.string.phone_type_home);
                    break;
                case 2:
                    phoneType = getResources().getString(R.string.phone_type_mobile);
                    break;
                case 3:
                    phoneType = getResources().getString(R.string.phone_type_work);
                    break;
                default:
                    phoneType = getResources().getString(R.string.phone_type_other);
                    break;
            }
            ((TextView)convertView.findViewById(R.id.phoneTypeView)).setText(phoneType);
            if (item.name == null) {
                convertView.findViewById(R.id.defaultAvatarView).setVisibility(View.VISIBLE);
            } else {
                if (item.photo == null) {
                    convertView.findViewById(R.id.contactAvatarView).setVisibility(View.VISIBLE);
                    ((TextView)convertView.findViewById(R.id.contactAvatarView)).setText(String.valueOf(Character.toUpperCase(item.name.charAt(0))));
                } else {
                    convertView.findViewById(R.id.photoImageView).setVisibility(View.VISIBLE);
                    ((ImageView)convertView.findViewById(R.id.photoImageView)).setImageBitmap(item.photo);
                }
            }
            ((TextView)convertView.findViewById(R.id.nameTextView)).setText(item.name);
            ((TextView)convertView.findViewById(R.id.phoneNumberView)).setText(item.phoneNumber);
            return convertView;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.sendButton) {
            String phoneNumber = receiverBox.getText().toString();
            String message = messageBox.getText().toString();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            finish();
            Intent intent = new Intent(this, MessageDetailActivity.class);
        }
    }
}
