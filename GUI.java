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

    private static JLabel username;
    private static JTextField usernameTextInput;
    private static JLabel password;
    private static JTextField passTextInput;
    private static JButton submitButton;
    private static JButton endProgram;

    private static JTextArea textArea;
    private static JScrollPane scrollBar;


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

        //creating the main frame and panel for the GUI and setting sizes
        //also setting a default close operation for the program when the user exits the menu
        frame = new JFrame("Home Page");
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setting background color for the main panel and declaring a null layout to
        //create the custom layout
        //panel.setBackground(bgColor);
        panel.setLayout(null);
        frame.add(panel);

        //welcome label - main title for GUI
        welcomeLabel = new JLabel("Login Information");
        welcomeLabel.setFont(fontHeader);
       // welcomeLabel.setForeground(textColor);
        welcomeLabel.setBounds(90,10,400,25);
        panel.add(welcomeLabel);

        //label declaration and adding to panel
        username = new JLabel("Username");
        username.setFont(secondHeader);
        username.setBounds(5, 50, 300, 25);
        panel.add(username);

        //creating a label and input for order name
        password = new JLabel("Password");
        password.setFont(secondHeader);
        password.setBounds(5, 120, 600, 25);
        panel.add(password);
        

        submitButton = new JButton("Submit");
        GUI gui  = new GUI();
        submitButton.addActionListener(gui);
        submitButton.setFont(secondHeader);
        submitButton.setBounds(5, 200, 200, 25);
        panel.add(submitButton);

        
        usernameTextInput = new JTextField(16);
        usernameTextInput.setBounds(5, 80, 300, 25);
        panel.add(usernameTextInput);

        passTextInput = new JTextField(16);
        passTextInput.setBounds(5, 150, 300, 25);
        panel.add(passTextInput);

        //creating a text area to display the number of hampers in the order form
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(5, 500, 250, 75);
        panel.add(textArea);

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

    //main actionPerformed method that does various things based on the button pressed
    //to use all buttons with one actionPerformed method, we use the e.getSource() method
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Submit"))
        {
            System.out.println("Username: " + usernameTextInput.getText());
            System.out.println("Password: " + passTextInput.getText());
        }

        //code for ending the program if endProgram button clicked
        if (e.getSource().equals(endProgram)){
            System.exit(1);
            frame.setVisible(false);
        }
    }
}

