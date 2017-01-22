package com.example.turgutre1s.firstaidapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.turgutre1s.firstaidapp.R;
import com.example.turgutre1s.firstaidapp.activitys.NavDrawerItem;

import java.util.Collections;
import java.util.List;

public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder> {
    private List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;

    public NavDrawerAdapter(Context context, List<NavDrawerItem> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        switch (current.getTitle().toLowerCase())
        {
            case "Notfälle":
                //holder.img.setImageResource(R.drawable.ic_audiotrack_light);
                break;
            case "friends":
                //holder.img.setImageResource(R.drawable.ic_audiotrack_light);
                break;
            case "Einsatzkarte":
                //holder.img.setImageResource(R.drawable.ic_audiotrack_light);
                break;
            default:
                //holder.img.setImageResource(R.drawable.ic_audiotrack_light);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}