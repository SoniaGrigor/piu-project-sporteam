package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sporteam.adapter.ConversationAdapter;
import com.example.sporteam.model.MemberData;
import com.example.sporteam.service.MessageService;

import java.util.List;

public class ConversationsActivity extends AppCompatActivity {

    private ListView conversationsView;
    private ConversationAdapter conversationAdapter;
    public List<MemberData> memberDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        conversationsView = (ListView) findViewById(R.id.conversations_view);
        memberDataList = MessageService.initConversations();

        conversationAdapter = new ConversationAdapter(this);
        conversationAdapter.addAll(memberDataList);
        conversationsView.setAdapter(conversationAdapter);
        registerForContextMenu(conversationsView);
    }
}
