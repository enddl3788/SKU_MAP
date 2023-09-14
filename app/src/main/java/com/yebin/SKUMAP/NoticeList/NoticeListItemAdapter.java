package com.yebin.SKUMAP.NoticeList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yebin.SKUMAP.R;

import java.util.ArrayList;

public class NoticeListItemAdapter extends BaseAdapter {
    ArrayList<NoticeListItem> items = new ArrayList<NoticeListItem>();
    Context context;

    public int getCount() {
        return items.size();
    }

    public  Object getItem(int position) {
        return items.get(position);
    }

    public  long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext();
        NoticeListItem listItem = items.get(position);

        // 생성자로부터 저장된 resourceId(listview_btn_item)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.notice_list_item, parent, false);
        }

        TextView list_main = convertView.findViewById(R.id.notice_main_text);
        TextView list_sub = convertView.findViewById(R.id.notice_sub_text);

        list_main.setText(listItem.getMain());
        list_sub.setText(listItem.getSub());

        /*
        LinearLayout notice_listItem_background = convertView.findViewById(R.id.notice_listItem_background);
        notice_listItem_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listItem.getUrl()));
                context.startActivity(intent);
            }
        });
         */

        return convertView;
    }
    public void addItem(NoticeListItem item) { items.add(item); }
}
