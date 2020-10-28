package ua.training.repository.impl;

import org.apache.log4j.Logger;
import ua.training.domain.Role;
import ua.training.domain.User;
import ua.training.repository.UserRepository;
import ua.training.repository.db.ConnectionPool;

import java.sql.*;
import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    protected static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class);

    public static final String FIND_NEXT_USER_ID = "select nextval('hibernate_sequence')";

    /**
     * SELECT * FROM usr WHERE email = ?;
     */

    public static final String FIND_BY_EMAIL = "select * from " + User.TABLE_NAME + " JOIN " + User.TABLE_NAME_ROLE + " ON usr.id =user_role.user_id" + " where "
            + User.NAME_EMAIL + " = ?";
    /**
     * INSERT INTO usr(id,email, name, password, active) VALUES (?,?,?,?,?);
     */
    public static final String INSERT_USER =
            "insert into " + User.TABLE_NAME
                    + " (" + User.ID_COLUMN + ", " + User.NAME_EMAIL + ", " + User.NAME_COLUMN + ", "
                    + User.PASSWORD_COLUMN + ", " + User.ACTIVE_COLUMN + ") values (?, ?, ?, ?, ?);"
                    + "insert into " + User.TABLE_NAME_ROLE
                    + " (" + User.ROLE_USER_ID_COLUMN + ", " + User.ROLE_COLUMN + ") values (?, ?)";
    private static final String SQL_FIND_ALL_USERS = "select * from " + User.TABLE_NAME + " JOIN " + User.TABLE_NAME_ROLE + "  on usr.id = user_role.user_id;";


    @Override
    public User getOne(String email) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getOne --> " + email);
        try {
            con = ConnectionPool.getConnection();
            ps = con.prepareStatement(FIND_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            user = null;
            while (rs.next()) {
                user = extractUserForGetOne(rs, user);
            }
        } catch (SQLException ex) {
            LOG.error(ex);
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
        User user = new User(email, name, password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        LOG.trace("Repository method createUser  --> " + user);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Long StringId = null;
        try {
            con = ConnectionPool.getConnection();
            ps = con.prepareStatement(FIND_NEXT_USER_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                StringId = rs.getLong("nextval");
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
            user = null;
            while (rs.next()) {
                user = extractUser(rs);
            }


        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method createUser returned --> " + user);
        return user;
    }


    private User extractUser(ResultSet rs) throws SQLException {
        User
                user = new User(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("active")
        );
        user.getRoles().add(Role.valueOf(rs.getString("roles")));
        return user;
    }

    private User extractUserForGetOne(ResultSet rs, User user) throws SQLException {
        if (user == null) {
           user=extractUser(rs);
        } else {
            user.getRoles().add(Role.valueOf(rs.getString("roles")));
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        User user = null;
        List<User> usersList = new ArrayList<>();
        Map<String, User> map = new HashMap();
        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        LOG.trace("Repository impl method getAllUsers.");
        try {
            con = ConnectionPool.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                user = extractUser(rs);
                if (map.containsKey(user.getEmail())) {
                    User tempUser = map.get(user.getEmail());
                    tempUser.getRoles().addAll(user.getRoles());
                    map.put(tempUser.getEmail(), tempUser);
                } else {
                    map.put(user.getEmail(), user);
                }
            }
            usersList = new ArrayList(map.values());
        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method getAll returned --> " + usersList);
        return usersList;

    }
}
