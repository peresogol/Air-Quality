package com.example.qualitair;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
        setContentView(R.layout.history);
        this.db = new SQLClient(this);
        this.viewData();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox_Star);
                cb.setChecked(!cb.isChecked());
                Place data = (Place) listView.getItemAtPosition(position);
                data.setIsFavourite(cb.isChecked());
                if (cb.isChecked()) {
                    Toast.makeText(History.this,"" + data.getPlaceName() + " a été rajouté aux favoris", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(History.this,"" + data.getPlaceName() + " a été enlevé des favoris", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewData() {
        Cursor cursor = db.viewData();
        this.listData = this.getListData();
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

    private List<Place> getListData() {
        List<Place> listData = new ArrayList<>();
        Place data1 = new Place("Toulouse", "44.44", "44.44", true);
        Place data2 = new Place("Cahors", "33.33", "33.33", true);
        Place data3 = new Place("Vierzon", "22.22", "22.22", false);
        listData.add(data1);
        listData.add(data2);
        listData.add(data3);
        return listData;
    }

}