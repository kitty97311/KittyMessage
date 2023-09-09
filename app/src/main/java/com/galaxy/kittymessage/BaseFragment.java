package com.galaxy.kittymessage;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment implements View.OnClickListener {

    protected BaseFragment lastFragment, curFragment;

    public BaseFragment() {
    }

    @Override
    public void onClick(View v) {

    }
}
