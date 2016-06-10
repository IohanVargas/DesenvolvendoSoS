package br.com.dao.config;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Diogenes
 */
public class HibernateCRUD implements Serializable {

    /**
     * Executa uma query baseada no HQL informado.
     *
     * @param hql
     * @param end
     * @param init
     * @return
     *
     */
    public static List executeQuery(String hql, int init, int end) {
        return executeQuery(hql, null, init, end);
    }

    /**
     * Executa uma query baseado no HQL e nos parametros passados.
     *
     * @param hql
     * @param params
     * @param init
     * @param end
     * @return
     *
     */
    public static List executeQuery(String hql, Map params, int init, int end) {
        Session session = null;
        try {

            session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(hql);
            if (params != null && params.size() > 0) {
                Iterator it = params.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    Object value = params.get(key);
                    query.setParameter(key, value);
                }
            }
            query.setFirstResult(init);
            query.setMaxResults(end);
            List retorno = query.list();
            return retorno;

        } catch (HibernateException he) {
            return null;
        }
    }

    /**
     * Executa uma query baseada no HQL informado.
     *
     * @param hql
     * @return
     *
     */
    public static Object executeQueryOneResult(String hql) {
        return executeQueryOneResult(hql, null);
    }

    /**
     * Executa uma query baseado no HQL e nos parametros passados.
     *
     * @param hql
     * @param params
     * @return
     *
     */
    public static Object executeQueryOneResult(String hql, Map params) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(hql);
            if (params != null && params.size() > 0) {
                Iterator it = params.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    Object value = params.get(key);
                    query.setParameter(key, value);
                }
            }
            Object retorno = query.uniqueResult();
            return retorno;
        } catch (HibernateException he) {
            return null;
        }
    }

    /**
     * Executa uma query em SQL nativo
     *
     * @param sql
     * @return
     *
     */
    public static List executeSQLQuery(String sql) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            Query query = session.createSQLQuery(sql);
            List retorno = query.list();
            return retorno;
        } catch (HibernateException he) {
            return null;
        }
    }

    /**
     * Executa um UPDATE/INSERT/DELETE em SQL nativo
     *
     * @param sql
     *
     */
    public static void executeSQLUpdate(String sql) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
        } catch (HibernateException he) {

        }
    }

    /**
     * Metodo responsavel por realizar uma insercao ou alteracao.
     *
     * @param obj
     *
     */
    public static void save(Object obj) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(obj);
            transaction.commit();
            session.flush();
        } catch (HibernateException he) {

        }
    }

    /**
     * Metodo responsavel por realizar uma insercao com um objeto que ja possui
     * ID..
     *
     * @param obj
     *
     */
    public static void saveWithId(Object obj) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.merge(obj);
        } catch (HibernateException he) {

        } finally {
        }
    }

    /**
     * Atualiza as informacoes do objeto ja carregado na session
     *
     * @param obj
     *
     */
    public static void updateInSession(Object obj) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.merge(obj);

            Transaction transaction = session.beginTransaction();
            transaction.commit();
        } catch (HibernateException he) {

        }
    }

    /**
     * Metodo responsavel por retornar um objeto generico pelo seu
     * ID(PrimaryKey)
     *
     * @param klass
     * @param id
     * @return
     *
     */
    public static Object getById(Class klass, Serializable id) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            Object obj = session.get(klass, id);
            return obj;
        } catch (HibernateException he) {
            return null;
        } 
    }

    /**
     * Metodo responsavel por excluir uma entidade.
     *
     * @param obj
     *
     */
    public static void delete(Object obj) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.delete(obj);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
        } catch (HibernateException he) {

        }
    }

    /**
     * Retorna todos os registros de uma determinada entidade.
     *
     * @param entityName
     * @return
     *
     */
    public static List getAll(String entityName) {
        return getAll(entityName, "id");
    }

    /**
     * Remove o objeto da sessao do hibernate
     *
     * @param obj
     *
     */
    public static void unloadObject(Object obj) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.evict(obj);
        } catch (HibernateException he) {

        }
    }

    public static void refreshObject(Object obj) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.refresh(obj);
        } catch (HibernateException he) {

        }
    }

    /**
     * Limpa a sessao do Hibernate
     *
     *
     */
    public static void clearSession() {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.clear();
        } catch (HibernateException he) {

        }

    }

    /**
     * Realiza um flush na sessao do Hibernate.
     *
     *
     */
    public static void flushSession() {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.flush();
        } catch (HibernateException he) {

        }

    }

    /**
     * Realiza um rebuild na sessao do Hibernate.
     *
     *
     */
    public static void rebuildSession() {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            session.getTransaction().rollback();
            session.close();
            HibernateSessionFactory.currentSession().beginTransaction();
        } catch (HibernateException he) {

        }

    }

    public static List getAll(String entityName, String sortField) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            String hql = "from " + entityName + " x order by x." + sortField;
            Query query = session.createQuery(hql);
            List retorno = query.list();
            Transaction transaction = session.beginTransaction();
            transaction.commit();

            return retorno;
        } catch (HibernateException he) {
            return null;
        }
    }

    public static List getAllAtivo(String entityName) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            String hql = "from " + entityName + " x where x.active = 1 order by x.name";
            Query query = session.createQuery(hql);
            List retorno = query.list();
            Transaction transaction = session.beginTransaction();
            transaction.commit();

            return retorno;
        } catch (HibernateException he) {
            return null;
        }
    }

    public static List getAllInative(String entityName) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            String hql = "from " + entityName + " x where x.active = 0 order by x.name";
            Query query = session.createQuery(hql);
            List retorno = query.list();
            Transaction transaction = session.beginTransaction();
            transaction.commit();

            return retorno;
        } catch (HibernateException he) {
            return null;
        }
    }

    public static List getAllnoFix(String entityName) {
        Session session = null;
        try {
            session = HibernateSessionFactory.currentSession();
            String hql = "from " + entityName + " x where x.fixedObject = 0 and x.active = 1 order by x.name";
            Query query = session.createQuery(hql);
            List retorno = query.list();
            Transaction transaction = session.beginTransaction();
            transaction.commit();

            return retorno;
        } catch (HibernateException he) {
            return null;
        }
    }

}
