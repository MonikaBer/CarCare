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
import com.plata.carcare.activities.AddControlActivity;
import com.plata.carcare.activities.MainActivity;
import com.plata.carcare.model.Control;
import java.util.ArrayList;

public class ControlsAdapter extends RecyclerView.Adapter<ControlsAdapter.ViewHolder> {

    private ArrayList<Control> controls;
    private Context context;

    public ControlsAdapter(ArrayList<Control> controls, Context context) {
        this.controls = controls;
        this.context = context;
    }

    @NonNull
    @Override
    public ControlsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_control, parent, false);
        return new ControlsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.getTimeTV().setText(String.valueOf(controls.get(position).getMileage()));
        holder.getDescTV().setText(String.valueOf(controls.get(position).getName()));

        holder.getDescTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idToEdit = controls.get(position).getId();
                Intent intent = new Intent(context, AddControlActivity.class);  // start edit activity
                intent.putExtra("id", String.valueOf(idToEdit));
                intent.putExtra("ifOnlyEdition", "true");
                context.startActivity(intent);
            }
        });

        holder.getDeleteItemFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Control toRemove = controls.get(position);
                MainActivity.sqLiteHelper.deleteControl(toRemove.getId());

                controls.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, controls.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Usunięto zrobioną kontrolę", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return controls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView timeTV;
        private final TextView descTV;
        private final FloatingActionButton deleteItemFab;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTV = (TextView) itemView.findViewById(R.id.controlItemTimeTV);
            descTV = (TextView) itemView.findViewById(R.id.controlItemDescTV);
            deleteItemFab = (FloatingActionButton) itemView.findViewById(R.id.deleteControlItemFab);
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
