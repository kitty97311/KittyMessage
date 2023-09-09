package com.galaxy.kittymessage;

public class AppConstant {
    public static final String APP_DEVELOPER = "Kitty";
    public static final String[][] LANGUAGE_ARRAY = {
            {Integer.toString(R.drawable.img_flag_us), Integer.toString(R.string.english), "US"},
            {Integer.toString(R.drawable.img_flag_cn), Integer.toString(R.string.chinese), "ZH"},
            {Integer.toString(R.drawable.img_flag_ru), Integer.toString(R.string.russian), "RU"},
            {Integer.toString(R.drawable.img_flag_in), Integer.toString(R.string.hindi), "HI"},
            {Integer.toString(R.drawable.img_flag_es), Integer.toString(R.string.spainish), "ES"},
            {Integer.toString(R.drawable.img_flag_ae), Integer.toString(R.string.arabic), "AR"},
            {Integer.toString(R.drawable.img_flag_jp), Integer.toString(R.string.japanese), "JA"},
            {Integer.toString(R.drawable.img_flag_pt), Integer.toString(R.string.portuguese), "PT"},
            {Integer.toString(R.drawable.img_flag_de), Integer.toString(R.string.german), "DE"},
    };

    public static final String FIRST_PASSWORD_KEY = "first_password";
    public static final String CUR_PASSWORD_KEY = "current_password";
    public static final String CUR_LANG_KEY = "current_language";
    public static final String NOT_SET_KEY = "not_set";
    public static final String THREAD_ID_KEY = "thread_id";
    public static final String CONTACT_NAME_KEY = "contact_name";
    public static final String NUMBER_KEY = "number";
    public static final int SENT_PENDING_INTENT_REQUEST_CODE = 1997;
    public static final int DELIVERY_PENDING_INTENT_REQUEST_CODE = 1998;
    public static final String BACK_BUTTON_TAG = "back_button";
    public static final String NAV_BUTTON_TAG = "nav_button";

    public static final Object[][] APP_SETTING = {
            /* 0 */{"show_profile_photos", true},
            /* 1 */{"assign_contact_photos", false},
            /* 2 */{"notification_group", true},
            /* 3 */{"show_blocked_sms", false},
            /* 4 */{"show_messages", true},
            /* 5 */{"delivery_status", false},
            /* 6 */{"delivery_sound", false},
            /* 7 */{"send_long_messages_as_mms", true},
            /* 8 */{"auto_compress_mms_attachments", 300},
            /* 9 */{"font_size", R.string.normal},
            /* 10 */{"popup_reply", true},
            /* 11 */{"tap_to_dismiss", false},
            /* 12 */{"notification_previews", R.string.show_name_and_message},
            /* 13 */{"button_1", R.string.mark_read},
            /* 14 */{"button_2", R.string.reply},
            /* 15 */{"button_3", R.string.none},
            /* 16 */{"delete_old_messages_automatically", R.string.delete_old_messages_automatically},
    };
}
