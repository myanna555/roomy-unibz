package unibz.roomie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by hlib on 17.11.17.
 */
@Service
public class DataService {

    @Autowired
    private DataSource dataSource;

    public String getSampleDataFromDb() {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            return output.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
