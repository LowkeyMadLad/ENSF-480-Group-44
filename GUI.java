import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

// Change
//main ProjectGUI class to generate the GUI components and implement actions
public class GUI extends JFrame implements ActionListener{
    User user = new User();
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
    private static JButton seatMapbutton;
    private static JButton endProgram;
    //private static JComboBox<String> combo;
    private static JComboBox<String> theatreComboBox;
    private static JComboBox<String> movieComboBox;
    private static JComboBox<String> showTimesComboBox;

    private static JTextField usernameTextInput;
    private static JTextField passTextInput;

    private static JTextField nameTextInput;
    private static JTextField emailTextInput;
    private static JTextField addressTextInput;
    private static JTextField creditTextInput;
    private static JTextField cvvTextInput;
    

    private static JTextField seatNumInput;
    private static JTextField confirmationNumInput;

    private static JTextArea textArea;
    private static JScrollPane scrollBar;

    private static String movieSelection = "";
    private static String theatreSelection = "";
    private static String showTimeSelection = "";


    private static String[] theatresToChoose = {};
    private static String[] theatreToChoose;
    private static String[] moviesToChoose;
    private static String[] showTimes;
    public static RegisteredUser RU;

    public GUI(){}

    //main function for the GU class
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
        


        loginButton = new JButton("Login");
        loginButton.addActionListener(new GUI());
        loginButton.setFont(textFont);
        loginButton.setBounds(20, 350, 200, 100);
        panel.add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new GUI());
        signUpButton.setFont(textFont);
        signUpButton.setBounds(240, 350, 200, 100);
        panel.add(signUpButton);

        cancelPaymentButton = new JButton("Cancel Payment");
        cancelPaymentButton.addActionListener(new GUI());
        cancelPaymentButton.setFont(textFont);
        cancelPaymentButton.setBounds(460, 350, 200, 100);
        panel.add(cancelPaymentButton);
        
        // seatMapbutton = new JButton("Seat Map Display");
        // seatMapbutton.addActionListener(new GUI());
        // seatMapbutton.setFont(textFont);
        // seatMapbutton.setBounds(460, 190, 200, 100);
        // panel.add(seatMapbutton);
        

        
        // JTextField theatreTextInput = new JTextField(16);
        // theatreTextInput.setBounds(5, 80, 300, 25);
        // panel.add(theatreTextInput);
        //Dropdown menu:

        try {
            TheatreDatabase theatreDB = TheatreDatabase.getDB();
            ArrayList<String> theatreList = theatreDB.getTheatreList();
            theatreToChoose = new String[theatreList.size() +1];
            for(int i=0;i<theatreList.size();i++)
            {
                theatreToChoose[i] = theatreList.get(i);
            }
            theatreToChoose[theatreList.size()] = "any";
            theatreComboBox = new JComboBox<>(theatreToChoose);
            theatreComboBox.setBounds(5, 80, 300, 25);
            JLabel tLabel = new JLabel();
            tLabel.setBounds(90, 100, 400, 100);
            panel.add(theatreComboBox);
            panel.add(tLabel);

        } catch (DBConnectException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        try {
            TheatreDatabase theatreDB = TheatreDatabase.getDB();
            ArrayList<String> movieList = theatreDB.getMovieList();
            moviesToChoose = new String[movieList.size() +1 ];
            for(int i=0;i<movieList.size();i++)
            {
                moviesToChoose[i] = movieList.get(i);
            }
            for(String x: movieList)
            {
                System.out.println(x);
            }

            moviesToChoose[movieList.size()] = "any";
            movieComboBox = new JComboBox<>(moviesToChoose);
            movieComboBox.setBounds(5, 150, 300, 25);
            JLabel mLabel = new JLabel();
            mLabel.setBounds(90, 100, 400, 100);
            panel.add(movieComboBox);
            panel.add(mLabel);

        } catch (DBConnectException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
            
        searchButton = new JButton("Search");
        GUI gui  = new GUI();
        searchButton.addActionListener(gui);
        searchButton.setFont(secondHeader);
        searchButton.setBounds(5, 200, 200, 25);
        panel.add(searchButton);




        // //creating a scrollbar for the text area when number of hampers increases
        // scrollBar = new JScrollPane(textArea);
        // scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // scrollBar.setBounds(5, 500, 200, 95);
        // panel.add(scrollBar);


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

    public void confirmationPaymentPage(ArrayList<Ticket> ticket)
    {
        JFrame frame1 = new JFrame("Confirmation Page");
        frame1.setSize(350, 350);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel confirmPaymentPage = new JLabel("Confirmation Of Your Payment");
        //username.setFont(secondHeader);
        confirmPaymentPage.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        confirmPaymentPage.setBounds(5, 10, 300, 50);
        panel1.add(confirmPaymentPage);

        JLabel movieInfo = new JLabel("Movie: " + ticket.get(0).getMovie());
        movieInfo.setBounds(5,70, 300, 50);
        panel1.add(movieInfo);

        JLabel theatreInfo = new JLabel("Theatre: " + ticket.get(0).getTheatre());
        theatreInfo.setBounds(5,130, 300, 50);
        panel1.add(theatreInfo);

        JLabel timeInfo = new JLabel("Time: " + ticket.get(0).getShowtime().toString());
        timeInfo.setBounds(5,190, 300, 50);
        panel1.add(timeInfo);

        String seats = "Seats Requested: ";
        for(Ticket x : ticket)
        {
            seats += x.getSeat() + " ";
        }

        JLabel displaySeats = new JLabel(seats);
        displaySeats.setBounds(5,250, 300, 50);
        panel1.add(displaySeats);

        frame1.setVisible(true);
    }

    public void PaymentPage(String movie, String theatre, Timestamp time, ArrayList<String> seatsRequested)
    {
        JFrame frame1 = new JFrame("Payment Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel paymentPage = new JLabel("Payment Page");
        //username.setFont(secondHeader);
        paymentPage.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        paymentPage.setBounds(5, 10, 300, 50);
        panel1.add(paymentPage);

        JLabel movieInfo = new JLabel("Movie: " + movie);
        movieInfo.setBounds(400,10, 300, 50);
        panel1.add(movieInfo);

        JLabel theatreInfo = new JLabel("Theatre: " + theatre);
        theatreInfo.setBounds(400,70, 300, 50);
        panel1.add(theatreInfo);

        JLabel timeInfo = new JLabel("Time: " + time);
        timeInfo.setBounds(400,140, 300, 50);
        panel1.add(timeInfo);

        String seats = "Seats Requested: ";
        for(String x : seatsRequested)
        {
            seats += x + " ";
        }

        JLabel displaySeats = new JLabel(seats);
        displaySeats.setBounds(400,210, 300, 50);
        panel1.add(displaySeats);

        JLabel name = new JLabel("Name");
        name.setBounds(10, 60, 300, 25);
        panel1.add(name);

        //creating a label and input for order name
        JLabel email = new JLabel("Email Address");
        //password.setFont(secondHeader);
        email.setBounds(10, 130, 600, 25);
        panel1.add(email);

        JLabel address = new JLabel("Address");
        address.setBounds(10, 200, 300, 25);
        panel1.add(address);

        //creating a label and input for order name
        JLabel creditCardInfo = new JLabel("Credit Card #");
        //password.setFont(secondHeader);
        creditCardInfo.setBounds(10, 270, 600, 25);
        panel1.add(creditCardInfo);

        JLabel CVV = new JLabel("CVV");
        CVV.setBounds(10, 340, 300, 25);
        panel1.add(CVV);
    
        nameTextInput = new JTextField(16);
        nameTextInput.setBounds(5, 80, 300, 25);
        panel1.add(nameTextInput);

        emailTextInput = new JTextField(16);
        emailTextInput.setBounds(5, 150, 300, 25);
        panel1.add(emailTextInput);

        addressTextInput = new JTextField(16);
        addressTextInput.setBounds(5, 220, 300, 25);
        panel1.add(addressTextInput);

        creditTextInput = new JTextField(16);
        creditTextInput.setBounds(5, 290, 300, 25);
        panel1.add(creditTextInput);

        cvvTextInput = new JTextField(16);
        cvvTextInput.setBounds(5, 360, 300, 25);
        panel1.add(cvvTextInput);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    TheatreDatabase database = TheatreDatabase.getDB();
                    ArrayList<Ticket> ticketTotal = new ArrayList<Ticket>();
                    for(String x : seatsRequested)
                    {
                        Ticket ticket = new Ticket(theatre, movie, time, x);
                        database.insertTicket(ticket , nameTextInput.getText());
                        ticketTotal.add(ticket);
                        
                    }
                    confirmationPaymentPage(ticketTotal);
                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException e2)
                {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception e1)
                {
                    JOptionPane.showMessageDialog(null, "Enter All Information", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }

                frame1.dispose();
            }
        });
        submitButton.setBounds(5, 600, 200, 25);
        panel1.add(submitButton);

        frame1.setVisible(true);

    }

    public void RUPaymentPage(String movie, String theatre, Timestamp time, ArrayList<String> seatsRequested)
    {
        JFrame frame1 = new JFrame("Payment Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel paymentPage = new JLabel("Payment Page");
        //username.setFont(secondHeader);
        paymentPage.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        paymentPage.setBounds(5, 10, 300, 50);
        panel1.add(paymentPage);

        JLabel movieInfo = new JLabel("Movie: " + movie);
        movieInfo.setBounds(400,10, 300, 50);
        panel1.add(movieInfo);

        JLabel theatreInfo = new JLabel("Theatre: " + theatre);
        theatreInfo.setBounds(400,70, 300, 50);
        panel1.add(theatreInfo);

        JLabel timeInfo = new JLabel("Time: " + time);
        timeInfo.setBounds(400,140, 300, 50);
        panel1.add(timeInfo);

        String seats = "Seats Requested: ";
        for(String x : seatsRequested)
        {
            seats += x + " ";
        }

        JLabel displaySeats = new JLabel(seats);
        displaySeats.setBounds(400,210, 300, 50);
        panel1.add(displaySeats);

        JLabel name = new JLabel("Name: " + RU.getName());
        name.setBounds(10, 60, 300, 25);
        panel1.add(name);

        //creating a label and input for order name
        JLabel email = new JLabel("Email Address: " + RU.getEmail());
        //password.setFont(secondHeader);
        email.setBounds(10, 90, 600, 25);
        panel1.add(email);

        JLabel address = new JLabel("Address: " + RU.getAddress());
        address.setBounds(10, 120, 300, 25);
        panel1.add(address);

        //creating a label and input for order name
        JLabel creditCardInfo = new JLabel("Credit Card #: " + RU.getCard().getCardNumber());
        //password.setFont(secondHeader);
        creditCardInfo.setBounds(10, 150, 600, 25);
        panel1.add(creditCardInfo);

        JLabel CVV = new JLabel("CVV: " + RU.getCard().getCvv());
        CVV.setBounds(10, 180, 300, 25);
        panel1.add(CVV);
    
        // nameTextInput = new JTextField(16);
        // nameTextInput.setBounds(5, 80, 300, 25);
        // panel1.add(nameTextInput);

        // emailTextInput = new JTextField(16);
        // emailTextInput.setBounds(5, 150, 300, 25);
        // panel1.add(emailTextInput);

        // addressTextInput = new JTextField(16);
        // addressTextInput.setBounds(5, 220, 300, 25);
        // panel1.add(addressTextInput);

        // creditTextInput = new JTextField(16);
        // creditTextInput.setBounds(5, 290, 300, 25);
        // panel1.add(creditTextInput);

        // cvvTextInput = new JTextField(16);
        // cvvTextInput.setBounds(5, 360, 300, 25);
        // panel1.add(cvvTextInput);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    TheatreDatabase database = TheatreDatabase.getDB();
                    ArrayList<Ticket> ticketTotal = new ArrayList<Ticket>();
                    for(String x : seatsRequested)
                    {
                        Ticket ticket = new Ticket(theatre, movie, time, x);
                        database.insertTicket(ticket , RU.getName());
                        ticketTotal.add(ticket);
                        
                    }
                    confirmationPaymentPage(ticketTotal);
                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException e2)
                {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                frame1.dispose();
            }
        });
        submitButton.setBounds(5, 600, 200, 25);
        panel1.add(submitButton);

        frame1.setVisible(true);

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
                try {
                    LoginDatabase loginDB = new LoginDatabase();
                    TheatreDatabase theatreDatabase = TheatreDatabase.getDB();
                    boolean verify = loginDB.checkLoginInformation(usernameTextInput.getText(), passTextInput.getText());
                    
                    if(verify == true)
                    {
                        RU  = loginDB.getLoginInformation(usernameTextInput.getText(), passTextInput.getText());
                        System.out.println(verify);
                        // System.out.println(verify);

                        JOptionPane.showMessageDialog(null, "Successful Login", "Login", JOptionPane.PLAIN_MESSAGE);



                        JLabel usernameHomePg = new JLabel("Hello " + RU.getName());
                        usernameHomePg.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
                        usernameHomePg.setBounds(500, 10, 100, 25);
                        
                        panel.add(usernameHomePg);
                        panel.repaint();

                        String specials = "";
                        for(String x : moviesToChoose)
                        {
                            if(theatreDatabase.isAnnounced(x) == true)
                            {
                                specials += x + "\n";
                            }
                        }

                        JOptionPane.showMessageDialog(null, specials, "Specials!!!", JOptionPane.PLAIN_MESSAGE);


                        frame1.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect Login Information", "Login Message", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (DBConnectException e1) {

                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException e2)
                {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                // catch(Exception e1)
                // {
                //     JLabel failText = new JLabel("Please Enter All Information");
                //     failText.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
                //     failText.setForeground(Color.RED);
                //     failText.setBounds(5, 210, 600, 25);
                //     panel1.add(failText);
                // }
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
        password.setBounds(10, 150, 600, 25);
        panel1.add(password);

        JLabel name = new JLabel("Name");
        name.setBounds(10, 220, 300, 25);
        panel1.add(name);

        //creating a label and input for order name
        JLabel email = new JLabel("Email Address");
        //password.setFont(secondHeader);
        email.setBounds(10, 290, 600, 25);
        panel1.add(email);

        JLabel address = new JLabel("Address");
        address.setBounds(10, 370, 300, 25);
        panel1.add(address);

        //creating a label and input for order name
        JLabel creditCardInfo = new JLabel("Credit Card #");
        //password.setFont(secondHeader);
        creditCardInfo.setBounds(10, 440, 600, 25);
        panel1.add(creditCardInfo);

        JLabel CVV = new JLabel("CVV");
        CVV.setBounds(10, 510, 300, 25);
        panel1.add(CVV);
        
        usernameTextInput = new JTextField(16);
        usernameTextInput.setBounds(5, 110, 300, 25);
        panel1.add(usernameTextInput);

        passTextInput = new JTextField(16);
        passTextInput.setBounds(5, 180, 300, 25);
        panel1.add(passTextInput);

        nameTextInput = new JTextField(16);
        nameTextInput.setBounds(5, 250, 300, 25);
        panel1.add(nameTextInput);

        emailTextInput = new JTextField(16);
        emailTextInput.setBounds(5, 320, 300, 25);
        panel1.add(emailTextInput);

        addressTextInput = new JTextField(16);
        addressTextInput.setBounds(5, 390, 300, 25);
        panel1.add(addressTextInput);

        creditTextInput = new JTextField(16);
        creditTextInput.setBounds(5, 460, 300, 25);
        panel1.add(creditTextInput);

        cvvTextInput = new JTextField(16);
        cvvTextInput.setBounds(5, 530, 300, 25);
        panel1.add(cvvTextInput);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                if(usernameTextInput.getText().isEmpty() || nameTextInput.getText().isEmpty() || emailTextInput.getText().isEmpty() || address.getText().isEmpty() || 
                    passTextInput.getText().isEmpty() || creditTextInput.getText().isEmpty() || cvvTextInput.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Enter All Information", "Invalid Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    LoginDatabase loginDB = LoginDatabase.getDB();

                    loginDB.signUp(usernameTextInput.getText(), nameTextInput.getText(), emailTextInput.getText(), addressTextInput.getText(), passTextInput.getText(), creditTextInput.getText(), Integer.parseInt(cvvTextInput.getText()));
                    frame1.dispose();
                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                }
                catch(SQLException e2)
                {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                }
                catch(Exception e1)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Card Information", "Input Error", JOptionPane.WARNING_MESSAGE);

                    // System.out.println("Fail");
                    // JLabel failText = new JLabel("Please Enter All Information");
                    // failText.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
                    // failText.setForeground(Color.RED);
                    // failText.setBounds(5, 560, 600, 25);
                    // panel1.add(failText);
                }

            }
        });
        submitButton.setBounds(5, 600, 200, 25);
        panel1.add(submitButton);

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


        JLabel nameCancel = new JLabel("What is Your Name: ");
        nameCancel.setBounds(10, 90, 300, 25);
        panel1.add(nameCancel);

        //creating a label and input for order name
        JLabel tktID = new JLabel("What is Your Ticket ID: ");
        //password.setFont(secondHeader);
        tktID.setBounds(20, 150, 600, 25);
        panel1.add(tktID);

        JTextField nameInput = new JTextField(16);
        nameInput.setBounds(5, 110, 300, 25);
        panel1.add(nameInput);

        JTextField tktInput= new JTextField(16);
        tktInput.setBounds(5, 180, 300, 25);
        panel1.add(tktInput);

        

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    TheatreDatabase theatreDB = TheatreDatabase.getDB();
                    theatreDB.cancelTicket(tktInput.getText(), nameInput.getText());
                    frame1.dispose();
                } catch (DBConnectException e1) {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                    System.exit(ABORT);
                }
                catch(SQLException e2)
                {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                    System.exit(ABORT);
                } catch (UnderTimeException e1) {
                    JOptionPane.showMessageDialog(null, "You cannot cancel a ticket within 72 hours of the showtime!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                }
                
                
                // System.out.println("Seat #: " + seatNumInput.getText());
                // System.out.println("Confirmation #: " + confirmationNumInput.getText());
            }
        });
        submitButton.setBounds(5, 230, 200, 25);
        panel1.add(submitButton);

        

        frame1.setVisible(true);
    }

    public void seatMap(String movie, String theatre, Timestamp time, ArrayList<String> seatsTaken)
    {
        int rows = 4;
        int columns = 9;
        int rowName = 'A';
        ArrayList<String> seats = new ArrayList<>();
        
        JPanel seatPanel = new JPanel(new GridLayout(rows , columns));
        
        seatPanel.setSize(1400, 500);
        for (int row = 0; row < rows; row++) {
            
            for (int column = 1; column < columns; column++) {
                String seatName = (char)rowName + Integer.toString(column);
                JToggleButton button = new JToggleButton(seatName);
                for(String x : seatsTaken)
                {
                    System.out.println(x);
                    if(seatName.equals(x))
                    {
                        button.setBackground(Color.RED);
                    }
                }
                button.addActionListener(new ActionListener() {
                
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                        boolean selected = abstractButton.getModel().isSelected();
                        if (selected && button.getBackground() != Color.RED) {
                            button.setBackground(Color.GREEN);
                            seats.add(button.getText());
                            //button.setIcon(new ImageIcon(resize));
                        } else if(button.getBackground() != Color.RED){
                            button.setBackground(null);
                            for(int i =0; i<seats.size() ; i++) 
                            {
                                if(seats.get(i) == button.getText())
                                {
                                    seats.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                });
                seatPanel.add(button);
            }
            rowName++;
        }

        final JFrame seatFrame = new JFrame("Seat Map");
        //seatFrame.pack();
        seatFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        seatFrame.add(seatPanel);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                seatFrame.removeAll();
                seatFrame.dispose();
                if(!seats.isEmpty())
                {
                    if(RU == null)
                        PaymentPage(movie, theatre, time, seats);
                    else
                    {
                        RUPaymentPage(movie, theatre, time, seats);
                    }

                }
                    
            }

        });
        seatFrame.setSize(1400, 700);
        submitButton.setBounds(700, 650, 20, 50);
        seatFrame.add(submitButton);
        
        seatFrame.setLocationRelativeTo(null);
        seatFrame.setVisible(true);
    }
    
    //main actionPerformed method that does various things based on the button pressed
    //to use all buttons with one actionPerformed method, we use the e.getSource() method
    @Override
    public void actionPerformed(ActionEvent e) {
        // something like this
        if(e.getActionCommand().equals("Search Theatre"))
        {
            user.setStrategy(new SearchTheatre());
        }
        if(e.getActionCommand().equals("Search Movie"))
        {
            user.setStrategy(new SearchMovie());
        }
            // somewhere we need to include the search

            // user.performSearch(panel);



        if(e.getActionCommand().equals("Search"))
        {   
            theatreSelection = theatreComboBox.getItemAt(theatreComboBox.getSelectedIndex());
            movieSelection = movieComboBox.getItemAt(movieComboBox.getSelectedIndex());
            String searchchoice = "";
            boolean valid = true;

            if(theatreSelection.equals("any") && movieSelection.equals("any"))
            {
                JOptionPane.showMessageDialog(null, "Please Enter either Movie / Theatre", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
            else if(theatreSelection.equals("any") && !movieSelection.equals("any"))
            {
                searchchoice = movieSelection;
                user.setStrategy(new SearchMovie());
                System.out.println("Search Movie Strategy Pattern");
            }
            else if(!theatreSelection.equals("any") && movieSelection.equals("any"))
            {
                searchchoice = theatreSelection;
                user.setStrategy(new SearchTheatre());
                System.out.println("Search Theatre Strategy Pattern");
            }
            if(valid)
            {
                try {
                    TheatreDatabase theatreDB = TheatreDatabase.getDB();
                    if(!searchchoice.equals("")){
                        String[] searchReturn = user.performSearch(searchchoice);
                        theatreSelection = searchReturn[0];
                        movieSelection = searchReturn[1];
                    }

                    ArrayList<Timestamp> showTimeList = theatreDB.getShowtimeList(theatreSelection, movieSelection, false);
                    // ArrayList<Timestamp> showTimeList;
                    // if(RU == null)
                    // {
                    //     showTimeList = theatreDB.getShowtimeList(theatreSelection, movieSelection, false);
                    // }
                    // else
                    // {
                    //     showTimeList = theatreDB.getShowtimeList(theatreSelection, movieSelection, true);
                    // }
                    String[] showTimes = new String[showTimeList.size()];


                    for(Timestamp x: showTimeList)
                    {
                        System.out.println(x);
                    }   


                    for(int i=0;i<showTimeList.size();i++)
                    {
                        showTimes[i] = showTimeList.get(i).toString();
                    }


                    if(showTimesComboBox != null)
                        panel.remove(showTimesComboBox);

                    showTimesComboBox = new JComboBox<>(showTimes);
                    showTimesComboBox.setBounds(5, 250, 300, 25);
                    panel.add(showTimesComboBox);
                    sLabel = new JLabel("ShowTimes");
                    sLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                    sLabel.setBounds(5,230, 300, 13);
                    panel.add(sLabel);
                    panel.repaint();

                    //Timestamp timeSelection = showTimeList.get((showTimesComboBox.getSelectedIndex()));
                    
                    JButton showTimeButton = new JButton("Search Seats");
                    if(RU == null)
                    {
                        System.out.println("Still null");
                    }
                    GUI gui  = new GUI();
                    showTimeButton.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e) 
                        {
                            try {
                                String timeSelection = showTimesComboBox.getItemAt(showTimesComboBox.getSelectedIndex());
                                ArrayList<String> seats = theatreDB.getSeats(movieSelection, theatreSelection, Timestamp.valueOf(timeSelection));
                                for(String x : seats)
                                {
                                    System.out.println(x);
                                }
                                seatMap(movieSelection, theatreSelection, Timestamp.valueOf(timeSelection), seats);
                            } catch (Exception e2) {
                                // TODO: handle exception
                            }


                            //theatreDB.g
                            //seatMap(movieSelection, theatreSelection);
                        }
                    });

                    showTimeButton.setBounds(5, 280, 200, 25);
                    panel.add(showTimeButton);

                } catch (Exception e1) {
                    // TODO: handle exception
                }
            }

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

        // if(e.getActionCommand().equals("Seat Map Display"))
        // {
        //     //seatMap();
        // }

        if(showTimesComboBox != null)
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

