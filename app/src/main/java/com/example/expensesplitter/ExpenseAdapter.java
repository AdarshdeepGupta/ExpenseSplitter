package com.example.expensesplitter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private ArrayList<Expense> expenses;

    public ExpenseAdapter(ArrayList<Expense> expenses) {
        this.expenses=expenses;
    }


    @NonNull
    @Override
    public ExpenseAdapter.ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.tvCategory.setText(expense.getCategory());
        holder.tvAmount.setText(String.valueOf((expense.getAmount())));
        holder.tvUsers.setText("Shared by " + String.join(", ",expense.getSelectedUsers()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        holder.tvTimestamp.setText("Date: " + sdf.format(expense.getTimestamp()));
        holder.tvPeramount.setText("Per Amount : "+expense.getPeramount());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory, tvAmount, tvUsers, tvTimestamp, tvPeramount;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory=itemView.findViewById(R.id.tvCategory);
            tvAmount=itemView.findViewById(R.id.tvAmount);
            tvUsers=itemView.findViewById(R.id.tvUsers);
            tvTimestamp=itemView.findViewById(R.id.tvTimestamp);
            tvPeramount=itemView.findViewById(R.id.tvperUser);
        }
    }
}
