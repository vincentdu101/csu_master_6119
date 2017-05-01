package trainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincentdu on 4/30/17.
 */
public class SeatService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public SeatService() {

    }

    public Seat create(Train train, Seat seat) {
        String SQL = "INSERT INTO seat (seat_type, taken, train_id, description, created_at, modified_at) " +
                "VALUES (:taken, :train_id, :description, :created_at, :modified_at)";
        Map namedParameters = new HashMap();
        namedParameters.put("taken", seat.isTaken());
        namedParameters.put("seat_type", seat.getSeatType());
        namedParameters.put("train_id", train.getId());
        namedParameters.put("description", seat.getDescription());
        namedParameters.put("create_at", seat.getCreatedAt());
        namedParameters.put("modified_at", seat.getModifiedAt());
        jdbcTemplate.update(SQL, namedParameters);
        return seat;
    }

}
