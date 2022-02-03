package ensisa.boeuf.jacquey.tekin.shareloc.controllers;

import ensisa.boeuf.jacquey.tekin.shareloc.model.Colocation;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Point;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
import io.swagger.annotations.Api;

@Api(value = "/colocation")
public class ColocationDao extends Dao {

    public ColocationDao() {
        super();
    }

    public static boolean createColocation(String name, String admin_email) {
        User admin = getUser(admin_email);
        if (admin != null && getColocation(name) == null) {
            if (!(name.equals(""))) {
                Colocation colocation = new Colocation(name, admin);
                colocationDao.create(colocation);
                Point point = new Point(colocation, DEFAULT_SCORE);
                admin.getPoints().add(point);
                userDao.edit(admin);
                return true;
            }
        }
        return false;
    }

    public static boolean inviteUserIntoColocation(String name, String admin_email, String email) {
        Colocation colocation = getColocation(name);
        User invited = getUser(email);
        User admin = getUser(admin_email);
        Point point = new Point(colocation, DEFAULT_SCORE);

        if (admin == null || invited == null || colocation == null) {
            return false;
        }

        if (isManager(admin, colocation) && !userIsIntoColoc(invited, colocation)) {
            colocation.addMember(invited);
            colocationDao.edit(colocation);
            invited.getPoints().add(point);
            userDao.edit(invited);
            return true;
        }

        return false;
    }

    public static boolean removeColocation(String name, String email) {
        Colocation colocation = getColocation(name);
        if (colocation != null && email.equals(colocation.getManager().getEmail())) {
            colocationDao.remove(colocation);
            return true;
        }
        return false;
    }

    public static boolean editColocationName(String name, String admin_email, String newName) {
        Colocation colocation = getColocation(name);
        User admin = getUser(admin_email);
        if (colocation != null && admin != null && admin.getEmail().equals(colocation.getManager().getEmail())) {
            colocation.setName(newName);
            colocationDao.edit(colocation);
            return true;
        }
        return false;
    }

    public static boolean removeMemberFromColoc(String name, String admin_email, String member_email) {
        User admin = getUser(admin_email);
        User toDelete = getUser(member_email);
        Colocation colocation = getColocation(name);

        if (colocation != null && admin != null && toDelete != null) {
            if (isManager(admin, colocation) && userIsIntoColoc(toDelete, colocation)) {
                colocation.getMembers().remove(toDelete);
                colocationDao.edit(colocation);
                return true;
            }
        }
        return false;
    }

    public static String getBestUser(String email, String name) {
        User user = getUser(email);
        Colocation colocation = getColocation(name);

        if (user == null || colocation == null || !userIsIntoColoc(user, colocation)) {
            return null;
        }

        if (colocation.getMembers().size() > 1) {
            User best = colocation.getMembers().get(0);
            int score = getUserScoreIntoColocation(colocation, best).getPoint();
            for (User u : colocation.getMembers()) {
                if (getUserScoreIntoColocation(colocation, best).getPoint()
                        < getUserScoreIntoColocation(colocation, u).getPoint()) {
                    best = u;
                    score = getUserScoreIntoColocation(colocation, best).getPoint();
                }
            }
            return best.toString() + ", score = " + score;
        } else {
            return "colocation size <= 1";
        }
    }

}
