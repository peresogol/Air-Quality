package com.example.qualitair;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class FavouriteCities extends AppCompatActivity {

    private ListView listView;
    private List<PersonalizedListData> listData;
    private PersonalizedListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        this.listData = getListData();
        this.listView = (ListView)findViewById(R.id.listView);
        this.adapter = new PersonalizedListAdapter(this.listData, this);
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox_Star);
                cb.setChecked(!cb.isChecked());
                PersonalizedListData data = (PersonalizedListData) listView.getItemAtPosition(position);
                data.setIsFavourite(cb.isChecked());
                if (cb.isChecked()) {
                    Toast.makeText(FavouriteCities.this,"" + data.getPlaceName() + " a été rajouté aux favoris", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FavouriteCities.this,"" + data.getPlaceName() + " a été enlevé des favoris", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private List<PersonalizedListData> getListData() {
        List<PersonalizedListData> listData = new ArrayList<>();
        PersonalizedListData data1 = new PersonalizedListData("Toulouse", "44.44, 44.44", true);
        PersonalizedListData data2 = new PersonalizedListData("Cahors", "33.33, 33.33", true);
        PersonalizedListData data3 = new PersonalizedListData("Vierzon", "22.22, 22.22", false);
        listData.add(data1);
        listData.add(data2);
        listData.add(data3);

        return listData;
    }
}
