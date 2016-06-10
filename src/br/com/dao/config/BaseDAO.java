package br.com.dao.config;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsavel por realizar operacoes genericas dos DAOs.
 *
 * @author Diogenes
 * @param <Id>
 * @param <Entity>
 */
public abstract class BaseDAO<Id extends Serializable, Entity> implements Serializable {

    private Class<Entity> entity = null;
    protected Map<String, Object> paramsToQuery = new HashMap<>();

    private Class<Entity> getEntity() {
        if (entity == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            entity = (Class<Entity>) type.getActualTypeArguments()[1];
        }
        return entity;
    }

    /**
     * Retorna uma entidade baseada em sua chave primaria.
     *
     * @param id
     * @return
     */
    public Entity getByPrimaryKey(Id id) {
        return (Entity) HibernateCRUD.getById(getEntity(), id);
    }

    /**
     * Insere ou Atualiza no mecanismo de persistencia uma entidade generica
     *
     * @param entity
     *
     */
    public void save(Entity entity) {
        HibernateCRUD.save(entity);
    }

    /**
     * Insere no mecanismo de persistencia uma entidade generica que ja possui
     * um ID
     *
     * @param entity
     *
     */
    public void saveWithId(Entity entity) {
        HibernateCRUD.saveWithId(entity);
    }

    /**
     * Remove do mecanismo de persistencia uma entidade generica.
     *
     * @param entity
     *
     */
    public void delete(Entity entity) {
        HibernateCRUD.delete(entity);
    }

    /**
     * Executa uma query generica sem parametros.
     *
     * @param hql
     * @param init
     * @param end
     * @return
     *
     */
    public List executeQuery(String hql, int init, int end) {
        return HibernateCRUD.executeQuery(hql, init, end);
    }

    /**
     * Executa uma query generica com parametros.
     *
     * @param hql
     * @param params
     * @param init
     * @param end
     * @return
     *
     */
    public List executeQuery(String hql, Map params, int init, int end) {
        List retorno = HibernateCRUD.executeQuery(hql, params, init, end);
        params.clear();
        return retorno;
    }

    /**
     * Executa uma query generica sem parametros.
     *
     * @param hql
     * @return
     *
     */
    public Object executeQueryOneResult(String hql) {
        return HibernateCRUD.executeQueryOneResult(hql);
    }

    /**
     * Executa uma query genÃ©rica com parÃ¢metros.
     *
     * @param hql
     * @param params
     * @return
     *
     */
    public Object executeQueryOneResult(String hql, Map params) {
        Object retorno = HibernateCRUD.executeQueryOneResult(hql, params);
        params.clear();
        return retorno;
    }

    /**
     * Executa uma query em SQL nativo.
     *
     * @param sql
     * @return
     *
     */
    public static List executeSQLQuery(String sql) {
        return HibernateCRUD.executeSQLQuery(sql);
    }

    /**
     * Executa um UPDATE/INSERT/DELETE em SQL nativo
     *
     * @param sql
     *
     */
    public void executeSQLUpdate(String sql) {
        HibernateCRUD.executeSQLUpdate(sql);
    }

    /**
     * Retorna todas as ocorrencias de uma determinada entidade
     *
     * @return
     *
     */
    public List getAll() {
        return HibernateCRUD.getAll(getEntity().getSimpleName());
    }

    public List getAllAtivo() {
        return HibernateCRUD.getAllAtivo(getEntity().getSimpleName());
    }

    public List getAllInative() {
        return HibernateCRUD.getAllInative(getEntity().getSimpleName());
    }

    public List getAllnoFix() {
        return HibernateCRUD.getAllnoFix(getEntity().getSimpleName());
    }

    public List getAll(String sortField) {
        return HibernateCRUD.getAll(getEntity().getSimpleName(), sortField);
    }

    /**
     * Atualiza um objeto ja vinculado a sessao do HBM
     *
     * @param entity
     *
     */
    public void updateObject(Entity entity) {
        HibernateCRUD.updateInSession(entity);
    }

    /**
     * Metodo responsavel por adicionar parametros a query HQL.
     *
     * @param paramName
     * @param paramValue
     */
    protected void addParamToQuery(String paramName, Object paramValue) {
        paramsToQuery.put(paramName, paramValue);
    }

    /**
     * Recarrega uma entidade com as informacoes do banco de dados.
     *
     * @param entity
     *
     */
    public void reloadEntity(Entity entity) {
        HibernateCRUD.refreshObject(entity);

    }

    /**
     * Limpa os objetos da sessao para nao ocorrer 'Automatic irty Checking'
     *
     *
     */
    public void limparSessao() {
        HibernateCRUD.clearSession();
    }

    /**
     * Remove o objeto da sessao de persistencia.
     *
     * @param ent
     *
     */
    public void unloadObject(Entity ent) {
        HibernateCRUD.unloadObject(ent);
    }

    /**
     * Realiza um flush na sessao de persistencia.
     *
     *
     */
    public void flushSession() {
        HibernateCRUD.flushSession();
    }

    /**
     * Realiza um rebuild na sessao de persistencia.
     *
     *
     */
    public void rebuildSession() {
        HibernateCRUD.rebuildSession();
    }

    public <T> T getGenericObject(Class<T> c, Integer i) {
        return (T) HibernateCRUD.getById(c, i);
    }
}
