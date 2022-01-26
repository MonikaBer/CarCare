package com.plata.carcare.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.plata.carcare.R;
import com.plata.carcare.activities.AddRepairActivity;
import com.plata.carcare.activities.MainActivity;
import com.plata.carcare.model.Repair;

import java.util.ArrayList;

public class RepairsAdapter extends RecyclerView.Adapter<RepairsAdapter.ViewHolder> {

    private ArrayList<Repair> repairs;
    private Context context;

    public RepairsAdapter(ArrayList<Repair> repairs, Context context) {
        this.repairs = repairs;
        this.context = context;
    }

    @NonNull
    @Override
    public RepairsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repair, parent, false);
        return new RepairsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.getTimeTV().setText(String.valueOf(repairs.get(position).getMileage()));
        holder.getDescTV().setText(String.valueOf(repairs.get(position).getName()));

        holder.getDescTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idToEdit = repairs.get(position).getId();
                Intent intent = new Intent(context, AddRepairActivity.class);  // start edit activity
                intent.putExtra("id", String.valueOf(idToEdit));
                intent.putExtra("ifOnlyEdition", "true");
                context.startActivity(intent);
            }
        });

        holder.getDeleteItemFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repair toRemove = repairs.get(position);
                MainActivity.sqLiteHelper.deleteSeasonChange(toRemove.getId());

                repairs.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, repairs.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Usunięto zrobioną wymianę sezonową", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return repairs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView timeTV;
        private final TextView descTV;
        private final FloatingActionButton deleteItemFab;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTV = (TextView) itemView.findViewById(R.id.repairItemTimeTV);
            descTV = (TextView) itemView.findViewById(R.id.repairItemDescTV);
            deleteItemFab = (FloatingActionButton) itemView.findViewById(R.id.deleteRepairItemFab);
        }

        public TextView getTimeTV() {
            return timeTV;
        }

        public TextView getDescTV() {
            return descTV;
        }

        public FloatingActionButton getDeleteItemFab() {
            return deleteItemFab;
        }
    }
}
