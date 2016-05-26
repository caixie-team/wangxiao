package io.wangxiao.core.model;


import io.wangxiao.core.annotation.Property;
import io.wangxiao.core.codebuilder.TableSchemaSqlBuilder;

import javax.persistence.Table;
import java.io.Serializable;

//@OrderBy("id desc")
public class BaseIncrementIdModel implements Serializable {

    /**
     * 获取 entity 的表名
     * 需要 entity 中的属性定义 @Table(name)
     *
     * @return tableName String
     */
    public String tableName() {
        Table table = this.getClass().getAnnotation(Table.class);
        if (table != null) {
            return table.name();
        } else
            throw new RuntimeException("undefine Entity @Table, need Tablename(@Table)");
    }

    private Long id;

    @Property(chineseName = "主键ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseIncrementIdModel other = (BaseIncrementIdModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * 生成当前实体的SQL创建语句
     *
     * @param prefix 表前缀
     * @return
     */
    public String generateTableSql(String... prefix) {
        TableSchemaSqlBuilder table = new TableSchemaSqlBuilder();
        if (prefix.length > 0) {
            return table.setPrefix(prefix[0]).buildByClass(getClass());
        } else
            return table.buildByClass(getClass());

    }
}
