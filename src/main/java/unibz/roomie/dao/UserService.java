package unibz.roomie.dao;


import org.springframework.stereotype.Service;
import unibz.roomie.model.User;
import unibz.roomie.util.DatabaseDriver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Service
public class UserService {
	
	private static final String REGISTER_USER_SQL = "INSERT INTO USERS " + 
			"(email, password, first_name, last_name, phone, faculty, picture) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_INFO_BY_ID = "SELECT * FROM USERS where id = ?";
    
    private static final String GET_USER_INFO_BY_EMAIL = "SELECT * FROM USERS where email = ?";

    private static final String GET_USER_PASSWORD_SQL = "SELECT password from USERS where email = ?";
    
    
    public int registerUser(User user) throws SQLException {
    
    	PreparedStatement preparedStatement = null;
    	try{
    	preparedStatement = DatabaseDriver.getConnection().prepareStatement(REGISTER_USER_SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getPhone());
        preparedStatement.setString(6, user.getFaculty());
        preparedStatement.setString(7, user.getPicture());
        
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);      
        
    	}
    	catch (SQLException e ) {
    		e.printStackTrace();
    		return 0;
    	}
    	finally {
    		if(preparedStatement !=null) preparedStatement.close();
    	}
        
    }
    
    //return user object by user id
    public User getUserInfo(int userId) throws SQLException {
    	PreparedStatement preparedStatement = null;
    	try{
    	preparedStatement = DatabaseDriver.getConnection().prepareStatement(GET_USER_INFO_BY_ID);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        User userInfo = new User();
        while (rs.next()) {
        	userInfo.setId(rs.getInt(1));
        	userInfo.setEmail(rs.getString(2));
        	userInfo.setFirstName(rs.getString(4));
        	userInfo.setLastName(rs.getString(5));
        	userInfo.setPhone(rs.getString(6));
        	userInfo.setFaculty(rs.getString(7));
        	userInfo.setPicture(rs.getString(8));
        }
        return userInfo;
        
    	}
    	catch (SQLException e ) {
    		e.printStackTrace();
    		return null;
    	}
    	finally {
    		if(preparedStatement !=null) preparedStatement.close();
    	}
    }

    //return user object by email
    public User getUserInfo(String email) throws SQLException {
    	PreparedStatement preparedStatement = null;
    	try{
    	preparedStatement = DatabaseDriver.getConnection().prepareStatement(GET_USER_INFO_BY_EMAIL);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        User userInfo = new User();
        while (rs.next()) {
        	userInfo.setId(rs.getInt(1));
        	userInfo.setEmail(rs.getString(2));
        	userInfo.setFirstName(rs.getString(4));
        	userInfo.setLastName(rs.getString(5));
        	userInfo.setPhone(rs.getString(6));
        	userInfo.setFaculty(rs.getString(7));
        	userInfo.setPicture(rs.getString(8));
        }
        return userInfo;
        
    	}
    	catch (SQLException e ) {
    		e.printStackTrace();
    		return null;
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
