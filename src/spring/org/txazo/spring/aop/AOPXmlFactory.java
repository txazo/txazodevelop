package org.txazo.spring.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AOPXmlFactory {

	private static Map<String, Bean> beanMap = new HashMap<String, Bean>();

	public AOPXmlFactory(String config) {
		try {
			loadConfig(config);

			initIOC();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String id) {
		Bean bean = beanMap.get(id);
		return bean == null ? null : bean.getInstance();
	}

	private void loadConfig(String config) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(this.getClass().getResourceAsStream(config));
		Element root = doc.getRootElement();
		Element element = null;
		String id = null;
		String className = null;
		Attribute idAttribute = null;
		Attribute classAttribute = null;
		for (Iterator<?> iterator = root.elementIterator("bean"); iterator.hasNext();) {
			element = (Element) iterator.next();
			idAttribute = element.attribute("id");
			classAttribute = element.attribute("class");
			if (idAttribute == null || StringUtils.isBlank((id = idAttribute.getStringValue()))) {
				throw new IllegalArgumentException("bean's attribute id cannot be null");
			}
			if (classAttribute == null || StringUtils.isBlank((className = classAttribute.getStringValue()))) {
				throw new IllegalArgumentException("bean's attribute class cannot be null");
			}

			Bean bean = new Bean();
			bean.setId(id);
			bean.setClassName(className);
			bean.setProperties(getProperty(element));

			beanMap.put(id, bean);
		}
	}

	public void initIOC() throws Exception {
		String id = null;
		Class<?> instanceClass = null;
		Object object = null;
		Bean bean = null;
		for (Iterator<String> keys = beanMap.keySet().iterator(); keys.hasNext();) {
			id = keys.next();
			bean = beanMap.get(id);
			instanceClass = Class.forName(bean.getClassName());
			object = instanceClass.newInstance();
			bean.setInstanceClass(instanceClass);
			bean.setInstance(object);
		}

		int type = 0;
		String name = null;
		String value = null;
		String methodName = null;
		Method method = null;
		Object instance = null;
		List<Property> properties = null;
		for (Iterator<String> keys = beanMap.keySet().iterator(); keys.hasNext();) {
			id = keys.next();
			bean = beanMap.get(id);
			properties = bean.getProperties();
			if (properties != null && properties.size() > 0) {
				instance = bean.getInstance();
				instanceClass = instance.getClass();
				for (Property property : properties) {
					type = property.getType();
					name = property.getName();
					value = property.getValue();
					methodName = getSetName(name);
					if (type == 0) {
						method = instanceClass.getDeclaredMethod(methodName, new Class[] { String.class });
						method.invoke(instance, value);
					} else {
						bean = beanMap.get(value);
						method = instanceClass.getDeclaredMethod(methodName, new Class[] { bean.getInstanceClass().getInterfaces()[0] });
						method.invoke(instance, bean.getInstance());
					}
				}
			}
		}
	}

	private String getSetName(String methodName) {
		return "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1, methodName.length());
	}

	private List<Property> getProperty(Element root) {
		if (root == null) {
			return null;
		}
		boolean valueFlag = false;
		String name = null;
		String value = null;
		String ref = null;
		Attribute nameAttribute = null;
		Attribute valueAttribute = null;
		Attribute refAttribute = null;
		Element element = null;
		List<Property> listProperty = new ArrayList<Property>();
		for (Iterator<?> iterator = root.elementIterator("property"); iterator.hasNext(); valueFlag = false) {
			element = (Element) iterator.next();
			nameAttribute = element.attribute("name");
			valueAttribute = element.attribute("value");
			refAttribute = element.attribute("ref");
			if (nameAttribute == null || StringUtils.isBlank((name = nameAttribute.getStringValue()))) {
				throw new IllegalArgumentException("property's attribute name cannot be null");
			}
			if (valueAttribute != null) {
				valueFlag = true;
				if (StringUtils.isBlank((value = valueAttribute.getStringValue()))) {
					throw new IllegalArgumentException("property's attribute value cannot be null");
				}
			}
			if (refAttribute != null) {
				if (valueFlag) {
					throw new IllegalArgumentException("property cannot has attribute value and ref");
				} else {
					if (StringUtils.isBlank((ref = refAttribute.getStringValue()))) {
						throw new IllegalArgumentException("property's attribute ref cannot be null");
					}
				}
			} else {
				if (!valueFlag) {
					throw new IllegalArgumentException("property has no attribute value or ref");
				}
			}

			Property property = new Property();
			property.setType(valueFlag ? 0 : 1);
			property.setName(name);
			property.setValue(valueFlag ? value : ref);

			listProperty.add(property);
		}

		return listProperty;
	}

	public class Bean {

		private String id;
		private String className;
		private List<Property> properties;
		private Class<?> instanceClass;
		private Object instance;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public List<Property> getProperties() {
			return properties;
		}

		public void setProperties(List<Property> properties) {
			this.properties = properties;
		}

		public Class<?> getInstanceClass() {
			return instanceClass;
		}

		public void setInstanceClass(Class<?> instanceClass) {
			this.instanceClass = instanceClass;
		}

		public Object getInstance() {
			return instance;
		}

		public void setInstance(Object instance) {
			this.instance = instance;
		}

	}

	private class Property {

		private int type;
		private String name;
		private String value;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
