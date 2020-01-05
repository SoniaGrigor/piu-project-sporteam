package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.adapter.EventAdapter;
import com.example.sporteam.model.Event;
import com.example.sporteam.service.EventService;
import java.util.List;

public class ViewEventsActivity extends AppCompatActivity {

    private ListView listView;
    private EventAdapter eventAdapter;
    private EventService eventService;

    public int detailsPageShown = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstaceState){
       super.onCreate(savedInstaceState);
       setContentView(R.layout.activity_events_list);

       listView = findViewById(R.id.events_list);
       eventService = EventService.getInstance();
       List<Event> events = eventService.getEvents();

       eventAdapter = new EventAdapter(this, events);
       listView.setAdapter(eventAdapter);

       registerForContextMenu(listView);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewEventsActivity.this, PaymentActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
