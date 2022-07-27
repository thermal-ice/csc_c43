package MyBnB;

import MyBnB.models.Listing;
import MyBnB.repository.implementations.ListingRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

//@ComponentScan({"MyBnB.repository.implementations", "MyBnB.repository.interfaces"})
@SpringBootApplication
//@ComponentScan("MyBnB.repository.implementations")
//@ComponentScan("MyBnB.repository.interfaces")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

//      ListingRepository listingRepository = new ListingRepository();
      //    ConnectionInitializer.printSampleDBQuery();
    }


}
