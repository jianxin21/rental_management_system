/**
    class: adminGui

    name: tamjianxin
*/
import java.awt.EventQueue;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingConstants;

public class adminGui extends JFrame {

    private JPanel contentPane;
    DefaultTableModel model;
    ArrayList<Admin> admin = readAdminFromFile();
    ArrayList<User> mUser = readUserFromFile();
    ArrayList<Property> property = readPropertyFromFile();
    Random rand = new Random();
    private JTextField usernameTF;
    private JTextField passwordTF;
    private JTable jTable1;
    private JTextField addressTF;
    private JTextField roomTF;
    private JTextField broomTF;
    private JTextField facilitiesTF;
    private JTextField priceTF;
    private JTextField typeTF;
    public static JTable jTable2;
    private JPanel viewPropertyPanel = new JPanel();
    private JPanel addPropertyPanel = new JPanel();
    private JPanel viewUserPanel = new JPanel();
    private JPanel addAdminPanel = new JPanel();
    private JPanel selectPanel = new JPanel();
    String[] propertyT = {"CONDOMINIUM","SERVICED RESIDENCE","3-STY TERRACE","SEMI-D HOUSE","RESIDENTIAL LAND"}; 
    String[] facilities = {"PARKING","WIFI","SWIMMING POOL","PLAYGROUND","BBQ","GYMNASIUM","SHUTTLE BUS","MINI MARKET",
                            "SAUNA","JOGGING TRACK","CLUB HOUSE","BADMINTON COURT","CAFETERIA","BASKETBALL COURT","JACUZZI",
                            "HALL"}; 
    private JPanel rr;
    private JPanel light;
    private JTextField rateTF;

    
    /*
     * Main method
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adminGui frame = new adminGui();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /*
     * Constructor
     */
    public adminGui() throws IOException{
        setTitle("Admin");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1466, 714);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 10, 1452, 670);
        contentPane.add(panel);
        panel.setLayout(null);
            viewUserPanel.setVisible(false);
            addAdminPanel.setBackground(Color.WHITE);
            addAdminPanel.setVisible(false);
                addPropertyPanel.setBackground(Color.WHITE);
                
                addPropertyPanel.setVisible(false);
                viewPropertyPanel.setBackground(Color.WHITE);
                viewPropertyPanel.setVisible(false);
                selectPanel.setVisible(false);
                
                selectPanel.setBackground(Color.WHITE);
                selectPanel.setBounds(178, 122, 1275, 548);
                panel.add(selectPanel);
                selectPanel.setLayout(null);
                
                light = new JPanel();
                light.setBorder(null);
                light.setBackground(new Color(255, 250, 205));
                light.setBounds(0, 0, 292, 548);
                selectPanel.add(light);
                light.setLayout(null);
                
                JPanel pt = new JPanel();
                pt.setBounds(0, 187, 292, 42);
                pt.setLayout(null);
                light.add(pt);
                
                JButton btnShowPropertyType = new JButton("Show Property Type");
                btnShowPropertyType.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated"); 
                            try {
                                createType(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }    
                    }
                });
                btnShowPropertyType.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowPropertyType.setOpaque(false);
                btnShowPropertyType.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowPropertyType.setBorder(null);
                btnShowPropertyType.setBackground(Color.WHITE);
                btnShowPropertyType.setBounds(10, 10, 272, 21);
                pt.add(btnShowPropertyType);
                
                JPanel al = new JPanel();
                al.setBounds(0, 135, 292, 42);
                al.setLayout(null);
                light.add(al);
                
                JButton btnShowAllListing = new JButton("Show All Listing");
                btnShowAllListing.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated"); 
                            
                            try {
                                createAll(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }    
                    }
                });
                btnShowAllListing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowAllListing.setOpaque(false);
                btnShowAllListing.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowAllListing.setBorder(null);
                btnShowAllListing.setBackground(Color.WHITE);
                btnShowAllListing.setBounds(10, 10, 272, 21);
                al.add(btnShowAllListing);
                
                rr = new JPanel();
                rr.setBounds(0, 343, 292, 42);
                rr.setLayout(null);
                light.add(rr);
                
                JButton btnShowRentalRate = new JButton("Show Rental Rate");
                btnShowRentalRate.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated"); 
                            try {
                                createRR(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                btnShowRentalRate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowRentalRate.setOpaque(false);
                btnShowRentalRate.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowRentalRate.setBorder(null);
                btnShowRentalRate.setBackground(Color.WHITE);
                btnShowRentalRate.setBounds(10, 10, 272, 21);
                rr.add(btnShowRentalRate);
                
                JPanel ap = new JPanel();
                ap.setBounds(0, 239, 292, 42);
                ap.setLayout(null);
                light.add(ap);
                
                JButton btnShowInactiveProperty = new JButton("Show Active Property");
                btnShowInactiveProperty.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated");
                            try {
                                createActive(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }    
                    }
                });
                btnShowInactiveProperty.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowInactiveProperty.setOpaque(false);
                btnShowInactiveProperty.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowInactiveProperty.setBorder(null);
                btnShowInactiveProperty.setBackground(Color.WHITE);
                btnShowInactiveProperty.setBounds(10, 10, 272, 21);
                ap.add(btnShowInactiveProperty);
                
                JPanel iap = new JPanel();
                iap.setBounds(0, 291, 292, 42);
                iap.setLayout(null);
                light.add(iap);
                
                JButton btnShowInactiveProperty_2 = new JButton("Show Inactive Property");
                btnShowInactiveProperty_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowInactiveProperty_2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated");
                            try {
                                createInactive(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                btnShowInactiveProperty_2.setOpaque(false);
                btnShowInactiveProperty_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowInactiveProperty_2.setBorder(null);
                btnShowInactiveProperty_2.setBackground(Color.WHITE);
                btnShowInactiveProperty_2.setBounds(10, 10, 272, 21);
                iap.add(btnShowInactiveProperty_2);
                
                JPanel ur_1 = new JPanel();
                ur_1.setBounds(0, 395, 292, 42);
                ur_1.setLayout(null);
                light.add(ur_1);
                
                JButton btnShowUserReport_1 = new JButton("Show Facilities");
                btnShowUserReport_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated"); 
                            try {
                                createFacilities(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        
                    }
                });
                btnShowUserReport_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowUserReport_1.setOpaque(false);
                btnShowUserReport_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowUserReport_1.setBorder(null);
                btnShowUserReport_1.setBackground(Color.WHITE);
                btnShowUserReport_1.setBounds(10, 10, 272, 21);
                ur_1.add(btnShowUserReport_1);
                
                JLabel labelReport = new JLabel("Report");
                labelReport.setBounds(95, 42, 120, 60);
                labelReport.setFont(new Font("Times New Roman", Font.PLAIN, 40));
                light.add(labelReport);
                
                JPanel white = new JPanel();
                white.setBackground(Color.WHITE);
                white.setBounds(0, 68, 292, 10);
                light.add(white);
                
                JPanel ur_2 = new JPanel();
                ur_2.setLayout(null);
                ur_2.setBounds(0, 447, 292, 42);
                light.add(ur_2);
                
                JButton btnShowRentalPrice = new JButton("Show Rental Price");
                btnShowRentalPrice.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated"); 
                            try {
                                createRP(property);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                btnShowRentalPrice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowRentalPrice.setOpaque(false);
                btnShowRentalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowRentalPrice.setBorder(null);
                btnShowRentalPrice.setBackground(Color.WHITE);
                btnShowRentalPrice.setBounds(10, 10, 272, 21);
                ur_2.add(btnShowRentalPrice);
                
                JPanel ur = new JPanel();
                ur.setBounds(0, 496, 292, 42);
                light.add(ur);
                ur.setLayout(null);
                
                JButton btnShowUserReport = new JButton("Show User Report");
                btnShowUserReport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowUserReport.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int n = JOptionPane.showConfirmDialog(null,"Are you sure want to print report?" ,"Report", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION)
                        {
                            JOptionPane.showMessageDialog(null, "Report has been generated"); 
                            
                            try {
                                createUserReport(mUser);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                btnShowUserReport.setOpaque(false);
                btnShowUserReport.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnShowUserReport.setBorder(null);
                btnShowUserReport.setBackground(Color.WHITE);
                btnShowUserReport.setBounds(10, 10, 272, 21);
                ur.add(btnShowUserReport);
                
                viewPropertyPanel.setBounds(178, 122, 1275, 548);
                panel.add(viewPropertyPanel);
                viewPropertyPanel.setLayout(null);
                
                JScrollPane scrollPane_1 = new JScrollPane();
                scrollPane_1.setBounds(10, 79, 1255, 307);
                viewPropertyPanel.add(scrollPane_1);
                
                jTable2 = new JTable();
                jTable2.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "ID", "Address", "Size", "No.of Room", "No.of Bathroom", "Facilities", "Rental Price", "Type", "Status", "Rental Rate"
                    }
                ) {
                    boolean[] columnEditables = new boolean[] {
                        false, false, false, false, false, false, false, false, false, false
                    };
                    public boolean isCellEditable(int row, int column) {
                        return columnEditables[column];
                    }
                });
                jTable2.getColumnModel().getColumn(0).setResizable(false);
                jTable2.getColumnModel().getColumn(0).setPreferredWidth(39);
                jTable2.getColumnModel().getColumn(1).setResizable(false);
                jTable2.getColumnModel().getColumn(1).setPreferredWidth(227);
                jTable2.getColumnModel().getColumn(2).setResizable(false);
                jTable2.getColumnModel().getColumn(2).setPreferredWidth(50);
                jTable2.getColumnModel().getColumn(3).setResizable(false);
                jTable2.getColumnModel().getColumn(3).setPreferredWidth(67);
                jTable2.getColumnModel().getColumn(4).setResizable(false);
                jTable2.getColumnModel().getColumn(4).setPreferredWidth(82);
                jTable2.getColumnModel().getColumn(5).setResizable(false);
                jTable2.getColumnModel().getColumn(5).setPreferredWidth(355);
                jTable2.getColumnModel().getColumn(6).setResizable(false);
                jTable2.getColumnModel().getColumn(6).setPreferredWidth(67);
                jTable2.getColumnModel().getColumn(7).setPreferredWidth(140);
                jTable2.getColumnModel().getColumn(7).setResizable(false);
                jTable2.getColumnModel().getColumn(8).setResizable(false);
                jTable2.getColumnModel().getColumn(8).setPreferredWidth(69);
                jTable2.getColumnModel().getColumn(9).setResizable(false);
                scrollPane_1.setViewportView(jTable2);
                
                JButton btnDelete = new JButton("Delete");
                btnDelete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //delete a property
                        if(jTable2.getSelectedRowCount()==1){
                            int x = jTable2.getSelectedRow();
                            property.remove(x);
                            model.setRowCount(0);
                            createTable();
                        }
                        else{
                            if(jTable2.getRowCount()==0){
                                JOptionPane.showMessageDialog(null,"Table is empty!");
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Please select an item!");
                            }
                        }
                        try {
                            sortId(property);
                            savePropertyToFile(property);
                        } catch (IOException ex) {
                            Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
                btnDelete.setIcon(new ImageIcon("images/waste_24px.png"));
                btnDelete.setOpaque(false);
                btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnDelete.setBorder(null);
                btnDelete.setBackground(SystemColor.menu);
                btnDelete.setBounds(1137, 404, 94, 35);
                viewPropertyPanel.add(btnDelete);
                
                JButton btnSort = new JButton("Sort");
                JButton btnRefresh1 = new JButton("Refresh");
                btnRefresh1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //refresh the table by sorting property id ascendingly
                        if(jTable2.getRowCount()==0){
                            JOptionPane.showMessageDialog(null,"Table is empty!");
                          }
                        else{
                            try {
                                model.setRowCount(0);
                                sortId(property);
                                createTable();
                                btnSort.setEnabled(true);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            
                        } 
                    }
                    
                });
                btnRefresh1.setIcon(new ImageIcon("images/refresh_25px.png"));
                btnRefresh1.setOpaque(false);
                btnRefresh1.setFont(new Font("Tahoma", Font.PLAIN, 15));
                btnRefresh1.setBorder(null);
                btnRefresh1.setBackground(SystemColor.menu);
                btnRefresh1.setBounds(1051, 404, 94, 35);
                viewPropertyPanel.add(btnRefresh1);
                
                btnSort.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //sort property according to rental price
                        if(jTable2.getRowCount()==0){
                            JOptionPane.showMessageDialog(null,"Table is empty!");
                        }
                        else{
                            try {
                                sortRental(property);
                                model.setRowCount(0);
                                createTable();  
                                btnSort.setEnabled(false);
                            } catch (IOException ex) {
                                Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                btnSort.setIcon(new ImageIcon("images/sort_numeric_up_24px.png"));
                btnSort.setOpaque(false);
                btnSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
                btnSort.setBorder(null);
                btnSort.setBackground(SystemColor.menu);
                btnSort.setBounds(943, 404, 94, 35);
                viewPropertyPanel.add(btnSort);
                
                JButton btnInactive = new JButton("Inactive");
                btnInactive.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // to inactivate property
                        if(jTable2.getSelectedRowCount()==1){
                            int x = jTable2.getSelectedRow();
                            property.get(x).setStatus("INACTIVE");
                            model.setRowCount(0);
                            createTable();
                        }
                        else{
                            if(jTable2.getRowCount()==0){
                                JOptionPane.showMessageDialog(null,"Table is empty!");
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Please select an item!");
                            }
                        }
                        try {
                            sortId(property);
                            savePropertyToFile(property);
                        } catch (IOException ex) {
                            Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btnInactive.setIcon(new ImageIcon("images/delete_24px.png"));
                btnInactive.setOpaque(false);
                btnInactive.setFont(new Font("Tahoma", Font.PLAIN, 15));
                btnInactive.setBorder(null);
                btnInactive.setBackground(SystemColor.menu);
                btnInactive.setBounds(266, 423, 104, 35);
                viewPropertyPanel.add(btnInactive);
                
                JButton btnActive = new JButton("Active");
                btnActive.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // to activate property
                        if(jTable2.getSelectedRowCount()==1){
                            int x = jTable2.getSelectedRow();
                            property.get(x).setStatus("ACTIVE");
                            model.setRowCount(0);
                            createTable();
                        }
                        else{
                            if(jTable2.getRowCount()==0){
                                JOptionPane.showMessageDialog(null,"Table is empty!");
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Please select an item!");
                            }
                        }
                        try {
                            sortId(property);
                            savePropertyToFile(property);
                        } catch (IOException ex) {
                            Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btnActive.setIcon(new ImageIcon("images/checkmark_24px.png"));
                btnActive.setOpaque(false);
                btnActive.setFont(new Font("Tahoma", Font.PLAIN, 15));
                btnActive.setBorder(null);
                btnActive.setBackground(SystemColor.menu);
                btnActive.setBounds(152, 423, 104, 35);
                viewPropertyPanel.add(btnActive);
                
                JLabel lblStatus = new JLabel("Property Status: ");
                lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
                lblStatus.setBounds(20, 422, 135, 35);
                viewPropertyPanel.add(lblStatus);
                
                JLabel lblNewLabel_1 = new JLabel("*Rental Rate = Price per square feet");
                lblNewLabel_1.setForeground(Color.RED);
                lblNewLabel_1.setBounds(10, 396, 205, 13);
                viewPropertyPanel.add(lblNewLabel_1);
                
                addPropertyPanel.setBounds(178, 122, 1275, 548);
                panel.add(addPropertyPanel);
                addPropertyPanel.setLayout(null);
                
                JLabel addressLabel = new JLabel("Address");
                addressLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                addressLabel.setBounds(158, 185, 85, 21);
                addPropertyPanel.add(addressLabel);
                
                JLabel sizeLabel = new JLabel("Size");
                sizeLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                sizeLabel.setBounds(158, 232, 100, 21);
                addPropertyPanel.add(sizeLabel);
                
                JLabel roomLabel = new JLabel("No of room");
                roomLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                roomLabel.setBounds(158, 274, 120, 21);
                addPropertyPanel.add(roomLabel);
                
                JLabel broomLabel = new JLabel("No of bathroom");
                broomLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                broomLabel.setBounds(158, 320, 141, 21);
                addPropertyPanel.add(broomLabel);
                
                JLabel facilitiesLabel = new JLabel("Facilities");
                facilitiesLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                facilitiesLabel.setBounds(628, 185, 100, 21);
                addPropertyPanel.add(facilitiesLabel);
                
                JLabel rentalLabel = new JLabel("Rental price");
                rentalLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                rentalLabel.setBounds(628, 232, 114, 21);
                addPropertyPanel.add(rentalLabel);
                
                JLabel typeLabel = new JLabel("Property type");
                typeLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                typeLabel.setBounds(628, 274, 114, 21);
                addPropertyPanel.add(typeLabel);
                
                addressTF = new JTextField();
                addressTF.setColumns(10);
                addressTF.setBounds(296, 183, 207, 25);
                addPropertyPanel.add(addressTF);
                
                JTextField sizeTF = new JTextField();
                sizeTF.setColumns(10);
                sizeTF.setBounds(296, 233, 207, 25);
                addPropertyPanel.add(sizeTF);
                
                roomTF = new JTextField();
                roomTF.setColumns(10);
                roomTF.setBounds(296, 275, 207, 25);
                addPropertyPanel.add(roomTF);
                
                broomTF = new JTextField();
                broomTF.setColumns(10);
                broomTF.setBounds(296, 320, 207, 25);
                addPropertyPanel.add(broomTF);
                
                facilitiesTF = new JTextField();
                facilitiesTF.setColumns(10);
                facilitiesTF.setBounds(780, 186, 207, 25);
                addPropertyPanel.add(facilitiesTF);
                
                priceTF = new JTextField();
                priceTF.setColumns(10);
                priceTF.setBounds(780, 233, 207, 25);
                addPropertyPanel.add(priceTF);
                
                typeTF = new JTextField();
                typeTF.setColumns(10);
                typeTF.setBounds(780, 275, 207, 25);
                addPropertyPanel.add(typeTF);
                
                JButton btnEnter_1 = new JButton("Enter");
                btnEnter_1.setBorder(null);
                btnEnter_1.setBackground(Color.LIGHT_GRAY);
                btnEnter_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //to add new property
                        if(addressTF.getText().isEmpty() || sizeTF.getText().isEmpty() || roomTF.getText().isEmpty() || broomTF.getText().isEmpty() || 
                                                    facilitiesTF.getText().isEmpty() || priceTF.getText().isEmpty() || typeTF.getText().isEmpty() || rateTF.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Data cannot be empty"); 
                        }
                        else {
                                JOptionPane.showMessageDialog(null, "Data added"); 
                             int newID = (property.get(property.size()-1).getId()) + 1;
                                int rental = Integer.parseInt(priceTF.getText());
                                String status = "active";
                                    property.add(new Property(newID,addressTF.getText().toUpperCase(),sizeTF.getText().toUpperCase(),roomTF.getText().toUpperCase(),broomTF.getText().toUpperCase(),
                                                            facilitiesTF.getText().toUpperCase(),rental,typeTF.getText().toUpperCase(),status.toUpperCase(),rateTF.getText().toUpperCase()));
                                    try {
                                        savePropertyToFile(property);
                                    } catch (IOException ex) {
                                        Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                addressTF.setText(null);
                                sizeTF.setText(null);
                                roomTF.setText(null);
                                broomTF.setText(null);
                                facilitiesTF.setText(null);
                                priceTF.setText(null);
                                typeTF.setText(null);
                                rateTF.setText(null);
                        }
                    }
                });
                btnEnter_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnEnter_1.setBounds(517, 384, 111, 26);
                addPropertyPanel.add(btnEnter_1);
                
                JLabel lblAddAdmin_1 = new JLabel("Add Property");
                lblAddAdmin_1.setIcon(new ImageIcon("images/house_50px.png"));
                lblAddAdmin_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                lblAddAdmin_1.setBounds(458, 87, 320, 51);
                addPropertyPanel.add(lblAddAdmin_1);
                
                JLabel lblRentalRate = new JLabel("Rental rate");
                lblRentalRate.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
                lblRentalRate.setBounds(628, 320, 114, 21);
                addPropertyPanel.add(lblRentalRate);
                
                rateTF = new JTextField();
                rateTF.setColumns(10);
                rateTF.setBounds(780, 321, 207, 25);
                addPropertyPanel.add(rateTF);
            
                addAdminPanel.setBounds(178, 122, 1275, 548);
                panel.add(addAdminPanel);
                addAdminPanel.setLayout(null);
                
                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                usernameLabel.setBounds(376, 193, 118, 25);
                addAdminPanel.add(usernameLabel);
                
                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
                passwordLabel.setBounds(376, 250, 118, 26);
                addAdminPanel.add(passwordLabel);
                
                usernameTF = new JTextField();
                usernameTF.setBounds(528, 194, 207, 25);
                addAdminPanel.add(usernameTF);
                usernameTF.setColumns(10);
                
                passwordTF = new JTextField();
                passwordTF.setBounds(528, 255, 207, 25);
                addAdminPanel.add(passwordTF);
                passwordTF.setColumns(10);
                
                JButton btnEnter = new JButton("Enter");
                btnEnter.setBackground(Color.LIGHT_GRAY);
                btnEnter.setBorder(null);
                btnEnter.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //to add new admin
                        boolean check = checkExist(usernameTF.getText());
                        if(check==true){
                            JOptionPane.showMessageDialog(null, "Username already existed!");
                            usernameTF.setText(null);
                            passwordTF.setText(null);
                        }
                        else if(usernameTF.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in the username");
                        }
                        else if(passwordTF.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in the password");
                        }
                        else {
                            int n = JOptionPane.showConfirmDialog(null,"Are you sure want to add new Admin?" ,"Add Admin", JOptionPane.YES_NO_OPTION);
                            if(n == JOptionPane.YES_OPTION){
                                admin.add(new Admin(usernameTF.getText(),passwordTF.getText()));
                                 try {
                                     saveAdminToFile(admin);
                                 } 
                                 catch (IOException ex) {
                                     Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                 JOptionPane.showMessageDialog(null, usernameTF.getText() + " Added!");
                                 usernameTF.setText(null);
                                 passwordTF.setText(null);
                                }
                        }
                    }
                });
                btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnEnter.setBounds(475, 341, 111, 26);
                addAdminPanel.add(btnEnter);
                
                JLabel lblAddAdmin = new JLabel("Add Admin");
                lblAddAdmin.setIcon(new ImageIcon("images/add_administrator_50px.png"));
                lblAddAdmin.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                lblAddAdmin.setBounds(458, 87, 320, 51);
                addAdminPanel.add(lblAddAdmin);
            
            viewUserPanel.setBackground(Color.WHITE);
            viewUserPanel.setBounds(178, 122, 1275, 548);
            panel.add(viewUserPanel);
            viewUserPanel.setLayout(null);
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(269, 148, 532, 222);
            viewUserPanel.add(scrollPane);
            
            jTable1 = new JTable();
            jTable1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Username", "Password", "Status"
                }
            ) {
                boolean[] columnEditables = new boolean[] {
                    false, false, true
                };
                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(102);
            scrollPane.setViewportView(jTable1);
            
            JButton btnRefresh = new JButton("  Refresh");
            //refresh to get the latest user status
            btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(jTable1.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Table is empty!");
                }
                else{
                    model.setRowCount(0);
                    model = (DefaultTableModel) jTable1.getModel();
                    Object rowData[] = new Object[3];
                    for(int i=0;i<mUser.size();i++){
                            rowData[0] = mUser.get(i).getUsername();
                            rowData[1] = mUser.get(i).getPassword();
                            rowData[2] = mUser.get(i).getUserStatus();
                            model.addRow(rowData);
                    }
                }
                }
            });
            btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 12));
            btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnRefresh.setBackground(SystemColor.window);
            btnRefresh.setOpaque(false);
            btnRefresh.setBorder(null);
            btnRefresh.setIcon(new ImageIcon("images/refresh_25px.png"));
            btnRefresh.setBounds(811, 186, 100, 33);
            viewUserPanel.add(btnRefresh);
            
            JButton btnAccept = new JButton("  Accept");
            btnAccept.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //to accept a user
                    model = (DefaultTableModel) jTable1.getModel();
                    if(jTable1.getSelectedRowCount()==1){
                        int x = jTable1.getSelectedRow();
                        mUser.get(x).setUserStatus("accept");
                        model.setRowCount(0);
                        Object rowData[] = new Object[3];
                        for(int i=0;i<mUser.size();i++){
                            rowData[0] = mUser.get(i).getUsername();
                            rowData[1] = mUser.get(i).getPassword();
                            rowData[2] = mUser.get(i).getUserStatus();
                            model.addRow(rowData);
                        }
                    }
                    else{
                        if(jTable1.getRowCount()==0){
                            JOptionPane.showMessageDialog(null,"Table is empty!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Please select an item!");
                        }
                    }
                   
                    try {
                        saveUserToFile(mUser);
                    } catch (IOException ex) {
                        Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            btnAccept.setIcon(new ImageIcon("images/checkmark_24px.png"));
            btnAccept.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnAccept.setOpaque(false);
            btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 12));
            btnAccept.setBorder(null);
            btnAccept.setBackground(Color.WHITE);
            btnAccept.setBounds(800, 229, 111, 33);
            viewUserPanel.add(btnAccept);
            
            JButton btnRefresh_2 = new JButton("  Reject");
            btnRefresh_2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // to reject a user
                    model = (DefaultTableModel) jTable1.getModel();
                    if(jTable1.getSelectedRowCount()==1){
                        int x = jTable1.getSelectedRow();
                        mUser.get(x).setUserStatus("reject");
                        model.setRowCount(0);
                        Object rowData[] = new Object[3];
                        for(int i=0;i<mUser.size();i++){
                            rowData[0] = mUser.get(i).getUsername();
                            rowData[1] = mUser.get(i).getPassword();
                            rowData[2] = mUser.get(i).getUserStatus();
                            model.addRow(rowData);
                        }
                    }
                    else{
                        if(jTable1.getRowCount()==0){
                            JOptionPane.showMessageDialog(null,"Table is empty!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Please select an item!");
                        }
                    }
                    try {
                        saveUserToFile(mUser);
                    } catch (IOException ex) {
                        Logger.getLogger(adminGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            btnRefresh_2.setIcon(new ImageIcon("images/delete_24px.png"));
            btnRefresh_2.setOpaque(false);
            btnRefresh_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
            btnRefresh_2.setBorder(null);
            btnRefresh_2.setBackground(Color.WHITE);
            btnRefresh_2.setBounds(800, 272, 111, 33);
            viewUserPanel.add(btnRefresh_2);
            
            JLabel lblViewUserList = new JLabel("View User List");
            lblViewUserList.setIcon(new ImageIcon("images/user_folder_50px.png"));
            lblViewUserList.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            lblViewUserList.setBounds(458, 87, 320, 51);
            viewUserPanel.add(lblViewUserList);
        
        JPanel yellow = new JPanel();
        yellow.setBackground(new Color(255, 215, 0));
        yellow.setBounds(0, 0, 179, 670);
        panel.add(yellow);
        yellow.setLayout(null);
        
        JPanel addAdmin = new JPanel();
        addAdmin.setBounds(0, 83, 179, 42);
        yellow.add(addAdmin);
        addAdmin.setLayout(null);
        
        JButton btnAddAdmin = new JButton("Add Admin");
        btnAddAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPropertyPanel.setVisible(false);
                addPropertyPanel.setVisible(false);
                addAdminPanel.setVisible(true);
                viewUserPanel.setVisible(false);
                selectPanel.setVisible(false);
            }
        });
        btnAddAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAddAdmin.setBorder(null);
        btnAddAdmin.setBackground(new Color(255, 255, 255));
        btnAddAdmin.setOpaque(false);
        btnAddAdmin.setBounds(10, 10, 159, 21);
        addAdmin.add(btnAddAdmin);
        
        JPanel viewUserList = new JPanel();
        viewUserList.setBounds(0, 135, 179, 42);
        yellow.add(viewUserList);
        viewUserList.setLayout(null);
        
        JButton btnViewUser = new JButton("View User List");
        btnViewUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jTable1.getRowCount()==0){
                addRowToJTable();
                }
                viewPropertyPanel.setVisible(false);
                addPropertyPanel.setVisible(false);
                addAdminPanel.setVisible(false);
                viewUserPanel.setVisible(true);
                light.setVisible(false);
            }
        });
        btnViewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewUser.setBackground(new Color(255, 255, 255));
        btnViewUser.setBorder(null);
        btnViewUser.setOpaque(false);
        btnViewUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnViewUser.setBounds(10, 10, 159, 21);
        viewUserList.add(btnViewUser);
        
        JPanel addProperty = new JPanel();
        addProperty.setBounds(0, 187, 179, 42);
        yellow.add(addProperty);
        addProperty.setLayout(null);
        
        JButton btnAddProperty = new JButton("Add Property");
        btnAddProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPropertyPanel.setVisible(false);
                addPropertyPanel.setVisible(true);
                addAdminPanel.setVisible(false);
                viewUserPanel.setVisible(false);
                selectPanel.setVisible(false);
            }
        });
        btnAddProperty.setBackground(new Color(255, 255, 255));
        btnAddProperty.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAddProperty.setOpaque(false);
        btnAddProperty.setBorder(null);
        btnAddProperty.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddProperty.setBounds(10, 10, 159, 21);
        addProperty.add(btnAddProperty);
        
        JPanel viewPropertyList = new JPanel();
        viewPropertyList.setBounds(0, 239, 179, 42);
        yellow.add(viewPropertyList);
        viewPropertyList.setLayout(null);
        
        JButton btnViewProperty = new JButton("View Property List");
        btnViewProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jTable2.getRowCount()==0) {
                    try {
                        sortId(property);
                        createTable();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                viewPropertyPanel.setVisible(true);
                addPropertyPanel.setVisible(false);
                addAdminPanel.setVisible(false);
                viewUserPanel.setVisible(false);
                selectPanel.setVisible(false);
            }
        });
        btnViewProperty.setBackground(new Color(255, 255, 255));
        btnViewProperty.setBorder(null);
        btnViewProperty.setOpaque(false);
        btnViewProperty.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnViewProperty.setBounds(10, 10, 159, 21);
        viewPropertyList.add(btnViewProperty);
        
        JPanel viewPropertyList_1 = new JPanel();
        viewPropertyList_1.setLayout(null);
        viewPropertyList_1.setBounds(0, 291, 179, 42);
        yellow.add(viewPropertyList_1);
        
        JButton btnPrint = new JButton("Print Report");
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPropertyPanel.setVisible(false);
                addPropertyPanel.setVisible(false);
                addAdminPanel.setVisible(false);
                viewUserPanel.setVisible(false);
                selectPanel.setVisible(true);
                light.setVisible(true);
                
            }
        });
        btnPrint.setOpaque(false);
        btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnPrint.setBorder(null);
        btnPrint.setBackground(Color.WHITE);
        btnPrint.setBounds(10, 10, 159, 21);
        viewPropertyList_1.add(btnPrint);
        
        JPanel lightbrown = new JPanel();
        lightbrown.setBackground(new Color(255, 222, 173));
        lightbrown.setBounds(0, 0, 1453, 123);
        panel.add(lightbrown);
        lightbrown.setLayout(null);
        
        JLabel adminLabel = new JLabel("Admin");
        adminLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 35));
        adminLabel.setBounds(638, 38, 103, 39);
        lightbrown.add(adminLabel);
    }
    
    /**
        createTable - use to create property list 

        get the details of a property, put them into a table(model)

        name: tamjianxin

    */
    public void createTable() {
        model = (DefaultTableModel) jTable2.getModel();
        Object rowData[] = new Object[10];
            for(int i=0;i<property.size();i++){    
                rowData[0] = property.get(i).getId();
                rowData[1] = property.get(i).getAddress();
                rowData[2] = property.get(i).getSize();
                rowData[3] = property.get(i).getNoRoom();
                rowData[4] = property.get(i).getNoBathroom();
                rowData[5] = property.get(i).getFacilities();
                rowData[6] = property.get(i).getRentalPrice();
                rowData[7] = property.get(i).getPropertyType();
                rowData[8] = property.get(i).getStatus();
                rowData[9] = property.get(i).getRentalrate();
                model.addRow(rowData);
        }
    }
    
    /**
        addRowToJTable - use to create user list in admin interface

        get the username, password and user status, put them into a table(model)

        name: tamjianxin

    */
    public void addRowToJTable(){
        model = (DefaultTableModel) jTable1.getModel();
        Object rowData[] = new Object[3];
        for(int i=0;i<mUser.size();i++){
            rowData[0] = mUser.get(i).getUsername();
            rowData[1] = mUser.get(i).getPassword();
            rowData[2] = mUser.get(i).getUserStatus();
            model.addRow(rowData);
        }
        
    }
    
    /**
        createAll - create html report of all user

        parameter: ArrayList<User> mUser

        name: tamjianxin

    */
    public void createUserReport(ArrayList<User> mUser) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/User report "+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
        myWriter.write("<h2>User List</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>Username </th>\n");
        myWriter.write("<th>Password </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<mUser.size();i++) {
            myWriter.write("<tr>\n");
            myWriter.write("<td>"+ mUser.get(i).getUsername() +"</td>\n");
            myWriter.write("<td>"+ mUser.get(i).getPassword() +"</td>\n");
            myWriter.write("<td>"+ mUser.get(i).getUserStatus() +"</td>\n");
            myWriter.write("</tr>\n");
        }
        myWriter.write("</table>\n");
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createAll - create html report of all property

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createAll(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Property report "+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
        
        myWriter.write("<h2>Property Listing</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Size </th>\n");
        myWriter.write("<th>No.of room </th>\n");
        myWriter.write("<th>No.of bathroom </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<property.size();i++) {
                myWriter.write("<tr>\n");
                myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                myWriter.write("</tr>\n");
        }
        myWriter.write("</table>\n");
        
        myWriter.write("<br>");
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createFacilities - create html report according to facilities

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createFacilities(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Facilities report "+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
        
        for(int x=0;x<facilities.length;x++) {
            myWriter.write("<h2>" + facilities[x] + "</h2>\n");
            myWriter.write("<table style='width:100%'\n");
            myWriter.write("<tr>\n");
            myWriter.write("<th>ID</th>\n");
            myWriter.write("<th>Address </th>\n");
            myWriter.write("<th>Size </th>\n");
            myWriter.write("<th>No.of room </th>\n");
            myWriter.write("<th>No.of bathroom </th>\n");
            myWriter.write("<th>Facilities </th>\n");
            myWriter.write("<th>Rental price </th>\n");
            myWriter.write("<th>Property type </th>\n");
            myWriter.write("<th>Status </th>\n");
            myWriter.write("<th>Rental rate </th>\n");
            
            myWriter.write("</tr>\n");
            for(int i=0;i<property.size();i++) {
                if(property.get(i).getFacilities().contains(facilities[x])) {
                    myWriter.write("<tr>\n");
                    myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getRentalrate() +"</td>\n");
                    myWriter.write("</tr>\n");
                }
            }
            myWriter.write("</table>\n");
        }
        
        myWriter.write("<br>");    
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createType - create html report according to property type

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createType(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Property type report "+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
        
        for(int x=0;x<propertyT.length;x++) {
            myWriter.write("<h2>" + propertyT[x] + "</h2>\n");
            myWriter.write("<table style='width:100%'\n");
            myWriter.write("<tr>\n");
            myWriter.write("<th>ID</th>\n");
            myWriter.write("<th>Address </th>\n");
            myWriter.write("<th>Size </th>\n");
            myWriter.write("<th>No.of room </th>\n");
            myWriter.write("<th>No.of bathroom </th>\n");
            myWriter.write("<th>Facilities </th>\n");
            myWriter.write("<th>Rental price </th>\n");
            myWriter.write("<th>Property type </th>\n");
            myWriter.write("<th>Status </th>\n");
            myWriter.write("<th>Rental rate </th>\n");
            myWriter.write("</tr>\n");
            for(int i=0;i<property.size();i++) {
                if(property.get(i).getPropertyType().equals(propertyT[x])) {
                    myWriter.write("<tr>\n");
                    myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getRentalrate() +"</td>\n");
                    myWriter.write("</tr>\n");
                }
            }
            myWriter.write("</table>\n");
        }
        myWriter.write("<br>");    
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createInactive - create html report of inactive property

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createInactive(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Inactive property report "+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
    
        myWriter.write("<h2>Inactive Property</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Size </th>\n");
        myWriter.write("<th>No.of room </th>\n");
        myWriter.write("<th>No.of bathroom </th>\n");
        myWriter.write("<th>Facilities </th>\n");
        myWriter.write("<th>Rental price </th>\n");
        myWriter.write("<th>Property type </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("<th>Rental rate </th>\n");
        myWriter.write("</tr>\n");
            for(int i=0;i<property.size();i++) {
                if(property.get(i).getStatus().equals("INACTIVE")) {
                    myWriter.write("<tr>\n");
                    myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                    myWriter.write("<td>"+ property.get(i).getRentalrate() +"</td>\n");
                    myWriter.write("</tr>\n");
                }
            }
        myWriter.write("</table>\n");
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createActive - create html report of active property

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createActive(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Active property report "+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
    
        myWriter.write("<h2>Active Property</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Size </th>\n");
        myWriter.write("<th>No.of room </th>\n");
        myWriter.write("<th>No.of bathroom </th>\n");
        myWriter.write("<th>Facilities </th>\n");
        myWriter.write("<th>Rental price </th>\n");
        myWriter.write("<th>Property type </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("<th>Rental rate </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<property.size();i++) {
            if(property.get(i).getStatus().equals("ACTIVE")) {
                myWriter.write("<tr>\n");
                myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getRentalrate() +"</td>\n");
                myWriter.write("</tr>\n");
            }
        }
        myWriter.write("</table>\n");
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createRP - create html report according to property rental price

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createRP(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Property Rental Price report"+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
        
        myWriter.write("<h2>Rental Price</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Size </th>\n");
        myWriter.write("<th>No.of room </th>\n");
        myWriter.write("<th>No.of bathroom </th>\n");
        myWriter.write("<th>Facilities </th>\n");
        myWriter.write("<th>Rental price </th>\n");
        myWriter.write("<th>Property type </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<property.size();i++) {
            myWriter.write("<tr>\n");
            myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
            myWriter.write("</tr>\n");
        }
        myWriter.write("</table>\n");
        
        myWriter.write("<br>");
        
        myWriter.write("<h2>Sorted Rental Price</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Size </th>\n");
        myWriter.write("<th>No.of room </th>\n");
        myWriter.write("<th>No.of bathroom </th>\n");
        myWriter.write("<th>Facilities </th>\n");
        myWriter.write("<th>Rental price </th>\n");
        myWriter.write("<th>Property type </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<property.size();i++) {
                sortRental(property);
                myWriter.write("<tr>\n");
                myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                myWriter.write("</tr>\n");
        }
        myWriter.write("</table>\n");
        
        myWriter.write("<h2>Sorted Property List according Rental Rate(Highest to lowest)</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Size </th>\n");
        myWriter.write("<th>No.of room </th>\n");
        myWriter.write("<th>No.of bathroom </th>\n");
        myWriter.write("<th>Facilities </th>\n");
        myWriter.write("<th>Rental price </th>\n");
        myWriter.write("<th>Property type </th>\n");
        myWriter.write("<th>Status </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<property.size();i++) {
                sortRental1(property);
                myWriter.write("<tr>\n");
                myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getSize() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoRoom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getNoBathroom() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getFacilities() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getRentalPrice() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getPropertyType() +"</td>\n");
                myWriter.write("<td>"+ property.get(i).getStatus() +"</td>\n");
                myWriter.write("</tr>\n");
        }
        myWriter.write("</table>\n");
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        createRR - create html report according to property rental rate

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public void createRR(ArrayList<Property> property) throws IOException{
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedDate = myDate.format(myFormatDate);
        String filename = "report/Property Rental Rate report"+ formattedDate +".html";
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write("<!DOCTYPE html>\n");
        myWriter.write("<html>\n");
        myWriter.write("<style>\n");
        myWriter.write("table, th, td{\nborder: 1px solid black; \n }");
        myWriter.write("th,td{text-align: left; \n padding: 6px;}\n");
        myWriter.write("tr:nth-child(even){background-color: #D6EEEE;}\n");
        myWriter.write("</style>\n");
        myWriter.write("<body>\n");
        
        myWriter.write("<h2>Rental Rate</h2>\n");
        myWriter.write("<table style='width:100%'\n");
        myWriter.write("<tr>\n");
        myWriter.write("<th>ID</th>\n");
        myWriter.write("<th>Address </th>\n");
        myWriter.write("<th>Rental rate </th>\n");
        myWriter.write("</tr>\n");
        for(int i=0;i<property.size();i++) {
            myWriter.write("<tr>\n");
            myWriter.write("<td>"+ property.get(i).getId() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getAddress() +"</td>\n");
            myWriter.write("<td>"+ property.get(i).getRentalrate() +"</td>\n");
            myWriter.write("</tr>\n");
        }
        myWriter.write("</table>\n");
        myWriter.write("</body>\n");
        myWriter.write("</html>\n");
        myWriter.close();
    }
    
    /**
        sortId - compare among the property id,sort ascendingly

        parameter: ArrayList<Property> currentproperty

        name: chengjiapao

    */
    public void sortId(ArrayList<Property> currentproperty) throws IOException {
        for(int i=0;i<currentproperty.size();i++) {
            for(int j=0;j<currentproperty.size();j++) {
                Property p1 = currentproperty.get(i);
                Property p2 = currentproperty.get(j);
                    if(p1.getId() < p2.getId()) {
                        currentproperty.set(i, p2);
                        currentproperty.set(j, p1);
                    }
            }
        }
    }
    
    /**
        sortRental1 - compare among the property rental price,sort descendingly

        parameter: ArrayList<Property> currentproperty

        name: chengjiapao

    */
    public void sortRental1(ArrayList<Property> currentproperty) throws IOException {
        for(int i=0;i<currentproperty.size();i++) {
            for(int j=0;j<currentproperty.size();j++) {
                Property p1 = currentproperty.get(i);
                Property p2 = currentproperty.get(j);
                    if(p1.getRentalPrice() > p2.getRentalPrice()) {
                        currentproperty.set(i, p2);
                        currentproperty.set(j, p1);
                    }
            }
        }
    }
    
    /**
        sortRental - compare among the property rental price,sort ascendingly

        parameter: ArrayList<Property> currentproperty

        name: chengjiapao

    */
     public void sortRental(ArrayList<Property> currentproperty) throws IOException {
            for(int i=0;i<currentproperty.size();i++) {
                for(int j=0;j<currentproperty.size();j++) {
                    Property p1 = currentproperty.get(i);
                    Property p2 = currentproperty.get(j);
                        if(p1.getRentalPrice() < p2.getRentalPrice()) {
                            currentproperty.set(i, p2);
                            currentproperty.set(j, p1);
                        }
                }
            }
        }
     
    /**
        readPropertyFromFile - read the property from property database

        read csv file, read the line, when read a comma, split it, 
        parse id and rental rate to int type variable 

        return: property

        name: tamjianxin

    */
    private static ArrayList<Property> readPropertyFromFile() throws IOException {
        ArrayList<Property> property = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("data/property.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            int id = Integer.parseInt(items[0]);
            int rentalrate = Integer.parseInt(items[6]);
            property.add (new Property(id, items[1],items[2],items[3],items[4],items[5],rentalrate,items[7],items[8],items[9]));
        }
        return property;
    }
    
    /**
        savePropertyToFile - save the property to property database

        parameter: ArrayList<Property> property

        name: tamjianxin

    */
    public static void savePropertyToFile(ArrayList<Property> property) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < property.size(); i++) {
            sb.append (property.get(i).toCSVString() + "\n");
        }
        Files.write(Paths.get("data/property.csv"), sb.toString().getBytes());
    }
    
     /**
        readAdminFromFile - read the admin from admin database

        read csv file, read the line, when read a comma, split it 

        return: admin

        name: tamjianxin

    */
    private static ArrayList<Admin> readAdminFromFile() throws IOException {
        ArrayList<Admin> admin = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("data/admin.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            admin.add (new Admin(items[0], items[1]));
        }
        return admin;
    }
    
    /**
        saveAdminToFile - save the admin to admin database

        parameter: ArrayList<Admin> admin

        name: tamjianxin

    */
    public static void saveAdminToFile(ArrayList<Admin> admin) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < admin.size(); i++) {
            sb.append (admin.get(i).toCSVString() + "\n");
        }
        Files.write(Paths.get("data/admin.csv"), sb.toString().getBytes());
    }
    
    /**
        readUserFromFile - save the user to user database

        read csv file, read the line, when read a comma, split it 

        return: user

        name: tamjianxin, chengjiapao

    */
    private static ArrayList<User> readUserFromFile() throws IOException {
        ArrayList<User> user = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("data/user.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            user.add (new User(items[0], items[1],items[2]));
        }
        return user;
    }

    /**
        saveUserToFile - save the user to user database

        parameter: ArrayList<User> user

        name: tamjianxin, chengjiapao

    */
    public static void saveUserToFile(ArrayList<User> user) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < user.size(); i++) {
            sb.append (user.get(i).toCSVString() + "\n");
        }
        Files.write(Paths.get("data/user.csv"), sb.toString().getBytes());
    }

    /**
        checkExist - Check if Username entered exists the database

        get the username entered, 
        compare with the username in the database

        parameter: String username

        return: false

        name: chengjiapao

    */
    public boolean checkExist(String username){
        for(int i=0;i<admin.size();i++){
            if(admin.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
