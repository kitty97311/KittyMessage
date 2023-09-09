package com.galaxy.kittymessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotificationPreviewsFragment extends BaseFragment {

    private static MessagingActivity context;

    public NotificationPreviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_notification_previews, container, false);
        context = (MessagingActivity) getContext();
        if (context != null) {
            context.setTitleText(R.string.notification_previews);
            context.setNavbar(true, false);
        }
        view.findViewById(R.id.settingButton1).setOnClickListener(this);
        view.findViewById(R.id.settingButton2).setOnClickListener(this);
        view.findViewById(R.id.settingButton3).setOnClickListener(this);
        view.findViewById(R.id.settingButton4).setOnClickListener(this);
        view.findViewById(R.id.settingButton5).setOnClickListener(this);
        view.findViewById(R.id.settingButton6).setOnClickListener(this);
        view.findViewById(R.id.settingButton7).setOnClickListener(this);

        view.findViewById(R.id.settingSwitch6).setBackgroundResource(AppHelper.GetPopupReply(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        view.findViewById(R.id.settingSwitch7).setBackgroundResource(AppHelper.GetTapToDismiss(context) ? R.drawable.img_switch_on : R.drawable.img_switch_off);
        ((TextView)view.findViewById(R.id.settingText2)).setText(AppHelper.GetNotificationPreviewsSetting(context));
        ((TextView)view.findViewById(R.id.settingText3)).setText(AppHelper.GetButton1Setting(context));
        ((TextView)view.findViewById(R.id.settingText4)).setText(AppHelper.GetButton2Setting(context));
        ((TextView)view.findViewById(R.id.settingText5)).setText(AppHelper.GetButton3Setting(context));

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

        } else if (view.getId() == R.id.settingButton2) {

        } else if (view.getId() == R.id.settingButton3) {

        } else if (view.getId() == R.id.settingButton4) {

        } else if (view.getId() == R.id.settingButton5) {

        } else if (view.getId() == R.id.settingButton6) {
            view.findViewById(R.id.settingSwitch6).setBackgroundResource(AppHelper.GetPopupReply(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetPopupReply(context, !AppHelper.GetPopupReply(context));
        } else if (view.getId() == R.id.settingButton7) {
            view.findViewById(R.id.settingSwitch7).setBackgroundResource(AppHelper.GetTapToDismiss(context) ? R.drawable.img_switch_off : R.drawable.img_switch_on);
            AppHelper.SetTapToDismiss(context, !AppHelper.GetTapToDismiss(context));
        }
    }
}