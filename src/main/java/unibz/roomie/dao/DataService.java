package unibz.roomie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import unibz.roomie.model.Booking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            "SELECT * FROM BOOKING WHERE TRUE ";

    private static final String DELETE_QUERY =
            "DELETE from BOOKING where ID = ?";

    @Autowired
    private DataSource dataSource;

    public int book(Booking booking) throws BookingException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(BOOK_QUERY, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, booking.getRoomId());
            stmt.setInt(2, booking.getDay());
            stmt.setInt(3, booking.getMonth());
            stmt.setInt(4, booking.getYear());
            stmt.setInt(5, booking.getFromTime());
            stmt.setInt(6, booking.getToTime());
            stmt.setString(7, booking.getTitle());
            stmt.setInt(8, booking.getUserId());

            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new BookingException("Room booking failed", e);
        }
    }

    public List<Booking> getAllBookings(Optional<String> date, Optional<Integer> userId) throws BookingException {
        try (Connection connection = dataSource.getConnection()) {
            List<Booking> result = new ArrayList<>();
            StringBuilder finalQuery = new StringBuilder(GET_ALL_BOOKINGS_QUERY);
            //TODO this is agly but will work for now
            if (userId.isPresent()) {
                finalQuery.append("AND USER_ID = ?");
            }
            if (date.isPresent()) {
                finalQuery.append("AND YEAR = ? AND MONTH = ? AND DAY = ?");
            }
            PreparedStatement stmt = connection.prepareStatement(finalQuery.toString());
            int counter = 0;
            if (userId.isPresent()) {
                stmt.setInt(++counter, userId.get());
            }
            if (date.isPresent()) {
                try {
                    int year = Integer.parseInt(date.get().substring(0, 4));
                    int month = Integer.parseInt(date.get().substring(5, 7));
                    int day = Integer.parseInt(date.get().substring(8, 10));
                    stmt.setInt(++counter, year);
                    stmt.setInt(++counter, month);
                    stmt.setInt(++counter, day);
                } catch (NumberFormatException nfe) {
                    throw new BookingException(
                            "Wrong date format. The format should be: YYYY-MM-DD, but was: "
                            + date.get(), nfe);
                }
            }
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
                booking.setUserId(rs.getInt(9));
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
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new BookingException("Query of bookings failed", e);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class BookingException extends RuntimeException {
        public BookingException(String s, Exception e) {
             super(s, e);
        }
    }
}
