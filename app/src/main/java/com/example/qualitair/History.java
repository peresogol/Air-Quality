package com.example.qualitair;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private ListView listView;
    private List<Place> listData;
    private PlacesListAdapter adapter;
    private SQLClient db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.db = new SQLClient(this);
        this.viewData();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = (Place) parent.getItemAtPosition(position);
                CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox_Star);
                cb.setChecked(!cb.isChecked());
                place.setIsFavourite(cb.isChecked());
                if (!db.updateData(place.getPlaceName(),place.getLongitude(),place.getLatitude(),place.getIsFavourite())) {
                    Toast.makeText(History.this, R.string.error_updating_favourites,Toast.LENGTH_SHORT);
                } else {
                    if (cb.isChecked()) {
                        Toast.makeText(History.this,"" + place.getPlaceName() + getString(R.string.well_deleted_favourites), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(History.this,"" + place.getPlaceName() + getString(R.string.added_to_favourites), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void viewData() {
        Cursor cursor = db.viewData();
        this.listData = new ArrayList<>();
        // if there is at least one result
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String longitude  = cursor.getString(2);
                String latitude = cursor.getString(3);
                boolean isFavourite = (cursor.getInt(4) == 1);
                Place data = new Place(name,longitude,latitude,isFavourite);
                this.listData.add(data);
                // while there is another next result
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this,"Il n'y a pas de valeurs à lire",Toast.LENGTH_SHORT).show();
        }
        this.listView = (ListView)findViewById(R.id.listView);
        this.adapter = new PlacesListAdapter(this.listData, this);
        this.listView.setAdapter(this.adapter);
    }

}