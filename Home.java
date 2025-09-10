package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    // Declare menu items as instance variables if you want to access them in actionPerformed
    JMenuItem flightDetails, customerDetails, reservationDetails, bookFlight, journeyDetails, ticketCancellation;

    public Home() {
        setLayout(null);

        // Load background image
        ImageIcon i1 = new ImageIcon(getClass().getResource("/airlinemanagement/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1600, 800);
        add(image);

        // Heading label
        JLabel heading = new JLabel("AIR BANGLADESH WELCOMES YOU");
        heading.setBounds(500, 40, 1000, 40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36));
        image.add(heading); // Add heading on top of the image

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Details menu
        JMenu details = new JMenu("Details");
        menuBar.add(details);

        flightDetails = new JMenuItem("Flight Details");
        details.add(flightDetails);

        customerDetails = new JMenuItem("Add Customer Details");
        details.add(customerDetails);

        reservationDetails = new JMenuItem("Reservation Details");
        details.add(reservationDetails);

        bookFlight = new JMenuItem("Book Flight");
        details.add(bookFlight);

        journeyDetails = new JMenuItem("Journey Details");
        details.add(journeyDetails);

        ticketCancellation = new JMenuItem("Cancel Ticket");
        details.add(ticketCancellation);

        // Ticket menu (you can add items here as needed)
        JMenu ticket = new JMenu("Ticket");
        menuBar.add(ticket);

        // Add action listeners
        flightDetails.addActionListener(this);
        customerDetails.addActionListener(this);
        reservationDetails.addActionListener(this);
        bookFlight.addActionListener(this);
        journeyDetails.addActionListener(this);
        ticketCancellation.addActionListener(this);

        // Maximize the window
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Airline Management System - Home");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        switch (command) {
            case "Flight Details":
                JOptionPane.showMessageDialog(this, "Flight Details clicked.");
                break;
            case "Add Customer Details":
                JOptionPane.showMessageDialog(this, "Add Customer Details clicked.");
                break;
            case "Reservation Details":
                JOptionPane.showMessageDialog(this, "Reservation Details clicked.");
                break;
            case "Book Flight":
                JOptionPane.showMessageDialog(this, "Book Flight clicked.");
                break;
            case "Journey Details":
                JOptionPane.showMessageDialog(this, "Journey Details clicked.");
                break;
            case "Cancel Ticket":
                JOptionPane.showMessageDialog(this, "Cancel Ticket clicked.");
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}

