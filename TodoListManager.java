// Cory Vo
// TA: Zane Lee
// CSE 122
// Oct 11 2023
// TodoListManager

// Class Comment: This class is a todolist. It allows for users to keep track of their tasks
// in order to keep them organized.

import java.util.*;
import java.io.*;
import java.util.ArrayList; 

public class TodoListManager {
    public static final boolean EXTENSION_FLAG = false;

    public static void main(String[] args) throws FileNotFoundException {
        // Todos ArrayList
        List<String> todos = new ArrayList<>();
        // Marked complete list
        List<String> complete = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        // Print introduction
        System.out.println("Welcome to your TODO List Manager!");
        boolean gameStatus = true;
        while(gameStatus){
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, " 
                + "(S)ave TODOs, (Q)uit? ");
            String userInput = console.nextLine();
            if(userInput.equalsIgnoreCase("Q")){
                gameStatus = false;
            }
            else if(userInput.equalsIgnoreCase("A")){
                addItem(console, todos);
            }
            else if(userInput.equalsIgnoreCase("M")){
                markItemAsDone(console, todos, complete);
            }
            else if(userInput.equalsIgnoreCase("L")){
                loadItems(console, todos);
            }
            else if(userInput.equalsIgnoreCase("S")){
                saveItems(console, todos);
            }
            // Unknown output from user
            else {
                System.out.println("Unknown input: " + userInput);
                printTodos(todos);
            }
        }
    }

    // Behavior: Prints out the user's todos that they have inputed. If 
    // their list is currently empty at the moment, a message prints out 
    // telling the user they have nothing on their todo list.
    // Parameters: Todos is an arraylist that contains the user's todos
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");
        if(todos.size() == 0){
            System.out.println("  You have nothing to do yet today! Relax!");
        }
        else {
            for(int i = 0; i < todos.size(); i++){
                System.out.println("  " + (i+1) + ": " + todos.get(i));
            }
        }
    }

    // Behavior: Adds whatever task the user wants to their todo list. It also allows 
    // them to add their task wherever in the list, besides their first task of the todo list.
    // It then prints out their todo list.
    // Parameters: Console is a scanner that takes in whatever the user types
    // Todos is an arraylist that contains the user's todos.
    public static void addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String userAdd = console.nextLine();
        if(todos.size() == 0){
            todos.add(userAdd);    
        } 
        else {
            System.out.print("Where in the list should it be (1-" + (todos.size() + 1)  + ")? (Enter for end): ");
            String number = console.nextLine();
            // if the user presses enter
            if(number == ""){
                todos.add(userAdd);
            }
            else {
                int num = Integer.parseInt(number);
                todos.add(num - 1, userAdd);
            }
        }
        printTodos(todos);
    }

    // Behavior: Allows the user to mark whenever they complete a task, which removes
    // that task from the todo list. They can specify which task to specifically remove.
    // The user will also be asked if they want to see their completed tasks. If they choose
    // to do so, a list of their completed tasks will display. It then prints out their todo list.
    // Parameters: Console is a scanner that takes in whatever the user types Todos is an arraylist
    // that contains the user's todos. Complete is another arraylist that contains the user's
    // completed tasks.
    public static void markItemAsDone(Scanner console, List<String> todos, List<String> complete) {
        String removal = "";
        if(todos.size() == 0){
            System.out.println("All done! Nothing left to mark as done!");
        }
        else {
            System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
            String userComplete = console.nextLine();
            int num = Integer.parseInt(userComplete);
            removal = todos.remove(num-1);
        }
        // Creative portion
        if(EXTENSION_FLAG){
            System.out.print("Would you like to see your completed tasks: (Y)es or (N)o? ");
            String response = console.nextLine();
            if(response.equalsIgnoreCase("Y")){
                complete.add(removal);
                System.out.println("List of Completed Tasks: ");
                for(int h = 0; h < complete.size(); h++){
                    System.out.println("  " + (h+1) + ": " + complete.get(h));
                }
            }
            else {
                System.out.println("Alrighty! You do not want to see your completed items. ");
            }
        }
        printTodos(todos);
    }

    // Behavior: Asks the user for a file, then it takes the todos in that file 
    // and puts it in their own todo list. It then prints out their todo list.
    // Parameters: Console is a scanner that takes in whatever the user types
    // Todos is an arraylist that contains the user's todos.
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        todos.clear();
        System.out.print("File name? ");
        String userFile = console.nextLine();
        Scanner file = new Scanner(new File(userFile));
        while(file.hasNextLine()){
            String fileText = file.nextLine();
            todos.add(fileText);
        }
        printTodos(todos);
    }

    // Behavior: Takes in a file the user inputs, then displays the user's todo
    // list in order, into the given file.
    // Parameters: Console is a scanner that takes in whatever the user types
    // Todos is an arraylist that contains the user's todos. 
    public static void saveItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
        String userFile = console.nextLine();
        PrintStream output = new PrintStream(new File(userFile));
        for(int k = 0; k < todos.size(); k++){
            output.println(todos.get(k));
        }
        printTodos(todos);
    }
}