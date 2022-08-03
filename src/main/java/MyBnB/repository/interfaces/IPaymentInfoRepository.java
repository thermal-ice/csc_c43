package MyBnB.repository.interfaces;

import MyBnB.models.basic.PaymentInfo;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentInfoRepository {
  public List<PaymentInfo> getAllPaymentInfos();
  public PaymentInfo getPaymentInfo(int paymentInfoID);
  public List<PaymentInfo> getPaymentInfoByRenterID(int renterID);
  public void addPaymentInfo(PaymentInfo info);
}
