package org.txazo.framework.freemarker.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * Freemarker模板工具类
 * 
 * @author txazo
 * 
 */
public class FreeMarkerUtil {

	private static Configuration cfg;

	private FreeMarkerUtil() {
	}

	private static Configuration getConfiguration() {
		if (cfg == null) {
			cfg = new Configuration();
			try {
				cfg.setDirectoryForTemplateLoading(new File("F:/GraduationProject/txazo/txazodevelop/web/freemarker"));
				cfg.setObjectWrapper(new DefaultObjectWrapper());
				cfg.setDefaultEncoding("UTF-8");
				cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
				cfg.setIncompatibleImprovements(new Version(2, 3, 20));
				cfg.setLocale(java.util.Locale.SIMPLIFIED_CHINESE);
				cfg.setNumberFormat("0.####");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cfg;
	}

	public static String getHtml(Map<String, Object> valuemap, String file) {
		Configuration cfg = getConfiguration();
		try {
			Template template = cfg.getTemplate(file);
			Writer out = new StringWriter();
			template.process(valuemap, out);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
