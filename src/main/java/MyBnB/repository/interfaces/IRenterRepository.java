package MyBnB.repository.interfaces;

import MyBnB.models.basic.Renter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRenterRepository {
    public List<Renter> getAllRenters();
    public void addRenter(Renter renter);
    public Renter getRenter(int id);
    public void deleteRenter(int id);
}
