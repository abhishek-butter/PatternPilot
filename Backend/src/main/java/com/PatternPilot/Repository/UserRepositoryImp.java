package com.PatternPilot.Repository;

import com.PatternPilot.Domain.User;
import com.PatternPilot.Exceptions.PPAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author Abhishek V S
 */
@Repository
public class UserRepositoryImp implements UserRepository {
    private static final String SQL_CREATE = "insert into pp_users(username, email, password) values (?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "select * from pp_users where userId = ?";
    private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD = "select * from pp_users where email = ?";
    private static final String SQL_UPDATE =
            "UPDATE pp_users SET username = ?, email = ?, password = ? WHERE userId = ?";
    private static final String SQL_DELETE = "DELETE FROM pp_users WHERE userId = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer createId(String username, String email, String password) throws PPAuthException{
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder key = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, hashedPassword);
                return ps;


            },key);
            return ((Number) key.getKeys().get("userid")).intValue();

        }
        catch (Exception e){
            e.printStackTrace();
            throw new PPAuthException("Invalid Data!");
        }
    }

    @Override
    public User findById(Integer userId) throws PPAuthException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,userRowMapper,userId);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws PPAuthException {
        /** @Unfinished - Logic errors in BCrypt comparison and SQL query selection */
        try{
            User user= jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL_AND_PASSWORD,userRowMapper,email);
            if(!BCrypt.checkpw(password, user.getPassword()))throw new PPAuthException("Invalid email/password");
            return user;

        }
        catch (Exception e){
            e.printStackTrace();
            throw new PPAuthException("Invalid email/password");
        }


    }

    @Override
    public void updateUser(Integer userId, User updatedUser) throws PPAuthException {
        try{
            jdbcTemplate.update(SQL_UPDATE,updatedUser.getUsername(),updatedUser.getEmail(),updatedUser.getPassword(),userId);

        }
        catch (Exception e){
            throw new PPAuthException("Invalid Data");
        }
    }

    @Override
    public void deleteUser(Integer userId) throws PPAuthException {
        /** @Unfinished - Implement DELETE functionality */
        try{
            jdbcTemplate.update(SQL_DELETE,userId);

        }
        catch (Exception e) {
            throw new PPAuthException("Invalid Data!");
        }
    }

    private final RowMapper<User> userRowMapper=((rs, rowNum)->{
        return new User
                (

                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("email"),

                        rs.getString("password")
                );
    });
}

