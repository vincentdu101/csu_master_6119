package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/train_app");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        log.info("Creating tables");
        jdbcTemplate = new JdbcTemplate(dataSource);

        setupTables();
        setupStations();
//        setupTrains();
    }

    public void setupTables() {
        jdbcTemplate.execute("DROP table if exists customer");
        jdbcTemplate.execute("CREATE TABLE customer(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        jdbcTemplate.execute("DROP table if exists station");
        jdbcTemplate.execute("CREATE TABLE station(" +
                "id SERIAL, description VARCHAR(255), " +
                "next_north_station_id SMALLINT UNSIGNED, " +
                "next_south_station_id SMALLINT UNSIGNED, " +
                "created_at datetime, " +
                "modified_at datetime)");

        jdbcTemplate.execute("DROP table if exists train_progress");
        jdbcTemplate.execute("CREATE TABLE train_service_progress(" +
                "id SERIAL, " +
                "station_id SMALLINT UNSIGNED, " +
                "train_id SMALLINT UNSIGNED NOT NULL REFERENCES train(id), " +
                "active bool, " +
                "created_at datetime, " +
                "modified_at datetime)");

        jdbcTemplate.execute("DROP table if exists train");
        jdbcTemplate.execute("CREATE TABLE train(" +
                "id SERIAL, name VARCHAR(255), description VARCHAR(255), " +
                "start_station_id SMALLINT UNSIGNED NOT NULL REFERENCES station(id), " +
                "created_at datetime, " +
                "modified_at datetime)");

        jdbcTemplate.execute("DROP table if exists seat");
        jdbcTemplate.execute("CREATE TABLE seat(" +
                "id SERIAL, description VARCHAR(255), taken bool, " +
                "train_id SMALLINT UNSIGNED NOT NULL, " +
                "created_at datetime, " +
                "modified_at datetime)");
    }

    public void setupStations() {
        jdbcTemplate.execute("insert into station(description) values('North A Station')");
        jdbcTemplate.execute("insert into station(description) values('North B Station')");
        jdbcTemplate.execute("insert into station(description) values('North C Station')");
        jdbcTemplate.execute("insert into station(description) values('South A Station')");
        jdbcTemplate.execute("insert into station(description) values('South B Station')");
        jdbcTemplate.execute("insert into station(description) values('South C Station')");

        jdbcTemplate.execute("update station set next_north_station_id=null, next_south_station_id=2 where id = 1");
        jdbcTemplate.execute("update station set next_north_station_id=1, next_south_station_id=3 where id = 2");
        jdbcTemplate.execute("update station set next_north_station_id=2, next_south_station_id=4 where id = 3");
        jdbcTemplate.execute("update station set next_north_station_id=3, next_south_station_id=5 where id = 4");
        jdbcTemplate.execute("update station set next_north_station_id=4, next_south_station_id=6 where id = 5");
        jdbcTemplate.execute("update station set next_north_station_id=5, next_south_station_id=null where id = 6");
    }

    public void setupTrains() {
        jdbcTemplate.execute("insert into train (name, description, start_station_id) values('North A Train', 'North A Train', 1)");
        jdbcTemplate.execute("insert into train (name, description, start_station_id) values('North B Train', 'North B Train', 2)");
        jdbcTemplate.execute("insert into train (name, description, start_station_id) values('North C Train', 'North C Train', 3)");
        jdbcTemplate.execute("insert into train (name, description, start_station_id) values('South A Train', 'South A Train', 4)");
        jdbcTemplate.execute("insert into train (name, description, start_station_id) values('South B Train', 'South B Train', 5)");
        jdbcTemplate.execute("insert into train (name, description, start_station_id) values('South C Train', 'South C Train', 6)");
    }
}