package io.wangxiao.auth.web;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
/**
 * Created by bison on 5/26/16.
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/layouts/default.jsp");
        builder.addExcludedPath("/static/*");
        builder.addExcludedPath("/api/*");
    }
}
