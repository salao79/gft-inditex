package com.gft.inditex.domain.helper;

import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class ReflectionUtil {

    @SneakyThrows
    public static <T> T mapWithReflection(Object source, Class<T> targetClass) {
        T target = targetClass.getDeclaredConstructor().newInstance();
        Method[] sourceMethods = source.getClass().getMethods();
        Method[] targetMethods = targetClass.getMethods();

        for (Method sourceMethod : sourceMethods) {
            if (isGetter(sourceMethod)) {
                String setterName = sourceMethod.getName().replaceFirst("get", "set");

                for (Method targetMethod : targetMethods) {
                    if (targetMethod.getName().equals(setterName) && targetMethod.getParameterCount() == 1) {
                        Object value = sourceMethod.invoke(source);
                        targetMethod.invoke(target, value);
                        break;
                    }
                }
            }
        }

        return target;
    }

    private static boolean isGetter(Method method) {
        return method.getName().startsWith("get") && method.getParameterCount() == 0 && !method.getReturnType().equals(void.class);
    }

}
