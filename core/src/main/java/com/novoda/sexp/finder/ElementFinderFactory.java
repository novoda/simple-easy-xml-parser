package com.novoda.sexp.finder;

import com.novoda.sexp.marshaller.*;
import com.novoda.sexp.parser.*;

import java.util.List;

/**
 * You can retrieve {@link ElementFinder ElementFinder's} here
 * <p/>
 * For example if you have the XML
 * <pre>
 * {@code <myTag>Paul Blundell</myTag> }
 * </pre>
 * and you want to retrieve it as a {@link String}
 * you would use the {@link #getStringFinder()} method
 * <pre>
 * {@code ElementFinder finder = getStringFinder();
 * finder.find(XML, "myTag");
 * String name = finder.getResultOrThrow();
 * }
 * </pre>
 */
public class ElementFinderFactory {

    /**
     * Will parse the body of an XML tag into a {@link String}
     *
     * @return {@link ElementFinder}
     */
    public ElementFinder<String> getStringFinder() {
        return getTypeFinder(new StringBodyMarshaller());
    }

    /**
     * Will parse the body of an XML tag into an {@link Integer}
     *
     * @return {@link ElementFinder}
     */
    public ElementFinder<Integer> getIntegerFinder() {
        return getTypeFinder(new IntegerBodyMarshaller());
    }

    /**
     * Will parse the body of an XML tag into a simple {@link Integer} wrapper class. This is a class that
     * has a constructor that takes a single primitive int parameter.
     *
     * @param clazz The class of your Integer Wrapper Class
     * @param <T>   The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getIntegerWrapperTypeFinder(Class<T> clazz) {
        return getTypeFinder(new BasicParser<T>(getIntegerWrapperMarshaller(clazz)));
    }

    /**
     * Will parse the body of an XML tag into a simple {@link String} wrapper class. This is a class that
     * has a constructor that takes a single {@link String} parameter.
     *
     * @param clazz The class of your String Wrapper Class
     * @param <T>   The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getStringWrapperTypeFinder(Class<T> clazz) {
        return getTypeFinder(new BasicParser<T>(getStringWrapperMarshaller(clazz)));
    }

    /**
     * Will parse the body of all XML tags with the {@code tag} argument
     * into a {@link java.util.List} of {@link Object} using the supplied {@link BodyMarshaller}.
     * <pre>
     * {@code <names>
     *     <name>Paul</name>
     *     <name>Peter</name>
     *   </names>
     *
     * ElementFinder<T> finder = getListFinder("name", marshallerOfT);
     * finder.find(element, "names");
     * List<T> names = finder.getResultOrThrow();
     * }
     * </pre>
     *
     * @param tag            The tag to parse the body for each list element
     * @param bodyMarshaller The marshaller to parse the body into your required type
     * @param <T>            The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<List<T>> getListFinder(String tag, BodyMarshaller<T> bodyMarshaller) {
        return getTypeFinder(new ListParser<T>(tag, this, bodyMarshaller));
    }

    /**
     * Will parse the body of all XML tags with the {@code tag} argument
     * into a {@link java.util.List} of {@link Object}. This is a simple String wrapper class that
     * has a constructor that takes a single {@link String} parameter.<br/>
     * See {@link #getStringWrapperTypeFinder(Class)} for more info.
     *
     * @param tag   The tag to parse the body for each list element
     * @param clazz The class of the wrapper type you wish your List to be made of
     * @param <T>   The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<List<T>> getStringWrapperTypeListFinder(String tag, Class<T> clazz) {
        return getTypeFinder(new ListParser<T>(tag, this, getStringWrapperMarshaller(clazz)));
    }

    /**
     * Will parse the body of all XML tags with the {@code tag} argument
     * into a {@link java.util.List} of {@link Object}. This is a simple Integer wrapper class that
     * has a constructor that takes a single {@link Integer} parameter.<br/>
     * See {@link #getIntegerWrapperTypeFinder(Class)} for more info.
     *
     * @param tag   The tag to parse the body for each list element
     * @param clazz The class of the wrapper type you wish your List to be made of
     * @param <T>   The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<List<T>> getIntegerWrapperTypeListFinder(String tag, Class<T> clazz) {
        return getTypeFinder(new ListParser<T>(tag, this, getIntegerWrapperMarshaller(clazz)));
    }

    /**
     * Will parse the attributes off an XML tag, these are then passed to your {@link AttributeMarshaller}
     * to create an object of type {@link Object}.
     *
     * @param attributeMarshaller The marshaller to parse the attributes into your required type
     * @param attrTags            The tags of the attributes you wish to parse
     * @param <T>                 The type you wish to create from the attributes
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getAttributeFinder(AttributeMarshaller<T> attributeMarshaller, String... attrTags) {
        return getTypeFinder(new BasicAttributeParser<T>(attributeMarshaller, attrTags));
    }

    /**
     * Will parse the body of an XML tag into {@link Object} using the supplied {@link BodyMarshaller}
     *
     * @param bodyMarshaller The marshaller to parse the body into your required type
     * @param <T>            The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getTypeFinder(BodyMarshaller<T> bodyMarshaller) {
        return getTypeFinder(new BasicParser<T>(bodyMarshaller));
    }

    /**
     * Will parse an XML tag using the passed {@link Parser}
     * This can be used when you want to parse attributes as well as the body into your object
     *
     * @param parser The parser you wish to parse the XML with
     * @param <T>    The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getTypeFinder(Parser<T> parser) {
        return new BasicElementFinder<T>(parser);
    }

    /**
     * Will parse the body of an XML tag into {@link Object} then inform the {@link ParseWatcher}
     * The idea is to have a callback for a number of elements to create a {@link java.util.List}
     *
     * @param bodyMarshaller The marshaller to create an object from the XML body
     * @param watcher        The watcher on elements to be informed of object creation
     * @param <T>            The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getListElementFinder(BodyMarshaller<T> bodyMarshaller, ParseWatcher<T> watcher) {
        return getListElementFinder(new BasicParser<T>(bodyMarshaller), watcher);
    }

    /**
     * Will parse an XML tag into {@link Object} then inform the {@link ParseWatcher}
     * The idea is to have a callback for a number of elements to create a {@link java.util.List}
     * This can be used when you want to parse attributes as well as the XML body into your object
     *
     * @param parser  The parser you wish to parse the XML with
     * @param watcher The watcher on elements to be informed of object creation
     * @param <T>     The type you wish to create from the XML body
     * @return {@link ElementFinder}
     */
    public <T> ElementFinder<T> getListElementFinder(Parser<T> parser, ParseWatcher<T> watcher) {
        return new ListElementFinder<T>(parser, watcher);
    }

    private <T> BodyMarshaller<T> getStringWrapperMarshaller(Class<T> clazz) {
        return new StringWrapperBodyMarshaller<T>(clazz);
    }

    private <T> BodyMarshaller<T> getIntegerWrapperMarshaller(Class<T> clazz) {
        return new IntegerWrapperBodyMarshaller<T>(clazz);
    }
}
