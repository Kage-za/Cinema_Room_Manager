package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int numberOfRows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int numberOfSeats = sc.nextInt();
        String[][] cinemaArray = new String[numberOfRows][numberOfSeats];
        for (int i = 0; i < cinemaArray.length; i++) {
            for (int j = 0; j < cinemaArray[i].length; j++) {
                cinemaArray[i][j] = "S";
            }
        }
        menu(numberOfRows, numberOfSeats, cinemaArray);
    }

    public static int menu(int numberOfRows, int numberOfSeats, String[][] cinemaArray) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int userInput = sc.nextInt();
        while (userInput >= 4 ) {
            System.out.println("\nWrong input!\n");
            menu(numberOfRows,numberOfSeats, cinemaArray);
        }
        switch (userInput) {
            case 0:
                return 0;
            case 1:
                showTheSeats(numberOfRows, numberOfSeats, cinemaArray);
                break;
            case 2:
                buyATicket(numberOfRows, numberOfSeats, cinemaArray);
                break;
            case 3:
                statistics(numberOfRows, numberOfSeats, cinemaArray);
                break;
            default:
                System.out.println("Wrong input!");
                buyATicket(numberOfRows, numberOfSeats, cinemaArray);
            }

        return userInput;
    }

    private static void statistics(int numberOfRows, int numberOfSeats, String[][] cinemaArray) {
        int cinemaSize = numberOfRows * numberOfSeats;
        int noOfTicketsPurchased = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        
        for (int i = 0; i < cinemaArray.length; i++) {
            for (int j = 0; j < cinemaArray[i].length; j++) {
                if (cinemaArray[i][j] == "B") {
                    noOfTicketsPurchased +=1;
                    int rowNumber = i+1;
                    currentIncome += ticketPricing(numberOfRows, numberOfSeats, rowNumber, cinemaArray);
                }
            }
        }
        double percentageOfTickets = noOfTicketsPurchased / (double)cinemaSize * 100;

        if (cinemaSize > 60) {
            int temp =  numberOfRows / 2;
            int frontVal = temp * numberOfSeats * 10;
            int backVal = (cinemaSize -(temp *  numberOfSeats))  * 8;
            totalIncome = frontVal + backVal;
        }else {
            totalIncome = 10 * cinemaSize;
        }

        System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f%%\nCurrent income: $%d\nTotal income: $%d\n", noOfTicketsPurchased, percentageOfTickets, currentIncome, totalIncome);
        menu(numberOfRows,numberOfSeats, cinemaArray);
    }


    private static void buyATicket(int numberOfRows, int numberOfSeats, String[][] cinemaArray) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter a row number:");
        int rowNumber = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = sc.nextInt();
        if (rowNumber <= 0 || rowNumber > cinemaArray.length) {
            System.out.println("\nWrong input!");
            buyATicket(numberOfRows, numberOfSeats, cinemaArray);
        } if (seatNumber <= 0 || seatNumber > numberOfSeats) {
            System.out.println("\nWrong input!");
            buyATicket(numberOfRows, numberOfSeats, cinemaArray);
        }

        System.out.println("\nTicket price: $" + ticketPricing(numberOfRows, numberOfSeats, rowNumber,cinemaArray));
        bookedSeats(numberOfRows,numberOfSeats,cinemaArray, seatNumber, rowNumber);
        showTheSeats(numberOfRows, numberOfSeats,cinemaArray);
        menu(numberOfRows,numberOfSeats, cinemaArray);
    }

    private static int ticketPricing(int numberOfRows, int numberOfSeats, int rowNumber, String[][] cinemaArray) {
        int cinemaSize = numberOfRows * numberOfSeats;
        int pricePerTicket = 0;


        boolean backRow = false;
        if (rowNumber > cinemaArray.length / 2) {
            backRow = true;
        } else if (rowNumber < cinemaArray.length / 2) {
            backRow = false;
        }

        if (cinemaSize <= 60) {
            pricePerTicket = 10;
        } else if (backRow) {
            pricePerTicket = 8;
        } else if (!backRow){
            pricePerTicket = 10;
        }
    return pricePerTicket;
    }


    public static String[][] bookedSeats(int numberOfRows, int numberOfSeats,String[][] cinemaArray, int seatNumber, int rowNumber) {
        if (cinemaArray[rowNumber-1][seatNumber-1] != "B") {
            cinemaArray[rowNumber - 1][seatNumber - 1] = "B";
            return cinemaArray;
        } else {
            System.out.println("that ticket has already been purchased!");
           buyATicket(numberOfRows, numberOfSeats, cinemaArray);
        }
    return cinemaArray;
    }

    private static void showTheSeats( int numberOfRows, int numberOfSeats, String[][] cinemaArray) {
        System.out.println("\nCinema:");
        System.out.print(" ");
        int m = 0;
        for (int k = 0; k <= cinemaArray.length; k++) {
            m +=1;
            System.out.printf(" %d", m);
        }
        System.out.println();
        for (int i = 0; i < cinemaArray.length ; i++) {
            System.out.print(i + 1 );
            for (int j = 0; j < cinemaArray[i].length; j++) {
                System.out.print(" " + cinemaArray[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        menu(numberOfRows,numberOfSeats, cinemaArray);
    }
}





