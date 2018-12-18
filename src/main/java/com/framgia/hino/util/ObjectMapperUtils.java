package com.framgia.hino.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectMapperUtils {
	public static <T> T map(Object sourceObject, Class<T> targetClass) {
		Map<String, Object> mapProperties = ReflectionUtils.getProperties(sourceObject);

		return ReflectionUtils.buildObject(mapProperties, targetClass);
	}

	public static <T> T map(Object sourceObject, T targetObject) {
		Map<String, Object> mapProperties = ReflectionUtils.getProperties(sourceObject);

		return ReflectionUtils.buildObject(mapProperties, targetObject);
	}

	public static <T> T map(Class<T> type, Object[] tuple) {
		List<Class<?>> tupleTypes = new ArrayList<>();

		for (Object field : tuple) {
			tupleTypes.add(field.getClass());
		}

		try {
			Constructor<T> constructor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));

			return constructor.newInstance(tuple);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> map(Class<T> type, List<Object[]> records) {
		List<T> result = new LinkedList<>();

		for (Object[] record : records) {
			result.add(map(type, record));
		}

		return result;
	}
}
