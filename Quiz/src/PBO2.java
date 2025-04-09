import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PBO2 {
    private static ArrayList<Product> products = new ArrayList<>();
    private static DefaultTableModel tableModel;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Daftar Produk");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Nama Produk", "Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JButton addButton = new JButton("Tambah");
        JButton deleteButton = new JButton("Hapus");
        JButton editButton = new JButton("Edit");

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            Product product = new Product(name, price);
            products.add(product);
            tableModel.addRow(new Object[]{name, price});
            nameField.setText("");
            priceField.setText("");
        });
        
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                products.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                nameField.setText("");
                priceField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "tidak ada yang dipilih");
            }
        });
            
        editButton.addActionListener(e -> {
            String newName = nameField.getText();
            double newPrice = Double.parseDouble(priceField.getText());
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                products.set(selectedRow, new Product(newName, newPrice));
                tableModel.setValueAt(newName, selectedRow, 0);
                tableModel.setValueAt(newPrice, selectedRow, 1);
                nameField.setText("");
                priceField.setText("");
            }
            else {
                JOptionPane.showMessageDialog(frame, "tidak ada yang dipilih");
            }
        });

        table.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String selectedName = table.getValueAt(selectedRow, 0).toString();
                String selectedPrice = table.getValueAt(selectedRow, 1).toString();
                nameField.setText(selectedName);
                priceField.setText(selectedPrice);
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nama:"));
        panel.add(nameField);
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(editButton);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
        
    }
}