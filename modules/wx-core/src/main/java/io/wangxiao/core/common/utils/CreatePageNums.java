package io.wangxiao.core.common.utils;


import io.wangxiao.core.Pagination;

/**
 * Created by yangjian on 14-3-20.
 */
public class CreatePageNums {
    //临界点7页
    private static final int PAGENUMSCOUNT = 7;
    private static final int PAGEFG = 4;
    private static final int PAGEFW = 2;

    public static String getnumview(Pagination pagination, int currentpage) {
        StringBuffer sb = new StringBuffer();
        if (pagination.getTotalPages() <= PAGENUMSCOUNT) {
            if (pagination.getTotalPages() < 3) {
                if (pagination.getCurrentPage() == 1) {
                    for (int i = 1; i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                    sb.append("<a onclick=\"changePage(2);\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
                } else {
                    sb.append("<a onclick=\"changePage(1);\" style='width:60px' href=\"javascript:void(0)\">上一页</a>");
                    for (int i = 1; i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                }
            } else {

                if (pagination.getCurrentPage() == 1) {
                    for (int i = 1; i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() + 1) + ");\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
                } else if (pagination.getCurrentPage() == pagination.getTotalPages()) {
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() - 1) + ");\" style='width:60px' href=\"javascript:void(0)\">上一页</a>");
                    for (int i = 1; i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                } else {
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() - 1) + ");\" href=\"javascript:void(0)\">上一页</a>");
                    for (int i = 1; i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() + 1) + ");\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
                }
            }
        } else {
            if (currentpage <= 4) {
                if (currentpage == 1) {
                    for (int i = 1; i <= (PAGEFG + PAGEFW); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                    sb.append("...");
                    sb.append("<a onclick=\"changePage(" + pagination.getTotalPages() + ");\" href=\"javascript:void(0)\">" + pagination.getTotalPages() + "</a>");
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() + 1) + ");\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
                } else {
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() - 1) + ");\" style='width:60px' href=\"javascript:void(0)\">上一页</a>");
                    for (int i = 1; i <= (PAGEFG + PAGEFW); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                    sb.append("...");
                    sb.append("<a onclick=\"changePage(" + pagination.getTotalPages() + ");\" href=\"javascript:void(0)\">" + pagination.getTotalPages() + "</a>");
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() + 1) + ");\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
                }
            } else if (currentpage >= (pagination.getTotalPages() - PAGEFW)) {
                if (currentpage == pagination.getTotalPages()) {
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() - 1) + ");\" style='width:60px' href=\"javascript:void(0)\">上一页</a>");
                    sb.append("...");
                    for (int i = (pagination.getTotalPages() - PAGEFW - PAGEFW); i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                } else {
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() - 1) + ");\" style='width:60px' href=\"javascript:void(0)\">上一页</a>");
                    sb.append("...");
                    for (int i = (pagination.getTotalPages() - PAGEFW - PAGEFW); i <= pagination.getTotalPages(); i++) {
                        if (i == currentpage) {
                            sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        } else {
                            sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                        }
                    }
                    sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() + 1) + ");\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
                }
            } else {
                sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() - 1) + ");\" style='width:60px' href=\"javascript:void(0)\">上一页</a>");
                sb.append("<a onclick=\"changePage(" + 1 + ");\" href=\"javascript:void(0)\">1</a>");
                sb.append("...");
                for (int i = (pagination.getCurrentPage() - PAGEFW); i <= (pagination.getCurrentPage() + PAGEFW); i++) {
                    if (i == currentpage) {
                        sb.append("<a style=\" background:#5472A4\" onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                    } else {
                        sb.append("<a onclick=\"changePage(" + i + ");\" href=\"javascript:void(0)\">" + i + "</a>");
                    }
                }
                sb.append("...");
                sb.append("<a onclick=\"changePage(" + pagination.getTotalPages() + ");\" href=\"javascript:void(0)\">" + pagination.getTotalPages() + "</a>");
                sb.append("<a onclick=\"changePage(" + (pagination.getCurrentPage() + 1) + ");\" style='width:60px' href=\"javascript:void(0)\">下一页</a>");
            }
        }
        return sb.toString();
    }
}
