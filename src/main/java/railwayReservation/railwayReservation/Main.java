package railwayReservation.railwayReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TrainService trainService = new TrainService();
        TicketService ticketService = new TicketService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Railway Reservation System ===");
            System.out.println("1. Add Train (Admin)");
            System.out.println("2. Show Trains");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Tickets");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // flush invalid input
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
            case 1:
                trainService.addTrain(sc);
                break;
            case 2:
                trainService.showTrains();
                break;
            case 3:
                ticketService.bookTicket(sc);
                break;
            case 4:
                ticketService.viewTickets();
                break;
            case 5:
                ticketService.cancelTicket(sc);
                break;
            case 6:
                System.exit(0);
                break;
                default:
                	System.out.println("Invalid Choice..");
            }
        }
    }
}
