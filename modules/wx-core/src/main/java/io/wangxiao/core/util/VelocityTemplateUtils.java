package io.wangxiao.core.util;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

/**
 * Velocity模板获取帮助类
 * 
 * @author ZhangYunHe
 * 
 */
public class VelocityTemplateUtils {

	private static VelocityEngine ve = null;

	static {
		ve = new VelocityEngine();
		// 可选值："class"--从classpath中读取，"file"--从文件系统中读取
		ve.setProperty("resource.loader", "class");
		// 如果从文件系统中读取模板，那么属性值为org.apache.velocity.runtime.resource.loader.FileResourceLoader
		ve.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Properties prop = new Properties();
		prop.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		prop.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");

		ve.init(prop);
	}

	/**
	 * 根据模板名称从classpath中获取指定模板文件
	 * 
	 * @param name
	 *            模板名称。如果模板放在classpath根目录直接写文件名e.g
	 *            VelocityTemplateUtils.getTemplate(example.vm);<br/>
	 *            如果模板文件放在classpath中的其他目录VelocityTemplateUtils.getTemplate(
	 *            example/example.vm);<br/>
	 * @return
	 */
	public static Template getTemplate(String name) {
		return ve.getTemplate(name);
	}

}
