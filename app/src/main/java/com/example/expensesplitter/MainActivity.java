package com.example.expensesplitter;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ExpenseAdapter expenseAdapter;
    ArrayList<Expense> expenseList;
    TextView tvTotalAndPerUser;
    CheckBox checkBoxUser1, checkBoxUser2, checkBoxUser3, checkBoxUser4;
    EditText etNameInput;
    Button btnAddName;
    List<String> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference expenseRef = database.getReference("expenses");

        //myRef.setValue("Hello, World!");


        tvTotalAndPerUser = findViewById(R.id.tvTotalAndPerUser);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);

        recyclerView = findViewById(R.id.rvExpenses);


        expenseList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(expenseAdapter);

        expenseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                expenseList.clear();
                for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()) {
                    Expense expense = expenseSnapshot.getValue(Expense.class);
                    expenseList.add(expense);
                }
                expenseAdapter.notifyDataSetChanged();
                updateTotals(expenseList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Failed to load expenses.", Toast.LENGTH_LONG).show();
            }
        });

        btnAddExpense.setOnClickListener(v -> {
            showAddExpenseDialog();
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateTotals(int numberOfUsers ) {
        double totalAmount = 0;

        // Calculate total amount from the list of expenses
        if (expenseList != null && !expenseList.isEmpty()) {
            for (Expense expense : expenseList) {
                totalAmount += expense.getAmount();
            }
        }




        // Check if the input is a valid number
        if (numberOfUsers!=0) {
            try {

                if (numberOfUsers <= 0) {
                    numberOfUsers = 1; // Reset to 1 if user enters 0 or negative
                }
            } catch (NumberFormatException e) {
                numberOfUsers = 1; // Reset to 1 if parsing fails
            }
        }

        // Calculate per-user amount
        double perUserAmount = totalAmount / numberOfUsers;

        // Update the TextView with the total and per-user amount
        tvTotalAndPerUser.setText("Total: ₹" + totalAmount + ", Per User: ₹" + perUserAmount);

    }

    private void showAddExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_expense, null);
        builder.setView(dialogView);

        EditText etAmount = dialogView.findViewById(R.id.etAmount);
        Spinner spinnerCategory = dialogView.findViewById(R.id.spinnerCategory);
        etNameInput=dialogView.findViewById(R.id.etNameInput);
        btnAddName = dialogView.findViewById(R.id.btnAddName);
        TextView tvUserList = dialogView.findViewById(R.id.tvUserList);


        btnAddName.setOnClickListener( v -> {
            String name = etNameInput.getText().toString().trim();
            if ( !name.isEmpty() ) {
                userList.add(name);
                etNameInput.setText("");
                tvUserList.setText("Users: " + userList.toString());
                Toast.makeText(MainActivity.this, "User Added", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Error adding user", Toast.LENGTH_LONG).show();
            }
        });

        builder.setTitle("Add Expense")
                .setPositiveButton("Add", (dialog, which) -> {
                    String category = spinnerCategory.getSelectedItem().toString();
                    String amount = etAmount.getText().toString();



                    int noofUsers=userList.size();

//                    int noofusers=0;
//                    if ( !amount.isEmpty()) {
//                        List<String> selectedUsers = new ArrayList<>();
//                        if ( checkBoxUser1.isChecked() ) {
//                            selectedUsers.add("Adarsh");
//                            noofusers++;
//                        }
//                        if ( checkBoxUser2.isChecked() ) {
//                            selectedUsers.add("Aayushi");
//                            noofusers++;
//                        }
//                        if ( checkBoxUser3.isChecked() ) {
//                            selectedUsers.add("Anshika");
//                            noofusers++;
//                        }
//                        if ( checkBoxUser4.isChecked() ) {
//                            selectedUsers.add("Bhavya");
//                            noofusers++;
//                        }
//
//                        Date timestamp = new Date();
//
//                        double peramount=Double.parseDouble(amount)/noofusers;
//
//                        Expense expense = new Expense(category, Double.parseDouble(amount), selectedUsers, timestamp, peramount);
//                        expenseList.add(expense);
//                        expenseAdapter.notifyDataSetChanged();
//                        updateTotals(noofusers);
//
//                        // Saving expense to Firebase
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference expenseRef = database.getReference("expenses").push(); // Unique key for each expense
//                        expenseRef.setValue(expense);
//                    }

                    Date timestamp = new Date();
                    double peramount = Double.parseDouble(amount)/noofUsers;

                    Expense expense = new Expense(category, Double.parseDouble(amount), userList, timestamp, peramount);
                    expenseList.add(expense);
                    expenseAdapter.notifyDataSetChanged();
                    updateTotals(noofUsers);

                    // Saving expense to Firebase
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference expenseRef = database.getReference("expenses").push(); // Unique key for each expense
                    expenseRef.setValue(expense);

                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}