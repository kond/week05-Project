import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kond on 2017. 03. 01..
 */
public class Users {
  private static final String workingDir = "/Users/kond/greenfox/kond/week-05/Project/.gitignore/todolists/";
  private List<User> users;

  public Users() {
    this.users = loadUsers();
  }

  public static String getWorkingDir() {
    return workingDir;
  }

  public List<User> getUsers() {
    return users;
  }

  private ArrayList<User> loadUsers() {
    ArrayList<User> users = new ArrayList<User>();
    try {
      File directory = new File(workingDir);
      if(directory.isDirectory()) {
        String[] fileList = directory.list();
        for(String todoFile : fileList) {
          String userName = todoFile.substring(0, todoFile.length() - 4);
          User user = new User(userName);
          users.add(user);
        }
      } else {
        System.out.println("The given path is not a valid directory path: " + workingDir);
      }
    } catch (Exception e) {
      System.out.println("Error: Couldn\'t list TODO files\n");
    }
    return users;
  }

  private ArrayList<String> getExistingUserNames() {
    ArrayList<String> userNames = new ArrayList<String>();

    for(User user : this.users) {
      userNames.add(user.getName());
    }

    return userNames;
  }

  public User login(String userName) {
    User user = new User(userName); // Returns this new user if won't find existing
    for(User u : this.users) {
      if(u.getName().equals(userName)) {
        user = u;
      }
    }
    return user;
  }

  public void show() {
    System.out.println("Users:");
    for(User user : this.users) {
      System.out.println("  " + user.getName());
    }
  }

}
