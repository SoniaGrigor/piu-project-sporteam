package com.example.sporteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sporteam.R;
import com.example.sporteam.model.MemberData;

import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends BaseAdapter {

    List<MemberData> memberDataList = new ArrayList<>();

    Context context;

    public ConversationAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<MemberData> memberDataList) {
        this.memberDataList.addAll(memberDataList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return memberDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.conversation, null);

        TextView textViewName = view.findViewById(R.id.nameConversation);
        ImageView imageView = view.findViewById(R.id.avatarConversation);
        MemberData memberData = memberDataList.get(position);
        textViewName.setText(memberData.getName());
        imageView.setImageDrawable(context.getResources().getDrawable(memberData.getImage()));
        return view;
    }
}
