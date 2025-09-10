package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cencel extends JFrame implements ActionListener {
    JTextField tfaadhar, tfFlightCode;
    JButton cancelButton;

    public Cencel() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Cancel Flight");
        heading.setBounds(350, 20, 400, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.RED);
        add(heading);

        // Aadhar Label + Field
        JLabel lblaadhar = new JLabel("NID");
        lblaadhar.setBounds(100, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblaadhar.setForeground(Color.RED);
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(280, 100, 200, 25);
        add(tfaadhar);

        // Flight Code Label + Field
        JLabel lblFlightCode = new JLabel("Flight Code");
        lblFlightCode.setBounds(100, 140, 150, 25);
        lblFlightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFlightCode.setForeground(Color.RED);
        add(lblFlightCode);

        tfFlightCode = new JTextField();
        tfFlightCode.setBounds(280, 140, 200, 25);
        add(tfFlightCode);

        // Cancel Button
        cancelButton = new JButton("Cancel Flight");
        cancelButton.setBounds(280, 190, 150, 30);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setSize(700, 350);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String aadhar = tfaadhar.getText();
        String flightCode = tfFlightCode.getText();

        if (aadhar.isEmpty() || flightCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both Aadhar Number and Flight Code.");
            return;
        }

        try {
            Conn conn = new Conn();
            // Delete record matching aadhar and flight code
            String query = "DELETE FROM passenger WHERE aadhar = '" + aadhar + "' AND flight_code = '" + flightCode + "'";
            int result = conn.s.executeUpdate(query);

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Flight Cancelled Successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No matching record found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not cancel flight.");
        }
    }

    public static void main(String[] args) {
        new Cencel();
    }
}
