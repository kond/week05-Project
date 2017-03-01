/**
 * Created by kond on 2017. 03. 01..
 */
public class Task {
  private String name;
  private Boolean checked;

  public Task(String name, Boolean checked) {
    this.name = name;
    this.checked = checked;
  }

  public String getName() {
    return name;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }
}
