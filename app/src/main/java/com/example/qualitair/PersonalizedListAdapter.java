package com.example.qualitair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class PersonalizedListAdapter extends BaseAdapter {

    private List<PersonalizedListData> listData;
    private LayoutInflater inflater;
    private Context context;

    public PersonalizedListAdapter(List<PersonalizedListData> listData, Context context) {
        this.listData = listData;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // voir p73
    }

    @Override
    public int getCount() {
        return this.listData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View retour = convertView;

        if (retour == null) {
            retour = inflater.inflate(R.layout.personalized_list, null);
        }

        TextView textView_Coordinates = (TextView) retour.findViewById(R.id.textView_Coordinates);
        TextView textView_Place_Name = (TextView) retour.findViewById(R.id.textView_Place_Name);
        CheckBox checkBox_Star = (CheckBox) retour.findViewById(R.id.checkBox_Star);
        textView_Coordinates.setText(this.listData.get(position).getGpsCoordinates());
        textView_Place_Name.setText(this.listData.get(position).getPlaceName());
        checkBox_Star.setChecked(this.listData.get(position).getIsFavourite());

        return retour;
    }
}

