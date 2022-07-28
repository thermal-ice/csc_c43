package MyBnB.repository.implementations;

import MyBnB.models.Listing;
import MyBnB.models.PaymentInfo;
import MyBnB.models.Renter;
import MyBnB.repository.interfaces.IPaymentInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentInfoRepository implements IPaymentInfoRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<PaymentInfo> getAllPaymentInfos() {
    return jdbcTemplate.query("SELECT * FROM PaymentInfo;",new BeanPropertyRowMapper<>(PaymentInfo.class));
  }

  @Override
  public PaymentInfo getPaymentInfo(int paymentInfoID) {
    try{
      return (PaymentInfo) jdbcTemplate.queryForObject("SELECT * FROM PaymentInfo WHERE id=?;",new BeanPropertyRowMapper(
          PaymentInfo.class), paymentInfoID);
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public List<PaymentInfo> getPaymentInfoByRenterID(int renterID) {
    return jdbcTemplate.query("SELECT * FROM PaymentInfo where renterID = ?;",
        new BeanPropertyRowMapper<>(PaymentInfo.class),
        renterID);
  }

  @Override
  public void addPaymentInfo(PaymentInfo info) {
    jdbcTemplate.update("insert into PaymentInfo (cardNumber, cardName, expiryDate, renterID)" +
            "values (?,?,?,?);",
        info.getCardNumber(),
        info.getCardName(),
        info.getExpiryDate(),
        info.getRenterID());
  }
}
