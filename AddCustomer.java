package airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.net.URL;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField tfName, tfPhone, tfaadhar, tfNationality, tfAddress;
    JRadioButton rbMale, rbFemale;
    JButton addButton, saveButton;

    public AddCustomer() {
        // Frame setup
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(150, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 28));
        heading.setForeground(Color.BLUE);
        add(heading);

        // Input Fields
        addLabelField("Name", tfName = new JTextField(), 80);
        addLabelField("Nationality", tfNationality = new JTextField(), 120);
        addLabelField("NID", tfaadhar = new JTextField(), 160);  // Corresponds to nid in DB
        addLabelField("Address", tfAddress = new JTextField(), 200);

        // Gender Section
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

        // Phone
        addLabelField("Phone", tfPhone = new JTextField(), 280);

        // Add Customer Button
        addButton = new JButton("Add Customer");
        addButton.setBounds(220, 330, 150, 30);
        addButton.addActionListener(this);
        add(addButton);

        // Save Button (Clears the form)
        saveButton = new JButton("Back");
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBounds(220, 380, 150, 30);
        add(saveButton);

        saveButton.addActionListener(e -> {
            tfName.setText("");
            tfPhone.setText("");
            tfaadhar.setText("");
            tfNationality.setText("");
            tfAddress.setText("");
            rbMale.setSelected(false);
            rbFemale.setSelected(false);
        });

       

        // Finalize Frame
        setSize(600, 600);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper to add label and field
    private void addLabelField(String label, JTextField field, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(60, y, 150, 25);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl.setForeground(Color.BLUE);
        add(lbl);

        field.setBounds(220, y, 150, 25);
        add(field);
    }

    // Add Customer button logic
    public void actionPerformed(ActionEvent ae) {
        String name = tfName.getText();
        String nationality = tfNationality.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String nid = tfaadhar.getText();  // using `nid` now
        String gender = "";

        if (rbMale.isSelected()) {
            gender = "Male";
        } else if (rbFemale.isSelected()) {
            gender = "Female";
        }

        try {
            Conn con = new Conn(); // Your connection class
            String query = "INSERT INTO passenger (name, nationality, phone, address, nid, gender) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.c.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, nationality);
            pst.setString(3, phone);
            pst.setString(4, address);
            pst.setString(5, nid);
            pst.setString(6, gender);
          
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not save customer");
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}

