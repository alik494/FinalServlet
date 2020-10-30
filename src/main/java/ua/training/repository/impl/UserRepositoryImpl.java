package ua.training.repository.impl;

import org.apache.log4j.Logger;
import ua.training.domain.Activity;
import ua.training.domain.Role;
import ua.training.domain.User;
import ua.training.repository.AdminRepository;
import ua.training.repository.UserRepository;
import ua.training.repository.db.ConnectionPool;

import java.sql.*;
import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    protected static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class);

    public static final String FIND_NEXT_ID = "select nextval('hibernate_sequence')";

    /**
     * SELECT * FROM usr WHERE email = ?;
     */

    public static final String FIND_BY_EMAIL = "select * from " + User.TABLE_NAME + " JOIN " + User.TABLE_NAME_ROLE
            + " ON usr.id =user_role.user_id" + " where "
            + User.NAME_EMAIL + " = ?";

    /**
     * SELECT * FROM usr WHERE id = ?;
     */

    public static final String FIND_BY_ID = "select * from " + User.TABLE_NAME + " JOIN " + User.TABLE_NAME_ROLE + " ON usr.id =user_role.user_id" + " where "
            + User.ID_COLUMN + " = ?";

    /**
     * INSERT INTO usr(id,email, name, password, active) VALUES (?,?,?,?,?);
     */
    public static final String INSERT_USER =
            "insert into " + User.TABLE_NAME
                    + " (" + User.ID_COLUMN + ", " + User.NAME_EMAIL + ", " + User.NAME_COLUMN + ", "
                    + User.PASSWORD_COLUMN + ", " + User.ACTIVE_COLUMN + ") values (?, ?, ?, ?, ?);"
                    + "insert into " + User.TABLE_NAME_ROLE
                    + " (" + User.ROLE_USER_ID_COLUMN + ", " + User.ROLE_COLUMN + ") values (?, ?)";

    public static final String INSERT_ACTIVITY =
            "insert into " + Activity.TABLE_NAME
                    + " (" + Activity.ID_COLUMN + ", " + Activity.TEXT_COLUMN + ", " + Activity.TAG_COLUMN + ", "
                    + Activity.ACTIVE_COLUMN + ", " + Activity.ARCHIVE_COLUMN + ", " + Activity.TIME_COLUMN + " ) values (?, ?, ?, ?, ?, ?);"
                    + "insert into " + Activity.TABLE_NAME_ACTIVITY_USERS
                    + " (" + Activity.ACTIVITY_USERS_ACTIVITY_ID_COLUMN + ", " + Activity.ACTIVITY_USERS_USERS_ID_COLUMN + ") values (?, ?)";


    private static final String SQL_FIND_ALL_USERS = "select * from " + User.TABLE_NAME + " JOIN " + User.TABLE_NAME_ROLE + "  on usr.id = user_role.user_id;";


    @Override
    public User getOne(String email) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LOG.trace("Repository impl method getOne --> " + email);
        try (Connection con = ConnectionPool.getConnection()) {
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
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LOG.trace("Repository impl method getOne BY ID --> " + id);
        try (Connection con = ConnectionPool.getConnection()) {
            ps = con.prepareStatement(FIND_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            user = null;
            while (rs.next()) {
                user = extractUserForGetOne(rs, user);
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method getOne BY ID returned --> " + user);
        return user;
    }

    @Override
    public User createUser(String email, String name, String password) {
        User user = new User(email, name, password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        LOG.trace("Repository method createUser  --> " + user);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long nextValID = null;
        try (Connection con = ConnectionPool.getConnection()) {
            ps = con.prepareStatement(FIND_NEXT_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                nextValID = rs.getLong("nextval");
            }
            user.setId(nextValID);
            LOG.trace("Repository method createUser  FIND_NEXT_USER_ID nextValID--> " + nextValID);
            ps = con.prepareStatement(INSERT_USER);
            ps.setLong(1, nextValID);
            ps.setString(2, email);
            ps.setString(3, name);
            ps.setString(4, password);
            ps.setBoolean(5, true);
            ps.setLong(6, nextValID);
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

    @Override
    public List<User> getAllUsers() {
        User user = null;
        List<User> usersList = null;
        Map<String, User> map = new LinkedHashMap<>();
        Statement st = null;
        ResultSet rs = null;
        LOG.trace("Repository impl method getAllUsers.");
        try (Connection con = ConnectionPool.getConnection()) {
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
            usersList = new ArrayList<>(map.values());
        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method getAll returned --> " + usersList);
        return usersList;

    }

    @Override
    public Activity createNewActivity(String text, String tag, Long userId) {
        Activity activity = new Activity();
        LOG.trace("Repository method createNewActivity for   --> " + userId + " " + text + " " + tag);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long nextValID = null;
        try (Connection con = ConnectionPool.getConnection()) {
            ps = con.prepareStatement(FIND_NEXT_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                nextValID = rs.getLong("nextval");
            }

            LOG.trace("Repository method createNewActivity nextValID--> " + nextValID);
            ps = con.prepareStatement(INSERT_ACTIVITY);
            ps.setLong(1, nextValID);
            ps.setString(2, text);
            ps.setString(3, tag);
            ps.setBoolean(4, false);
            ps.setBoolean(5, false);
            ps.setInt(6, 1);
            ps.setLong(7, nextValID);
            ps.setLong(8, userId);
            rs = ps.executeQuery();
            activity = null;
            while (rs.next()) {
                activity = AdminRepositoryImpl.extractActivity(rs);
            }


        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method createNewActivity returned --> " + activity);
        return activity;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
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

    protected static User extractUserForGetOne(ResultSet rs, User user) throws SQLException {
        if (user == null) {
            user = extractUser(rs);
        } else {
            user.getRoles().add(Role.valueOf(rs.getString("roles")));
        }
        return user;
    }


}
