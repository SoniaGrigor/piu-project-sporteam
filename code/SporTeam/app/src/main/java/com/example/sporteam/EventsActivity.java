package com.example.sporteam;

import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.adapter.EventAdapter;
import com.example.sporteam.model.Event;
import com.example.sporteam.service.EventService;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private ListView listView;
    private EventAdapter eventAdapter;
    private EventService eventService;

    public int detailsPageShown = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstaceState){
       super.onCreate(savedInstaceState);
       setContentView(R.layout.activity_events_list);

       listView = findViewById(R.id.events_list);
       eventService = new EventService();
       List<Event> events = eventService.getEvents();

       eventAdapter = new EventAdapter(this, events);
       listView.setAdapter(eventAdapter);

       registerForContextMenu(listView);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
