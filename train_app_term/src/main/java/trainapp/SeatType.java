package trainapp;

/**
 * Created by vincentdu on 4/30/17.
 */
public enum SeatType {

    REGULAR_SEAT("Regular Seat"),
    SPECIAL_RESERVED_SEAT("Special Regular Seat"),
    TABLE_SEAT("Table Seat");

    String seat;

    SeatType(String seat) {
        this.seat = seat;
    }

    public String getSeat() {
        return seat;
    }

}
