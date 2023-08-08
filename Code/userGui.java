/**
    *userGui.java

    name: tamjianxin, chengjiapao
    
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class userGui extends JFrame {

    private JPanel contentPane;
    private JTextField usernameTF;
    private JTextField newUsernameTF;
    private JPanel passwordPanel = new JPanel();
    private JPanel usernamePanel = new JPanel();
    private JLabel errorLabel = new JLabel("");
    private JLabel errorLabel1 = new JLabel("");
    
    ArrayList<User> mUser = readUserFromFile();
    private JTextField newPasswordTF;
    private JTextField passwordTF;
    public static JTextField welcomeTF;

    /*
     * Main method
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    userGui frame = new userGui();
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
    public userGui() throws IOException {
        setTitle("User");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1183, 657);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBounds(10, 10, 1149, 610);
        contentPane.add(bottomPanel);
        bottomPanel.setLayout(null);
        usernamePanel.setBackground(Color.WHITE);
        usernamePanel.setVisible(false);
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setVisible(false);
                
        passwordPanel.setBounds(247, 157, 902, 453);
        bottomPanel.add(passwordPanel);
        passwordPanel.setLayout(null);
                        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordLabel.setBounds(275, 143, 155, 25);
        passwordPanel.add(passwordLabel);
                        
        JLabel newPassowordLabel = new JLabel("New Password:");
        newPassowordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        newPassowordLabel.setBounds(275, 200, 155, 25);
        passwordPanel.add(newPassowordLabel);
                        
        JButton btnEnter_1 = new JButton("Enter");
        btnEnter_1.setBackground(Color.LIGHT_GRAY);
        btnEnter_1.setBorder(null);
        btnEnter_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEnter_1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //to update password
            if(newPasswordTF.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please insert New Password!");
            }
            else if(passwordTF.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please insert Password!");
            }
            else{
                //check if username and password match 
                boolean checkp = checkpassword(passwordTF.getText(),newPasswordTF.getText(), welcomeTF.getText());
                if(checkp==true){
                    JOptionPane.showMessageDialog(null, "Password updated!");
                    errorLabel.setText(null);
                    try {
                        saveUserToFile(mUser);
                    } catch (IOException ex) {
                        Logger.getLogger(userGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    passwordTF.setText(null);
                    newPasswordTF.setText(null);
                }
                else{
                    errorLabel.setText("Password do not match.");
                    newPasswordTF.setText(null);
                }
            }
        }
    });                 
    btnEnter_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnEnter_1.setBounds(397, 274, 109, 25);
    passwordPanel.add(btnEnter_1);
                        
    newPasswordTF = new JTextField();
    newPasswordTF.setColumns(10);
    newPasswordTF.setBounds(442, 200, 202, 25);
    passwordPanel.add(newPasswordTF);
                        
    passwordTF = new JTextField();
    passwordTF.setColumns(10);
    passwordTF.setBounds(442, 144, 202, 25);
    passwordPanel.add(passwordTF);
    errorLabel.setForeground(new Color(255, 0, 0));
    errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
                        
    errorLabel.setBorder(null);
    errorLabel.setBounds(489, 167, 155, 13);
    passwordPanel.add(errorLabel);
                        
    JLabel label1 = new JLabel("Change Password");
    label1.setIcon(new ImageIcon("images/password_50px.png"));
    label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
    label1.setBounds(314, 46, 310, 70);
    passwordPanel.add(label1);
                
    usernamePanel.setBounds(247, 157, 902, 453);
    bottomPanel.add(usernamePanel);
    usernamePanel.setLayout(null);
                
    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setBounds(275, 143, 155, 25);
    usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    usernamePanel.add(usernameLabel);
                
    usernameTF = new JTextField();
    usernameTF.setBounds(442, 144, 202, 25);
    usernamePanel.add(usernameTF);
    usernameTF.setColumns(10);
                
    JLabel newUsernameLabel = new JLabel("New Username:");
    newUsernameLabel.setBounds(275, 200, 155, 25);
    newUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    usernamePanel.add(newUsernameLabel);
                
    newUsernameTF = new JTextField();
    newUsernameTF.setBounds(442, 200, 202, 25);
    newUsernameTF.setColumns(10);
    usernamePanel.add(newUsernameTF);
                
    JButton btnEnter = new JButton("Enter");
    btnEnter.setBorder(null);
    btnEnter.setBackground(Color.LIGHT_GRAY);
    btnEnter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnEnter.setBounds(397, 274, 109, 25);
    btnEnter.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        //to edit username
        if(usernameTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please insert Username!");
        }
        else if (newUsernameTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please insert new Username!");
        }
        else{
            //check if username match 
            boolean checku = check(usernameTF.getText(),newUsernameTF.getText());
            if(checku==true){
                JOptionPane.showMessageDialog(null, "Username updated!");
                    errorLabel1.setText(null);
                    welcomeTF.setText(newUsernameTF.getText());
                    try {
                        saveUserToFile(mUser);
                    } catch (IOException ex) {
                        Logger.getLogger(userGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    usernameTF.setText(null);
                    newUsernameTF.setText(null);
            }
            else{
                errorLabel1.setText("Username doesn't exist");
                newUsernameTF.setText(null);
            }  
        }                    
    }
    });
    btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 18));
    usernamePanel.add(btnEnter);
    errorLabel1.setForeground(new Color(255, 0, 0));
                
    errorLabel1.setFont(new Font("Tahoma", Font.PLAIN, 9));
    errorLabel1.setBorder(null);
    errorLabel1.setBounds(495, 167, 149, 13);
    usernamePanel.add(errorLabel1);
                        
    JLabel label = new JLabel("Change Username");
    label.setIcon(new ImageIcon("images/username_50px.png"));
    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
    label.setBounds(314, 46, 303, 70);
    usernamePanel.add(label);
        
    JPanel darkBluePanel = new JPanel();
    darkBluePanel.setBackground(new Color(51, 153, 204));
    darkBluePanel.setBorder(null);
    darkBluePanel.setBounds(0, 0, 1149, 157);
    bottomPanel.add(darkBluePanel);
    darkBluePanel.setLayout(null);
        
    JLabel welcomeLabel = new JLabel("Welcome Back, User");
    welcomeLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 50));
    welcomeLabel.setBounds(320, 35, 306, 91);
    darkBluePanel.add(welcomeLabel);
        
    welcomeTF = new JTextField();
    welcomeTF.setEditable(false);
    welcomeTF.setBorder(null);
    welcomeTF.setOpaque(false);
    welcomeTF.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
    welcomeTF.setBounds(636, 34, 291, 91);
    darkBluePanel.add(welcomeTF);
    welcomeTF.setColumns(10);
        
    JPanel middleBluePanel = new JPanel();
    middleBluePanel.setBorder(null);
    middleBluePanel.setBackground(new Color(100, 149, 237));
    middleBluePanel.setBounds(0, 0, 248, 610);
    bottomPanel.add(middleBluePanel);
    middleBluePanel.setLayout(null);
        
    JPanel whitePanel = new JPanel();
    whitePanel.setBounds(0, 214, 248, 49);
    middleBluePanel.add(whitePanel);
    whitePanel.setLayout(null);
        
    JButton btnChangeUsername = new JButton("Change Username");
    btnChangeUsername.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            usernamePanel.setVisible(true);
            passwordPanel.setVisible(false);
        }
    });
    btnChangeUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnChangeUsername.setBackground(new Color(255, 255, 255));
    btnChangeUsername.setBorder(null);
    btnChangeUsername.setOpaque(false);
    btnChangeUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnChangeUsername.setBounds(10, 10, 228, 29);
    whitePanel.add(btnChangeUsername);
        
    JPanel whitePanel1 = new JPanel();
    whitePanel1.setLayout(null);
    whitePanel1.setBounds(0, 291, 248, 49);
    middleBluePanel.add(whitePanel1);
        
    JButton btnChangePassword = new JButton("Change Password");
    btnChangePassword.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            usernamePanel.setVisible(false);
            passwordPanel.setVisible(true);
        }
    });
    btnChangePassword.setOpaque(false);
    btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnChangePassword.setBorder(null);
    btnChangePassword.setBackground(Color.WHITE);
    btnChangePassword.setBounds(10, 10, 228, 29);
    whitePanel1.add(btnChangePassword);
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
        checkpassword - Check if password entered match the database

        get the username and password entered, 
        compare with the username and password in the database, 
        if equal then set the new password 

        parameter: String password,String newpassword,String username

        return: false

        name: chengjiapao

    */
    private boolean checkpassword(String password,String newpassword,String username){
        for(int i=0;i<mUser.size();i++){
                if(mUser.get(i).getPassword().equals(password) && mUser.get(i).getUsername().equals(username)){
                    mUser.get(i).setPassword(newpassword);
                    return true;
                }
            }
        return false;
    }
    
    /**
        check - Check if Username entered match the database

        get the username entered, compare with the username in the database, 
        if equal then set username 

        parameter: String username, String newUsername

        return: false

        name: chengjiapao

    */
    private boolean check(String username,String newUsername){
        for(int i=0;i<mUser.size();i++){
                if(mUser.get(i).getUsername().equals(username)){
                    mUser.get(i).setUsername(newUsername);
                    return true;
                }
            }
        return false;
    }
}
