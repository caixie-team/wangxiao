package io.wangxiao.core;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 记录每条SQL 的运行时长
 */
//只拦截select部分
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class LogSQLExcutionTimeInterceptor implements Interceptor {
    private static Logger log = LoggerFactory.getLogger(LogSQLExcutionTimeInterceptor.class);
    private static final Long DEFAULT_ZERO = (long) 0;

    private long minMilliseconds = 80; //最小执行时间，只有超过此时间的才打印

    public void setMinMilliseconds(long minMilliseconds) {
        this.minMilliseconds = minMilliseconds;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Long now = DEFAULT_ZERO;
        if (log.isDebugEnabled())
            now = System.currentTimeMillis();
        Object result = invocation.proceed();
        if (log.isDebugEnabled()) {
            Long period = System.currentTimeMillis() - now;
            if (period > minMilliseconds)
                log.debug("*** total time is " + period);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
