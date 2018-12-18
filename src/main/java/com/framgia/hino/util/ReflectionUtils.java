package com.framgia.hino.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionUtils {
	public static <T> Map<String, Object> getProperties(T obj) {
		Map<String, Object> result = new HashMap<>();

		Class<?> classObj = obj.getClass();
		Field[] fields = classObj.getDeclaredFields();

		for (Field field : fields) {
			if (field.getName().contains("$")) {
				continue;
			}

			try {
				boolean accessible = field.isAccessible();
				field.setAccessible(true);
				result.put(field.getName(), field.get(obj));
				field.setAccessible(accessible);
			} catch (IllegalAccessException e) {
				log.error("couldn't get value of field: " + field.getName());
			}
		}

		return result;
	}

	public static <T> T buildObject(Map<String, Object> properties, Class<T> targetClass) {
		T resultObj = createInstance(targetClass);

		return buildObject(properties, resultObj);
	}

	static <T> T buildObject(Map<String, Object> properties, T object) {
		Field[] fields = object.getClass().getDeclaredFields();

		try {
			for (Field field : fields) {
				if (properties.containsKey(field.getName())) {
					boolean accessible = field.isAccessible();

					try {
						field.setAccessible(true);
						field.set(object, properties.get(field.getName()));
					} catch (IllegalArgumentException ignored) {
					} finally {
						field.setAccessible(accessible);
					}
				}
			}
		} catch (IllegalAccessException e) {
			log.error("Couldn't set field for object " + object.getClass().getSimpleName(), e);
		}

		return object;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static <T> T createInstance(Class<T> targetClass) {
		T object = null;
		Constructor[] constructors = targetClass.getDeclaredConstructors();
		Constructor constructor = null;

		for (Constructor c : constructors) {
			constructor = c;
			if (constructor.getGenericParameterTypes().length == 0)
				break;
		}

		try {
			assert constructor != null;
			constructor.setAccessible(true);
			Type[] types = constructor.getGenericParameterTypes();
			Object[] constructorParams = Arrays.stream(types).map(t -> {
				Object result = null;
				try {
					if (t.getClass().isPrimitive()) {
						result = t.getClass().getConstructors()[0].newInstance();
					}
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					log.error("Coudn't build instance of " + t.getClass().getSimpleName(), e);
				}

				return result;
			}).toArray();

			object = (T) constructor.newInstance(constructorParams);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			log.error("Coudn't build instance of " + targetClass.getSimpleName(), e);
		}

		return object;
	}
}