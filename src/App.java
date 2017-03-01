import java.util.Scanner;

/**
 * Created by kond on 2017. 03. 01..
 */
public class App {

  private static final String help =
          "CLI Todo application\n" +
          "====================\n" +
          "\n" +
          "Available commands:\n" +
          " list       Lists all the tasks\n" +
          " l          shorthand for list\n" +
          " add        Adds a new task\n" +
          " a          shorthand for add\n" +
          " remove     Removes an task\n" +
          " r          shorthand for remove\n" +
          " complete   Completes an task\n" +
          " c          shorthand for complete\n" +
          " help       Print out this list again\n" +
          " h          shorthand for help\n" +
          " exit       Exit\n" +
          " e          shorthand for exit";

  public static void main(String[] args) {

    User user = logInUser();
    TodoList todoList = user.loadTodoList();

    System.out.println(help);

    Scanner userInput = new Scanner(System.in);

    while (userInput.hasNextLine()) {
      String input = userInput.nextLine();
      if(input.equals("exit") || input.equals("e")) {
        todoList.save();
        break;
      } else {
        doUserAction(todoList, input);
      }
    }
  }

  private static User logInUser() {
    Users users = new Users();
    Scanner userInput = new Scanner(System.in);
    System.out.println("Log in with an existing user or create a new one: ");
    String userName = userInput.nextLine();
    User user = users.login(userName);
    System.out.println("Welcome " + userName + "!");
    return user;
  }

  private static void doUserAction(TodoList todoList, String userInput) {
    String[] inputData = userInput.split(" ");
    String unsupportedError = "Unsupported argument";

    if(inputData.length == 1) {
      if(inputData[0].equals("list") || inputData[0].equals("l")) {
        todoList.show();
      } else if (inputData[0].equals("help") || inputData[0].equals("h")) {
        System.out.println(help);
      } else if (inputData[0].equals("remove") || inputData[0].equals("r") ||
              inputData[0].equals("complete") || inputData[0].equals("c")) {
        System.out.println("Unable to complete: No index is provided");
      } else if (inputData[0].equals("add") || inputData[0].equals("a")) {
        System.out.println("Unable to add: No task is provided");
      } else {
        System.out.println(unsupportedError);
      }
    } else {
      if(inputData[0].equals("remove") || inputData[0].equals("r")) {
        todoList.remove(inputData[1]);
      } else if (inputData[0].equals("complete") || inputData[0].equals("c")) {
        todoList.complete(inputData[1]);
      } else if (inputData[0].equals("add") || inputData[0].equals("a")) {
        String newTask = userInput.substring(4, userInput.length());
        todoList.add(newTask);
      } else {
        System.out.println(unsupportedError);
      }
    }
  }
}
