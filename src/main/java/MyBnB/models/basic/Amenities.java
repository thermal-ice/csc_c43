package MyBnB.models.basic;

public class Amenities {
  public enum COL {
    NAME("name"),
    TYPE("type");
    private String value;
    COL (final String value) {
      this.value = value;
    }
    @Override
    public String toString() {
      return value;
    }
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
