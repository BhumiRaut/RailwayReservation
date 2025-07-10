package railwayReservation.railwayReservation;

import java.sql.*;
import java.util.Scanner;

public class TicketService {

    public void bookTicket(Scanner sc) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Passenger Name: ");
            String name = sc.nextLine();
            System.out.print("Train ID: ");
            int trainId = sc.nextInt();
            System.out.print("Seats to Book: ");
            int seats = sc.nextInt();
            sc.nextLine();

            String check = "SELECT seat_available FROM train WHERE train_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(check);
            ps1.setInt(1, trainId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int available = rs.getInt(1);
                if (available >= seats) {
                    String book = "INSERT INTO ticket_booked (passenger_name, train_id, seats_booked) VALUES (?, ?, ?)";
                    PreparedStatement ps2 = conn.prepareStatement(book);
                    ps2.setString(1, name);
                    ps2.setInt(2, trainId);
                    ps2.setInt(3, seats);
                    ps2.executeUpdate();

                    String update = "UPDATE train SET seat_available = seat_available - ? WHERE train_id = ?";
                    PreparedStatement ps3 = conn.prepareStatement(update);
                    ps3.setInt(1, seats);
                    ps3.setInt(2, trainId);
                    ps3.executeUpdate();

                    System.out.println("Ticket booked successfully!!!");
                } else if(available==0)
                {
                	System.out.println("All seats are already booked.");
                }
                else {
                    System.out.println(" Only " + available + " seats are available. Cannot book " + seats + " seats.");
                }
            } else {
                System.out.println(" Train not found...");
            }
        }
    }

    public void viewTickets() throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM ticket_booked";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            System.out.println("Booked Tickets:");
            while (rs.next()) {
                System.out.printf("Ticket ID: %d | Passenger: %s | Train ID: %d | Seats: %d\n",
                        rs.getInt("ticket_id"),
                        rs.getString("passenger_name"),
                        rs.getInt("train_id"),
                        rs.getInt("seats_booked"));
            }
        }
    }

    public void cancelTicket(Scanner sc) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Ticket ID to cancel: ");
            int ticketId = sc.nextInt();

            String get = "SELECT train_id, seats_booked FROM ticket_booked WHERE ticket_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(get);
            ps1.setInt(1, ticketId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int trainId = rs.getInt("train_id");
                int seats = rs.getInt("seats_booked");

                String delete = "DELETE FROM ticket_booked WHERE ticket_id = ?";
                PreparedStatement ps2 = conn.prepareStatement(delete);
                ps2.setInt(1, ticketId);
                ps2.executeUpdate();

                String update = "UPDATE train SET seat_available = seat_available + ? WHERE train_id = ?";
                PreparedStatement ps3 = conn.prepareStatement(update);
                ps3.setInt(1, seats);
                ps3.setInt(2, trainId);
                ps3.executeUpdate();

                System.out.println(" Ticket cancelled!!!");
            } else {
                System.out.println(" Ticket not found...");
            }
        }
    }
}

