package com.novoda.sexp.marshaller;

import java.lang.reflect.InvocationTargetException;

public class LongWrapperBodyMarshaller<T> implements BodyMarshaller<T> {

    private final Class<T> clazz;

    public LongWrapperBodyMarshaller(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T marshal(String input) {
        try {
            return clazz.getDeclaredConstructor(long.class).newInstance(Long.parseLong(input));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
