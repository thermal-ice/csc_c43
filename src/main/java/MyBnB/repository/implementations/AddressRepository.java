package MyBnB.repository.implementations;

import MyBnB.models.basic.Address;
import MyBnB.models.basic.PaymentInfo;
import MyBnB.repository.interfaces.IAddressRespository;
import MyBnB.repository.interfaces.IPaymentInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository implements IAddressRespository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query("SELECT * FROM Address;",new BeanPropertyRowMapper<>(Address.class));
    }

    @Override
    public Address getAddress(int listingID) {
        try{
            return (Address) jdbcTemplate.queryForObject("SELECT * FROM Address WHERE listingID=?;",new BeanPropertyRowMapper(
                    Address.class), listingID);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void addAddress(Address newAddress) {
        jdbcTemplate.update("insert into Address (listingID, addressLine, city, province_territory, postalCode, country)" +
                        "values (?,?,?,?,?,?);",
                newAddress.getListingID(),
                newAddress.getAddressLine(),
                newAddress.getCity(),
                newAddress.getProvince_territory(),
                newAddress.getPostalCode(),
                newAddress.getCountry());
    }
}
