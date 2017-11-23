package unibz.roomie.dao;


import org.springframework.stereotype.Service;
import unibz.roomie.model.User;
import unibz.roomie.util.DatabaseDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class UserService {
	
	private static final String REGISTER_USER_SQL = "INSERT INTO USERS " + 
			"(email, password, first_name, last_name, phone, faculty, picture) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_INFO_SQL = "SELECT * FROM USERS where id = ?";

    private static final String GET_USER_PASSWORD_SQL = "SELECT password from USERS where email = ?";
    
    
    public boolean registerUser(User user) throws SQLException {
    
    	PreparedStatement preparedStatement = null;
    	try{
    	preparedStatement = DatabaseDriver.getConnection().prepareStatement(REGISTER_USER_SQL);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getPhone());
        preparedStatement.setString(6, user.getFaculty());
        preparedStatement.setString(7, user.getPicture());
        
        int i = preparedStatement.executeUpdate();
        if (i>0) return true;
        else return false;        
        
    	}
    	catch (SQLException e ) {
    		e.printStackTrace();
    		return false;
    	}
    	finally {
    		if(preparedStatement !=null) preparedStatement.close();
    	}
        
    }
    
   
    
    //check if user's login email corresponds to their pass in db
    
    public boolean isUserValid(User user) throws SQLException {
    	PreparedStatement preparedStatement = null;
    	try{
    	preparedStatement = DatabaseDriver.getConnection().prepareStatement(GET_USER_PASSWORD_SQL);
        preparedStatement.setString(1, user.getEmail());
        ResultSet rs = preparedStatement.executeQuery();
        String pass="";
        while (rs.next()) {
        	pass = rs.getString(1);
        }
        if(user.getPassword().equals(pass)) return true;
        else return false;
        
    	}
    	catch (SQLException e ) {
    		e.printStackTrace();
    		return false;
    	}
    	finally {
    		if(preparedStatement !=null) preparedStatement.close();
    	}
    }

  
	

}
