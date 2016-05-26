package com.atdld.os.core.util.lucene;

import com.atdld.os.core.util.StringUtils;

/**
 * @ClassName QueryWordFilter
 * @package com.atdld.os.core.util.lucene
 * @description
 * @author
 * @Create Date: 2013-11-30 下午1:55:30
 * 
 */
public class QueryWordFilter {
    private QueryWordFilter() {
    }

    private static final QueryWordFilter queryWordFilter = new QueryWordFilter();
    // 防止js/html注入
    private static String regex_unvalid_char = "[<|>|\"]";
    // 过滤掉lucene不支持符合
    private final static String regex_not_supported = "[\\+|\\-|&|区|!|(|)|{|}|\\[|\\]|\\^|\"|~|*|\\?|:|\\\\]";

    public static QueryWordFilter getInstance() {
        return queryWordFilter;
    }

    /**
     * 
     * @author
     * @param keyword
     * @return 过滤特殊字符,后续需增强过滤
     */
    public static String filterQueryWord(String keyword) {
        try {
            if (StringUtils.isNotEmpty(keyword)) {
                keyword = keyword.trim();
                keyword = keyword.replaceAll(regex_unvalid_char, ",");
                keyword = keyword.replaceAll(regex_not_supported, "");
                return keyword;
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}
