package MyBnB.controller;

import MyBnB.models.basic.PaymentInfo;
import MyBnB.repository.implementations.PaymentInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paymentInfo")
public class PaymentInfoController {

  @Autowired
  PaymentInfoRepository paymentInfoRepository;

  @GetMapping("/all")
  public List<PaymentInfo> getAllPaymentInfos() {
    return paymentInfoRepository.getAllPaymentInfos();
  }

  @GetMapping("/byUserID")
  public List<PaymentInfo> getPaymentInfoForUserID(@RequestParam("renterID") Integer renterID ){
    return paymentInfoRepository.getPaymentInfoByRenterID(renterID);
  }

  @PostMapping("/addPayment")
  public void addPaymentInfoForRenter(@RequestBody PaymentInfo newPaymentInfo){
    paymentInfoRepository.addPaymentInfo(newPaymentInfo);
  }
}
