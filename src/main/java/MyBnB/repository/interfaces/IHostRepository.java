package MyBnB.repository.interfaces;

import MyBnB.models.basic.Host;
import MyBnB.models.composite.CountryCityHostIDListingCount;
import MyBnB.models.composite.CountryHostIDListingCount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHostRepository {
    public List<Host> getAllHosts();
    public void addHost(Host host);
    public Host getHost(int id);
    public void deleteHost(int id);
    public List<CountryHostIDListingCount> getHostsRankedByNumberOfListingsPerCountry();
    public List<CountryCityHostIDListingCount> getHostsRankedByNumberOfListingsPerCountryCity();
}
