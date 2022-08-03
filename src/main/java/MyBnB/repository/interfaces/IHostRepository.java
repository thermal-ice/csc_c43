package MyBnB.repository.interfaces;

import MyBnB.models.basic.Host;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHostRepository {
    public List<Host> getAllHosts();
    public void addHost(Host host);
    public Host getHost(int id);
    public void deleteHost(int id);
}
