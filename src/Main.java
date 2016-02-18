
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {   // http://javastudy.ru/hibernate/hibernate-hql-examples/
        final Session session = getSession();
        try {
//        read();
        delete();
        } finally {
            session.close();
        }
    }

    public static void read() {
        Query query = getSession().createQuery("from UserEntity");
        List list = query.list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void delete() {

    }

    public static void update(int id, String name, int age, boolean admin, Date date) {
        Query query = getSession().createQuery("update UserEntity set name = :nameParam, age = :ageParam" +
                ", createdDate = :createdDateParam, admin = :admin "+
                " where id = :idCode");

        query.setParameter("idCode", id);
        query.setParameter("nameParam", name);
        query.setParameter("ageParam", age );
        query.setParameter("admin", admin);
        query.setParameter("createdDateParam", date);

        int result = query.executeUpdate();
    }

    public static void create() {

    }
}
