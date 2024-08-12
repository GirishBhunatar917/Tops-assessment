#include <stdio.h>

// Function prototypes
void displayMenu();
int getPrice(int choice);
void processOrder();

int main() {
    processOrder();
    return 0;
}

// Function to display the menu
void displayMenu() {
    printf("--------- Food Menu ---------\n");
    printf("1. Burger - $5\n");
    printf("2. Pizza - $8\n");
    printf("3. Pasta - $7\n");
    printf("4. Salad - $4\n");
    printf("5. Exit\n");
    printf("-----------------------------\n");
}

// Function to get the price based on the user's choice
int getPrice(int choice) {
    switch (choice) {
        case 1: return 5;  // Burger
        case 2: return 8;  // Pizza
        case 3: return 7;  // Pasta
        case 4: return 4;  // Salad
        default: return 0; // Invalid choice
    }
}

// Function to process the customer's order
void processOrder() {
    int choice, quantity, more = 1;
    int totalBill = 0;

    while (more) {
        displayMenu();
        printf("Enter the number of the item you want to order (1-5): ");
        scanf("%d", &choice);

        // Check if the user wants to exit
        if (choice == 5) {
            break;
        }

        // Get the price of the selected item
        int price = getPrice(choice);

        // Validate the choice
        if (price == 0) {
            printf("Invalid choice. Please select a valid item.\n");
            continue;
        }

        // Get the quantity of the selected item
        printf("Enter the quantity: ");
        scanf("%d", &quantity);

        // Update the total bill
        totalBill += price * quantity;

        // Ask the user if they want to select more items
        printf("Do you want to select more items? (1 for Yes, 0 for No): ");
        scanf("%d", &more);
    }

    // Display the final bill
    printf("Thank you for your order!\n");
    printf("Total Bill: $%d\n", totalBill);
}
