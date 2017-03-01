/**
 * Created by kond on 2017. 03. 01..
 */
public class User {
  private String name;
  private String file;
  private TodoList todoList;

  public User(String name) {
    this.name = name;
    this.file = Users.getWorkingDir() + name + ".csv";
    this.todoList = new TodoList(file);
  }

  public String getName() {
    return name;
  }

  public TodoList loadTodoList() {
    this.todoList = this.todoList.load();
    return this.todoList;
  }

}
