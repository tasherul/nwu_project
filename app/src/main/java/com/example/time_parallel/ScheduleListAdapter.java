package com.example.time_parallel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScheduleListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Schedul> mProductList;

    public ScheduleListAdapter(Context mContext, List<Schedul> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_product_list, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        TextView tvDescription = (TextView)v.findViewById(R.id.tv_description);
        //Set text for TextView
        tvName.setText(mProductList.get(position).getTitle());
        tvPrice.setText(String.valueOf(mProductList.get(position).getWeekly())  );
        tvDescription.setText(mProductList.get(position).getStartTimeEndTime());

        //Save product id to tag
        v.setTag(mProductList.get(position).getID());

        return v;
    }
}
