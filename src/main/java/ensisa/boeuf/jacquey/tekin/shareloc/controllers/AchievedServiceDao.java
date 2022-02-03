package ensisa.boeuf.jacquey.tekin.shareloc.controllers;

import ensisa.boeuf.jacquey.tekin.shareloc.model.AchievedService;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Image;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Service;
import ensisa.boeuf.jacquey.tekin.shareloc.model.User;
import io.swagger.annotations.Api;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Api(value = "/achievedService")
public class AchievedServiceDao extends Dao {

    public AchievedServiceDao() {
        super();
    }

    public static boolean newAchievedService(String email, Long serviceID, String date, String picture, List<String> to_emails) {

        Service service = serviceDao.find(serviceID);
        User from = getUser(email);

        if (from == null || service == null || date == null || serviceIsAlreadyAchieved(serviceID)) {
            return false;
        }

        ArrayList<User> to = new ArrayList<>();
        for (String user_email : to_emails) {
            User to_user = getUser(user_email);
            if (to_user != null) to.add(to_user);
        }

        Image image = downloadImg(picture);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        Date achieved_date = null;
        try {
            achieved_date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp _date = new java.sql.Timestamp(achieved_date.getTime());

        AchievedService achievedService = new AchievedService(to, from, _date, image, false, service);
        achievedServiceDao.create(achievedService);

        return true;
    }

}
