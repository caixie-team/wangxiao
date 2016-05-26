package io.wangxiao.core;

import io.wangxiao.core.model.BaseIncrementIdModel;
import io.wangxiao.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * service 层的基类，所有service类必须继承自此类，该类不能直接使用。
 * <p>
 * 将service层一些通用的操作给抽离出来，封装到此类中，其他service类必须继承此类，子类可以直接使用此类中的方法。<br/>
 * 该类使用泛型实现了实体和dao层封装，子类继承此方法时必须指明对应的Model和Dao具体实现类，在{@link #setBaseDao(BaseDao)}方法中使用spring注解方式实现。<br/>
 * 子类在需要使用dao对象的地方，直接调用baseDao.<i>method()</i>，该类当前只支持自动装配一个dao实例，如果需要多个，在自己的service类中以spring注解方式自行配置。
 * </p>
 *
 * @param <T> 主要操作的实体类型
 * @param <K> 主要操作的Dao类型
 */
public abstract class BaseService<T extends BaseIncrementIdModel, K extends BaseDao<T>> {
//    protected Logger logger = Logger.getLogger(getClass());

    private Class<T> entityClass;
    private final List<Condition> emptyConditions = new ArrayList<Condition>();

    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        if (this.entityClass == null) {
            Type type = getClass().getGenericSuperclass();
            Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
            this.entityClass = (Class<T>) trueType;
        }
        return this.entityClass;
    }

    /**
     * dao原型属性
     */
    protected K baseDao;

    /**
     * 根据K泛型自动装载BaseDao
     *
     * @param baseDao
     */
    @Autowired
    public final void setBaseDao(K baseDao) {
        this.baseDao = baseDao;
    }

    public void deleteById(Object id) {
        baseDao.deleteById(id);
    }

    public void delete(T entity) {
        baseDao.delete(entity);
    }

    public T findById(Object id) {
        // -1 代表 NULL
        if (id == null || id.toString().equals("-1")) return null;
        if (id.getClass() != Long.class) {
            return baseDao.findById(Long.valueOf(id.toString()));
        }
        return baseDao.findById((Long) id);
    }

    /**
     * 根据分页和条件进行查询。如果不需要分页，把pagination设为null。
     * 主要是为了方便一个条件的查询，不用在调用时自己封装成List
     *
     * @param pagination
     * @param condition
     * @return
     */
    public List<T> find(Pagination pagination, Condition condition) {
        List<Condition> conditions = null;

        if (condition != null) {
            conditions = new ArrayList<Condition>();
            conditions.add(condition);
        }
        return find(pagination, conditions);
    }

    /**
     * 根据分页和条件进行查询。如果不需要分页，把pagination设为null。
     *
     * @param pagination
     * @param conditions
     * @return
     */
    public List<T> find(Pagination pagination, ConditionMap conditions) {
        List<Condition> conditionList = null;
        if (conditions != null) {
            conditionList = conditions.getItems();
        }
        return this.find(pagination, conditionList);
    }

//    public List<T> find(ListPageQuery query) {
//        return this.find(query.getPagination(), query.getConditions().getItems());
//    }

    /**
     * ListPageQuery
     * 查找所有的记录
     *
     * @return
     */
    public List<T> findAll() {
        return this.find(null, emptyConditions);
    }


    /**
     * 根据分页和条件进行查询。如果不需要分页，把pagination设为null。
     *
     * @param pagination
     * @param conditions
     * @return
     */
    public List<T> find(Pagination pagination, List<Condition> conditions) {
        return baseDao.find(pagination, conditions);
    }


    public List<T> find(Pagination pagination) {
        return this.find(pagination, emptyConditions);

    }


    public void save(T entity) {
        if (entity.getId() == null) {
            baseDao.add(entity);
        } else {
            baseDao.update(entity);
        }
    }

    /**
     * 批量保存所有实体
     *
     * @param entities
     */
    public void saveBatch(List<T> entities) {
        if (!CollectionUtil.isEmpty(entities)) {
            for (T entity : entities) {
                save(entity);
            }
        }
    }

    /**
     * 根据id的集合删除一批记录
     *
     * @param ids
     */
    public void deleteByIds(List<Long> ids) {
        if (!CollectionUtil.isEmpty(ids)) {
            baseDao.deleteByIds(ids);
        }
    }

    /**
     * 把ids对应的实体中的属性值更新成entity中所有非null的属性值
     *
     * @param entity
     * @param ids
     */
    public void updateBatch(T entity, List<Long> ids) {
        if (!CollectionUtil.isEmpty(ids)) {
            baseDao.updateBatch(entity, ids);
        }
    }

    /**
     * 更新list中所有的实体。
     *
     * @param entities
     */
    public void updateBatch(List<T> entities) {
        if (!CollectionUtil.isEmpty(entities)) {
            for (T entity : entities) {
                save(entity);
            }
        }
    }


    /**
     * 功能:修改相关实体的递增值，如Question的查看次数等
     *
     * @param entity
     */
    public void updateIncrement(T entity) {
        baseDao.updateIncrement(entity);
    }

    public void print(String content) {
        System.out.println(this.getClass().getName() + " - Print:[ " + content + " ]");
    }


}
