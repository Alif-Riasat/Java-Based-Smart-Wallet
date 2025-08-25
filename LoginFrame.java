package axia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    //Private Variable Declaration
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private JCheckBox showPasswordCheck;
    private JLabel titleLabel, subtitleLabel;

    //Login Frame Colors
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);//Ocean Blue
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);//Sky Blue
    private final Color SUCCESS_COLOR = new Color(39, 174, 96);//Green
    private final Color DANGER_COLOR = new Color(231, 76, 60);//Red
    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);//Gray
    private final Color TEXT_COLOR = new Color(44, 62, 80);//Charcoal
    private final Color FIELD_COLOR = Color.WHITE;

    public LoginFrame() {
        //All method Declaration
        initializeComponents();
        setupLayout();
        setupStyling();
        setupEventHandlers();

        setTitle("AXIA");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.LIGHT_GRAY));
        setVisible(true);
    }
    //Swing Components
    private void initializeComponents() {
        titleLabel = new JLabel("AXIA", SwingConstants.CENTER);
        subtitleLabel = new JLabel("Secure Financial Management", SwingConstants.CENTER);
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        showPasswordCheck = new JCheckBox("Show Password");
        loginBtn = new JButton("Sign In");
        registerBtn = new JButton("Create Account");
    }
    
    
    //Setting Layout
    private void setupLayout() {
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel headerPanel = createHeaderPanel();
        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel footerPanel = createFooterPanel();

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(footerPanel);

        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);

        JLabel iconLabel = new JLabel("💳", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitleLabel);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(TEXT_COLOR);
        formPanel.add(userLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setForeground(TEXT_COLOR);
        formPanel.add(passLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        formPanel.add(passwordField, gbc);

        // Show password
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        formPanel.add(showPasswordCheck, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(loginBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(registerBtn);
        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        JLabel footerLabel = new JLabel("© 2024 Smart Wallet - Secure & Reliable");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(Color.GRAY);
        panel.add(footerLabel);
        return panel;
    }

    private void setupStyling() {
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(PRIMARY_COLOR);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        styleTextField(usernameField, "Enter your username");
        stylePasswordField(passwordField, "Enter your password");

        showPasswordCheck.setBackground(BACKGROUND_COLOR);
        showPasswordCheck.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPasswordCheck.setForeground(TEXT_COLOR);

        styleButton(loginBtn, PRIMARY_COLOR, Color.WHITE, true);
        styleButton(registerBtn, FIELD_COLOR, PRIMARY_COLOR, false);
    }

    private void styleTextField(JTextField field, String placeholder) {
        field.setPreferredSize(new Dimension(300, 45));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(FIELD_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_COLOR);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }
        });
    }

    private void stylePasswordField(JPasswordField field, String placeholder) {
        field.setPreferredSize(new Dimension(300, 45));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(FIELD_COLOR);
        field.setForeground(Color.GRAY);
        field.setText(placeholder);
        field.setEchoChar((char) 0);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_COLOR);
                    field.setEchoChar('*');//**** if password is selected not visible
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
            }
        });
    }

    private void styleButton(JButton button, Color bgColor, Color textColor, boolean isPrimary) {
        button.setPreferredSize(new Dimension(300, 45));
        button.setMaximumSize(new Dimension(300, 45));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(!isPrimary);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (isPrimary) {
            button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        } else {
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    BorderFactory.createEmptyBorder(10, 18, 10, 18)
            ));
        }

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(isPrimary ? SECONDARY_COLOR : BACKGROUND_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
    }

    private void setupEventHandlers() {
        showPasswordCheck.addActionListener(e -> {
            if (showPasswordCheck.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });
//User Name and Password actionlistener
loginBtn.addActionListener(e -> {
    String username = usernameField.getText();
    String password = String.valueOf(passwordField.getPassword());

    if (username.equals("Enter your username")) username = "";
    if (password.equals("Enter your password")) password = "";

    if (username.isEmpty() || password.isEmpty()) {
        showStyledMessage("Please fill in all fields!", "Input Required", JOptionPane.WARNING_MESSAGE);
        return;
    }
//Checks User Name and Password from Database
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            double userBalance = rs.getDouble("balance");
            dispose();
            new WalletUI(username, userBalance);
        } else {
            showStyledMessage("Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    } catch (Exception ex) {
        showStyledMessage("Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});
//Create Account from Login Page
registerBtn.addActionListener(e -> {
    dispose();//Close the Page           
    new RegisterFrame();//Register Frame Launch
});

        KeyAdapter enterKey = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginBtn.doClick();
                }
            }
        };
        usernameField.addKeyListener(enterKey);
        passwordField.addKeyListener(enterKey);
    }

    private void showStyledMessage(String msg, String title, int type) {
        JOptionPane.showMessageDialog(this, msg, title, type);//Error or Successful
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//get the look of system and launch the login frame
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
