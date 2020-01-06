package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.sporteam.adapter.ConversationAdapter;
import com.example.sporteam.model.MemberData;
import com.example.sporteam.service.MessageService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ConversationsActivity extends AppCompatActivity {

    private ListView conversationsView;
    private ConversationAdapter conversationAdapter;
    public List<MemberData> memberDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(ConversationsActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(ConversationsActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(ConversationsActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(ConversationsActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        conversationsView = (ListView) findViewById(R.id.conversations_view);
        memberDataList = MessageService.initConversations();

        conversationAdapter = new ConversationAdapter(this);
        conversationAdapter.addAll(memberDataList);
        conversationsView.setAdapter(conversationAdapter);
        registerForContextMenu(conversationsView);
    }
}
