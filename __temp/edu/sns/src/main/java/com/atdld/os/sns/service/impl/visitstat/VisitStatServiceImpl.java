package com.atdld.os.sns.service.impl.visitstat;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.blog.BlogReplyService;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.discuss.DisMemberService;
import com.atdld.os.sns.service.suggest.SugSuggestService;
import com.atdld.os.sns.service.visitstat.VisitStatService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.visitstat.VisitStatServiceImpl
 * @description 计数队列实现，定时器可设置1-5分钟执行
 * @Create Date : 2014-1-24 下午11:59:06
 */
@SuppressWarnings("unchecked")
@Service("visitStatService")
public class VisitStatServiceImpl implements VisitStatService {

    private static MemCache memCache = MemCache.getInstance();
    private static String queueskey = "sns_queues_";
    @Autowired
    private WeiBoService weiBoService;
    @Autowired
    private BlogReplyService blogReplyService;
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private DisArticleService disArticleService;
    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private DisMemberService disMemberService;
    @Autowired
    private SugSuggestService sugSuggestService;

   /* // 上次启动的uuid
    public static String olduuid = PropertiesReader.getValue("uuid", "uuid");
    // 本次的启动uuid
    public static String uuid = "";*/

    private final static Logger logger = Logger.getLogger(VisitStatServiceImpl.class);

    /**
     * 支持统计的对象类型
     * <p/>
     * 1.微博评论数
     * <p/>
     * 2.博客查看 3.博客回复
     * <p/>
     * 4.建议查看 5.建议回复
     * <p/>
     * 6.小组文章查看7.小组文章回复 8.小组文章数9.小组成员数
     */
    public final static byte[] TYPES = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09};
    // 计数队列
    @SuppressWarnings("serial")
    private final static ConcurrentHashMap<Byte, ConcurrentHashMap<Long, Integer>> queues = new ConcurrentHashMap<Byte, ConcurrentHashMap<Long, Integer>>() {
        {
            for (byte type : TYPES) {
                put(type, new ConcurrentHashMap<Long, Integer>());
            }
        }
    };

    // 每次项目重新启动时把memcache中的数据放到queues中
    static {
        /*uuid = UUID.randomUUID().toString();// 本次的启动uuid
        // 存到配置文件中下次使用
        PropertiesReader.setValue("uuid", "uuid", uuid);*/
        ConcurrentHashMap<Byte, ConcurrentHashMap<Long, Integer>> memqueues = (ConcurrentHashMap<Byte, ConcurrentHashMap<Long, Integer>>) memCache.get(queueskey );//+ olduuid注意用的是上次的oldid
        if (!ObjectUtils.isNull(memqueues)) {
            logger.info("set queues from mem:" + memqueues);
            queues.putAll(memqueues);
        }
    }

    /**
     * 记录访问统计
     *
     * @param type
     * @param obj_id
     */
    public void record(byte type, long obj_id) {
        ConcurrentHashMap<Long, Integer> queue = queues.get(type);
        if (queue != null) {
            Integer nCount = queue.get(obj_id);
            nCount = (nCount == null) ? 1 : nCount + 1;
            queue.put(obj_id, nCount.intValue());
            //memCache.set(queueskey + uuid, queues);
            memCache.set(queueskey , queues);
        }
    }

    /**
     * 执行数据入库操作run
     */
    public void run() {
        logger.info("queues run:" + queues);
        if (!ObjectUtils.isNull(queues)) {
            for (byte type : TYPES) {
                // 删除时返回的是本次的对象
                ConcurrentHashMap<Long, Integer> queue = queues.remove(type);
                queues.put(type, new ConcurrentHashMap<Long, Integer>());
                try {
                    if (!ObjectUtils.isNull(queue)) {
                        _flush(type, queue);// 刷新入库
                        //memCache.set(queueskey + uuid, queues);
                        memCache.set(queueskey , queues);
                    }
                } catch (Throwable t) {
                    logger.error("queue error:type" + type + "queue=" + queue, t);
                    // 此处发送异常报告
                } finally {
                }
            }
        }

    }

    /**
     * 写访问统计数据到数据库
     *
     * @param type
     * @param queue
     */
    private void _flush(byte type, ConcurrentHashMap<Long, Integer> queue) {
        if (queue.size() == 0) {
            return;
        }
        switch (type) {
            case 0x01: {
                for (Long id : queue.keySet()) {
                    try {
                        weiBoService.updateWeiBoCommentNumAddCount(id, queue.get(id)); // 1.微博评论数
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }
            }
            break;
            case 0x02: {
                for (Long id : queue.keySet()) {
                    try {
                        blogBlogService.updateBlogViewCount(id, queue.get(id)); // 2.博客查看
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }
            }
            break;
            case 0x03: {
                for (Long id : queue.keySet()) {
                    try {
                        blogReplyService.updateBlogReplyCount(id, queue.get(id)); // 3.博客回复
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }

            }
            break;
            case 0x04: {
                for (Long id : queue.keySet()) {
                    try {
                        sugSuggestService.updateSuggestViewCount(id, queue.get(id)); // 4.建议查看
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }

            }
            break;
            case 0x05: {
                for (Long id : queue.keySet()) {
                    try {
                        sugSuggestService.updateSugSuggestBySuggestIdCount(id, queue.get(id)); // 5.建议回复
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }

            }
            break;
            case 0x06: {
                for (Long id : queue.keySet()) {
                    try {
                        disArticleService.updateDisArticleViewCount(id, queue.get(id)); // 6.小组文章查看
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }
            }
            break;
            case 0x07: {
                for (Long id : queue.keySet()) {
                    try {
                        disArticleService.updateDisArticleReplyCount(id, queue.get(id)); // 7.小组文章回复
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }

            }
            break;
            case 0x08: {
                for (Long id : queue.keySet()) {
                    try {
                        disGroupService.updateArticleCount(id, queue.get(id)); // 8.小组文章数
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }

            }
            break;
            case 0x09: {
                for (Long id : queue.keySet()) {
                    try {
                        disMemberService.updateDisGroupMemberCount(id, queue.get(id)); // 9.小组成员数
                    } catch (Exception e) {
                        errorrecord(type, id, queue.get(id));
                    }
                }
            }
            break;
        }
    }

    /**
     * 数据库操作出错时，重新放回到内存中
     *
     * @param type
     * @param obj_id
     * @param count
     */
    public void errorrecord(byte type, long obj_id, int count) {
        ConcurrentHashMap<Long, Integer> queue = queues.get(type);
        if (queue != null) {
            Integer nCount = queue.get(obj_id);
            nCount = (nCount == null) ? count : nCount + count;
            queue.put(obj_id, nCount.intValue());
            //memCache.set(queueskey + uuid, queues);
            memCache.set(queueskey , queues);
        }
    }

}
