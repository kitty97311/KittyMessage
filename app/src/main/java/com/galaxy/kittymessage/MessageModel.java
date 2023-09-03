package com.galaxy.kittymessage;

import android.graphics.Bitmap;

import java.util.Calendar;

public class MessageModel {
    public long _id = 0;
    public String number = null;
    public long contactId = 0;
    public String contactName = null;
    public Bitmap photo = null;
    public Calendar date = null;
    public SmsType type;
    public String subject;
    public String body;
    public String service_center;
    public boolean isRead;
    public int status;
    public boolean isLocked;
    public int sub_id;

    public enum SmsType {
        RECEIVED,
        SENT,
        DRAFT,
        FAILED
    }
}
