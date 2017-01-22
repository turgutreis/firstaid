package com.example.turgutre1s.firstaidapp.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.turgutre1s.firstaidapp.jsonparser.Notfall;
import com.example.turgutre1s.firstaidapp.R;

import java.util.List;

/**
 * Created by TurgutRe1s on 10.11.2016.
 */

public class NotfallAdpater extends RecyclerView.Adapter<NotfallAdpater.ViewHolder> {

    private List<Notfall> notfaelle;

    // Konstruktor
    public NotfallAdpater(List<Notfall> data) {
        notfaelle = data;

    }


    @Override
    public NotfallAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View notfallView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_notfall_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(notfallView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NotfallAdpater.ViewHolder viewHolder, int position) {

        Notfall notfall = notfaelle.get(position);

        TextView nameView = viewHolder.notfallNameTextView;
        nameView.setText(notfall.getName());

        TextView vornameView = viewHolder.notfallVornameTextView;
        vornameView.setText(notfall.getVorname());

        TextView notfallPtsView = viewHolder.notfallPointsView;
        notfallPtsView.setText(""+notfall.getTelefon());
        viewHolder.telefonView = notfaelle.get(position).getTelefon();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cv;
        public TextView notfallNameTextView;
        public TextView notfallVornameTextView;
      //public TextView notfallDatumView;
        public TextView notfallPointsView;
        public int telefonView;


        public ViewHolder(View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setClickable(true);

            cv = (CardView) itemView.findViewById(R.id.card_view);
            notfallNameTextView = (TextView) cv.findViewById(R.id.tv_notfall_name);
            notfallVornameTextView = (TextView) cv.findViewById(R.id.tv_notfall_vorname);
            notfallPointsView = (TextView) cv.findViewById(R.id.tv_notfall_telefon);
        }

        @Override
        public void onClick(View view) {

        }
    }


    @Override
    public int getItemCount() {
        return notfaelle == null ? 0 : notfaelle.size();
    }


}
