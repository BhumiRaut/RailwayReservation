package railwayReservation.railwayReservation;

import java.sql.*;
import java.util.Scanner;

public class TrainService {

    public void addTrain(Scanner sc) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Train Name: ");
            String name = sc.nextLine();
            System.out.print("Source: ");
            String source = sc.nextLine();
            System.out.print("Destination: ");
            String dest = sc.nextLine();
            System.out.print("Seats Available: ");
            int seats = Integer.parseInt(sc.nextLine());

            String sql = "INSERT INTO train (name, train_source, train_destination, seat_available) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, source);
            ps.setString(3, dest);
            ps.setInt(4, seats);
            ps.executeUpdate();
            System.out.println("Train added successfully!!!");
        }
    }

    public void showTrains() throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM train";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            System.out.println("Train List:");
            while (rs.next()) {
                System.out.printf("ID: %d | %s - %s -> %s | Seats: %d\n",
                        rs.getInt("train_id"),
                        rs.getString("name"),
                        rs.getString("train_source"),
                        rs.getString("train_destination"),
                        rs.getInt("seat_available"));
            }
        }
    }
}
