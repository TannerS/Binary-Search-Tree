package bst;

import java.util.Scanner;

/**
 *
 * @author Tanner Summers
 */
public class BinarySearchTree 
{
    public static void main(String[] args) 
    {
        // create scanner to read the input of numbers
        Scanner userinputer = new Scanner(System.in);
        // create a string to record user input
        String userinput = new String();
        // a array of string objects that will hold strings that
        // are needed to be split
        String [] values;
        // a do loop to check for valid user input
        do {
            // display message for user to input data
            System.out.println("Please enter the initial sequence of values:");
            // take in user input
            userinput = userinputer.nextLine();
        // loop the same sequence if the user does not enter a valid input of
        // a number, a space, a number, a space....
        }while(!(userinput.matches("[\\d+\\s]+"))); // mattern match a valid number with a space
        // there was an issue with the pattern matching
        // the pattern should only except numbers with a single space in between
        // but accordding to the internet, there is a problem with java and unicode
        // where this will not work
        // so this is a temp solution to replace all spaces+ with a single space
        // the trim method takes a way spaces at front and end of a string
        userinput = userinput.replaceAll("\\s+", " ").trim(); // the space wont work on java,error on forums about it, so replace mroe then one space with  a sapce and trim for space at start and end
        // split the numbers and put them into an array
        values = userinput.split(" ");
        // create new binary search tree object  
        BST<Integer> tree = new BST<Integer>(); 
        // enchaned loop to input the numbers (as strings)
        // into the tree as integers
        for (String value : values)
            // call tree's addValue method to add numbers as int
            tree.addValue(Integer.parseInt(value));
        // call the method to display numbers in preFix order
        System.out.println("Pre-Order: " + tree.preFix());
        // call the method to display numbers in inFix order
        System.out.println("In-Order: " + tree.inFix());
        // call the method to display numbers in postFix order
        System.out.println("Post-Order: " + tree.postFix());
        // start a do loop that will loop and allow users
        // to do commands to the tree and will exit
        // when user chooses too
        do{
            // a do loop for user to input a valid formatted command
            do{
                // get list of commands
                viewMenu();
                // ask user for a command
                System.out.print("Command? ");
                // get user input
                userinput = userinputer.nextLine();
            // keep looping it the user input does macth the format of
            // one of the selected letters below, and a space and a digit
            }while(!((userinput.matches("[IDPSEH]\\s\\d+")) ));
            // using same array, split command by spaces
            values = userinput.split(" ");
            // switch statement for the menu of commands
            // the switch is going off the first array element
            // which is the command letter
            switch(values[0].charAt(0))
            {
                // if user typed an I
                case 'I':
                    // call the addValue method to add the number into tree
                    // and if the number does not exist, return true
                    if(tree.addValue(Integer.parseInt(values[1])))
                        // display the new in order of the tree
                        System.out.println("In-Order: " + tree.inFix());
                    // if number aready exist
                    else
                        // do not add it and display message stating this
                        System.out.println(values[1] + " already exists, ignore.");
                    break;
                // if user typed a D
                case 'D':
                    // call the deleteValue method to delete the number from tree
                    // and if the number exist, return true
                    if(tree.deleteValue(Integer.parseInt(values[1])))
                       // display the value deleted
                       System.out.println("Value: " + values[1] + " deleted");
                    // if number does not exist
                    else
                        // display message stating so
                        System.out.println(values[1] + " doesn't exist!");
                    // print new in-order of numbers
                    System.out.println("In-Order: " + tree.inFix());
                    break;
                // if user typed a P
                case 'P':
                        // call method to show the Predecessor of chosen number
                        System.out.println(tree.findPre(Integer.parseInt(values[1])));
                    break;
                // if user typed a S
                case 'S':
                        // call method to show the successor of chosen number
                        System.out.println(tree.findSuc(Integer.parseInt(values[1])));
                    break;
                // if user typed an E
                case 'E':
                    System.out.println("Thank you for using!");
                    break;
                // if user typed an H
                case 'H':
                    viewMenu();
                    break;
                // if user typed an invalid input
                default:
                    System.out.println("Invalid input");
                    break;
            }
          // keep looking switch statement if user does not type in an E
        } while(!(userinput.equals("E")));
    }
    /**
    * Prints to screen the menu of user selections
    * 
    * @param  
    * @return          
    */
    public static void viewMenu()
    {
        System.out.println("I Insert a value");
        System.out.println("D Delete a value");
        System.out.println("P Find Predecessor");
        System.out.println("S Find successor");
        System.out.println("E Exit the program");
        System.out.println("H Display this message");
    }
}
