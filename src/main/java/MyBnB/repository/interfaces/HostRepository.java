package MyBnB.repository.interfaces;

import MyBnB.models.Host;
import MyBnB.models.User;

import java.util.List;

public interface HostRepository {
    public List<Host> getAllHosts();
    public void addHost(Host host);
    public Host getHost(int id);
    public void deleteHost(int id);
}
