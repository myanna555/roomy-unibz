package unibz.roomie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibz.roomie.model.Booking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hlib on 17.11.17.
 */
@Service
public class DataService {

    private static final String BOOK_QUERY =
            "INSERT INTO BOOKING " +
                    "(ROOM_ID, DAY, MONTH, YEAR, FROM_TIME, TO_TIME, TITLE, USER_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_BOOKINGS_QUERY =
            "SELECT * FROM BOOKING";

    private static final String DELETE_QUERY =
            "DELETE from BOOKING where BOOKING_ID = ?";

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

    public List<Booking> getAllBookings() throws BookingException {
        try (Connection connection = dataSource.getConnection()) {
            List<Booking> result = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(GET_ALL_BOOKINGS_QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getLong(1));
                booking.setRoomId(rs.getString(2));
                booking.setDay(rs.getInt(3));
                booking.setMonth(rs.getInt(4));
                booking.setYear(rs.getInt(5));
                booking.setFromTime(rs.getInt(6));
                booking.setToTime(rs.getInt(7));
                booking.setTitle(rs.getString(8));
                booking.setUserId(rs.getString(9));
                result.add(booking);
            }
            return result;
        } catch (SQLException e) {
            throw new BookingException("Query of bookings failed", e);
        }
    }

    public void delete(long bookingId) throws BookingException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);
            stmt.setLong(1, bookingId);
        } catch (SQLException e) {
            throw new BookingException("Query of bookings failed", e);
        }
    }

    public class BookingException extends Throwable {
        public BookingException(String s, SQLException e) {
            super(s, e);
        }
    }
}
