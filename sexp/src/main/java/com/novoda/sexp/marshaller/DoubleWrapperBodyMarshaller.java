package com.novoda.sexp.marshaller;

import java.lang.reflect.InvocationTargetException;

public class DoubleWrapperBodyMarshaller<T> implements BodyMarshaller<T> {

    private final Class<T> clazz;

    public DoubleWrapperBodyMarshaller(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T marshal(String input) {
        try {
            return clazz.getDeclaredConstructor(double.class).newInstance(Double.parseDouble(input));
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
