#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int string_reverse();
int string_concate();
int Palindrome();
int cop();
int string_length();
int frequency_characters();
int vowels_consonants();
int spaces_digits(); 
int main()

{
        int choice;
        char continueChoice;

    printf("\nmain menu");
    printf("\n--------------------------------\n");
    printf("\n1.reverse a string");
    printf("\n2.Concatenation");
    printf("\n3. Palindrome");
    printf("\n4.Copy a string");
    printf("\n5.Length of the string ");
    printf("\n6.Frequency of character in string ");
    printf("\n7.Find number of vowels and consonants");
    printf("\n8.Find number of blank spaces and digits");
    printf("\n9. you are exited");
    printf("\n--------------------------------\n");
    printf("Enter your choice :");
    scanf("%d",&choice);
    
    do{
            switch (choice) 
            {
            case 1:
                string_reverse();
                break;
            case 2:
                string_concate();
                break;
            case 3:
                if (Palindrome()) {
                    printf("Entered string is palindrome\n");
                } else {
                    printf("Entered string is not a palindrome\n");
                }
                break;
            case 4:
                cop();
                break;
            case 5:
                string_length();
                break;
            case 6:
                frequency_characters();
                break;
            case 7:
                vowels_consonants();
                break;
            case 8:
                spaces_digits();
                break;
            case 9:
                printf("Exited....\n");
                return 0;
            default:
                printf("Invalid choice\n");
        }

        printf("\nDo you want to continue (yes/no): ");
        scanf(" %c", &continueChoice);

    } while (continueChoice == 'y' || continueChoice == 'Y');

    printf("Exited...\n");
    return 0;
}
    
    
    int string_reverse()

{
    char str[100];
    printf("Enter the string :");
    scanf("%s",str);
    
    strrev(str);
    
    printf("\nstring after reverse :%s",str);
}

int string_concate()

{
    char str1[100],str2[100];
    
    printf("Enter your first :");
    scanf("%s",str1);
    printf("Enter your second :");
    scanf("%s",str2);
    
    strcat(str1,str2);

    printf("\nconcatenation of the string is :%s ",str1);

}
int Palindrome() 
{
    char str[100];
    
    printf("Enter the string: ");
    scanf("%s", str);
    
    int length = 0;
    while (str[length] != '\0') 
    {
        length++;
    }

    int start = 0;
    int end = length - 1;
    while (start < end) {
        if (str[start] != str[end]) 
        {
            return 0; 
        }
        start++;
        end--;
    }
    return 1;
}
 int cop()
{
    char str1[100],str2[100];

    printf("Enter the string:1:");
    scanf("%s",str1);
    
    strcpy(str2,str1);

    printf("\nstring after copy :string:2[%s]",str1);
}

int string_length()

{
    char str[100];
    int i,length=0;

    printf("Enter the string :");
    scanf("%s",str);

    for(i=0;str[i]!='\0';i++)
    {
        length++;
    }
    printf("Length of the string is %d",length);
}

int frequency_characters() {
    char str[100];
    int i,freq[256] = {0};
    
    printf("Enter the string: ");
    scanf("%s", str);
    
    
    for( i = 0; str[i] != '\0'; i++) 
    {
        freq[str[i]]++;
    }
    
    printf("Frequency of characters:\n");

    for(int i = 0; i < 256; i++) 
    {
        if(freq[i] != 0) 
        {
            printf("%c: %d\n", i, freq[i]);
        }
    }
    return 0;
}

int vowels_consonants() 
{
    char str[100];
    int vowels = 0, consonants = 0,i;

    printf("Enter the string: ");
    scanf("%s", str);

    
    for( i = 0; str[i] != '\0'; i++) 
    {
        if(str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u' ||
        str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U') 
        {
            vowels++;
        } else if((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z')) 
        {
            consonants++;
        }
    }
    printf("Number of vowels: %d\n", vowels);
    printf("Number of consonants: %d\n", consonants);
    return 0;
}

int spaces_digits() {
    char str[100];
    printf("Enter the string: ");
    scanf(" %[^\n]s", str);
    int spaces = 0, digits = 0, alpha = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] == ' ') {
            spaces++;
        } else if (str[i] >= '0' && str[i] <= '9') {
            digits++;
        } else if ((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z')) {
            alpha++;
        }
    }
    printf("Number of spaces: %d\n", spaces);
    printf("Number of digits: %d\n", digits);
    printf("Number of alphabets: %d\n", alpha);
}



