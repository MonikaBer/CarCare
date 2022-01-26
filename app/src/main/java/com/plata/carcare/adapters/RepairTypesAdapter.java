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
import com.plata.carcare.model.RepairType;
import java.util.ArrayList;

public class RepairTypesAdapter extends RecyclerView.Adapter<RepairTypesAdapter.ViewHolder> {

    private ArrayList<RepairType> repairTypes;
    private Context context;
    private int repairId;
    private boolean ifOnlyEdition;

    public RepairTypesAdapter(ArrayList<RepairType> repairTypes, Context context, int repairId, boolean ifOnlyEdition) {
        this.repairTypes = repairTypes;
        this.context = context;
        this.repairId = repairId;
        this.ifOnlyEdition = ifOnlyEdition;
    }

    @NonNull
    @Override
    public RepairTypesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repair_type, parent, false);
        return new RepairTypesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairTypesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.getRepairTypeTV().setText(repairTypes.get(position).getType());

        holder.getRepairTypeTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String repairType = repairTypes.get(position).getType();
                Intent intent = new Intent(context, AddRepairActivity.class);  // return to add/edit repair activity

                intent.putExtra("id", String.valueOf(repairId));
                intent.putExtra("ifOnlyEdition", String.valueOf(ifOnlyEdition));
                intent.putExtra("repairType", repairType);
                context.startActivity(intent);
            }
        });

        holder.getDeleteRepairTypeFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepairType toRemove = repairTypes.get(position);
                MainActivity.sqLiteHelper.deleteSeasonChangeType(toRemove.getId());

                repairTypes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, repairTypes.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Usunięto typ wymiany sezonowej", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return repairTypes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView repairTypeTV;
        private final FloatingActionButton deleteRepairTypeFab;

        public ViewHolder(View itemView) {
            super(itemView);
            repairTypeTV = (TextView) itemView.findViewById(R.id.repairTypeTV);
            deleteRepairTypeFab = (FloatingActionButton) itemView.findViewById(R.id.deleteRepairTypeFab);
        }

        public TextView getRepairTypeTV() {
            return repairTypeTV;
        }

        public FloatingActionButton getDeleteRepairTypeFab() {
            return deleteRepairTypeFab;
        }
    }
}
