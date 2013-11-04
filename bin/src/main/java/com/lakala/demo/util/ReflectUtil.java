package com.lakala.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: <a mailto="litj@lakala.com">Li Taijun</a>
 * Create time: 12-3-23 下午4:43
 * Description: 
 */
public final class ReflectUtil {

	/**
	 * @param obj
	 *            The source object to reflect
	 * @param superLevels
	 *            The level of superClasses to reflect. For a non-inherited class, this will be 0. For classes where you also want to reflect the parent fields,
	 *            set to 1 or higher.
	 * @return string value representing the given object
	 */
	@SuppressWarnings("unchecked")
	private static Object[] p_fieldsToArray(Object obj, int superLevels) {
		List result = new ArrayList();
		try {
			Class sourceClass = obj.getClass();
			for (int level = 0; level <= superLevels; level++) {
				Field[] fields = sourceClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field f = fields[i];
					int modifier = f.getModifiers();
					if (Modifier.isStatic(modifier)) {
						continue;
					}
					f.setAccessible(true);
					String name = f.getName();
					if (name.indexOf("m_") == 0) {
						name = name.substring(2);
					}
					Object value = f.get(obj);

					result.add(name);
					result.add(value);
				}
				sourceClass = sourceClass.getSuperclass();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toArray();
	}

	@SuppressWarnings("unchecked")
	public static Object[] fieldsToArray(Object obj) {
		int levels = 0;
		Class sourceClass = obj.getClass();
		while (!sourceClass.getName().equals("java.lang.Object")) {
			levels++;
			sourceClass = sourceClass.getSuperclass();
		}
		levels--;

		return p_fieldsToArray(obj, levels);
	}

	/**
	 * @param obj
	 *            The source object to reflect
	 * @param superLevels
	 *            The level of superClasses to reflect. For a non-inherited class, this will be 0. For classes where you also want to reflect the parent fields,
	 *            set to 1 or higher.
	 * @return string value representing the given object
	 */
	private static String p_fieldsToString(Object obj, int superLevels) {
		String[] results = new String[superLevels + 1];
		StringBuffer result = new StringBuffer();
		try {
			Class sourceClass = obj.getClass();

			for (int level = 0; level <= superLevels; level++) {
				Field[] fields = sourceClass.getDeclaredFields();

				for (int i = 0; i < fields.length; i++) {
					Field f = fields[i];
					int modifier = f.getModifiers();
					if (Modifier.isStatic(modifier)) {
						continue;
					}
					f.setAccessible(true);
					String name = f.getName();
					if (name.indexOf("m_") == 0) {
						name = name.substring(2);
					}
					Object tempObject = f.get(obj);
					result.append(name);
					result.append("[");
					result.append(tempObject);
					result.append("]");
					if (i < fields.length - 1) {
						result.append(",");
					}
					result.append(" ");
				}
				results[superLevels - level] = result.toString();
				result.setLength(0);
				sourceClass = sourceClass.getSuperclass();
			}

			for (int i = 0; i <= superLevels; i++) {
				result.append(results[i]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result.toString();
	}

	public static String fieldsToString(Object obj) {
		int levels = 0;
		Class sourceClass = obj.getClass();
		while (!sourceClass.getName().equals("java.lang.Object")) {
			levels++;
			sourceClass = sourceClass.getSuperclass();
		}
		levels--;

		return p_fieldsToString(obj, levels);
	}

}
