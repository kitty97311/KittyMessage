package com.galaxy.kittymessage;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MessagingActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fm;
    private DrawerLayout mDrawerLayout;
    private Fragment curFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        fm = getSupportFragmentManager();

        findViewById(R.id.navButton).setOnClickListener(this);
        findViewById(R.id.settingButton).setOnClickListener(this);
        findViewById(R.id.unreadNavButton).setOnClickListener(this);
        findViewById(R.id.privateNavButton).setOnClickListener(this);
        findViewById(R.id.archivedNavButton).setOnClickListener(this);
        findViewById(R.id.scheduledNavButton).setOnClickListener(this);
        findViewById(R.id.starredNavButton).setOnClickListener(this);
        findViewById(R.id.blockNavButton).setOnClickListener(this);
        findViewById(R.id.backupNavButton).setOnClickListener(this);
        findViewById(R.id.themeNavButton).setOnClickListener(this);
        findViewById(R.id.swipeNavButton).setOnClickListener(this);
        findViewById(R.id.appearanceNavButton).setOnClickListener(this);
        findViewById(R.id.privacyNavButton).setOnClickListener(this);
        findViewById(R.id.inviteNavButton).setOnClickListener(this);
        findViewById(R.id.rateNavButton).setOnClickListener(this);

        mDrawerLayout = findViewById(R.id.drawerView);

        openPage(new MessagingFragment());
    }

    protected void setTitleText(String s) {
        ((TextView) findViewById(R.id.titleText)).setText(s);
    }

    protected void setTitleText(int i) {
        ((TextView) findViewById(R.id.titleText)).setText(getResources().getString(i));
    }

    protected void setNavbar(boolean showBack, boolean showSetting) {
        findViewById(R.id.navButton).setBackgroundResource(showBack ? R.drawable.btn_back : R.drawable.btn_nav);
        findViewById(R.id.navButton).setTag(showBack ? "back_button" : "nav_button");
        findViewById(R.id.settingButton).setVisibility(showSetting ? View.VISIBLE : View.INVISIBLE);
    }

    public void openPage(Fragment f) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.fragmentContainer, f).commit();
        curFragment = f;
    }

    public void closePage() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment f = new BaseFragment();
        if (curFragment instanceof MessagingFragment) {
            finish();
        }
        if (curFragment instanceof UnreadMessagesFragment
                || curFragment instanceof PrivateBoxFragment
                || curFragment instanceof EnterPasswordFragment
                || curFragment instanceof SettingsFragment
        ) {
            f = new MessagingFragment();
        } else if (curFragment instanceof NotificationPreviewsFragment) {
            f = new SettingsFragment();
        }
        ft.replace(R.id.fragmentContainer, f).commit();
        curFragment = f;
    }

    @Override
    public void onBackPressed() {
        closePage();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.navButton) {
            if (v.getTag().equals(AppConstant.NAV_BUTTON_TAG)) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            } else {
                onBackPressed();
            }
        } else if (v.getId() == R.id.settingButton) {
            openPage(new SettingsFragment());
        }
        else {
            if (v.getId() == R.id.unreadNavButton) {
                openPage(new UnreadMessagesFragment());
            } else if (v.getId() == R.id.privateNavButton) {
                openPage(new EnterPasswordFragment());
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
