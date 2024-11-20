# Expense Splitter App

The **Expense Splitter App** is a handy Android application designed to help roommates manage shared expenses efficiently. The app tracks expenses, splits bills among users, and syncs data in real-time using Firebase.

---

## Features

- **Dynamic User Addition**: Users can be added dynamically in the "Add Expense" dialog box.
- **Expense Categorization**: Supports multiple expense categories (e.g., Rent, Groceries, Utilities).
- **Per-User Expense Calculation**: Automatically calculates the per-user share of the total expense.
- **Real-Time Database**: Data is synced across devices using Firebase Realtime Database.
- **Date & Time Tracking**: Each expense is tagged with the date and time of entry.
- **User-Friendly Interface**: Intuitive design with support for RecyclerView and dialogs.

---

## Screenshots

![Home Screen](path_to_home_screen_image.png)
![Add Expense Dialog](path_to_add_expense_dialog_image.png)

---

## How It Works

1. **Add Users**: In the "Add Expense" dialog box, users can add their names dynamically.
2. **Enter Expense Details**: Specify the amount, category, and select the users who are splitting the bill.
3. **Expense Calculation**: The app calculates the total and per-user amounts automatically.
4. **Firebase Sync**: All expense data is stored in Firebase Realtime Database, ensuring it is available anytime the app is accessed.

---

## Tech Stack

- **Java**: Primary language for app development.
- **Firebase Realtime Database**: For storing and syncing data in real-time.
- **Android Studio**: IDE for development and testing.

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/expense-splitter.git
