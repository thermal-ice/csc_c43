package MyBnB.repository.interfaces;

import MyBnB.models.Host;
import MyBnB.models.Renter;

import java.util.List;

public interface RenterRepository {
    public List<Renter> getAllRenters();
    public void addRenter(Renter renter);
    public Renter getRenter(int id);
    public void deleteRenter(int id);
}
