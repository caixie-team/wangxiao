package com.atdld.os.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3428610204086857703L;

    /** 总数据条数 */
    private int totalResultSize = 0;

    /** 总页数 */
    private int totalPageSize = 0;

    /** 每页条数 */
    private int pageSize = 10;

    /** 当前页码 */
    private int currentPage = 1;

    private int startRow = 0; // 当前页在数据库中的起始行

    public PageEntity() {

    }

    public PageEntity(int _totalRows, int _pageSize) {
        totalResultSize = _totalRows;
        pageSize = _pageSize;
        totalPageSize = totalResultSize / pageSize;
        int mod = totalResultSize % pageSize;
        if (mod > 0) {
            totalPageSize++;
        }
        currentPage = 1;
        startRow = 0;

    }

    public PageEntity(int currentPage, int _totalRows, int _pageSize) {
        int totalPages1 = _totalRows / _pageSize;
        int mod1 = _totalRows % _pageSize;
        totalResultSize = _totalRows;
        pageSize = _pageSize;
        if (mod1 > 0) {
            totalPages1++;
        }
        if (currentPage > totalPages1) {
            currentPage = currentPage - 1;
        }
        if (currentPage == 0) {
            this.setStart(1);
        } else {
            this.setStart(currentPage);
        }
        totalPageSize = totalResultSize / pageSize;
        int mod = totalResultSize % pageSize;
        if (mod > 0) {
            totalPageSize++;
        } else {
            if (currentPage == 0) {
                currentPage = 1;
            }
        }
    }

    /** 是否首页 */
    @SuppressWarnings("unused")
    private boolean first = false;
    /** 是否尾页 */
    @SuppressWarnings("unused")
    private boolean last = false;

    public boolean isFirst() {
        return getCurrentPage() <= 1;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return getCurrentPage() >= getTotalPageSize();
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    /**
     * 获得页码 List<Integer>
     * 
     * @return
     */
    public List<Integer> getPageNums() {
        int maxDispalyCount = 7;// 最多显示7个页码
        int beforeCount = 3;// 当前页码前面显示数字
        List<Integer> returnList = new ArrayList<Integer>();
        if(getTotalPageSize()<=maxDispalyCount){
            for(int i=1;i<=getTotalPageSize();i++){
                returnList.add(i);
            }
            return returnList;
        }
        int maxNum_new = getCurrentPage() > beforeCount ? maxDispalyCount : maxDispalyCount - currentPage;
        int discnt = 1;
        for (int i = beforeCount; i > 0; i--) {
            if (currentPage > i) {
                returnList.add(currentPage - i);
                discnt++;
            }
        }
        returnList.add(currentPage);
        for (int i = 1; i <= maxNum_new; i++) {
            if (currentPage + i <= getTotalPageSize() && discnt < maxDispalyCount) {
                returnList.add(currentPage + i);
                discnt++;
            } else {
                break;
            }
        }
        return returnList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取起始记录数
     * 
     * @return
     */
    public int getStartRecord() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * 获得结束记录数
     * 
     * @return
     */
    public int getEndRecord() {
        return getStartRecord() + pageSize - 1;
    }

    public int getTotalResultSize() {
        return totalResultSize;
    }

    public void setTotalResultSize(int totalResultSize) {
        this.totalResultSize = totalResultSize;
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public void setStart(int currentPage) {
        this.currentPage = currentPage;
        startRow = (currentPage - 1) * pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public static PageEntity getPageEntity(int currentPage, int totalRows, int pageSize) {
        PageEntity pager = new PageEntity(totalRows, pageSize);
        if (currentPage == 0) {
            pager.setStart(1);
        } else {
            pager.setStart(currentPage);
        }
        return pager;
    }
}
