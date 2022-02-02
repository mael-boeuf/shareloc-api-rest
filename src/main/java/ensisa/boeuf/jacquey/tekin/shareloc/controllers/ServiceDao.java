package ensisa.boeuf.jacquey.tekin.shareloc.controllers;

import ensisa.boeuf.jacquey.tekin.shareloc.model.Colocation;
import ensisa.boeuf.jacquey.tekin.shareloc.model.Service;

public class ServiceDao extends Dao {

    public ServiceDao() {
        super();
    }

    public static boolean createService(String title, String description, int cost, String colocationName) {
        Colocation colocation = getColocation(colocationName);

        if (colocation != null) {
            Service service = new Service(title, description, cost, colocation);
            serviceDao.create(service);
            return true;
        }
        return false;
    }

}
