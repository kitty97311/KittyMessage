package com.galaxy.kittymessage;

import android.graphics.Bitmap;

import java.util.Calendar;

public class MessageGroupModel {
    public long _id = 0;
    public String number = null;
    public long contactId = 0;
    public String contactName = null;
    public Bitmap photo = null;
    public Calendar date = null;
    public int type;
    public String body;
    public boolean isRead;

}
