package MyBnB.models.basic;

import java.time.LocalDate;

public class PaymentInfo {
  public enum Field {
    ID("id"),
    CARD_NUMBER("cardNumber"),
    CARD_NAME("cardName"),
    EXPIRY_DATE("expiryDate"),
    RENTER_ID("renterID");
    private final String value;
    Field (final String value) { this.value = value; }
    @Override
    public String toString() { return this.value; }
  }

  private int id;
  private String cardNumber;
  private String cardName;
  private LocalDate expiryDate;
  private int renterID;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
  }

  public int getRenterID() {
    return renterID;
  }

  public void setRenterID(int renterID) {
    this.renterID = renterID;
  }

  @Override
  public String toString() {
    return "PaymentInfo{" +
        "id=" + id +
        ", cardNumber='" + cardNumber + '\'' +
        ", cardName='" + cardName + '\'' +
        ", expiryDate=" + expiryDate +
        ", renterID=" + renterID +
        '}';
  }
}
