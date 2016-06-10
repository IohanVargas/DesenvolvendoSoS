package br.com.dao.config;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

/**
 *
 * @author Diogenes
 */
public class HibernateSessionFactory {

    /**
     * Holds a single instance of Session
     */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    /**
     * The single instance of hibernate configuration
     */
    private static final Configuration cfg = new Configuration();

    /**
     * The single instance of hibernate SessionFactory
     */
    private static org.hibernate.SessionFactory sessionFactory;

    public static Session newSession() throws HibernateException {
        Session session = null;
        if (sessionFactory == null) {
            try {
                cfg.configure();
                cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/bancoSoS?createDatabaseIfNotExist=true");
                cfg.setProperty("hibernate.connection.username", "root");
                cfg.setProperty("hibernate.connection.password", "root");
                sessionFactory = cfg.buildSessionFactory();
            } catch (HibernateException exception) {
                System.err.println("%%%% Error Creating SessionFactory %%%%" + exception);
            }
        }
        session = sessionFactory.openSession();
        threadLocal.set(session);

        return session;
    }

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the
     * <code>SessionFactory</code> if needed.
     *
     * @return Session
     * @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session != null && !session.isOpen()) {
            session = null;
        }
        if (session == null) {
            if (sessionFactory == null) {
                try {
                    cfg.configure();
                    cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/bancoSoS?createDatabaseIfNotExist=true");
                    cfg.setProperty("hibernate.connection.username", "root");
                    cfg.setProperty("hibernate.connection.password", "root");
                    sessionFactory = cfg.buildSessionFactory();
                } catch (HibernateException exception) {
                    System.err.println("%%%% Error Creating SessionFactory %%%%" + exception);
                }
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }

        return session;
    }

    /**
     * Close the single hibernate session instance.
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * Default constructor.
     */
    private HibernateSessionFactory() {
    }
}
