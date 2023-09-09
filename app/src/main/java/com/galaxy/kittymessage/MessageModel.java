package com.galaxy.kittymessage;

import android.graphics.Bitmap;

import java.util.Calendar;

public class MessageModel {
    public Calendar date = null;
    public int type;
    public int status;
    public String body;
    public boolean isRead;
    public String dateFormatString = null;
}
