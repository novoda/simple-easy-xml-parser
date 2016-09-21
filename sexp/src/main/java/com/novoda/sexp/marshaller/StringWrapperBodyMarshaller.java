package com.novoda.sexp.marshaller;

import java.lang.reflect.InvocationTargetException;

public class StringWrapperBodyMarshaller<T> implements BodyMarshaller<T> {

    private final Class<T> clazz;

    public StringWrapperBodyMarshaller(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T marshal(String input) {
        try {
            return clazz.getDeclaredConstructor(String.class).newInstance(input);
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
