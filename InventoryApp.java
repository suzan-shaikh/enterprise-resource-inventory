import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryApp extends JFrame {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public InventoryApp() {
        // Setting up a professional GUI for Aramco-themed Enterprise System
        setTitle("Enterprise Resource Inventory System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton btnAdd = new JButton("Add Resource");
        panel.add(btnAdd);
        add(panel);

        // Action Listener representing Software Engineering workflows
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewResource("Industrial Tool", 150);
            }
        });
    }

    // Method to insert data into MySQL Database
    public void addNewResource(String name, int quantity) {
        String query = "INSERT INTO inventory (resource_name, qty) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Resource added to database successfully!");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryApp().setVisible(true));
    }
}
