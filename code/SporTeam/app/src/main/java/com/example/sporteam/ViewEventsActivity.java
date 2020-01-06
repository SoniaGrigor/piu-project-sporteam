package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sporteam.adapter.EventAdapter;
import com.example.sporteam.model.Event;
import com.example.sporteam.service.EventService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class ViewEventsActivity extends AppCompatActivity {

    private ListView listView;
    private EventAdapter eventAdapter;
    private EventService eventService;
    private Button newEventButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_events_list);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(ViewEventsActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(ViewEventsActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(ViewEventsActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(ViewEventsActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        listView = findViewById(R.id.events_list);
        eventService = EventService.getInstance();
        List<Event> events = eventService.getEvents();

        eventAdapter = new EventAdapter(this, events);
        listView.setAdapter(eventAdapter);

        newEventButton = findViewById(R.id.new_event_button);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event currentItem = eventAdapter.getItem(position);
                String paymentMethod = currentItem.getEventPaymentMethod();

                if (paymentMethod.equals("Plată cu cardul")) {
                    Intent intent = new Intent(ViewEventsActivity.this, PaymentActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(ViewEventsActivity.this, "Felicitări! Te-ai alăturat cu succes la acest eveniment", Toast.LENGTH_SHORT).show();
                    currentItem.setEventMinimumPersonNumber(currentItem.getEventMinimumPersonNumber() - 1);
                    currentItem.setEventMmaximumPersonNumber(currentItem.getEventMmaximumPersonNumber() - 1);
                }
            }
        });

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewEventsActivity.this, CreateEventActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
        startActivity(getIntent());
    }

}
