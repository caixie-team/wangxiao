package com.atdld.os.core.util.web;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * @ClassName com.atdld.os.core.util.web.VelocityHtmlUtil
 * @description 生成静态html
 * @author :
 * @Create Date : 2014-4-13 下午10:06:45
 */
public class VelocityHtmlUtil {
    private VelocityContext context = null;
    private Template template = null;
    private String basepath = null;

    public VelocityHtmlUtil() {
    }

    public VelocityHtmlUtil(String basepath, String vmFile) {
        this.basepath = basepath;
        this.loadvm(vmFile);
    }

    /**
     * 加载vm文件
     * 
     * @param vmFile
     *            vm路径
     * @throws Exception
     */
    public void loadvm(String vmFile) {
        Properties p = new Properties();
        p.setProperty(Velocity.RESOURCE_LOADER, "file");
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, basepath);
        p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        VelocityEngine engine = new VelocityEngine();// 引擎初始化
        engine.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
        engine.init(p);// 装入初始化信息
        template = engine.getTemplate(vmFile);// 把模板读到模板引擎去
        context = new VelocityContext();
    }

    /**
     * 设置值
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        context.put(key, value);
    }

    /**
     * 获取string内容
     * 
     * @return
     */
    public String getText() {
        StringWriter sw = new StringWriter();
        try {
            template.merge(context, sw);
        } catch (Exception e) {
        }
        return sw.toString();
    }

    /**
     * 获取string内容
     * 
     * @param param
     *            设置的参数
     * @return
     */
    public String getText(Map<String, Object> param) {
        setValue(param);
        StringWriter sw = new StringWriter();
        try {
            template.merge(context, sw);
        } catch (Exception e) {
        }
        return sw.toString();
    }

    /**
     * 存取到文件
     * 
     * @param fileName
     *            文件路径
     */
    public void toFile(String fileName) {
        try {
            StringWriter sw = new StringWriter();
            template.merge(context, sw);
            PrintWriter filewriter = new PrintWriter(new FileOutputStream(fileName), true);
            filewriter.println(sw.toString());
            filewriter.close();
        } catch (Exception e) {
        }

    }

    public void setValue(Map<String, Object> param) {
        for (Entry<String, Object> entry : param.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
    }

    public void createFile(Map<String, Object> param, String himlFile) {
        this.setValue(param);
        this.toFile(himlFile);
    }

    public static void main(String[] args) {
        VelocityHtmlUtil htmlUtil = new VelocityHtmlUtil("D:\\workspaces\\demo_admin\\src\\main\\webapp\\back\\images\\website", "a.vm");
        htmlUtil.put("name", "u009");
        System.out.println(htmlUtil.getText());
    }
}
