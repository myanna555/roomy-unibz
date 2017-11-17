package unibz.roomie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibz.roomie.model.Booking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hlib on 17.11.17.
 */
@Service
public class DataService {

    private final static String BOOK_QUERY =
            "INSERT INTO BOOKING " +
                    "(ROOM_ID, DAY, MONTH, YEAR, FROM_TIME, TO_TIME, TITLE, USER_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Autowired
    private DataSource dataSource;

    public void book(Booking booking) throws BookingException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(BOOK_QUERY);
            stmt.setString(1, booking.getRoomId());
            stmt.setInt(2, booking.getDay());
            stmt.setInt(3, booking.getMonth());
            stmt.setInt(4, booking.getYear());
            stmt.setInt(5, booking.getFromTime());
            stmt.setInt(6, booking.getToTime());
            stmt.setString(7, booking.getTitle());
            stmt.setString(8, booking.getUserId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new BookingException("Room booking failed", e);
        }
    }

    public class BookingException extends Throwable {
        public BookingException(String s, SQLException e) {
            super(s, e);
        }
    }
}
