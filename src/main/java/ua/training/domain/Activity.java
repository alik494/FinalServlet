package ua.training.domain;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME = "activity";

    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME_ACTIVITY_USERS = "activity_users";



    /**
     * The corresponding name of id column in table.
     */
    public static final String ID_COLUMN = "id";
    /**
     * The corresponding name of name column in table.
     */
    public static final String ACTIVE_COLUMN = "active_act";
    /**
     * The corresponding name of name column in table.
     */
    public static final String ARCHIVE_COLUMN = "archive_act";
    /**
     * The corresponding name of password column in table.
     */
    public static final String TAG_COLUMN = "tag";
    /**
     * The corresponding name of active column in table.
     */
    public static final String TEXT_COLUMN = "text";
    /**
     * The corresponding name of active column in table.
     */
    public static final String TIME_COLUMN = "time";

    /**
     * The corresponding name of active column in table.
     */
    public static final String ACTIVITY_USERS_ACTIVITY_ID_COLUMN = "activity_id";
    /**
     * The corresponding name of active column in table.
     */
    public static final String ACTIVITY_USERS_USERS_ID_COLUMN = "users_id";




    private Long id;
    private String text;
    private String tag;
    private boolean activeAct;
    private boolean archiveAct;

    private Integer time;

    private List<User> users = new ArrayList<>();

    public Activity() {
    }

    public Activity(String text, String tag, boolean activeAct, boolean archiveAct, Integer time, List<User> users) {
        this.text = text;
        this.tag = tag;
        this.activeAct = activeAct;
        this.archiveAct = archiveAct;
        this.time = time;
        this.users = users;
    }

    public Activity(Long id, String text, String tag, boolean activeAct, boolean archiveAct, Integer time) {
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.activeAct = activeAct;
        this.archiveAct = archiveAct;
        this.time = time;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isActiveAct() {
        return activeAct;
    }

    public void setActiveAct(boolean activeAct) {
        this.activeAct = activeAct;
    }

    public boolean isArchiveAct() {
        return archiveAct;
    }

    public void setArchiveAct(boolean archiveAct) {
        this.archiveAct = archiveAct;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", tag='" + tag + '\'' +
                ", activeAct=" + activeAct +
                ", archiveAct=" + archiveAct +
                ", time=" + time +
                ", users=" + users +
                '}';
    }
}
