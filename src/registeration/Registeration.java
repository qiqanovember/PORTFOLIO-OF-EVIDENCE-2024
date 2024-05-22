/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registeration;

import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

/**
 *
 * @author RC_Student_lab
 */
public class Registeration {

    String enteredUserName;
    String enteredPassword;
    String firstName;
    String surname;
    String password;
    String userName;

    public boolean checkUsername() {

        boolean check = false;
        for (int i = 0; i < userName.length(); i++) {
            if (userName.length() <= 5) {

                if ((int) userName.charAt(i) == 95) {
                    check = true;
                }
            }
        }
        return check;
    }

    public boolean checkPasswordComplexity() {

        boolean CapitalLetter = false;
        boolean Number = false;
        boolean Special = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.length() >= 8) {
                if ((int) password.charAt(i) > 65 && (int) password.charAt(i) <= 90) {
                    CapitalLetter = true;
                }
                if ((int) password.charAt(i) >= 48 && (int) password.charAt(i) <= 57) {
                    Number = true;
                }
                if ((int) password.charAt(i) >= 33 && (int) password.charAt(i) <= 47
                        || (int) password.charAt(i) >= 58 && (int) password.charAt(i) <= 64
                        || (int) password.charAt(i) >= 91 && (int) password.charAt(i) <= 96
                        || (int) password.charAt(i) >= 123 && (int) password.charAt(i) <= 126) {
                    Special = true;
                }
            }

        }
        return CapitalLetter && Number && Special;
    }

    public String registerUser() {

        if (checkUsername() == true) {
            System.out.println("Username succefully captured.");
        } else {
            System.out.println("Username is not correctly formatted,please ensure that your Username contains an underscore and is no more than 5 characters in length.");
        }
        if (checkPasswordComplexity() == true) {
            System.out.println("Password succefully captured.");
        } else {
            System.out.println("Password is not correctly formatted please ensure that the password contains atleast 8characters, a capital letter , a number and a special character.");
        }
        if (checkUsername() == true && (checkPasswordComplexity() == true)) {
            System.out.println("The two above conditions have been met and the user has been registered succefully.");

        }
        if (checkPasswordComplexity() == false) {
            System.out.println("The Password does not meet the complexity requirements.");
        }
        if (checkUsername() == false) {
            System.out.println("The username is incorrectly formatted.");

        }

        return ("");
    }

    public boolean loginUser() {

        boolean Compare = false;

        if (userName.equals(enteredUserName) && (password.equals(enteredPassword))) {

            Compare = true;
        }
        return Compare;
    }

    public String returnLoginStatus() {

        if (loginUser() == true) {

            System.out.println("succeful login");

            System.out.println("welcome " + " " + firstName + " " + surname + " " + " it is great to see you again ");

        } else {

            System.out.println("A failed login");
            System.out.println("Username or Password incorrect please try again");

        }

        return "";
    }

    public static void main(String[] args) {
        //This allows the application to be on top of the other applications
        JDialog myWindowObj = new JDialog();
        myWindowObj.setAlwaysOnTop(true);

        Registeration myRegisterObj = new Registeration();
        Task myTaskObj = new Task();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter FirstName :");
        myRegisterObj.firstName = input.next();

        System.out.print("Enter LastName :");
        myRegisterObj.surname = input.next();

        System.out.print("Enter UserName :");
        myRegisterObj.userName = input.next();

        System.out.print("Enter Password :");
        myRegisterObj.password = input.next();

        System.out.println(myRegisterObj.registerUser());
        if (myRegisterObj.checkUsername() == true & (myRegisterObj.checkPasswordComplexity()) == true) {

            System.out.println("Enter Username :");
            myRegisterObj.enteredUserName = input.next();
            System.out.println("Enter Password :");
            myRegisterObj.enteredPassword = input.next();

            System.out.println(myRegisterObj.returnLoginStatus());
        }
        if (myRegisterObj.loginUser() == true) {
            JOptionPane.showMessageDialog(null, "Welcome, it is great to see you.", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
            int choice;
            do {

                myTaskObj.input = JOptionPane.showInputDialog(null, "Choose an option:\n1. Add tasks\n2. Show report\n3. Quit", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                choice = Integer.parseInt(myTaskObj.input);

                switch (choice) {
                    case 1:
                        int numTasks = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of tasks:", "Qiqa November", JOptionPane.PLAIN_MESSAGE));
                        Task task = new Task();
                        int totalHours = 0;

                        for (int i = 0; i < numTasks; i++) {
                            String taskName = JOptionPane.showInputDialog(null, "Enter task name:", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                            String taskDescription = JOptionPane.showInputDialog(null, "Enter task description:", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                            String developerFirstName = JOptionPane.showInputDialog(null, "Enter developer's first name:", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                            String developerLastName = JOptionPane.showInputDialog(null, "Enter developer's last name:", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter task duration:", "Qiqa November", JOptionPane.PLAIN_MESSAGE));

                            String AuthUserID = task.createTaskID(taskName, i, developerLastName);
                            String AuthUserStatus = "";

                            int option = Integer.parseInt(JOptionPane.showInputDialog(null, "Please choose the Status of this task from the three options.\n"
                                    + "1.To Do\n"
                                    + "2.Doing\n"
                                    + "3.Done"));
                            switch (option) {
                                case 1:
                                    AuthUserStatus = "To Do";
                                    break;
                                case 2:
                                    AuthUserStatus = "Doing";
                                    break;
                                case 3:
                                    AuthUserStatus = "Done";
                                    break;
                            }
                            String taskDetails = task.printTaskDetails(AuthUserStatus, developerFirstName, developerLastName,
                                    i, taskName, taskDescription, AuthUserID, taskDuration);
                            JOptionPane.showMessageDialog(null, taskDetails, "Qiqa November", JOptionPane.PLAIN_MESSAGE);

                            totalHours = totalHours + taskDuration;
                        }

                        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours, "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "Coming Soon", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Exiting the application.", "Qiqa November", JOptionPane.PLAIN_MESSAGE);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Qiqa November", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } while (choice != 3);
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.");
        }
    }
}
