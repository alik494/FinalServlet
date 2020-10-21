package ua.training.domain;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private Integer id;
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

    public Activity(Integer id, String text, String tag, boolean activeAct, boolean archiveAct, Integer time, List<User> users) {
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.activeAct = activeAct;
        this.archiveAct = archiveAct;
        this.time = time;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
