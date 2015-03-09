package com.aloknath.totango.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aloknath.totango.Objects.ParsedJsonListObject;
import com.aloknath.totango.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ALOKNATH on 3/8/2015.
 */
public class CustomerAdapter extends ArrayAdapter<ParsedJsonListObject> {
    private Context context;
    private List<ParsedJsonListObject> items;
    TextView textView;

    public CustomerAdapter(Context context, int resource,  List<ParsedJsonListObject> items) {
        super(context, resource , items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.custom_adapter_layout, parent, false);

        ParsedJsonListObject item = getItem(position);

        textView = (TextView)convertView.findViewById(R.id.title);
        textView.setText(item.getDisplayName());

        textView = (TextView)convertView.findViewById(R.id.time_duration);
        textView.setText(String.valueOf(getDate((long)item.getTime(), "dd/MM/yyyy")));

        textView = (TextView)convertView.findViewById(R.id.amount);
        textView.setText("$"+String.valueOf((item.getSalary()/1000.0))+"K");

        textView = (TextView)convertView.findViewById(R.id.engagement_number);
        textView.setText(String.valueOf((int)(item.getEngagedUsers()))+"/100");

        return convertView;
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
