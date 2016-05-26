package io.wangxiao.core.model;
/**
 * 
 *
 */
public interface Hierarchy {

	Long getId() ;
	void setId(Long id);
	Long getParentId() ;
	void setParentId(Long parentId) ;
	String getNodeName() ;
	void setNodeName(String nodeName) ;
	String getFullCode();
	void setFullCode(String fullCode);
	
}
