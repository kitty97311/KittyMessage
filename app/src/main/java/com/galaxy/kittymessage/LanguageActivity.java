package com.galaxy.kittymessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LanguageActivity extends AppCompatActivity {

    private String selectedTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ListView languageListView = findViewById(R.id.languageListView);
        LanguageListAdapter lla = new LanguageListAdapter();
        languageListView.setAdapter(lla);
        selectedTag = AppHelper.GetCurrentLanguage(this).equals("not_set")
                ? AppConstant.LANGUAGE_ARRAY[0][2] : AppHelper.GetCurrentLanguage(this);
        languageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0 ; i < languageListView.getChildCount(); i ++) {
                    ImageView itemView = languageListView.getChildAt(i).findViewById(R.id.checkRadioButton);
                    itemView.setImageResource(R.drawable.img_unchecked);
                }
                ImageView radioButton = view.findViewById(R.id.checkRadioButton);
                radioButton.setImageResource(R.drawable.img_checked);
                selectedTag = view.getTag().toString();
            }
        });

        findViewById(R.id.okButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppHelper.SetCurrentLanguage(LanguageActivity.this, selectedTag);
                finish();
                startActivity(new Intent(LanguageActivity.this, MessagingActivity.class));
            }
        });

    }

    private class LanguageListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return AppConstant.LANGUAGE_ARRAY.length;
        }

        @Override
        public Object getItem(int position) {
            return AppConstant.LANGUAGE_ARRAY[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View itemView, ViewGroup parent) {
            if (itemView == null)
                itemView = LayoutInflater.from(LanguageActivity.this).inflate(R.layout.item_language, null);
            String[] item = AppConstant.LANGUAGE_ARRAY[position];
            itemView.setTag(item[2]);
            ImageView imageView = itemView.findViewById(R.id.flagImageView);
            imageView.setImageResource(Integer.parseInt(item[0]));
            TextView textView = itemView.findViewById(R.id.languageTextView);
            textView.setText(getResources().getString(Integer.parseInt(item[1])) + " (" + item[2] + ")");
            ImageView radioButton = itemView.findViewById(R.id.checkRadioButton);
            if (item[2].equals(selectedTag)) {
                radioButton.setImageResource(R.drawable.img_checked);
            } else {
                radioButton.setImageResource(R.drawable.img_unchecked);
            }
            return itemView;
        }
    }
}
