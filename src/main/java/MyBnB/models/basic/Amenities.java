package MyBnB.models.basic;

public class Amenities {
  public enum Field {
    NAME("name"),
    TYPE("type");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }
  private String name;
  private String type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Amenities{" +
        "name='" + name + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
