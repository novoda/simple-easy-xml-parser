package com.novoda.sexp.marshaller;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntegerWrapperBodyMarshallerShould {

    @Test
    public void marshallStringInput() throws Exception {
        IntegerWrapperBodyMarshaller<IntegerWrapperClass> cut = integerWrapperBodyMarshaller(IntegerWrapperClass.class);

        IntegerWrapperClass output = cut.marshall("1");

        assertThat(output).isEqualTo(new IntegerWrapperClass(1));
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithAnIntegerConstructorArg() throws Exception {
        IntegerWrapperBodyMarshaller<StringWrapperClass> cut = integerWrapperBodyMarshaller(StringWrapperClass.class);

        cut.marshall("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithASingleConstructorArg() throws Exception {
        IntegerWrapperBodyMarshaller<TwoArgWrapperClass> cut = integerWrapperBodyMarshaller(TwoArgWrapperClass.class);

        cut.marshall("");
    }

    @Test(expected = RuntimeException.class)
    public void failForClassesWithNoArgConstructor() throws Exception {
        IntegerWrapperBodyMarshaller<NoArgWrapperClass> cut = integerWrapperBodyMarshaller(NoArgWrapperClass.class);

        cut.marshall("");
    }

    @Test(expected = RuntimeException.class)
    public void failForClassesWithPrivateConstructor() throws Exception {
        IntegerWrapperBodyMarshaller<PrivateConstructorWrapperClass> cut = integerWrapperBodyMarshaller(
                PrivateConstructorWrapperClass.class);

        cut.marshall("1");
    }

    private <T> IntegerWrapperBodyMarshaller<T> integerWrapperBodyMarshaller(Class<T> clazz) {
        return new IntegerWrapperBodyMarshaller<T>(clazz);
    }

    private static class IntegerWrapperClass {
        private final int toWrap;

        public IntegerWrapperClass(int toWrap) {
            this.toWrap = toWrap;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IntegerWrapperClass that = (IntegerWrapperClass) o;

            return toWrap == that.toWrap;
        }

        @Override
        public int hashCode() {
            return toWrap;
        }
    }

    private static class StringWrapperClass {
        private StringWrapperClass(String one) {
        }
    }

    private static class TwoArgWrapperClass {
        private TwoArgWrapperClass(int one, int two) {
        }
    }

<<<<<<< HEAD
    private static class NoArgWrapperClass {
        public NoArgWrapperClass() {
=======
<<<<<<< HEAD
    private static class PrivateConstructorWrapperClass {
        private PrivateConstructorWrapperClass(int toWrap) {
=======
    private static class NoArgWrapperClass {
        public NoArgWrapperClass() {
>>>>>>> 4c6ad0847b67d7aa90d7bc1497d472fd1e2df728
>>>>>>> f0eec3c... Merge branch 'droidcon' of github.com:novoda/SimpleEasyXmlParser into cketti-test_integer_wrapper_private_constructor
        }
    }
}
