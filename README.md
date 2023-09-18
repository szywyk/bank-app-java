# BankApp
## Project Description
"BankApp" is a simple console application written in Java that simulates basic banking operations. The project aims to showcase fundamental object-oriented programming concepts, exception handling, and unit testing in Java.

The application allows the user to:

- Create new bank accounts.
- Perform deposit and withdrawal operations.
- Transfer money between bank accounts.
- Check transaction history for a given account.
- Display user information and account balance.

## Project Structure
The "BankApp" application consists of several classes in *src/main/java*:

- **Bank** - a class responsible for managing bank accounts and financial operations.
- **BankAccount** - a class representing a bank account with deposit, withdrawal, and transaction history operations.
- **Transaction** - a class representing a single financial transaction with date, type, and other data.
- **User** - a class representing a user with personal data and a bank account.
- **BankApp** - a class with main method with a full scenario of creating users and making multiple operations on the accounts.

**Unit tests** classes in *src/test/java* which cover all classes in this project:
- **UserTest** (it also covers the whole code in Transaction class, because of strong realation between classes)
- **BankTest**
- **BankAccountTest**
