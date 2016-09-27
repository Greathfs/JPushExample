package com.example.hfs.jpushexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hfs.jpushexample.R;

import java.util.List;
import java.util.Map;

/**
 * Created by HFS on 2016/9/26.
 */

public class MyAdapter extends BaseAdapter {
    private List<Map<String, Object>> mList;
    private Context context;
    private ViewHolder viewHolder;

    /**
     * 构造函数
     *
     * @param context
     * @param data
     */
    public MyAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.mList = data;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 返回这个条目对应的控件对象
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 判断当前条目是否为null
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.list_item, null);
            viewHolder.txt_title = (TextView) convertView
                    .findViewById(R.id.txt_title);
            viewHolder.txt_message = (TextView) convertView
                    .findViewById(R.id.txt_message);
            viewHolder.txt_date = (TextView) convertView
                    .findViewById(R.id.txt_date);
            viewHolder.image_picture = (ImageView) convertView
                    .findViewById(R.id.image_picture);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Map<String, Object> map = mList.get(position);
        String message = map.get("message").toString();
        String key = map.get("key").toString();
        String date = map.get("date").toString();

        if (key.equals("apple")) {
            viewHolder.txt_title.setText("苹果");
            viewHolder.image_picture.setImageResource(R.mipmap.apple);
        } else if (key.equals("banana")) {
            viewHolder.txt_title.setText("香蕉");
            viewHolder.image_picture.setImageResource(R.mipmap.banana);
        } else if (key.equals("watermelon")) {
            viewHolder.txt_title.setText("西瓜");
            viewHolder.image_picture.setImageResource(R.mipmap.watermelon);
        } else if (key.equals("grape")) {
            viewHolder.txt_title.setText("葡萄");
            viewHolder.image_picture.setImageResource(R.mipmap.grape);
        }

        viewHolder.txt_message.setText(message);
        viewHolder.txt_date.setText(date);

        return convertView;
    }



    class ViewHolder {
        public TextView txt_title, txt_message, txt_date;
        public ImageView image_picture;

    }

}
