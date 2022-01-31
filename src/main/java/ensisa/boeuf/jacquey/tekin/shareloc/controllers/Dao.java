package ensisa.boeuf.jacquey.tekin.shareloc.controllers;

import ensisa.boeuf.jacquey.tekin.shareloc.model.AchievedService;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Colocation;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Service;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;

import java.awt.*;

public class Dao {

    protected static final AbstractDao<User> userDao = new AbstractDao(User.class);
    protected static final AbstractDao<Colocation> colocationDao = new AbstractDao(Colocation.class);
    protected static final AbstractDao<Service> serviceDao = new AbstractDao(Service.class);
    protected static final AbstractDao<AchievedService> achievedServiceDao = new AbstractDao(AchievedService.class);

    //Table names
    protected static final String TABLE_EMAIL = "email";
    protected static final String TABLE_NAME = "name";

    //Constants
    protected static final int DEFAULT_SCORE = 100;

    public Dao() {
    }

    public static User getUser(String email) {
        if (email == null) return null;
        User u = userDao.findByTable(TABLE_EMAIL, email);
        return u;
    }

    public static Colocation getColocation(String name) {
        if (name == null) return null;
        Colocation colocation = colocationDao.findByTable(TABLE_NAME, name);
        return colocation;
    }

    public static boolean isManager(User user, Colocation colocation) {
        return user.getEmail().equals(colocation.getManager().getEmail());
    }

    public static boolean userIsIntoColoc(User user, Colocation colocation) {
        int cpt = 0;
        for (User u : colocation.getMembers()) {
            if (u.getEmail().equals(user.getEmail())) {
                cpt++;
            }
        }
        return cpt == 1;
    }

    public static boolean serviceIsAlreadyAchieved(Long serviceID) {
        int cpt = 0;
        for (AchievedService achievedService : achievedServiceDao.findAll()) {
            if (achievedService.getService().getId() == serviceID) {
                cpt++;
            }
        }
        return cpt == 1;
    }
}
