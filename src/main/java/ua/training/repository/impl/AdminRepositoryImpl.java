package ua.training.repository.impl;

import org.apache.log4j.Logger;
import ua.training.domain.Activity;
import ua.training.domain.Role;
import ua.training.domain.User;
import ua.training.repository.AdminRepository;
import ua.training.repository.db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {

    protected static final Logger LOG = Logger.getLogger(AdminRepositoryImpl.class);

    private static final String CHANGE_USER_STATUS_BY_ID = "UPDATE " + User.TABLE_NAME + " SET " + User.ACTIVE_COLUMN + " = NOT " + User.ACTIVE_COLUMN +
            " WHERE " + User.ID_COLUMN + " = ?";
    private static final String FIND_USERS_ACTIVITIES_BY_ACTIVE_ARCHIVE = "SElECT * FROM " + Activity.TABLE_NAME +
            " join activity_users ON activity.id = activity_users.activity_id" +
            " join usr on activity_users.users_id = usr.id" +
            " where " + Activity.ACTIVE_COLUMN + " = ? AND "
            + Activity.ARCHIVE_COLUMN + " = ?";

    @Override
    public void changeUserStatus(Long id) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;

        LOG.trace("Repository impl method getOne BY ID --> " + id);
        try (Connection con = ConnectionPool.getConnection()) {
            ps = con.prepareStatement(CHANGE_USER_STATUS_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            user = null;
            while (rs.next()) {
                user = UserRepositoryImpl.extractUserForGetOne(rs, user);
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method getOne BY ID returned --> " + user);

    }

    @Override
    public void changeUserRole(Long userId) {


    }

    @Override
    public void ActivateUserActivate(Long activityId) {

    }

    @Override
    public List getAllNotActive() {
        List<Activity> list = new LinkedList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        LOG.trace("Repository impl method getAllNotActive --> ");
        try (Connection con = ConnectionPool.getConnection()) {
            ps = con.prepareStatement(FIND_USERS_ACTIVITIES_BY_ACTIVE_ARCHIVE);
            ps.setBoolean(1, false);
            ps.setBoolean(2, false);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractActivity(rs));
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
        LOG.trace("Repository method getAllNotActivereturned --> " + list);
        return list;
    }

    protected static Activity extractActivity(ResultSet rs) throws SQLException {
        Activity activity = new Activity(
                rs.getLong("id"),
                rs.getString("text"),
                rs.getString("tag"),
                rs.getBoolean(Activity.ACTIVE_COLUMN),
                rs.getBoolean(Activity.ARCHIVE_COLUMN),
                rs.getInt("time")

        );
        activity.getUsers().add(extractUser(rs));
        return activity;
    }

    //todo to extract more user then one
    private static User extractUser(ResultSet rs) throws SQLException {
        User
                user = new User(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("active")
        );
//        user.getRoles().add(Role.valueOf(rs.getString("roles")));
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
