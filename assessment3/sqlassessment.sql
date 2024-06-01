use college;

-- Bank table
CREATE TABLE Bank (
    branch_id INT PRIMARY KEY,
    branch_name VARCHAR(255),
    branch_city VARCHAR(255)
);

-- Loan table
CREATE TABLE Loan (
    loan_no INT PRIMARY KEY,
    branch_id INT,
    account_holder_id INT,
    loan_amount DECIMAL(10, 2),
    loan_type VARCHAR(50),
    FOREIGN KEY (branch_id) REFERENCES Bank(branch_id)
);

-- Account holder table
CREATE TABLE AccountHolder (
    account_holder_id INT PRIMARY KEY,
    account_no INT,
    account_holder_name VARCHAR(255),
    city VARCHAR(255),
    contact VARCHAR(20),
    date_of_account_created DATE,
    account_status VARCHAR(10),
    account_type VARCHAR(50),
    balance DECIMAL(10, 2)
);



-- Sample transaction for transferring $100 from account A to account B
BEGIN TRANSACTION;
UPDATE AccountHolder SET balance = balance - 100 WHERE account_no = 'A'; -- Debit from account A
UPDATE AccountHolder SET balance = balance + 100 WHERE account_no = 'B'; -- Credit to account B
COMMIT;




SELECT account_no, account_holder_name 
FROM AccountHolder 
WHERE DAY(date_of_account_created) > 15;

INSERT INTO Bank (branch_id, branch_name, branch_city) VALUES
(1, 'Branch A', 'City A'),
(2, 'Branch B', 'City B'),
(3, 'Branch C', 'City A');


INSERT INTO Loan (loan_no, branch_id, account_holder_id, loan_amount, loan_type) VALUES
(101, 1, 1001, 5000.00, 'Personal Loan'),
(102, 2, 1002, 7000.00, 'Home Loan'),
(103, 1, 1003, 3000.00, 'Education Loan');


INSERT INTO AccountHolder (account_holder_id, account_no, account_holder_name, city, contact, date_of_account_created, account_status, account_type, balance) VALUES
(1001, 123456, 'John Doe', 'City A', '123-456-7890', '2022-01-01', 'Active', 'Savings', 5000.00),
(1002, 789012, 'Jane Smith', 'City B', '987-654-3210', '2022-02-15', 'Active', 'Current', 7000.00),
(1003, 456789, 'Alice Johnson', 'City A', '321-654-9870', '2022-03-20', 'Active', 'Savings', 3000.00);


SELECT * FROM AccountHolder WHERE city = 'city a';

SELECT ah.account_holder_id, ah.account_holder_name, l.branch_id, l.loan_amount
FROM AccountHolder ah
JOIN Loan l ON ah.account_holder_id = l.account_holder_id;




