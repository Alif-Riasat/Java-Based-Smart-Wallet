package axia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class WalletUI extends JFrame {
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);//Blue
    private final Color BACKGROUND_COLOR = new Color(248, 249, 250);//White
    private final Color TEXT_COLOR = new Color(44, 62, 80);//Charcoal
    private final JLabel balanceLabel = new JLabel("৳ 0.00", SwingConstants.RIGHT);//Initial balance

    private double balance = 0.0;
    private String username;
    private final java.util.List<String> history = new ArrayList<>();//Collection Array List

    public WalletUI(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;

        setTitle("AXIA - Home");
        setSize(500, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        mainPanel.add(createHeader(username), BorderLayout.NORTH);
        mainPanel.add(createGridPanel(), BorderLayout.CENTER);
        mainPanel.add(createFooter(), BorderLayout.SOUTH);
        mainPanel.add(createCollaborationPanel(), BorderLayout.PAGE_END);

        add(mainPanel);
        setVisible(true);

        balanceLabel.setText("৳ " + String.format("%.2f", balance));
    }

    private JPanel createHeader(String username) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(20, 20, 20, 20));
        header.setBackground(PRIMARY_COLOR);

        JLabel welcome = new JLabel("Welcome, " + username);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        welcome.setForeground(Color.WHITE);

        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        balanceLabel.setForeground(Color.WHITE);

        header.add(welcome, BorderLayout.WEST);
        header.add(balanceLabel, BorderLayout.EAST);

        return header;
    }

    private JPanel createGridPanel() {
        JPanel grid = new JPanel(new GridLayout(5, 2, 20, 20));
        grid.setBorder(new EmptyBorder(30, 30, 30, 30));
        grid.setBackground(BACKGROUND_COLOR);

        grid.add(createGridItem("💰", "Cash In", this::handleCashIn));
        grid.add(createGridItem("💸", "Cash Out", this::handleCashOut));
        grid.add(createGridItem("🎟", "E-Ticket", this::openETicketOptions));
        grid.add(createGridItem("🎁", "Charity", this::openCharityWindow));
        grid.add(createGridItem("💳", "Payment", this::openPaymentOptions));
        grid.add(createGridItem("👑", "Gold Investment", () -> openWebLink("https://www.bajus.org/gold-price")));
        grid.add(createGridItem("🎤", "Concert", () -> openWebLink("https://tickify.live/events/")));
        grid.add(createGridItem("💱", "Money Exchange", () -> openWebLink("https://www.bb.org.bd/en/index.php/econdata/exchangerate")));
        grid.add(createGridItem("📜", "History", this::showTransactionHistory));
        grid.add(createGridItem("⚙", "Settings", this::openSettings));

        return grid;
    }

    private JPanel createGridItem(String emoji, String title, Runnable action) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(20, 10, 20, 10)));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel icon = new JLabel(emoji, SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(TEXT_COLOR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(icon);
        panel.add(Box.createVerticalStrut(10));
        panel.add(label);

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });

        return panel;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel ad = new JLabel("📢 Sponsored by AdTech Inc. Click here");
        ad.setForeground(Color.BLUE);
        ad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ad.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        ad.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openWebLink("https://www.adtech.com");
            }
        });

        footer.add(ad);
        return footer;
    }

    private JPanel createCollaborationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel label = new JLabel("<html><div style='text-align: center;'>"
                + "<b>Sponsored by Bangladesh University of Professionals</b><br>"
                + "Join ICT Department for a remarkable future<br>"
                + "<a href='https://bup.edu.bd/department_home/3'>https://bup.edu.bd/department_home/3</a>"
                + "</div></html>", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(TEXT_COLOR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openWebLink("https://bup.edu.bd/department_home/3");
            }
        });

        JLabel remark = new JLabel(" July 36– Remembering Our Heroes ", SwingConstants.CENTER);
        remark.setFont(new Font("Segoe UI", Font.BOLD, 13));
        remark.setForeground(new Color(178, 34, 34));
        remark.setAlignmentX(Component.CENTER_ALIGNMENT);
        remark.setBorder(new EmptyBorder(10, 0, 0, 0));

        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(remark);

        return panel;
    }

    private void handleCashIn() {
        String atmCard = JOptionPane.showInputDialog(this, "Enter your ATM card number (e.g. 12345/24):");
        if (atmCard == null || atmCard.trim().isEmpty()) {
            showError("ATM card number cannot be empty.");
            return;
        }

        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash in:");
        if (!isValidAmount(amountStr)) {
            showError("Invalid amount. Enter between ৳500 - ৳50000");
            return;
        }
        double amt = Double.parseDouble(amountStr);

        balance += amt;
        history.add("Cash In: +৳" + amt + " (ATM: " + atmCard + ")");
        balanceLabel.setText("৳ " + String.format("%.2f", balance));
        updateBalanceInDB();
        JOptionPane.showMessageDialog(this, "Successfully cashed in ৳" + amt + " using ATM card " + atmCard);
    }

    private void handleCashOut() {
        Object[] options = {"Bank", "Agent"};
        int choice = JOptionPane.showOptionDialog(this, "Choose withdrawal method:", "Cash Out",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            String[] banks = {"DBBL", "BRAC Bank", "City Bank", "Dutch Bangla Bank", "Islami Bank"};
            String selectedBank = (String) JOptionPane.showInputDialog(this,
                    "Select Bank:", "Bank Selection",
                    JOptionPane.PLAIN_MESSAGE, null, banks, banks[0]);

            if (selectedBank == null || selectedBank.trim().isEmpty()) {
                showError("Bank selection is required.");
                return;
            }

            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash out:");
            if (!isValidAmount(amountStr)) {
                showError("Invalid amount. Enter between ৳500 - ৳50000");
                return;
            }
            double amt = Double.parseDouble(amountStr);
            double charge = amt * 0.01;
            double total = amt + charge;

            if (total > balance) {
                showError("Insufficient balance (includes 10% charge: ৳" + String.format("%.2f", charge) + ")");
                return;
            }

            balance -= total;
            history.add("Cash Out: -৳" + amt + " (Bank: " + selectedBank + ", Charge: ৳" + String.format("%.2f", charge) + ")");
            balanceLabel.setText("৳ " + String.format("%.2f", balance));
            updateBalanceInDB();
            JOptionPane.showMessageDialog(this, "Cash Out ৳" + amt + " via " + selectedBank + "\nCharge Applied: ৳" + String.format("%.2f", charge));

        } else if (choice == 1) {
            String agentName = JOptionPane.showInputDialog(this, "Enter Agent Name:");
            if (agentName == null || agentName.trim().isEmpty()) {
                showError("Agent name cannot be empty.");
                return;
            }

            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to cash out:");
            if (!isValidAmount(amountStr)) {
                showError("Invalid amount. Enter between ৳500 - ৳50000");
                return;
            }
            double amt = Double.parseDouble(amountStr);
            double charge = amt * 0.10;
            double total = amt + charge;

            if (total > balance) {
                showError("Insufficient balance (includes 10% charge: ৳" + String.format("%.2f", charge) + ")");
                return;
            }

            balance -= total;
            history.add("Cash Out: -৳" + amt + " (Agent: " + agentName + ", Charge: ৳" + String.format("%.2f", charge) + ")");
            balanceLabel.setText("৳ " + String.format("%.2f", balance));
            updateBalanceInDB();
            JOptionPane.showMessageDialog(this, "Cash Out ৳" + amt + " via Agent " + agentName + "\nCharge Applied: ৳" + String.format("%.2f", charge));
        }
    }
        private void openETicketOptions() {
        Object[] options = {"Train Ticket", "Flight Ticket", "Bus Ticket"};
        int choice = JOptionPane.showOptionDialog(this, "Choose ticket type:", "E-Ticket",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> openWebLink("https://eticket.railway.gov.bd/");
            case 1 -> openWebLink("https://www.biman-airlines.com/");
            case 2 -> openWebLink("https://busbd.com.bd/");
        }
    }

    private void openCharityWindow() {
        Object[] charities = {
                "BRAC", "JAAGO Foundation", "Bidyanondo", "Shakti Foundation",
                "CRP Bangladesh", "Save the Children BD", "UNICEF Bangladesh",
                "DSK", "Aporajeyo Bangladesh", "Leedo"
        };
        String selected = (String) JOptionPane.showInputDialog(this,
                "Select a charity organization:", "Donate",
                JOptionPane.PLAIN_MESSAGE, null, charities, charities[0]);

        if (selected != null) {
            String amount = JOptionPane.showInputDialog(this, "Enter donation amount (৳):");
            if (amount != null && !amount.isEmpty()) {
                double amt = Double.parseDouble(amount);
                balance -= amt;
                history.add("Donation: -৳" + amt + " to " + selected);
                updateBalanceInDB();
                JOptionPane.showMessageDialog(this, "Donated ৳" + amt + " to " + selected + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void openPaymentOptions() {
        Object[] payments = {"Electricity Bill", "Utility Bill", "Grocery", "Education Fee", "Tax", "Passport Fee"};
        String selected = (String) JOptionPane.showInputDialog(this,
                "Select a bill to pay:", "Bill Payment",
                JOptionPane.PLAIN_MESSAGE, null, payments, payments[0]);

        if (selected != null) {
            String id = JOptionPane.showInputDialog(this, "Enter your account ID:");
            String amount = JOptionPane.showInputDialog(this, "Enter amount to pay (৳):");

            if (id != null && amount != null && !amount.isEmpty()) {
                double amt = Double.parseDouble(amount);
                balance -= amt;
                history.add("Payment: -৳" + amt + " for " + selected + " (ID: " + id + ")");
                updateBalanceInDB();
                JOptionPane.showMessageDialog(this, "Paid ৳" + amt + " for " + selected + " (ID: " + id + ")!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showTransactionHistory() {
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No transactions yet.", "History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String record : history) {
            sb.append(record).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        textArea.setBackground(new Color(245, 245, 245));

        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openSettings() {
        showHelpline();
    }

    private void showHelpline() {
        String message = "📧 Developer Support:\n\n" +
                "1. alifborno@gmail.com\n" +
                "2. amdadulhaque1203@gmail.com";
        JOptionPane.showMessageDialog(this, message, "Helpline", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isValidAmount(String input) {
        try {
            double amt = Double.parseDouble(input);
            return amt >= 500 && amt <= 50000;
        } catch (Exception e) {
            return false;
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void openWebLink(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                showError("Browser not supported on this system.");
            }
        } catch (Exception e) {
            showError("Unable to open link: " + url);
        }
    }

    private void updateBalanceInDB() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE users SET balance = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, balance);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            showError("Failed to update balance in database.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WalletUI("Alif_Riasat", 0.0)); // Load actual balance if needed
    }
}
