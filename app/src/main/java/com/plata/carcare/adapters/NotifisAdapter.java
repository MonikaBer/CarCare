package com.plata.carcare.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.plata.carcare.R;
import com.plata.carcare.activities.MainActivity;
import com.plata.carcare.model.Notifi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NotifisAdapter extends RecyclerView.Adapter<NotifisAdapter.ViewHolder> {

    private ArrayList<Notifi> notifis;
    private Context context;

    public NotifisAdapter(ArrayList<Notifi> notifis, Context context) {
        this.notifis = notifis;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_notifi, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String type = String.valueOf(notifis.get(position).getType());
        holder.getTypeTV().setText(type);
        holder.getNameTV().setText(String.valueOf(notifis.get(position).getName()));

        if (!type.equals("SEASON_CHANGE")) {
            // calculate mileage diff
            // get current mileage from DB
            Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CAR");
            if (cursor == null)
                return;

            int currMileage = -1;

            int count = 1;
            while (cursor.moveToNext() && count == 1) {
                currMileage = cursor.getInt(2);
                ++count;
            }

            if (currMileage == -1) {
                Toast.makeText(context, "Błąd przy odczycie przebiegu z bazy", Toast.LENGTH_SHORT).show();
                return;
            }

            int time = Integer.parseInt(String.valueOf(notifis.get(position).getTime()));
            long diff = currMileage - time;
            if (diff > 0) {
                holder.getTypeTV().setBackgroundColor(R.color.colorRed);
                holder.getDiffTV().setText("od " + diff + " km");
            } else {
                holder.getTypeTV().setBackgroundColor(R.color.colorGreen);
                holder.getDiffTV().setText("za " + Math.abs(diff) + " km");
            }
        } else {
            // calculate days diff
            String time = String.valueOf(notifis.get(position).getTime());
            Long days = null;
            try {
                days = new SimpleDateFormat("dd-mm-yyyy").parse(time).getTime() / (1000 * 60 * 60* 24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long currDays = (long) Math.floor(Double.parseDouble(String.valueOf(System.currentTimeMillis())) / (1000 * 60 * 60* 24));
            long diff = currDays - days;

            if (diff > 0) {
                holder.getTypeTV().setBackgroundColor(R.color.colorRed);
                holder.getTypeTV().setText("od " + diff + " dni");
            } else {
                holder.getTypeTV().setBackgroundColor(R.color.colorGreen);
                holder.getTypeTV().setText("za " + Math.abs(diff) + " dni");
            }
        }

        holder.getDeleteNotifiItemFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notifi toRemove = notifis.get(position);
                MainActivity.sqLiteHelper.deleteNotifi(toRemove.getId());

                notifis.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, notifis.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Usunięto powadomienie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifis.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView typeTV;
        private final TextView nameTV;
        private final TextView diffTV;
        private final FloatingActionButton deleteNotifiItemFab;

        public ViewHolder(View itemView) {
            super(itemView);
            typeTV = (TextView) itemView.findViewById(R.id.notifiItemTypeTV);
            nameTV = (TextView) itemView.findViewById(R.id.notifiItemNameTV);
            diffTV = (TextView) itemView.findViewById(R.id.notifiItemDiffTV);
            deleteNotifiItemFab = (FloatingActionButton) itemView.findViewById(R.id.deleteNotifiItemFab);
        }

        public TextView getTypeTV() {
            return typeTV;
        }

        public TextView getNameTV() {
            return nameTV;
        }

        public TextView getDiffTV() {
            return diffTV;
        }

        public FloatingActionButton getDeleteNotifiItemFab() {
            return deleteNotifiItemFab;
        }
    }
}
