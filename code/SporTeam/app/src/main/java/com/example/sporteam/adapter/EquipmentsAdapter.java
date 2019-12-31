package com.example.sporteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sporteam.EquipmentDetailsActivity;
import com.example.sporteam.EquipmentsActivity;
import com.example.sporteam.R;
import com.example.sporteam.model.Equipment;

import java.util.List;

public class EquipmentsAdapter extends ArrayAdapter<Equipment> {

    public EquipmentsAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final int pos = position;

        LayoutInflater inflater	= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View myRow = (convertView == null)
                ? inflater.inflate(R.layout.equipment_list_element, parent, false)
                : convertView;

        ImageView image = myRow.findViewById(R.id.equipmentImage);
        image.setImageResource(getItem(position).getImage());

        TextView name = myRow.findViewById(R.id.equipmentText);
        name.setText(getItem(position).getName());

        Button eq = myRow.findViewById(R.id.equipmentDetailsButton);

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                final Equipment eq = (Equipment) listView.getItemAtPosition(position);
                Intent intent = new Intent(v.getContext(), EquipmentDetailsActivity.class);
                intent.putExtra("equipment", eq);
                v.getContext().startActivity(intent);
            }
        });

        return	myRow;
    }

}
