package MyBnB.controller;

import MyBnB.models.Listing;
import MyBnB.models.PaymentInfo;
import MyBnB.repository.implementations.ListingRepository;
import MyBnB.repository.implementations.PaymentInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
