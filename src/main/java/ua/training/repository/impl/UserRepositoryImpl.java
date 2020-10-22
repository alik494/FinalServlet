package ua.training.repository.impl;

import org.apache.log4j.Logger;
import ua.training.domain.Role;
import ua.training.domain.User;
import ua.training.repository.UserRepository;
import ua.training.repository.db.ConnectionPool;

import java.sql.*;
import java.util.Collections;

public class UserRepositoryImpl implements UserRepository {

    protected static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class);

    public static final String FIND_NEXT_USER_ID = "select nextval('hibernate_sequence')";

    /**
     * SELECT * FROM app_users WHERE email = ?;
     */

    public static final String FIND_BY_EMAIL = "select * from " + User.TABLE_NAME + " where "
                    + User.NAME_EMAIL + " = ?";
    /**
     * INSERT INTO usr(id,email, name, password, active) VALUES (?, ?,?,?, ?);
     */
    public static final String INSERT_USER =
                    "insert into " + User.TABLE_NAME
                    + " (" + User.ID_COLUMN + ", " + User.NAME_EMAIL + ", " + User.NAME_COLUMN + ", "
                    + User.PASSWORD_COLUMN + ", " + User.ACTIVE_COLUMN + ") values (?, ?, ?, ?, ?);"
                    +"insert into " + User.TABLE_NAME_ROLE
                    + " (" + User.ROLE_USER_ID_COLUMN + ", " + User.ROLE_COLUMN + ") values (?, ?)";



    @Override
    public User getOne(String email) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne --> " + email);
        try {
           con=ConnectionPool.getConnection();

            ps = con.prepareStatement(FIND_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("active")
                );
            }
        } catch (SQLException ex) {
            LOG.error( ex);
        }
        LOG.trace("Repository method getOne returned --> " + user);
        return user;
    }

    @Override
    public User getOne(long id) {
        return null;
    }

    @Override
    public User createUser(String email, String name, String password) {
        User user=new User(email,name,password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        LOG.trace("Repository method createUser  --> " + user);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Long StringId = null;
        try {
            con=ConnectionPool.getConnection();
            ps=con.prepareStatement(FIND_NEXT_USER_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                 StringId=rs.getLong("nextval");
            }
            user.setId(StringId);
            LOG.trace("Repository method createUser  FIND_NEXT_USER_ID StringId--> " + StringId);


            ps = con.prepareStatement(INSERT_USER);
            ps.setLong(1, StringId);
            ps.setString(2, email);
            ps.setString(3, name);
            ps.setString(4, password);
            ps.setBoolean(5, true);
            ps.setLong(6, StringId);
            ps.setString(7, "USER");
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("active")
                );
            }


        } catch (SQLException ex) {
            LOG.error( ex);
        }
        LOG.trace("Repository method createUser returned --> " + user);
        return user;
    }
}
