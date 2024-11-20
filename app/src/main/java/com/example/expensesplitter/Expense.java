package com.example.expensesplitter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Expense {
    private String category;
    private double amount;
    private List<String> selectedUsers;
    private Date timestamp;
    private double peramount;

    public Expense() {
    }

    public  Expense(String category, double amount, List<String> selectedUsers, Date timestamp, double peramount) {
        this.category=category;
        this.amount=amount;
        this.selectedUsers=selectedUsers;
        this.timestamp=timestamp;
        this.peramount=peramount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public List<String> getSelectedUsers() {
        return selectedUsers;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public  double getPeramount() {
        return peramount;
    }

}
