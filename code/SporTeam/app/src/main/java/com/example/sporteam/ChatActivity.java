package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sporteam.adapter.MessageAdapter;
import com.example.sporteam.model.MemberData;
import com.example.sporteam.model.Message;
import com.example.sporteam.service.MessageService;

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
