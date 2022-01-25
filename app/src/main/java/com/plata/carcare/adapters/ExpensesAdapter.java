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
import com.plata.carcare.activities.AddExpenseActivity;
import com.plata.carcare.activities.MainActivity;
import com.plata.carcare.model.Control;
import com.plata.carcare.model.Expense;
import java.util.ArrayList;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {

    private ArrayList<Expense> expenses;
    private Context context;

    public ExpensesAdapter(ArrayList<Expense> expenses, Context context) {
        this.expenses = expenses;
        this.context = context;
    }

    @NonNull
    @Override
    public ExpensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_expense, parent, false);
        return new ExpensesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.getTimeTV().setText(String.valueOf(expenses.get(position).getMileage()));
        holder.getDescTV().setText(String.valueOf(expenses.get(position).getName()));

        holder.getDescTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idToEdit = expenses.get(position).getId();
                Intent intent = new Intent(context, AddExpenseActivity.class);  // start edit activity
                intent.putExtra("id", String.valueOf(idToEdit));
                intent.putExtra("ifOnlyEdition", "true");
                context.startActivity(intent);
            }
        });

        holder.getDeleteItemFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expense toRemove = expenses.get(position);
                MainActivity.sqLiteHelper.deleteExpense(toRemove.getId());

                expenses.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, expenses.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Usunięto poniesiony wydatek", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView timeTV;
        private final TextView descTV;
        private final FloatingActionButton deleteItemFab;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTV = (TextView) itemView.findViewById(R.id.expenseItemTimeTV);
            descTV = (TextView) itemView.findViewById(R.id.expenseItemDescTV);
            deleteItemFab = (FloatingActionButton) itemView.findViewById(R.id.deleteExpenseItemFab);
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
