package com.example.sporteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sporteam.R;
import com.example.sporteam.model.Event;
import java.util.List;


public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, List<Event> objects){
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext()
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = (convertView == null) ?
                inflater.inflate(R.layout.events_list_context, parent, false) :
                convertView;

        ImageView eventImage = item.findViewById(R.id.event_image);
        TextView eventSport = item.findViewById(R.id.event_title);
        TextView eventDate = item.findViewById(R.id.event_date);
        TextView eventTime = item.findViewById(R.id.event_time);
        TextView eventLocation = item.findViewById(R.id.event_location);
        TextView eventPrice = item.findViewById(R.id.event_price);

        eventImage.setImageResource(getItem(position).getEventImage());
        eventSport.setText(getItem(position).getEventSport());
        eventDate.setText(getItem(position).getEventDate());
        eventTime.setText(getItem(position).getEventTime());
        eventLocation.setText(getItem(position).getEventLocation());
        eventPrice.setText(getItem(position).getEventPrice());

        return item;
    }
}
