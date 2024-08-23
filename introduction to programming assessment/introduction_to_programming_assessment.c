#include <stdio.h>

int main() {

    int choice,quantity;
    float  itemTotal,itemPrice,totalBill = 0;
    char moreItems;

    do {
        printf("\n----------Menu:--------------\n");
        printf("1. Pizza - 180\n");
        printf("2. Burger - 100\n");
        printf("3. Dosa - 120\n");
        printf("4. Idli - 50\n");
        printf("5. Exit\n");
         printf("\n-----------------------------\n");
        printf("Please enter your choice (1-5): ");
        scanf("%d", &choice);

        switch(choice) {
            case 1: 
            itemPrice = 180.00; printf("\nYou have selected Pizza."); 
            break;
            case 2: 
            itemPrice = 100.00; printf("You have selected Burger.\n"); 
            break;
            case 3:
             itemPrice = 120.00; printf("\nYou have selected Dosa."); 
             break;
            case 4: 
            itemPrice = 50.00; printf("You have selected Idli.\n"); 
            break;
            case 5:
            printf("Exiting... Thank you for your order.\n");
            break;
            default:
             printf("Invalid choice. Please select a valid item.\n");




        }

        printf("Enter the quantity: ");
        scanf("%d", &quantity);

        if (quantity < 0) {
            printf("Quantity cannot be negative. Please try again.\n");


        }
         itemTotal = itemPrice * quantity;

        totalBill = totalBill+itemTotal;

        printf("Amount for this item: %f\n", itemTotal);

        printf("Total Amount is: %f\n", totalBill);

    
        printf("Do you want to place more orders? (y/n): ");
        scanf(" %c", &moreItems);

    } while (moreItems == 'y' || moreItems == 'Y');


    printf("Your final total bill is: %f\n", totalBill);

    return 0;
}
