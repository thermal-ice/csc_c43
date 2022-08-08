package MyBnB.controller.requestbodies;

public class DeleteAvailabilityBody {
  private int availID;
  private int hostID;

  public int getAvailID() {
    return availID;
  }

  public void setAvailID(int availID) {
    this.availID = availID;
  }

  public int getHostID() {
    return hostID;
  }

  public void setHostID(int hostID) {
    this.hostID = hostID;
  }

  @Override
  public String toString() {
    return "DeleteAvailabilityBody{" +
        "availID=" + availID +
        ", hostID=" + hostID +
        '}';
  }
}
