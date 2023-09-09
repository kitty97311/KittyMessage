package com.galaxy.kittymessage;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsFragment extends BaseFragment {

    private static MessagingActivity context;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = (MessagingActivity) getContext();
        if (context != null) {
            context.setTitleText(R.string.settings);
            context.setNavbar(true, false);
        }
        view.findViewById(R.id.settingButton1).setOnClickListener(this);
        view.findViewById(R.id.settingButton2).setOnClickListener(this);
        view.findViewById(R.id.settingButton3).setOnClickListener(this);
        view.findViewById(R.id.settingButton4).setOnClickListener(this);
        view.findViewById(R.id.settingButton5).setOnClickListener(this);
        view.findViewById(R.id.settingButton6).setOnClickListener(this);
        view.findViewById(R.id.settingButton7).setOnClickListener(this);
        view.findViewById(R.id.settingButton8).setOnClickListener(this);
        view.findViewById(R.id.settingButton9).setOnClickListener(this);
        view.findViewById(R.id.settingButton10).setOnClickListener(this);
        view.findViewById(R.id.settingButton11).setOnClickListener(this);
        view.findViewById(R.id.settingButton12).setOnClickListener(this);
        view.findViewById(R.id.settingButton13).setOnClickListener(this);
        view.findViewById(R.id.settingButton15).setOnClickListener(this);
        view.findViewById(R.id.settingButton16).setOnClickListener(this);
        view.findViewById(R.id.settingButton17).setOnClickListener(this);

        view.findViewById(R.id.settingSwitch2).setBackgroundResource(AppHelper.GetShowProfilePhotosSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch3).setBackgroundResource(AppHelper.GetAssignContactPhotosSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch4).setBackgroundResource(AppHelper.GetNotificationGroupSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch5).setBackgroundResource(AppHelper.GetShowBlockedSmsSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch6).setBackgroundResource(AppHelper.GetShowMessagesSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch7).setBackgroundResource(AppHelper.GetDeliveryStatusSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch8).setBackgroundResource(AppHelper.GetDeliverySoundSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch10).setBackgroundResource(AppHelper.GetSendLongMessagesAsMmsSetting(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        ((TextView)view.findViewById(R.id.settingText11)).setText(AppHelper.GetAutoCompressMmsAttachmentsSetting(context) + " KB");
        ((TextView)view.findViewById(R.id.settingText12)).setText(AppHelper.GetFontSizeSetting(context));
        ((TextView)view.findViewById(R.id.settingText13)).setText(AppHelper.GetDeleteOldMessagesAutomaticallySetting(context));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.settingButton1) {
            // App Languages
            startActivity(new Intent(context, LanguageActivity.class));
        } else if (view.getId() == R.id.settingButton2) {
            // Show Profile Photos
            view.findViewById(R.id.settingSwitch2).setBackgroundResource(AppHelper.GetShowProfilePhotosSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetShowProfilePhotosSetting(context, !AppHelper.GetShowProfilePhotosSetting(context));
        } else if (view.getId() == R.id.settingButton3) {
            // Assign Contact Photos
            view.findViewById(R.id.settingSwitch3).setBackgroundResource(AppHelper.GetAssignContactPhotosSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetAssignContactPhotosSetting(context, !AppHelper.GetAssignContactPhotosSetting(context));
        } else if (view.getId() == R.id.settingButton4) {
            // Notification Group
            view.findViewById(R.id.settingSwitch4).setBackgroundResource(AppHelper.GetNotificationGroupSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetNotificationGroupSetting(context, !AppHelper.GetNotificationGroupSetting(context));
        } else if (view.getId() == R.id.settingButton5) {
            // Show Blocked SMS
            view.findViewById(R.id.settingSwitch5).setBackgroundResource(AppHelper.GetShowBlockedSmsSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetShowBlockedSmsSetting(context, !AppHelper.GetShowBlockedSmsSetting(context));
        } else if (view.getId() == R.id.settingButton6) {
            // Show Messages
            view.findViewById(R.id.settingSwitch6).setBackgroundResource(AppHelper.GetShowMessagesSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetShowMessagesSetting(context, !AppHelper.GetShowMessagesSetting(context));
        } else if (view.getId() == R.id.settingButton7) {
            // Delivery Status
            view.findViewById(R.id.settingSwitch7).setBackgroundResource(AppHelper.GetDeliveryStatusSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetDeliveryStatusSetting(context, !AppHelper.GetDeliveryStatusSetting(context));
        } else if (view.getId() == R.id.settingButton8) {
            // Delivery Sound
            view.findViewById(R.id.settingSwitch8).setBackgroundResource(AppHelper.GetDeliverySoundSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetDeliverySoundSetting(context, !AppHelper.GetDeliverySoundSetting(context));
        } else if (view.getId() == R.id.settingButton9) {
            // Notification Previews
            context.openPage(new NotificationPreviewsFragment());
        } else if (view.getId() == R.id.settingButton10) {
            // Send Long Messages as MMS
            view.findViewById(R.id.settingSwitch10).setBackgroundResource(AppHelper.GetSendLongMessagesAsMmsSetting(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetSendLongMessagesAsMmsSetting(context, !AppHelper.GetSendLongMessagesAsMmsSetting(context));
        } else if (view.getId() == R.id.settingButton11) {
            // Auto-compress MMS Attachments
        } else if (view.getId() == R.id.settingButton12) {
            // Suggestion in chat
        } else if (view.getId() == R.id.settingButton13) {
            // Font Size
        } else if (view.getId() == R.id.settingButton14) {
            // Delete Old Messages Automatically
        } else if (view.getId() == R.id.settingButton15) {
            // Help and FAQ
        } else if (view.getId() == R.id.settingButton16) {
            // Feedback
        } else if (view.getId() == R.id.settingButton17) {
            // About Messages
        }
    }
}