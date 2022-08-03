package MyBnB.repository.interfaces;

import MyBnB.models.basic.Address;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRespository {

  public List<Address> getAllAddresses();
  public Address getAddress(int id);
  public void addAddress(Address newAddress);

}
