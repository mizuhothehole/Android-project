package com.example.ishiiaya.homeaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ishiiaya on 2017/05/06.
 */

public class HomeIssueAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<HomeIssueEntity> issueList;

    public HomeIssueAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setIssueList(ArrayList<HomeIssueEntity> issueList) {
        this.issueList = issueList;
    }

    @Override
    public int getCount() {
        return issueList.size();
    }

    @Override
    public Object getItem(int position) {
        return issueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.valueOf(issueList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.homeissue_listview, parent, false);
        ((TextView) convertView.findViewById(R.id.issueDate)).
                setText(issueList.get(position).getDate());
        ((TextView) convertView.findViewById(R.id.issueTitle)).
                setText(String.valueOf(issueList.get(position).getTitle()));
        ((TextView) convertView.findViewById(R.id.issueAmount)).
                setText(String.valueOf(issueList.get(position).getAmount()));
        return convertView;
    }
}
