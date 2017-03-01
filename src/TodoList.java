import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kond on 2017. 03. 01..
 */
public class TodoList {
  private String filePath;
  private List<Task> tasks;

  public TodoList(String filePath) {
    this.filePath = filePath;
    this.tasks = new ArrayList<Task>();
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public TodoList load() {
    try {
      File file = new File(this.filePath);

      if(!file.exists()) {
        file.createNewFile();
      }

      BufferedReader fileContent = new BufferedReader(
              new FileReader(file)
      );

      String line = fileContent.readLine();
      while (line != null) {
        String[] taskData = line.split(";");
        Boolean isChecked = (taskData[0].equals("checked")) ? true : false;
        Task task = new Task(taskData[1], isChecked);
        this.tasks.add(task);
        line = fileContent.readLine();
      }

      fileContent.close();

    } catch (FileNotFoundException e) {
      System.out.println("File not found :(\n");
    } catch (IOException e) {
      System.out.println("Can't open the file... :(\n");
    } catch (Exception e) {
      System.out.println("Ooooops, something happened... :(\n");
    }
    return this;
  }

  public void show() {
    System.out.println((this.tasks.size() > 0) ? "Your Todolist: " : "No todos for today! :)");
    int i = 0;
    for(Task t : this.tasks) {
      String checked = (t.getChecked()) ? "x" : " ";
      System.out.println(++i + " -  [" + checked + "] " + t.getName());
    }
    System.out.println();
  }

  public void add(String taskName) {
    Task task = new Task(taskName, false);
    this.tasks.add(task);
  }

  public void remove(String taskId) {
    try {
      int id = Integer.parseInt(taskId) - 1;
      this.tasks.remove(id);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Unable to remove: Index is out of bound");
    } catch (NumberFormatException e) {
      System.out.println("Unable to remove: Index is not a number");
    } catch (Exception e) {
      System.out.println("Unable to remove");
    }
  }

  public void complete(String taskId) {
    try {
      int id = Integer.parseInt(taskId) - 1;
      this.tasks.get(id).setChecked(true);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Unable to complete: Index is out of bound");
    } catch (NumberFormatException e) {
      System.out.println("Unable to complete: Index is not a number");
    } catch (Exception e) {
      System.out.println("Unable to complete");
    }
  }

  public void save() {
    try {
      File file = new File(this.filePath);

      PrintWriter fileInput = new PrintWriter(
              new BufferedWriter(
                      new FileWriter(file)
              )
      );

      for(Task t : this.tasks) {
        String checkedState = (t.getChecked()) ? "checked" : "not_checked";
        fileInput.printf("%s;%s\n", checkedState, t.getName());
      }
      fileInput.close();

    } catch (FileNotFoundException e) {
      System.out.println("File not found :(\n");
    } catch (IOException e) {
      System.out.println("Can't open the file... :(\n");
    } catch (Exception e) {
      System.out.println("Ooooops, something happened... :(\n");
    }
  }
}
