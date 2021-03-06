package ensisa.boeuf.jacquey.tekin.shareloc.controllers;

import ensisa.boeuf.jacquey.tekin.shareloc.model.*;
import io.swagger.annotations.Api;

@Api(value = "/user")
public class UserDao extends Dao {

    public UserDao() {
        super();
    }

    public static boolean createUser(String email, String password, String firstname, String lastname) {
        if (getUser(email) == null) {
            if (!(email.equals("") && password.equals("") && firstname.equals("") && lastname.equals(""))) {
                User user = new User(email, password, firstname, lastname);
                userDao.create(user);
                return true;
            }
        }
        return false;
    }

    public static User login(String email, String password) {
        User u = getUser(email);
        if (u != null && u.getPassword().equals(password))
            return u;
        return null;
    }

    public static boolean editUser(String email, String password, String firstname, String lastname) {
        User user = getUser(email);
        if (user.getPassword().equals(password)) {
            if (user != null && firstname != null && lastname != null && firstname != "" && lastname != "") {
                user.setFirstName(firstname);
                user.setLastName(lastname);
                userDao.edit(user);
                return true;
            }
        }
        return false;
    }

    public static boolean quitColocation(String email, String name) {
        User user = getUser(email);
        Colocation colocation = getColocation(name);
        if (user != null && colocation != null && !isManager(user, colocation)) {
            colocation.getMembers().remove(user);
            colocationDao.edit(colocation);
            return true;
        }
        return false;
    }

    public static boolean vote(String email, Long serviceID, int vote) {
        User user = getUser(email);
        Service service = serviceDao.find(serviceID);

        if (user == null || service == null) {
            return false;
        }

        Colocation colocation = service.getColocation();
        if (userIsIntoColoc(user, colocation) && !service.getUsersVoted().contains(user)) {
            if (vote == 1) service.setForVote(service.getForVote() + 1);
            else service.setAgainstVote(service.getAgainstVote() + 1);
            service.addUserVoted(user);
            serviceDao.edit(service);

            int votes_number = service.getUsersVoted().size();
            int members_number = service.getColocation().getMembers().size();
            if (votes_number == members_number) {
                if (!service.countingVote()) serviceDao.remove(service);
            }
            return true;
        }
        return false;
    }

    public static boolean valid(String email, Long achievedServiceID, boolean validated) {
        User validator = getUser(email);
        AchievedService achievedService = achievedServiceDao.find(achievedServiceID);
        User from = achievedService.getFrom();
        Colocation colocation = achievedService.getService().getColocation();

        if (validator == null || achievedService == null || from == null) {
            return false;
        }

        Point point = getUserScoreIntoColocation(colocation,from);

        if (!validator.equals(from) && achievedService.getTo().contains(validator)) {
            if (validated) {
                int cost = achievedService.getService().getCost();
                achievedService.setValid(true);
                point.addPoint(cost);
                achievedServiceDao.edit(achievedService);
                userDao.edit(from);
                pointDao.edit(point);
                for (User user : achievedService.getTo()) {
                    Point _point = getUserScoreIntoColocation(colocation,user);
                    _point.decreaseScore(cost);
                }
                serviceDao.remove(achievedService.getService());
            } else {
                achievedService.setValid(false);
                achievedServiceDao.edit(achievedService);
            }

            return true;
        }

        return false;
    }

}
