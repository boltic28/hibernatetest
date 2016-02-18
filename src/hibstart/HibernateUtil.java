package hibstart;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Сергей on 17.02.2016.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();

        sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
