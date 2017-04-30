package trainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by vincentdu on 4/30/17.
 */
public class StationService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public StationService() {

    }

    private Station createStationFromMapObject(Map<String, Object> objectMap) {
        return new Station(
                Integer.parseInt(objectMap.get("id").toString()),
                objectMap.get("description").toString(),
                Integer.parseInt(objectMap.get("next_north_station_id").toString()),
                Integer.parseInt(objectMap.get("next_south_station_id").toString()),
                new Date(objectMap.get("created_at").toString()),
                new Date(objectMap.get("modified_at").toString())
        );
    }

    public Station findStationByDescription(String description) {
        String SQL = "select * from stations where description = :description";
        Map namedParameters = new HashMap();
        namedParameters.put("description", "North A Station");
        List<Map<String, Object>> stationObject = jdbcTemplate.queryForList(SQL, namedParameters);
        for (Map<String, Object> objectMap : stationObject) {
            return createStationFromMapObject(objectMap);
        }
        return null;
    }

    public Station findStationById(Integer id) {
        String SQL = "select * from stations where description = :id limit 1";
        Map namedParameters = new HashMap();
        namedParameters.put("id", id);
        List<Map<String, Object>> stationObject = jdbcTemplate.queryForList(SQL, namedParameters);
        for (Map<String, Object> objectMap : stationObject) {
            return createStationFromMapObject(objectMap);
        }
        return null;
    }

}
