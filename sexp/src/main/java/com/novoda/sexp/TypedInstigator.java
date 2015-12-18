package com.novoda.sexp;

/**
 * This instigator can be used when you want to return your parsed object synchronously.
 * A good example being {@link SimpleEasyXmlParser#parse(String, TypedInstigator)}
 *
 * @param <R> the type of object you expect as a result of xml parsing
 */
public interface TypedInstigator<R> extends Instigator {

    /**
     * @return the object you expected to be parsed
     */
    R getResultOrThrow();
}
