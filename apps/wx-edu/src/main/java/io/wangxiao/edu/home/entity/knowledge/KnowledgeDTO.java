package io.wangxiao.edu.home.entity.knowledge;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Description:知识体系
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KnowledgeDTO extends Knowledge implements Serializable {

    /**
     * 目录列表
     */
    private List<KnowledgeCatalog> knowledgeCatalogList;

}
