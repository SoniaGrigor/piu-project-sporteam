package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sporteam.adapter.MessageAdapter;
import com.example.sporteam.model.MemberData;
import com.example.sporteam.model.Message;
import com.example.sporteam.service.MessageService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {

    private ListView messagesView;
    private MessageAdapter messageAdapter;
    public List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(ChatActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(ChatActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(ChatActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(ChatActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(ChatActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        messagesView = (ListView) findViewById(R.id.messages_view);
        messageList = MessageService.initMessages();

        messageAdapter = new MessageAdapter(this);
        messageAdapter.addAll(messageList);
        messagesView.setAdapter(messageAdapter);
        registerForContextMenu(messagesView);
    }

    public void sendMessage(View view) {
//        String message = editText.getText().toString();
//        if (message.length() > 0) {
//            editText.getText().clear();
//        }
    }

}
