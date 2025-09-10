package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class BookFlight extends JFrame implements ActionListener {
    JTextField tfName, tfPhone, tfaadhar, tfNationality, tfAddress;
    JTextField tfFlightName, tfFlightCode, tfTravelDate;
    JRadioButton rbMale, rbFemale;
    JButton addButton;

    public BookFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        // Passenger Details
        addLabelAndField("Name", 80, tfName = new JTextField());
        addLabelAndField("Nationality", 120, tfNationality = new JTextField());
        addLabelAndField("NID", 160, tfaadhar = new JTextField());
        addLabelAndField("Address", 200, tfAddress = new JTextField());

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(60, 240, 150, 25);
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblGender.setForeground(Color.BLUE);
        add(lblGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(220, 240, 70, 25);
        rbMale.setBackground(Color.WHITE);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(300, 240, 80, 25);
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        addLabelAndField("Phone", 280, tfPhone = new JTextField());

        // Flight Details
        addLabelAndField("Flight Name", 80, tfFlightName = new JTextField(), 400);
        addLabelAndField("Flight Code", 120, tfFlightCode = new JTextField(), 400);

        JLabel lblTravelDate = new JLabel("Travel Date (yyyy-MM-dd)");
        lblTravelDate.setBounds(400, 160, 200, 25);
        lblTravelDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTravelDate.setForeground(Color.BLUE);
        add(lblTravelDate);

        tfTravelDate = new JTextField();
        tfTravelDate.setBounds(600, 160, 100, 25);
        add(tfTravelDate);

        // Add Button
        addButton = new JButton("Add Customer");
        addButton.setBounds(220, 330, 150, 30);
        addButton.addActionListener(this);
        add(addButton);

        // Frame settings
        setSize(800, 450);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addLabelAndField(String label, int y, JTextField field) {
        addLabelAndField(label, y, field, 60);
    }

    private void addLabelAndField(String label, int y, JTextField field, int x) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 150, 25);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl.setForeground(Color.BLUE);
        add(lbl);

        field.setBounds(x + 160, y, 150, 25);
        add(field);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfName.getText();
        String nationality = tfNationality.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String nid = tfaadhar.getText();
        String gender = rbMale.isSelected() ? "Male" : (rbFemale.isSelected() ? "Female" : "");
        String flightName = tfFlightName.getText();
        String flightCode = tfFlightCode.getText();
        String travelDate = tfTravelDate.getText();

        if (!travelDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Please enter travel date in yyyy-MM-dd format.");
            return;
        }

        try {
            Conn con = new Conn();
            String query = "INSERT INTO passenger (name, nationality, gender, phone, aadhar, address, flight_name, flight_code, travel_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.c.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, nationality);
            pst.setString(3, phone);
            pst.setString(4, address);
            pst.setString(5, nid);
            pst.setString(6, gender);
            pst.setString(7, flightName);
            pst.setString(8, flightCode);
            pst.setString(9, travelDate);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not save customer");
        }
    }

    public static void main(String[] args) {
        new BookFlight();  // Fixed constructor call
    }
}
