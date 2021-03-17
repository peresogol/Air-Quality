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
                AlertDialog.Builder popUp = new AlertDialog.Builder(History.this);
                popUp.setTitle(R.string.popup_title);
                popUp.setMessage(R.string.popup_message);
                final EditText input = new EditText(History.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);
                Place place = (Place) parent.getItemAtPosition(position);
                input.setText(place.getPlaceName());
                popUp.setView(input);
                popUp.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox_Star);
                        cb.setChecked(!cb.isChecked());
                        place.setIsFavourite(cb.isChecked());
                        place.setPlaceName(input.getText().toString());
                        if (!db.updateData(place.getPlaceName(),place.getLongitude(),place.getLatitude(),place.getIsFavourite())) {
                            Toast.makeText(History.this, R.string.toast_rename_error,Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(History.this,getString(R.string.toast_rename_ok) + place.getPlaceName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                popUp.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(History.this,getString(R.string.toast_popup_when_canceled) + place.getPlaceName(), Toast.LENGTH_SHORT).show();
                    }
                });
                popUp.show();
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