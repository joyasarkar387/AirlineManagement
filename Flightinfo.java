package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Flightinfo extends JFrame {

    public Flightinfo() {
        setTitle("Flight Information");
        setSize(800, 500);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("Flight Information", SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);

        // Table
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        // Load data from database
        try {
            Conn con = new Conn();
            ResultSet rs = con.s.executeQuery("SELECT * FROM flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading flight data.");
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Flightinfo();
    }
}
