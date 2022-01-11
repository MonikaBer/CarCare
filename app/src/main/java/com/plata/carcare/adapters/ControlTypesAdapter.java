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
import com.plata.carcare.model.ControlType;

import java.util.ArrayList;

public class ControlTypesAdapter extends RecyclerView.Adapter<ControlTypesAdapter.ViewHolder> {

    private ArrayList<ControlType> controlTypes;
    private Context context;
    private int controlId;
    private boolean ifOnlyEdition;

    public ControlTypesAdapter(ArrayList<ControlType> controlTypes, Context context, int controlId, boolean ifOnlyEdition) {
        this.controlTypes = controlTypes;
        this.context = context;
        this.controlId = controlId;
        this.ifOnlyEdition = ifOnlyEdition;
    }

    @NonNull
    @Override
    public ControlTypesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_control_type, parent, false);
        return new ControlTypesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlTypesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.getControlTypeTV().setText(controlTypes.get(position).getType());

        holder.getControlTypeTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String controlType = controlTypes.get(position).getType();
                Intent intent = new Intent(context, AddControlActivity.class);  // return to add/edit control activity

                intent.putExtra("id", String.valueOf(controlId));
                intent.putExtra("ifOnlyEdition", String.valueOf(ifOnlyEdition));
                intent.putExtra("controlType", controlType);
                context.startActivity(intent);
            }
        });

        holder.getDeleteControlTypeFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlType toRemove = controlTypes.get(position);
                MainActivity.sqLiteHelper.deleteControlType(toRemove.getId());

                controlTypes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, controlTypes.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Usunięto typ kontroli", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return controlTypes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView controlTypeTV;
        private final FloatingActionButton deleteControlTypeFab;

        public ViewHolder(View itemView) {
            super(itemView);
            controlTypeTV = (TextView) itemView.findViewById(R.id.controlTypeTV);
            deleteControlTypeFab = (FloatingActionButton) itemView.findViewById(R.id.deleteControlTypeFab);
        }

        public TextView getControlTypeTV() {
            return controlTypeTV;
        }

        public FloatingActionButton getDeleteControlTypeFab() {
            return deleteControlTypeFab;
        }
    }
}
