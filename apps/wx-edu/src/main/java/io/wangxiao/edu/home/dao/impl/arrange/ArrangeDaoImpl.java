package io.wangxiao.edu.home.dao.impl.arrange;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.arrange.ArrangeDao;
import io.wangxiao.edu.home.entity.arrange.Arrange;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("arrangeDao")
public class ArrangeDaoImpl extends GenericDaoImpl implements ArrangeDao {
    public Long addArrange(Arrange arrange) {
        return this.insert("ArrangeMapper.createArrange", arrange);
    }

    public void deleteArrangeById(Long id) {
        this.delete("ArrangeMapper.deleteArrangeById", id);
    }

    public java.lang.Long updateArrange(Arrange arrange) {
        return this.update("ArrangeMapper.updateArrange", arrange);
    }

    public Arrange getArrangeById(Long id) {
        return this.selectOne("ArrangeMapper.getArrangeById", id);
    }

    public List<Arrange> getArrangeList(Arrange arrange, PageEntity page) {
        return this.queryForListPage("ArrangeMapper.getArrangeList", arrange, page);
    }

    /**
     * 我的部门任务
     */
    public List<Arrange> getGroupArrangeList(Arrange arrange, PageEntity page) {
        return this.queryForListPage("ArrangeMapper.getGroupArrangeList", arrange, page);
    }

    /**
     * 任务列表详情
     */
    public List<Arrange> arrangeDetailsById(Arrange arrange, PageEntity page) {
        return this.queryForListPage("ArrangeMapper.arrangeDetailsById", arrange, page);
    }

    /**
     * 更改任务状态
     */
    public void updateArrangeStatus(Arrange arrange) {
        this.update("ArrangeMapper.updateArrangeStatus", arrange);
    }

    /**
     * 前台我的任务
     */
    public List<Arrange> myArrangeForWeb(Arrange arrange, PageEntity page) {
        return this.queryForListPage("ArrangeMapper.myArrangeForWeb", arrange, page);
    }

    /**
     * 前台我的部门任务
     */
    public List<Arrange> myGroupArrangeFroweb(Arrange arrange, PageEntity page) {
        return this.queryForListPage("ArrangeMapper.myGroupArrangeFroweb", arrange, page);
    }

    /**
     * 获取小于今日的考试任务
     */
    public List<Arrange> getArrangeListByTime(Date date) {
        return this.selectList("ArrangeMapper.getArrangeListByTime", date);
    }

    /**
     * 修改考试任务结束时间小于当前时间的安排状态为已结束
     *
     * @param date
     */
    public void updateArrangeListByTime(Date date) {
        this.update("ArrangeMapper.updateArrangeListByTime", date);
    }

}
