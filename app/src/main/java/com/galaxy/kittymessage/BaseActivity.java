package com.galaxy.kittymessage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setTitleText(String s) {
        ((TextView) findViewById(R.id.titleText)).setText(s);
    }

    protected void setTitleText(int i) {
        ((TextView) findViewById(R.id.titleText)).setText(getResources().getString(i));
    }

    protected void setSubTitleText(String s) {
        ((TextView) findViewById(R.id.subTitleText)).setText(s);
    }

    protected void setSubTitleText(int i) {
        ((TextView) findViewById(R.id.subTitleText)).setText(getResources().getString(i));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            finish();
        }
    }

}
