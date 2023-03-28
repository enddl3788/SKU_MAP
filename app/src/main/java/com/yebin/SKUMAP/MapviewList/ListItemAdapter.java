package com.yebin.SKUMAP.MapviewList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yebin.SKUMAP.MapviewActivity;
import com.yebin.SKUMAP.R;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {

    ArrayList<ListItem> items = new ArrayList<ListItem>();
    Context context;
    String clickName;
    Intent intent;

    public MapviewActivity mapviewActivity = new MapviewActivity();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        final ListItem listItem = items.get(position);

        // 생성자로부터 저장된 resourceId(listview_btn_item)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mapview_list_item, parent, false);
        }

        TextView nameText = convertView.findViewById(R.id.list_name);
        nameText.setText(listItem.getName());

        Button btn_list_info = (Button) convertView.findViewById(R.id.btn_list_info);
        btn_list_info.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickName = nameText.getText().toString();
                ((MapviewActivity) MapviewActivity.mContext).btn_clickEvent(clickName);
            }
        });

        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickName = nameText.getText().toString();
                mapviewActivity.removeMarker();
                ((MapviewActivity) MapviewActivity.mContext).clickEvent(clickName);
            }
        });

        return convertView;
    }
    public void addItem(ListItem item) {
        items.add(item);
    }
}