package com.galaxy.kittymessage;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EnterPasswordFragment extends BaseFragment {

    private MessagingActivity context;
    private String password = "";
    private String firstPassword = "";
    private TextView passwordTitleText;
    private TextView[] passwordBoxGroup = new TextView[4];
    private View passwordPanel;

    public EnterPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_enter_password, container, false);
        context = (MessagingActivity) getContext();
        if (context != null) {
            context.setTitleText(R.string.private_box);
            context.setNavbar(true, false);
        }
        passwordTitleText = view.findViewById(R.id.passwordTitleText);
        Bundle bundle = getArguments();
        if (bundle != null) {
            firstPassword = bundle.getString(AppConstant.FIRST_PASSWORD_KEY);
            passwordTitleText.setText(R.string.confirm_your_password);
        }
        else if (AppHelper.GetCurrentPassword(context).equals(AppConstant.NOT_SET_KEY)) {
            passwordTitleText.setText(R.string.enter_new_password);
        } else {
            passwordTitleText.setText(R.string.enter_your_password);
        }
        passwordPanel = view.findViewById(R.id.passwordPanel);
        passwordBoxGroup[0] = view.findViewById(R.id.passwordBox1);
        passwordBoxGroup[1] = view.findViewById(R.id.passwordBox2);
        passwordBoxGroup[2] = view.findViewById(R.id.passwordBox3);
        passwordBoxGroup[3] = view.findViewById(R.id.passwordBox4);
        view.findViewById(R.id.button0).setOnClickListener(this);
        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);
        view.findViewById(R.id.button5).setOnClickListener(this);
        view.findViewById(R.id.button6).setOnClickListener(this);
        view.findViewById(R.id.button7).setOnClickListener(this);
        view.findViewById(R.id.button8).setOnClickListener(this);
        view.findViewById(R.id.button9).setOnClickListener(this);
        view.findViewById(R.id.buttonDel).setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setPasswordView() {
        for (TextView tv : passwordBoxGroup) {
            tv.setText("");
        }
        for (int i = 0; i < password.length(); i ++) {
            passwordBoxGroup[i].setText("*");
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.buttonDel) {
            if (!password.isEmpty()) {
                password = password.substring(0, password.length() - 1);
            }
            setPasswordView();
        } else {
            String p = ((Button)view).getText().toString();
            password += p;
            setPasswordView();
            if (password.length() == 4) {
                if (AppHelper.GetCurrentPassword(context).equals(AppConstant.NOT_SET_KEY)) {
                    if (firstPassword.isEmpty()) {
                        EnterPasswordFragment f = new EnterPasswordFragment();
                        Bundle b = new Bundle();
                        b.putString(AppConstant.FIRST_PASSWORD_KEY, password);
                        f.setArguments(b);
                        context.openPage(f);
                    } else if (password.equals(firstPassword)) {
                        AppHelper.SetCurrentPassword(context, password);
                        context.openPage(new PrivateBoxFragment());
                    } else {
                        password = "";
                        Animation shakeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_incorrect_password);
                        passwordPanel.startAnimation(shakeAnim);
                        setPasswordView();
                    }
                } else if (password.equals(AppHelper.GetCurrentPassword(context))) {
                    context.openPage(new PrivateBoxFragment());
                } else {
                    password = "";
                    Animation shakeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_incorrect_password);
                    passwordPanel.startAnimation(shakeAnim);
                    setPasswordView();
                }
            }
        }
    }

}