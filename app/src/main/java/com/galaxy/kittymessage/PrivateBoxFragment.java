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

public class PrivateBoxFragment extends BaseFragment {

    private static List<MessageGroupModel> smsGroupList;

    private static RecyclerView smsGroupListView;

    private static MessagingActivity context;

    public PrivateBoxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_private_box, container, false);
        context = (MessagingActivity) getContext();
        if (context != null) {
            context.setTitleText(R.string.private_box);
            context.setNavbar(true, false);
        }

        smsGroupListView = view.findViewById(R.id.messageList);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshList();
    }

    public static void refreshList() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.addContactButton) {

        }
    }

    private static class SmsListAdapter extends RecyclerView.Adapter<SmsListAdapter.SmsGroupViewHolder> {

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        private OnItemClickListener listener;

        public SmsListAdapter(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public SmsGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SmsGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message_group, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SmsGroupViewHolder holder, int position) {
            MessageGroupModel item = smsGroupList.get(position);
            holder.contentView.setText(item.body);
            if (item.contactName == null) {
                holder.nameView.setText(item.number);
                holder.defaultView.setVisibility(View.VISIBLE);
            } else {
                holder.nameView.setText(item.contactName);
                if (item.photo == null) {
                    holder.contactView.setVisibility(View.VISIBLE);
                    holder.contactView.setText(String.valueOf(Character.toUpperCase(item.contactName.charAt(0))));
                } else {
                    holder.photoView.setVisibility(View.VISIBLE);
                    holder.photoView.setImageBitmap(item.photo);
                }
            }
            if (!item.isRead) {
                holder.nameView.setTypeface(null, Typeface.BOLD);
                holder.contentView.setTextColor(context.getResources().getColor(R.color.colorOne, context.getTheme()));
                holder.newBadge.setVisibility(View.VISIBLE);
            }
            String content = item.body.replace("\n", " ");
            if (item.type == 1) {
                holder.contentView.setText(content);
            } else if (item.type == 2) {
                holder.contentView.setText(context.getResources().getString(R.string.you_) + content);
            } else if (item.type == 3) {
                holder.contentView.setText(context.getResources().getString(R.string.you_) + content);
                holder.contentView.setTypeface(null, Typeface.ITALIC);
            }

            holder.dateView.setText(AppHelper.GetMessageGroupDate(context, item.date));

            final int p = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(p);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return smsGroupList.size();
        }

        private class SmsGroupViewHolder extends RecyclerView.ViewHolder {

            public CircleImageView photoView;
            public ImageView defaultView;
            public TextView contactView;
            public TextView nameView;
            public TextView contentView;
            public TextView dateView;
            public View newBadge;

            public SmsGroupViewHolder(@NonNull View itemView) {
                super(itemView);
                photoView = itemView.findViewById(R.id.photoImageView);
                defaultView = itemView.findViewById(R.id.defaultAvatarView);
                contactView = itemView.findViewById(R.id.contactAvatarView);
                nameView = itemView.findViewById(R.id.nameTextView);
                contentView = itemView.findViewById(R.id.contentTextView);
                dateView = itemView.findViewById(R.id.timeView);
                newBadge = itemView.findViewById(R.id.newBadge);
            }
        }
    }
}