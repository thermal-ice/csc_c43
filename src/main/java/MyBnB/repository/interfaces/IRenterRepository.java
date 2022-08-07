package MyBnB.repository.interfaces;

import MyBnB.models.basic.Renter;
import MyBnB.models.composite.RenterIDWithBookingCount;
import MyBnB.models.composite.RenterIDWithCityWithBookingCount;
import MyBnB.models.composite.YearUserIDBookingCount;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRenterRepository {
    public List<Renter> getAllRenters();
    public void addRenter(Renter renter);
    public Renter getRenter(int id);
    public void deleteRenter(int id);
    public List<RenterIDWithBookingCount> getRenterRankedByNumberOfBookingsWithinRange(LocalDate startDate, LocalDate endDate);
    public List<RenterIDWithCityWithBookingCount> getRenterRankedByNumberOfBookingsWithinRangePerCity(LocalDate startDate, LocalDate endDate);
    public List<YearUserIDBookingCount> getRentersRankedByNumberOfCancellationsInYear();

}
