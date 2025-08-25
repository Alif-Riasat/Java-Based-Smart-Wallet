/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package axia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerBtn, backBtn;

    public RegisterFrame() {
        setTitle("Create Account - AXIA");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 10, 10));

        usernameField = new JTextField();
        phoneField = new JTextField();
        passwordField = new JPasswordField();

        registerBtn = new JButton("Register");
        backBtn = new JButton("Back to Login");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Phone Number:"));
        add(phoneField);
        add(new JLabel("Password:"));
        add(passwordField);

        add(registerBtn);
        add(backBtn);

        registerBtn.addActionListener(e -> registerUser());
        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String phone = phoneField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, phone, password, balance) VALUES (?, ?, ?, 0.0)";//Insert in database with initial 00 balance
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, phone);
            stmt.setString(3, password);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose();
            new LoginFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
