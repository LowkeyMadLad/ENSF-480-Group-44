import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.sql.*;

// Change
//main ProjectGUI class to generate the GUI components and implement actions
public class GUI extends JFrame implements ActionListener{
    User user = new User();
    //declaring all required variables (frames, buttons, global vars etc.)    
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

    private static String movieSelection = "";
    private static String theatreSelection = "";

    private static String[] theatreToChoose;
    private static String[] moviesToChoose;

    //static instance of a RU object, so the program only has one instance of RU
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
        
        //creating the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new GUI());
        loginButton.setFont(textFont);
        loginButton.setBounds(20, 350, 200, 100);
        panel.add(loginButton);

        //creting the sign up button
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new GUI());
        signUpButton.setFont(textFont);
        signUpButton.setBounds(240, 350, 200, 100);
        panel.add(signUpButton);

        //creating the cancel button
        cancelPaymentButton = new JButton("Cancel Payment");
        cancelPaymentButton.addActionListener(new GUI());
        cancelPaymentButton.setFont(textFont);
        cancelPaymentButton.setBounds(460, 350, 200, 100);
        panel.add(cancelPaymentButton);
        
        //this block inserts all theatres that are in the database
        try {
            TheatreDatabase theatreDB = TheatreDatabase.getDB();            //gets the databse
            ArrayList<String> theatreList = theatreDB.getTheatreList();     //gets the list of theatres
            theatreToChoose = new String[theatreList.size() +1];
            for(int i=0;i<theatreList.size();i++)
            {
                theatreToChoose[i] = theatreList.get(i);
            }

            //creates a dropdown box of all theatres
            theatreToChoose[theatreList.size()] = "any";
            theatreComboBox = new JComboBox<>(theatreToChoose);
            theatreComboBox.setBounds(5, 80, 300, 25);
            JLabel tLabel = new JLabel();
            tLabel.setBounds(90, 100, 400, 100);
            panel.add(theatreComboBox);
            panel.add(tLabel);

        } catch (DBConnectException e1) {
            // TODO: handle exception
            //displays a pop up msg
            JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e2)
        {
            JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
        }

        //this block inserts all movies that are in the database
        try {
            TheatreDatabase theatreDB = TheatreDatabase.getDB();
            ArrayList<String> movieList = theatreDB.getMovieList();         //gets all movies in the database
            moviesToChoose = new String[movieList.size() +1 ];
            for(int i=0;i<movieList.size();i++)
            {
                moviesToChoose[i] = movieList.get(i);
            }

            //creates a dropdown menu where user can select a movie
            moviesToChoose[movieList.size()] = "any";
            movieComboBox = new JComboBox<>(moviesToChoose);
            movieComboBox.setBounds(5, 150, 300, 25);
            JLabel mLabel = new JLabel();
            mLabel.setBounds(90, 100, 400, 100);
            panel.add(movieComboBox);
            panel.add(mLabel);

        } catch (DBConnectException e1) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e2)
        {
            JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
        }
        
        //creates the search button
        searchButton = new JButton("Search");
        GUI gui  = new GUI();
        searchButton.addActionListener(gui);
        searchButton.setFont(secondHeader);
        searchButton.setBounds(5, 200, 200, 25);
        panel.add(searchButton);

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
    public void cancelConfirmationPage(int token)
    {
        //creates the frame
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

        //creates a label which lets user knwo what their voucher code is
        JLabel tokenInfo = new JLabel("Voucher (Valid Only for One Year): " + token);
        tokenInfo.setBounds(5,70, 300, 50);
        panel1.add(tokenInfo);

        frame1.setVisible(true);

        String msg = "Cancellation Confirmation \n" + "Voucher (Valid Only for One Year): " + token;

        //Email.sendEmail(sendEmail, msg);
    }

    public void confirmationPaymentPage(ArrayList<Ticket> ticket) throws SQLException, DBConnectException
    {
        JFrame frame1 = new JFrame("Confirmation Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        
        JLabel confirmPaymentPage = new JLabel("Confirmation Of Your Payment");
        confirmPaymentPage.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        confirmPaymentPage.setBounds(5, 10, 300, 50);
        panel1.add(confirmPaymentPage);

        //displays movie
        JLabel movieInfo = new JLabel("Movie: " + ticket.get(0).getMovie());
        movieInfo.setBounds(5,70, 300, 50);
        panel1.add(movieInfo);

        //displays the theatre
        JLabel theatreInfo = new JLabel("Theatre: " + ticket.get(0).getTheatre());
        theatreInfo.setBounds(5,130, 300, 50);
        panel1.add(theatreInfo);

        //displays the time of the movie
        JLabel timeInfo = new JLabel("Time: " + ticket.get(0).getShowtime().toString());
        timeInfo.setBounds(5,190, 300, 50);
        panel1.add(timeInfo);

        String seats = "Seats Requested: ";
        for(Ticket x : ticket)
        {
            seats += x.getSeat() + " Ticket number: " + x.getTicketNum() + "\t\n";
        }

        //displays all of the seats requested with their tkt number
        JLabel displaySeats = new JLabel(seats);
        displaySeats.setBounds(5,250, 600, 300);
        panel1.add(displaySeats);

        frame1.setVisible(true);

        // Email email = new Email();

        // String msg = "Movie: " + ticket.get(0).getMovie() + "\n" + "Theatre: " + ticket.get(0).getTheatre() +"\n"
        //                 +"Time: " + ticket.get(0).getShowtime().toString() + "\n"  +seats;

        // Email.sendEmail(sendEmail, msg);
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

        int price = seatsRequested.size() * 10;
        JLabel totalPrice = new JLabel("Total: " + Integer.toString(price) + "$");
        totalPrice.setBounds(400,250, 300, 20);
        panel1.add(totalPrice);

        JLabel voucher = new JLabel("Voucher");
        voucher.setBounds(400,300, 300, 20);
        panel1.add(voucher);

        JTextField voucherText = new JTextField(16);
        voucherText.setBounds(400,340, 200, 30);
        panel1.add(voucherText);

        JButton submitVoucher = new JButton("Submit Voucher");
        submitVoucher.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    TheatreDatabase theatreDB= TheatreDatabase.getDB();
                    float amount = theatreDB.retrieveVoucher(Integer.parseInt(voucherText.getText()));

                    float newPrice = price - amount;
                    JLabel newTotal = new JLabel("Total: " + Float.toString(newPrice) + "$");
                    newTotal.setBounds(400,270, 300, 20);
                    panel1.add(newTotal);
                    panel1.repaint();

                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException e2)
                {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        submitVoucher.setBounds(400, 370, 200, 25);
        panel1.add(submitVoucher);

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
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(IllegalArgumentException e1)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Card Information", "Input Error", JOptionPane.WARNING_MESSAGE);
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

        int price = seatsRequested.size() * 10;
        JLabel totalPrice = new JLabel("Total: " + Integer.toString(price));
        totalPrice.setBounds(400,250, 300, 50);
        panel1.add(totalPrice);

        JLabel voucher = new JLabel("Voucher");
        voucher.setBounds(400,300, 300, 20);
        panel1.add(voucher);

        //this where the user can enter a voucher code if they have one
        JTextField voucherText = new JTextField(16);
        voucherText.setBounds(400,340, 200, 30);
        panel1.add(voucherText);

        JButton submitVoucher = new JButton("Submit Voucher");
        submitVoucher.addActionListener(new ActionListener() 
        {
            //if a voucher code is entered then it will change the price and display the new price
            public void actionPerformed(ActionEvent e)
            {
                try {
                    TheatreDatabase theatreDB= TheatreDatabase.getDB();
                    float amount = theatreDB.retrieveVoucher(Integer.parseInt(voucherText.getText()));

                    float newPrice = price - amount;
                    JLabel newTotal = new JLabel("Total: " + Float.toString(newPrice) + "$");
                    newTotal.setBounds(400,270, 300, 20);
                    panel1.add(newTotal);
                    panel1.repaint();

                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException e2)
                {
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        submitVoucher.setBounds(400, 370, 200, 25);
        panel1.add(submitVoucher);

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
        
        //creates the submit button to proceed with payment
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    TheatreDatabase database = TheatreDatabase.getDB();
                    ArrayList<Ticket> ticketTotal = new ArrayList<Ticket>();        //inserts a ticket for each ticket requested
                    for(String x : seatsRequested)
                    {
                        Ticket ticket = new Ticket(theatre, movie, time, x);
                        database.insertTicket(ticket , RU.getName());           //inserts the tkt in the database with its corresponding seat $
                        ticketTotal.add(ticket);
                        
                    }
                    confirmationPaymentPage(ticketTotal, emailTextInput.getText());       //user gets a confirmation with their tkt number for each seat
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
                    boolean verifyAdmin = loginDB.checkAdminInformation(usernameTextInput.getText(), passTextInput.getText());
                    System.out.println(verifyAdmin);
                    if(verifyAdmin == true)
                    {
                        adminPage();
                    }

                    else{
                        boolean verify = loginDB.checkLoginInformation(usernameTextInput.getText(), passTextInput.getText());
                        
                        if(verify == true)
                        {
                            RU  = loginDB.getLoginInformation(usernameTextInput.getText(), passTextInput.getText());

                            JOptionPane.showMessageDialog(null, "Successful Login", "Login", JOptionPane.PLAIN_MESSAGE);


                            JLabel usernameHomePg = new JLabel("Hello " + RU.getName());
                            usernameHomePg.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
                            usernameHomePg.setBounds(500, 10, 200, 25);
                            
                            panel.add(usernameHomePg);
                            panel.repaint();

                            ArrayList<String> movies = theatreDatabase.getMovieList();
                            String specials = "Movies you have early access to buy: \n";
                            for(String x : movies)
                            {
                                System.out.println(x);
                                if(theatreDatabase.isAnnounced(x) == false)
                                {
                                    specials += x + "\n";
                                }
                            }

                            JOptionPane.showMessageDialog(null, specials, "Specials", JOptionPane.PLAIN_MESSAGE);


                            frame1.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Incorrect Login Information", "Login Message", JOptionPane.ERROR_MESSAGE);

                        }

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

            }
        });
        submitButton.setBounds(5, 230, 200, 25);
        panel1.add(submitButton);

        usernameTextInput = new JTextField(16);
        usernameTextInput.setBounds(5, 120, 300, 25);
        panel1.add(usernameTextInput);

        passTextInput = new JTextField(16);
        passTextInput.setBounds(5, 180, 300, 30);
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

        JLabel paymentFee = new JLabel("Payment Fee: 20$");
        paymentFee.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        paymentFee.setBounds(400,10, 300, 50);
        panel1.add(paymentFee);

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
                else if(creditTextInput.getText().length() != 16 || cvvTextInput.getText().length() != 3)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Card Information", "Input Error", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    try {
                        LoginDatabase loginDB = LoginDatabase.getDB();

                        loginDB.signUp(usernameTextInput.getText(), nameTextInput.getText(), emailTextInput.getText(), addressTextInput.getText(), passTextInput.getText(), creditTextInput.getText(), Integer.parseInt(cvvTextInput.getText()));
                        JOptionPane.showMessageDialog(null, "Signup was succesful and account is valid for 1 year", "Sign Up", JOptionPane.PLAIN_MESSAGE);
                        frame1.dispose();
                    } catch (DBConnectException e1) {
                        // TODO: handle exception
                        JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                    }
                    catch(SQLException e2)
                    {
                        JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                    }
                    catch(IllegalArgumentException e1)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Card Information", "Input Error", JOptionPane.WARNING_MESSAGE);
                    }
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
                    int token = theatreDB.cancelTicket(tktInput.getText(), nameInput.getText(), RU);            //user enters which tkt they want to cancel, and recieve a voucher code to use
                    cancelConfirmationPage(token);      //cancellation confirmation page will display the voucher
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
                

            }
        });
        submitButton.setBounds(5, 230, 200, 25);
        panel1.add(submitButton);

        frame1.setVisible(true);
    }

    public void seatMap(String movie, String theatre, Timestamp time, ArrayList<String> seatsTaken)
    {
        int rows = 4;               //number of rows of seats
        int columns = 9;            //number of columns in seat map
        int rowName = 'A';        
        ArrayList<String> seats = new ArrayList<>();            //arraylist of seats which user selects
        
        JPanel seatPanel = new JPanel(new GridLayout(rows , columns));

        int numberOfCurrSeatTaken = seatsTaken.size();          //current # seats which are taken

        
        seatPanel.setSize(1400, 500);
        for (int row = 0; row < rows; row++) {
            for (int column = 1; column < columns; column++) {
                String seatName = (char)rowName + Integer.toString(column);
                JToggleButton button = new JToggleButton(seatName);
                for(String x : seatsTaken)
                {
                    if(seatName.equals(x))          //if the seat is already taken then set the color to RED and user will be unable to select it
                    {
                        button.setBackground(Color.RED);
                    }
                }
                button.addActionListener(new ActionListener() {     //when seat button is pressed
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                        boolean selected = abstractButton.getModel().isSelected();
                        int reserved = numberOfCurrSeatTaken + seats.size();            //for reserved seats

                        boolean isAnnounced = false;            //checks if the movie hasnt been announced as only RU can pre buy
                        try {
                            TheatreDatabase database = TheatreDatabase.getDB();
                            isAnnounced =  database.isAnnounced(movie);
                
                        } catch (DBConnectException e1) {
                            // TODO: handle exception
                            JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                        }
                        catch(SQLException e2)
                        {
                            e2.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                        }
                        
                        if (RU != null && selected && button.getBackground() != Color.RED && reserved != 3 && isAnnounced == false)     //if it is an RU and the movie hasnt been announced then RU can select 10% of seats
                        {
                            button.setBackground(Color.GREEN);
                            seats.add(button.getText());
                            
                        }
                        else if(RU != null && selected && button.getBackground() != Color.RED && reserved == 3 && isAnnounced == false)         //if 10% of seats are selected a warning msgs pops up and RU cant select any seats
                        {
                            button.setBackground(null);
                            JOptionPane.showMessageDialog(null, "Sorry it seems that number of reserved seats has filled up, please buy when open to the public", "Reserved Seats", JOptionPane.WARNING_MESSAGE);
                        }

                        else if (selected && button.getBackground() != Color.RED) {         //if movie is announced and the seats hasnt been taken then the User can select the seat
                            button.setBackground(Color.GREEN);
                            seats.add(button.getText());            //adds to arraylist
                            //button.setIcon(new ImageIcon(resize));
                        } else if(button.getBackground() != Color.RED){         //if the seats wants to be unselected
                            button.setBackground(null);
                            for(int i =0; i<seats.size() ; i++) 
                            {
                                if(seats.get(i) == button.getText())
                                {
                                    seats.remove(i);            //removes from the arraylist
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
                    if(RU == null)              //if normal user then a payment page pops up where they must enter their info
                        PaymentPage(movie, theatre, time, seats);
                    else        //if user has logged in
                    {
                        RUPaymentPage(movie, theatre, time, seats);         //they dont have to enter their info just confirm it
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
    

    public void adminPage()
    {
        JFrame frame1 = new JFrame("Admin Page");
        frame1.setSize(700, 700);
        frame1.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);

        panel1.setLayout(null);
        frame1.add(panel1);

        JLabel loginPage = new JLabel("Admin Page");
        //username.setFont(secondHeader);
        loginPage.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        loginPage.setBounds(5, 40, 300, 50);
        panel1.add(loginPage);

        JLabel movieSelected = new JLabel("What Movie Would You Like To Remove");
        movieSelected.setBounds(5, 90, 300, 20);
        panel1.add(movieSelected);

        JComboBox<String> adminMovieComboBox = new JComboBox<>(moviesToChoose);
        adminMovieComboBox.setBounds(5, 120, 300, 25);
        JLabel mLabel = new JLabel();
        mLabel.setBounds(90, 150, 400, 20);
        panel1.add(adminMovieComboBox);
        panel1.add(mLabel);

        JButton selectedMovie = new JButton("Delete Movie");
        GUI gui  = new GUI();
        selectedMovie.addActionListener(new ActionListener()            //if the delete movie is selected 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    TheatreDatabase theatreDatabase = TheatreDatabase.getDB();
                    String movie = adminMovieComboBox.getItemAt(adminMovieComboBox.getSelectedIndex());         //get the movie from dropdown menu
                    
                    theatreDatabase.deleteMovie(movie);             //delete the movie from the database
                    panel.repaint();
                    panel1.repaint();
                    JOptionPane.showMessageDialog(null, "Succesful Deletion", "Admin", JOptionPane.PLAIN_MESSAGE);

                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                }
                catch(SQLException e2)
                {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        

        selectedMovie.setBounds(5, 150, 200, 25);
        panel1.add(selectedMovie);

        JLabel addLabel = new JLabel("Add Movie");
        addLabel.setBounds(5, 210, 300, 30);
        panel1.add(addLabel);

        JTextField addMovie = new JTextField();
        addMovie.setBounds(5,240,300,30);
        panel1.add(addMovie);

        JTextField addTime = new JTextField();
        addTime.setBounds(5, 270, 300, 30);
        panel1.add(addTime);

        JButton addedMovie = new JButton("Add Movie");
        addedMovie.addActionListener(new ActionListener()               //if add movie is selected
        {
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    TheatreDatabase theatreDatabase = TheatreDatabase.getDB();
            
                    theatreDatabase.addMovie(addMovie.getText(), Timestamp.valueOf(addTime.getText()));         //add the movie which user has selected with its corresponding announcment date
                    panel.repaint();    
                    panel1.repaint();
                    JOptionPane.showMessageDialog(null, "Succesful Insertion", "Admin", JOptionPane.PLAIN_MESSAGE);

                } catch (DBConnectException e1) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                }
                catch(SQLException e2)
                {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        

        addedMovie.setBounds(5, 300, 200, 25);
        panel1.add(addedMovie);

        frame1.setVisible(true);

    }
    //main actionPerformed method that does various things based on the button pressed
    //to use all buttons with one actionPerformed method, we use the e.getSource() method
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Search"))           //if the search button has been pressed
        {   
            theatreSelection = theatreComboBox.getItemAt(theatreComboBox.getSelectedIndex());
            movieSelection = movieComboBox.getItemAt(movieComboBox.getSelectedIndex());
            String searchchoice = "";
            boolean valid = true;

            if(theatreSelection.equals("any") && movieSelection.equals("any"))          //if both inputs are any then warning pop up msg appears
            {
                JOptionPane.showMessageDialog(null, "Please Enter either Movie / Theatre", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
            else if(theatreSelection.equals("any") && !movieSelection.equals("any"))            //if movie is selected and theatre is not
            {   
                //we use a startegy a pattern which finds all theatres that have that movie
                searchchoice = movieSelection;
                user.setStrategy(new SearchMovie());
            }
            else if(!theatreSelection.equals("any") && movieSelection.equals("any"))            //if theatre is selected and movie is not
            {
                //set the strategy pattern to serach for all the movies
                searchchoice = theatreSelection;
                user.setStrategy(new SearchTheatre());
            }
            if(searchchoice == null) valid = false;
            if(valid)           //if input were valid choices then
            {
                try {
                    TheatreDatabase theatreDB = TheatreDatabase.getDB();
                    if(!searchchoice.equals("")){
                        String[] searchReturn = user.performSearch(searchchoice);           //perform the search if only a movie or theatre were selected
                        theatreSelection = searchReturn[0];
                        movieSelection = searchReturn[1];
                        if(theatreSelection == null || movieSelection == null) return;
                    }

                    ArrayList<Timestamp> showTimeList = theatreDB.getShowtimeList(theatreSelection, movieSelection);            //get the showtimes for the movie
                    if(showTimeList.isEmpty()){         //if no showtimes are available display user with a pop up msg
                        String msg = "Sorry, " + movieSelection + " is not available at " + theatreSelection + "!";
                        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String[] showTimes = new String[showTimeList.size()];
                    
                    if(theatreDB.isAnnounced(movieSelection) || RU != null)         //check if the movie has been announced to ordinary users
                    {
                        for(int i=0;i<showTimeList.size();i++)
                        {
                            showTimes[i] = showTimeList.get(i).toString();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Sorry this movie hasn't been released to the public yet", "Showtime Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    if(showTimesComboBox != null)
                        panel.remove(showTimesComboBox);


                    //creates a dropdown menu with all the available showtimes
                    showTimesComboBox = new JComboBox<>(showTimes);
                    showTimesComboBox.setBounds(5, 250, 300, 25);
                    panel.add(showTimesComboBox);
                    sLabel = new JLabel("ShowTimes");
                    sLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                    sLabel.setBounds(5,230, 300, 13);
                    panel.add(sLabel);
                    panel.repaint();

                    //serach seats button
                    JButton showTimeButton = new JButton("Search Seats");           

                    GUI gui  = new GUI();
                    showTimeButton.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e) 
                        {
                            try {
                                String timeSelection = showTimesComboBox.getItemAt(showTimesComboBox.getSelectedIndex());           //get the showtime
                                ArrayList<String> seats = theatreDB.getSeats(movieSelection, theatreSelection, Timestamp.valueOf(timeSelection));           //get all seats which are taken for that show
      
                                seatMap(movieSelection, theatreSelection, Timestamp.valueOf(timeSelection), seats);     //display the seat map
                            } catch (DBConnectException e1) {

                                // TODO: handle exception
                                JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                            }
                            catch(SQLException e2)
                            {
                                e2.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    });

                    showTimeButton.setBounds(5, 280, 200, 25);
                    panel.add(showTimeButton);
                } catch (DBConnectException e1) {

                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException e2)
                {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Problem Please Restart the Program", "Database Problem", JOptionPane.ERROR_MESSAGE);
                }
                catch(IllegalArgumentException e2)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Information", "Invalid Information", JOptionPane.ERROR_MESSAGE);

                }
            }

        }

        if(e.getActionCommand().equals("Login"))            //if the login button is selected display login page
        {
            LoginPage();
        }

        if(e.getActionCommand().equals("Sign Up"))          //if the sign up button is selected display sign up page
        {
            SignUpPage();
        }

        if(e.getActionCommand().equals("Cancel Payment"))           //if the cancel payment button is selected display the cancel payment page
        {
            CancelPayment();
        }


        //code for ending the program if endProgram button clicked
        if (e.getSource().equals(endProgram)){
            System.exit(1);
            frame.setVisible(false);
        }
    }

}


// mock gui

/*
        db = TheatreDatabase.getDB();
        // fillDatabase();
        Timestamp testDate = new Timestamp(System.currentTimeMillis());
        System.out.println(testDate.toString());
        System.out.println("Unix Time: " + System.currentTimeMillis());
        // Calendar testCalendar = Calendar.getInstance();
        System.out.println("How would you like to find a movie?\nT = Search by theatre\nM = Search by movie\nB = Search both");
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter your choice: ");
        String searchstrat = scanner.nextLine(); 
        // scanner.close();
        switch (searchstrat.toUpperCase()) {
            case "T":
                user.setStrategy(new SearchTheatre());
                break;
            case "M":
                user.setStrategy(new SearchMovie());
                break;
            // case "B":
            //     user.setStrategy(new SearchBoth());
            default:
                System.out.println("Invalid Entry! Exiting...");
                System.exit(1);
                break;
        }
        Ticket ticket = user.performSearch();
        System.out.println("Your ticket: ");
        System.out.println(ticket.toString());

        scanner.close();
*/

