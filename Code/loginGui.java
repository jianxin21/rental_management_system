/**
    *userGui.java

    name: tamjianxin, chengjiapao    
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class loginGui extends JFrame {

    private JPanel contentPane;
    public static JTextField usernameTF;
    public static JPasswordField passwordTF;
    public static JPanel adminPanel = new JPanel();
    public static JPanel chooseUserPanel = new JPanel();
    public static JPanel userPanel = new JPanel();
    public static JPanel registerPanel = new JPanel();
    public static JPanel listPanel = new JPanel();
    public static JPanel welcomePanel = new JPanel();
    private JButton btnClear = new JButton("Clear");
    DefaultTableModel model;

    ArrayList<Property> property = readPropertyFromFile();
    ArrayList<Admin> admin = readAdminFromFile();
    ArrayList<User> mUser = readUserFromFile();
    public static JTextField usernameTF1;
    private JPasswordField passwordTF1;
    private JTextField usernameTF3;
    private JTextField passwordTF3;
    private JTable jTable1;
    private JTextField searchTF;
    
    /*
         * Main method
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginGui frame = new loginGui();
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
    public loginGui() throws IOException {
        setResizable(false);
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1182, 656);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        welcomePanel.setVisible(true);
        chooseUserPanel.setVisible(false);
        
        registerPanel.setVisible(false);
        userPanel.setVisible(false);
        adminPanel.setVisible(false);
        
        listPanel.setVisible(false);
        listPanel.setBackground(SystemColor.window);
        listPanel.setBounds(0, 0, 1168, 619);
        contentPane.add(listPanel);
        listPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 159, 1148, 402);
        listPanel.add(scrollPane);
        
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "ID", "Address", "Size", "No of room", "No of bathroom", "Facilities", "Rental Price", "Property type", "Status", "Rental rate"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(263);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(81);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(331);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(141);
        jTable1.getColumnModel().getColumn(8).setResizable(false);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(81);
        jTable1.getColumnModel().getColumn(9).setResizable(false);
        
        scrollPane.setViewportView(jTable1);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBorder(null);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBackground(SystemColor.window);
        btnLogin.setIcon(new ImageIcon("images/male_user_32px.png"));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerPanel.setVisible(false);
                chooseUserPanel.setVisible(true);
                adminPanel.setVisible(false);
                userPanel.setVisible(false);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
            }
        });
        btnLogin.setBounds(1044, 71, 86, 40);
        listPanel.add(btnLogin);
        
        searchTF = new JTextField();
        searchTF.setBounds(348, 79, 340, 29);
        listPanel.add(searchTF);
        searchTF.setColumns(10);
        
        JLabel searchLabel = new JLabel("");
        searchLabel.setIcon(new ImageIcon("images/search_24px.png"));
        searchLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        searchLabel.setBounds(304, 82, 30, 29);
        listPanel.add(searchLabel);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //search property according to property type
                if(searchTF.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No data insert!");
                }
                else{
                    model.setRowCount(0);
                    searchTable(searchTF.getText().toUpperCase());  
                }
            }
        });
        btnSearch.setBackground(SystemColor.window);
        btnSearch.setBorder(null);
        btnSearch.setOpaque(false);
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearch.setBounds(698, 79, 85, 29);
        listPanel.add(btnSearch);
        
        JLabel tittleLabel = new JLabel("Cyberjaya");
        tittleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tittleLabel.setIcon(new ImageIcon("images/marker_24px.png"));
        tittleLabel.setBounds(38, 24, 126, 40);
        listPanel.add(tittleLabel);
        btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTF.setText(null);
                model.setRowCount(0);
                createTable();
            }
        });
        btnClear.setOpaque(false);
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClear.setBorder(null);
        btnClear.setBackground(Color.WHITE);
        btnClear.setBounds(796, 79, 85, 29);
        listPanel.add(btnClear);
        
        JButton btnExit_1 = new JButton("Exit");
        btnExit_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //close program
            }
        });
        btnExit_1.setIcon(new ImageIcon("images/exit_24px.png"));
        btnExit_1.setBorder(null);
        btnExit_1.setBackground(Color.WHITE);
        btnExit_1.setBounds(1065, 10, 93, 44);
        listPanel.add(btnExit_1);
        
        JLabel lblNewLabel_1 = new JLabel("*Rental Rate = Price per square feet");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setBounds(10, 571, 205, 13);
        listPanel.add(lblNewLabel_1);
        adminPanel.setBackground(SystemColor.window);
        
        adminPanel.setBounds(0, 0, 1168, 619);
        contentPane.add(adminPanel);
        adminPanel.setLayout(null);
        
        JLabel adminLabel = new JLabel("Admin");
        adminLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        adminLabel.setBounds(535, 103, 126, 61);
        adminPanel.add(adminLabel);
        
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        usernameLabel.setBounds(475, 263, 154, 35);
        adminPanel.add(usernameLabel);
        
        JLabel adminPicLabel = new JLabel("");
        adminPicLabel.setBackground(Color.WHITE);
        adminPicLabel.setIcon(new ImageIcon("images/admin.png"));
        adminPicLabel.setBounds(216, 204, 187, 181);
        adminPanel.add(adminPicLabel);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        passwordLabel.setBounds(475, 338, 154, 35);
        adminPanel.add(passwordLabel);
        
        usernameTF = new JTextField();
        usernameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        usernameTF.setBounds(639, 269, 236, 35);
        adminPanel.add(usernameTF);
        usernameTF.setColumns(10);
        
        JButton btnBack = new JButton("");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setBackground(SystemColor.window);
        btnBack.setBorder(null);
        btnBack.setIcon(new ImageIcon("images/back_50px.png"));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseUserPanel.setVisible(true);
                adminPanel.setVisible(false);
                userPanel.setVisible(false);
                registerPanel.setVisible(false);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
                
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBack.setBounds(35, 500, 120, 50);
        adminPanel.add(btnBack);
        
        JButton btnEnter = new JButton("Enter");
        btnEnter.setBorder(null);
        btnEnter.setBackground(SystemColor.inactiveCaption);
        btnEnter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //login as admin
                 boolean check = checkAdminLogin(usernameTF.getText(),passwordTF.getText());
                    if(check==true) {
                       JOptionPane.showMessageDialog(null, "Login Sucessful!");
                       
                        usernameTF.setText(null);
                        passwordTF.setText(null);
                        chooseUserPanel.setVisible(true);
                        adminPanel.setVisible(false);
                        userPanel.setVisible(false);
                        registerPanel.setVisible(false);
                        listPanel.setVisible(false);
                        welcomePanel.setVisible(false);
                    try {
                        adminGui aGui = new adminGui();
                        aGui.setVisible(true);
                        aGui.setLocationRelativeTo(null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }            
                    }
                    else if(usernameTF.getText().isEmpty() && passwordTF.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please insert Username/Password!");
                        usernameTF.setText(null);
                        passwordTF.setText(null);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Wrong Username/Password"); 
                        usernameTF.setText(null);
                        passwordTF.setText(null);
                    }
            }
        });
        btnEnter.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnEnter.setBounds(512, 414, 136, 40);
        adminPanel.add(btnEnter);
        
        passwordTF = new JPasswordField();
        passwordTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        passwordTF.setBounds(639, 339, 236, 36);
        adminPanel.add(passwordTF);
        userPanel.setBackground(SystemColor.window);
        
        userPanel.setBounds(0, 0, 1168, 619);
        contentPane.add(userPanel);
        userPanel.setLayout(null);
        
        JLabel usernameLabel1 = new JLabel("Username:");
        usernameLabel1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        usernameLabel1.setBounds(475, 263, 154, 35);
        userPanel.add(usernameLabel1);
        
        JLabel passwordLabel1 = new JLabel("Password:");
        passwordLabel1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        passwordLabel1.setBounds(475, 338, 154, 35);
        userPanel.add(passwordLabel1);
        
        JLabel lblNewLabel_2 = new JLabel("User");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_2.setBounds(535, 103, 96, 59);
        userPanel.add(lblNewLabel_2);
        
        usernameTF1 = new JTextField();
        usernameTF1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        usernameTF1.setBounds(639, 269, 236, 35);
        userPanel.add(usernameTF1);
        usernameTF1.setColumns(10);
        
        passwordTF1 = new JPasswordField();
        passwordTF1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        passwordTF1.setBounds(639, 338, 236, 35);
        userPanel.add(passwordTF1);
        
        JButton btnEnter1 = new JButton("Enter");
        btnEnter1.setBorder(null);
        btnEnter1.setBackground(SystemColor.inactiveCaption);
        btnEnter1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEnter1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //login as tenant 
                boolean check = checkUserLogin(usernameTF1.getText(),passwordTF1.getText());
                
                if(check==true) {
                    JOptionPane.showMessageDialog(null, "Login Sucessful!");
                            
                    try {
                        userGui uGui = new userGui();
                        userGui.welcomeTF.setText(loginGui.usernameTF1.getText());
                        uGui.setTitle("Welcome Back, "+loginGui.usernameTF1.getText() + " !");
                        uGui.setVisible(true);
                        uGui.setLocationRelativeTo(null);
                    } catch (IOException e1) {
                         e1.printStackTrace();
                    }
                    usernameTF1.setText(null);
                    passwordTF1.setText(null);
                    chooseUserPanel.setVisible(true);
                    adminPanel.setVisible(false);
                    userPanel.setVisible(false);
                    registerPanel.setVisible(false);
                    listPanel.setVisible(false);
                    welcomePanel.setVisible(false);
                }
                else {
                    usernameTF1.setText(null);
                    passwordTF1.setText(null);
                }
                
            }
        });
        btnEnter1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnEnter1.setBounds(512, 414, 136, 40);
        userPanel.add(btnEnter1);
        
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.setBorder(null);
        btnNewButton_1.setBackground(SystemColor.window);
        btnNewButton_1.setIcon(new ImageIcon("images/back_50px.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminPanel.setVisible(false);
                chooseUserPanel.setVisible(true);
                userPanel.setVisible(false);
                registerPanel.setVisible(false);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setBounds(35, 500, 120, 50);
        userPanel.add(btnNewButton_1);
        
        JLabel askLabel = new JLabel("Not a member?");
        askLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        askLabel.setBounds(779, 372, 96, 18);
        userPanel.add(askLabel);
        
        JLabel signUpLabel = new JLabel("Sign Up here");
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                registerPanel.setVisible(true);
                chooseUserPanel.setVisible(false);
                adminPanel.setVisible(false);
                userPanel.setVisible(false);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
            }
        });
        signUpLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        signUpLabel.setForeground(Color.RED);
        signUpLabel.setBounds(790, 390, 85, 21);
        userPanel.add(signUpLabel);
        
        JLabel userPicLabel = new JLabel("");
        userPicLabel.setIcon(new ImageIcon("images/user.png"));
        userPicLabel.setBounds(261, 204, 187, 181);
        userPanel.add(userPicLabel);
        
        registerPanel.setBackground(SystemColor.window);
        registerPanel.setBounds(0, 0, 1168, 619);
        contentPane.add(registerPanel);
        registerPanel.setLayout(null);
        
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        registerLabel.setBounds(535, 103, 155, 69);
        registerPanel.add(registerLabel);
        
        JLabel usernameLabel3 = new JLabel("Username:");
        usernameLabel3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        usernameLabel3.setBounds(475, 263, 154, 50);
        registerPanel.add(usernameLabel3);
        
        JLabel passwordLabel3 = new JLabel("Password:");
        passwordLabel3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        passwordLabel3.setBounds(475, 338, 154, 35);
        registerPanel.add(passwordLabel3);
        
        usernameTF3 = new JTextField();
        usernameTF3.setBounds(639, 269, 236, 35);
        registerPanel.add(usernameTF3);
        usernameTF3.setColumns(10);
        
        passwordTF3 = new JTextField();
        passwordTF3.setBounds(639, 338, 236, 35);
        registerPanel.add(passwordTF3);
        passwordTF3.setColumns(10);
        
        JButton btnEnter3 = new JButton("Enter");
        btnEnter3.setBackground(SystemColor.inactiveCaption);
        btnEnter3.setBorder(null);
        btnEnter3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check if the username entered exist in the database
                boolean check = checkExist(usernameTF3.getText());
                if(check==true){
                    //must not have duplicate username
                    JOptionPane.showMessageDialog(null, "Username already existed!");
                    usernameTF3.setText(null);
                    passwordTF3.setText(null);
                }
                else if(usernameTF3.getText().isEmpty() || passwordTF3.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please insert Username/Password!");
                    usernameTF3.setText(null);
                    passwordTF3.setText(null);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Sucessful!");
                    String userStatus = "new";
                    mUser.add(new User(usernameTF3.getText(),passwordTF3.getText(),userStatus)); //add new user
                    usernameTF3.setText(null);
                    passwordTF3.setText(null);
                    try {
                        saveUserToFile(mUser);
                    } catch (IOException ex) {
                        Logger.getLogger(loginGui.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        
        btnEnter3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEnter3.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnEnter3.setBounds(512, 414, 136, 40);
        registerPanel.add(btnEnter3);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerPanel.setVisible(false);
                chooseUserPanel.setVisible(false);
                adminPanel.setVisible(false);
                userPanel.setVisible(true);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
            }
        });
        btnNewButton.setBackground(SystemColor.window);
        btnNewButton.setIcon(new ImageIcon("images/back_50px.png"));
        btnNewButton.setOpaque(false);
        btnNewButton.setBorder(null);
        btnNewButton.setBounds(35, 500, 120, 50);
        registerPanel.add(btnNewButton);
        
        JLabel adminPicLabel_1 = new JLabel("");
        adminPicLabel_1.setIcon(new ImageIcon("images/registration_100px.png"));
        adminPicLabel_1.setBackground(Color.WHITE);
        adminPicLabel_1.setBounds(450, 65, 100, 107);
        registerPanel.add(adminPicLabel_1);
        
        chooseUserPanel.setBackground(SystemColor.window);
        chooseUserPanel.setBounds(0, 0, 1168, 619);
        contentPane.add(chooseUserPanel);
        chooseUserPanel.setLayout(null);
        
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdmin.setBorder(null);
        btnAdmin.setForeground(SystemColor.text);
        btnAdmin.setBackground(SystemColor.activeCaption);
        
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminPanel.setVisible(true);
                chooseUserPanel.setVisible(false);
                userPanel.setVisible(false);
                registerPanel.setVisible(false);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
            }
        });
        btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAdmin.setBounds(617, 437, 149, 56);
        chooseUserPanel.add(btnAdmin);
        JButton btnUser = new JButton("User");
        btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUser.setBackground(SystemColor.activeCaption);
        btnUser.setForeground(SystemColor.text);
        btnUser.setBorder(null);
        btnUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    mUser = readUserFromFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                userPanel.setVisible(true);
                adminPanel.setVisible(false);
                chooseUserPanel.setVisible(false);
                registerPanel.setVisible(false);
                listPanel.setVisible(false);
                welcomePanel.setVisible(false);
            }
        });
        btnUser.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnUser.setBounds(393, 435, 149, 56);
        chooseUserPanel.add(btnUser);
        
        JLabel welcomeLabel1 = new JLabel("Cyberjaya Rental Management System");
        welcomeLabel1.setFont(new Font("Times New Roman", Font.BOLD, 45));
        welcomeLabel1.setBounds(199, 58, 757, 44);
        chooseUserPanel.add(welcomeLabel1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("images/bg1.jpg"));
        lblNewLabel.setBackground(SystemColor.window);
        lblNewLabel.setBounds(173, 0, 800, 619);
        chooseUserPanel.add(lblNewLabel);
        
        //homepage property list
        JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    property = readPropertyFromFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                model.setRowCount(0);
                createTable();
                registerPanel.setVisible(false);
                chooseUserPanel.setVisible(false);
                adminPanel.setVisible(false);
                userPanel.setVisible(false);
                listPanel.setVisible(true);
                welcomePanel.setVisible(false);
            }
        });
        btnNewButton_2.setOpaque(false);
        btnNewButton_2.setIcon(new ImageIcon("images/back_50px.png"));
        btnNewButton_2.setBackground(SystemColor.window);
        btnNewButton_2.setBorder(null);
        btnNewButton_2.setBounds(35, 500, 120, 50);
        chooseUserPanel.add(btnNewButton_2);
        welcomePanel.setBackground(SystemColor.window);
        welcomePanel.setBounds(0, 0, 1168, 619);
        contentPane.add(welcomePanel);
        welcomePanel.setLayout(null);
        
        //launch the program
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jTable1.getRowCount()==0) {
                    createTable();
                }
                registerPanel.setVisible(false);
                chooseUserPanel.setVisible(false);
                adminPanel.setVisible(false);
                userPanel.setVisible(false);
                listPanel.setVisible(true);
                welcomePanel.setVisible(false);
                setTitle("Cyberjaya Rental Management System");
                
            }
        });
        btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnStart.setBorder(null);
        btnStart.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
        btnStart.setBackground(SystemColor.info);
        btnStart.setBounds(490, 533, 156, 53);
        welcomePanel.add(btnStart);
        
        JLabel startLabel = new JLabel("");
        startLabel.setIcon(new ImageIcon("images/rent.jpg"));
        startLabel.setBounds(250, 154, 718, 442);
        welcomePanel.add(startLabel);
        
        JLabel welcomeLabel_1 = new JLabel("Welcome to");
        welcomeLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 35));
        welcomeLabel_1.setBounds(490, 32, 187, 44);
        welcomePanel.add(welcomeLabel_1);
        
        JLabel welcomeLabel1_1 = new JLabel("Cyberjaya Rental Management System");
        welcomeLabel1_1.setFont(new Font("Times New Roman", Font.BOLD, 45));
        welcomeLabel1_1.setBounds(214, 86, 757, 44);
        welcomePanel.add(welcomeLabel1_1);
        
        JButton btnExit = new JButton("Exit");
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setIcon(new ImageIcon("images/exit_24px.png"));
        btnExit.setBorder(null);
        btnExit.setBackground(SystemColor.window);
        btnExit.setBounds(1081, 565, 77, 44);
        welcomePanel.add(btnExit);
        
        /*
         * Show User Panel
         */
    }
    
    /**
        searchTable - use to search by property type 

        if the type of property entered by the user exist, display in a table

        name: tamjianxin

    */
    public void searchTable(String type) {
        for(int i=0;i<property.size();i++){
            if((property.get(i).getStatus().equals("ACTIVE")) && (property.get(i).getPropertyType().equals(type))){
                model = (DefaultTableModel) jTable1.getModel();
                Object rowData[] = new Object[10];
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
    }

    /**
        createTable - use to create property list 

        get the details of a property, put them into a table(model)

        name: tamjianxin

    */
    public void createTable() {
        for(int i=0;i<property.size();i++){
            if(property.get(i).getStatus().equals("ACTIVE")){
                model = (DefaultTableModel) jTable1.getModel();
                Object rowData[] = new Object[10];
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
    }
        
    /**
        checkExist - check if the user username exist in the database

        parameter: String username
        
        return: false

        name: chengjiapao

    */
    public boolean checkExist(String username){
        for(int i=0;i<mUser.size();i++){
            if(mUser.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    
    /**
        checkUserLogin - compare username and password entered with the database

        parameter: String username, String password
        
        return: false

        name: chengjiapao

    */
    public boolean checkUserLogin(String username,String password) {
        for(int i=0;i<mUser.size();i++) {
            if((mUser.get(i).getUsername().equals(username)) && (mUser.get(i).getPassword().equals(password))){
                if(mUser.get(i).getUserStatus().equals("accept")||(mUser.get(i).getUserStatus().equals("new"))) {
                    return true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "User " + username + " has been rejected by admin.\n"
                            + "For further assistance please contact 03-3546789");
                    return false;
                }
            }    
        }
        JOptionPane.showMessageDialog(null, "Wrong Username/Password");
        return false;
    }   
        
    /**
        checkAdminLogin - compare username and password entered with the database

        parameter: String username, String password

        return: false

        name: tamjianxin

    */
    public boolean checkAdminLogin(String username, String password) {
        for(int i=0;i<admin.size();i++) {
            if((admin.get(i).getUsername().equals(username)) && (admin.get(i).getPassword().equals(password))) {
                return true;
            }
        }
        return false;
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
}
