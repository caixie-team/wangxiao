package co.bluepx.edu.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserLevel implements Serializable {

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public List<UserLevel> getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(List<UserLevel> userLevel) {
        this.userLevel = userLevel;
    }

    private Long level;//等级
    private String title;//头衔
    private Long exp;//经验值

    List<UserLevel> userLevel = new ArrayList<UserLevel>();
}
