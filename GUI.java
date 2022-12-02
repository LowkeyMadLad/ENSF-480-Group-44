import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

// Change
//main ProjectGUI class to generate the GUI components and implement actions
public class GUI extends JFrame implements ActionListener{

    //declaring all required variables (frames, buttons, global vars etc.)
    //for the GUI, we are not using a pre-designed layout, we are designing a custom layout

    private static JFrame frame;                //main frame for GUI
    private static JPanel panel;                //main panel for GUI
    private static JLabel welcomeLabel;
    private static JLabel sLabel;

    private static JLabel theatreLabel;
    private static JLabel movieLabel;
    private static JButton searchButton;
    private static JButton loginButton;
    private static JButton signUpButton;
    private static JButton cancelPaymentButton;
    private static JButton endProgram;
    //private static JComboBox<String> combo;
    private static JComboBox<String> theatreComboBox;
    private static JComboBox<String> movieComboBox;
    private static JComboBox<String> showTimesComboBox;

    private static JTextField usernameTextInput;
    private static JTextField passTextInput;
    private static JTextField seatNumInput;
    private static JTextField confirmationNumInput;

    private static JTextArea textArea;
    private static JScrollPane scrollBar;

    private static String movieSelection = "";
    private static String theatreSelection = "";
    private static String showTimeSelection = "";


    private static String[] theatresToChoose = {};

    public GUI(){}

    //main function for the ProjectGUI class
    public static void main(String[] args) throws IOException {

        //setting the look and feel of the GUI to a predefined look and feel
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException exception){
            exception.printStackTrace();
        }

        //declaring all the required fonts and colors for the GUI
        //the theme was selected using a Color theme picker online
        Font fontHeader = new Font(Font.DIALOG, Font.BOLD, 20);
        Font secondHeader = new Font(Font.DIALOG, Font.BOLD, 15);
        Font textFont = new Font(Font.DIALOG, Font.PLAIN, 12);
        Color bgColor = new Color(17, 75, 95, 255);
        Color textColor = new Color(198, 218, 191);
        Color buttonColor = new Color(136, 212, 152);
        Color buttonTextColor = new Color(26, 147, 111);
        
        String[] moviesToChoose = {"any option", "movie1", "movie2", "movie3"};
        String[] showTimes = {"time1", "time2", "time3"};

        //creating the main frame and panel for the GUI and setting sizes
        //also setting a default close operation for the program when the user exits the menu
        frame = new JFrame("Home Page");
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setting background color for the main panel and declaring a null layout to
        //create the custom layout
        //panel.setBackground(bgColor);
        panel.setLayout(null);
        frame.add(panel);

        //welcome label - main title for GUI
        welcomeLabel = new JLabel("Search for showtimes by theatre or movie:");
        welcomeLabel.setFont(fontHeader);
       // welcomeLabel.setForeground(textColor);
        welcomeLabel.setBounds(5,10,500,25);
        panel.add(welcomeLabel);

        //label declaration and adding to panel
        theatreLabel = new JLabel("Search by theatre:");
        theatreLabel.setFont(secondHeader);
        theatreLabel.setBounds(5, 50, 300, 25);
        panel.add(theatreLabel);

        //creating a label and input for order name
        movieLabel = new JLabel("Search by movie:");
        movieLabel.setFont(secondHeader);
        movieLabel.setBounds(5, 120, 600, 25);
        panel.add(movieLabel);
        

        searchButton = new JButton("Search");
        GUI gui  = new GUI();
        searchButton.addActionListener(gui);
        searchButton.setFont(secondHeader);
        searchButton.setBounds(5, 200, 200, 25);
        panel.add(searchButton);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new GUI());
        loginButton.setFont(textFont);
        loginButton.setBounds(20, 300, 200, 100);
        panel.add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new GUI());
        signUpButton.setFont(textFont);
        signUpButton.setBounds(240, 300, 200, 100);
        panel.add(signUpButton);

        cancelPaymentButton = new JButton("Cancel Payment");
        cancelPaymentButton.addActionListener(new GUI());
        cancelPaymentButton.setFont(textFont);
        cancelPaymentButton.setBounds(460, 300, 200, 100);
        panel.add(cancelPaymentButton);
        
        

        
        //theatreTextInput = new JTextField(16);
        //theatreTextInput.setBounds(5, 80, 300, 25);
        //panel.add(theatreTextInput);
        // Dropdown menu:

        // try {
        //     TheatreDatabase theatreDB = TheatreDatabase.getDB();
        //     for(int i = 0; i < theatreDB.getTheatreList().size() ; i++)
        //     {
        //         theatresToChoose[i] = theatreDB.getTheatreList().get(i);
        //     }

        // } catch (DBConnectException e1) {
        //     // TODO Auto-generated catch block
        //     e1.printStackTrace();
        // } catch (SQLException e1) {
        //     // TODO Auto-generated catch block
        //     e1.printStackTrace();
        // }

        theatreComboBox = new JComboBox<>(theatresToChoose);
        theatreComboBox.setBounds(5, 80, 300, 25);
        JLabel tLabel = new JLabel();
        tLabel.setBounds(90, 100, 400, 100);
        panel.add(theatreComboBox);
        panel.add(tLabel);

        // movieTextInput = new JTextField(16);
        // movieTextInput.setBounds(5, 150, 300, 25);
        // panel.add(movieTextInput);
        movieComboBox = new JComboBox<>(moviesToChoose);
        movieComboBox.setBounds(5, 150, 300, 25);
        JLabel mLabel = new JLabel();
        mLabel.setBounds(90, 100, 400, 100);
        panel.add(movieComboBox);
        panel.add(mLabel);

        showTimesComboBox = new JComboBox<>(showTimes);
        showTimesComboBox.setBounds(5, 250, 300, 25);
        mLabel.setBounds(90, 100, 400, 100);
        
        
        //creating a text area to display the number of hampers in the order form
        // textArea = new JTextArea();
        // textArea.setEditable(false);
        // textArea.setBounds(5, 500, 250, 75);
        // panel.add(textArea);

        //creating a scrollbar for the text area when number of hampers increases
        scrollBar = new JScrollPane(textArea);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setBounds(5, 500, 200, 95);
        panel.add(scrollBar);


        //creating a button to end the program and exit the GUI
        endProgram = new JButton("End Program");
        endProgram.setFont(textFont);
        endProgram.setBackground(buttonColor);
        endProgram.setForeground(buttonTextColor);
        endProgram.setBounds(285, 600, 130, 30);
        endProgram.addActionListener(new GUI());
        panel.add(endProgram);

        //setting the frame visibility to true
        frame.setVisible(true);
    }

    public void LoginPage()
    {
        JFrame frame1 = new JFrame("Login Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel loginPage = new JLabel("Login Page");
        //username.setFont(secondHeader);
        loginPage.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        loginPage.setBounds(5, 10, 300, 50);
        panel1.add(loginPage);

        JLabel username = new JLabel("Username");
        //username.setFont(secondHeader);
        username.setBounds(5, 90, 300, 25);
        panel1.add(username);

        //creating a label and input for order name
        JLabel password = new JLabel("Password");
        //password.setFont(secondHeader);
        password.setBounds(5, 150, 600, 25);
        panel1.add(password);
        

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Username: " + usernameTextInput.getText());
                System.out.println("Password: " + passTextInput.getText());
            }
        });
        submitButton.setBounds(5, 230, 200, 25);
        panel1.add(submitButton);

        
        usernameTextInput = new JTextField(16);
        usernameTextInput.setBounds(5, 120, 300, 25);
        panel1.add(usernameTextInput);

        passTextInput = new JTextField(16);
        passTextInput.setBounds(5, 180, 300, 25);
        panel1.add(passTextInput);

        frame1.setVisible(true);
    }

    public void SignUpPage()
    {
        JFrame frame1 = new JFrame("Sign Up Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel signUp = new JLabel("Sign Up Page");
        //username.setFont(secondHeader);
        signUp.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        signUp.setBounds(5, 10, 300, 50);
        panel1.add(signUp);


        JLabel username = new JLabel("Username");
        username.setBounds(10, 90, 300, 25);
        panel1.add(username);

        //creating a label and input for order name
        JLabel password = new JLabel("Password");
        //password.setFont(secondHeader);
        password.setBounds(20, 150, 600, 25);
        panel1.add(password);
        

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Username: " + usernameTextInput.getText());
                System.out.println("Password: " + passTextInput.getText());
            }
        });
        submitButton.setBounds(5, 230, 200, 25);
        panel1.add(submitButton);

        
        usernameTextInput = new JTextField(16);
        usernameTextInput.setBounds(5, 110, 300, 25);
        panel1.add(usernameTextInput);

        passTextInput = new JTextField(16);
        passTextInput.setBounds(5, 180, 300, 25);
        panel1.add(passTextInput);

        frame1.setVisible(true);
    }

    public void CancelPayment()
    {
        JFrame frame1 = new JFrame("Cancel Payment Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel signUp = new JLabel("Cancel Payment");
        //username.setFont(secondHeader);
        signUp.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        signUp.setBounds(5, 10, 300, 50);
        panel1.add(signUp);


        JLabel seatDispTxt = new JLabel("What is Your Seat #");
        seatDispTxt.setBounds(10, 90, 300, 25);
        panel1.add(seatDispTxt);

        //creating a label and input for order name
        JLabel confirmationTxt = new JLabel("What is Your Confirmation #");
        //password.setFont(secondHeader);
        confirmationTxt.setBounds(20, 150, 600, 25);
        panel1.add(confirmationTxt);
        

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Seat #: " + seatNumInput.getText());
                System.out.println("Confirmation #: " + confirmationNumInput.getText());
            }
        });
        submitButton.setBounds(5, 230, 200, 25);
        panel1.add(submitButton);

        
        seatNumInput = new JTextField(16);
        seatNumInput.setBounds(5, 110, 300, 25);
        panel1.add(seatNumInput);

        confirmationNumInput = new JTextField(16);
        confirmationNumInput.setBounds(5, 180, 300, 25);
        panel1.add(confirmationNumInput);

        frame1.setVisible(true);
    }

    //main actionPerformed method that does various things based on the button pressed
    //to use all buttons with one actionPerformed method, we use the e.getSource() method
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Search"))
        {   

            theatreSelection = theatreComboBox.getItemAt(theatreComboBox.getSelectedIndex());
            movieSelection = movieComboBox.getItemAt(movieComboBox.getSelectedIndex());
            System.out.println("Theatre: " + theatreComboBox.getItemAt(theatreComboBox.getSelectedIndex()));
            System.out.println("Movie: " + movieComboBox.getItemAt(movieComboBox.getSelectedIndex()));
            panel.add(showTimesComboBox);
            sLabel = new JLabel();
            panel.add(sLabel);
            panel.repaint();
            
        }

        if(e.getActionCommand().equals("Login"))
        {
            LoginPage();
        }

        if(e.getActionCommand().equals("Sign Up"))
        {
            SignUpPage();
        }

        if(e.getActionCommand().equals("Cancel Payment"))
        {
            CancelPayment();
        }

        showTimesComboBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Time: "+ showTimesComboBox.getItemAt(showTimesComboBox.getSelectedIndex()));
            }
        }


    });

        //code for ending the program if endProgram button clicked
        if (e.getSource().equals(endProgram)){
            System.exit(1);
            frame.setVisible(false);
        }
    }

}

