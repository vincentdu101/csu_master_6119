package trainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincentdu on 4/30/17.
 */
public class TrainStationProgressService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public TrainStationProgressService() {

    }

    private void deleteExistingProgress(Integer stationId) {
        String SQL = "delete from train_station_progress where station_id = :id";
        Map namedParameters = new HashMap();
        namedParameters.put("id", stationId);
        jdbcTemplate.update(SQL, namedParameters);
    }

    private void createNewProgressEntry(Integer trainId, Integer stationId) {
        String SQL = "INSERT INTO station (station_id, train_id, created_at, modified_at) " +
                "VALUES (:station_id, :train_id, :created_at, :modified_at)";
        Map namedParameters = new HashMap();
        namedParameters.put("train_id", trainId);
        namedParameters.put("station_id", stationId);
        namedParameters.put("create_at", new Date());
        namedParameters.put("modified_at", new Date());
        jdbcTemplate.update(SQL, namedParameters);
    }

    public void create(Train train, Integer stationId) {
        createNewProgressEntry(train.getId(), stationId);
    }

    public void delete(Integer stationId) {
        deleteExistingProgress(stationId);
    }

}
