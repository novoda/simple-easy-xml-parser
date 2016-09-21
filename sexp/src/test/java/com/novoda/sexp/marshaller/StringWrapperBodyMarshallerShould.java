package com.novoda.sexp.marshaller;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class StringWrapperBodyMarshallerShould {

    @Test
    public void marshalStringInput() throws Exception {
        StringWrapperBodyMarshaller<StringWrapperClass> cut = stringWrapperBodyMarshaller(StringWrapperClass.class);

        String testInput = "testInput";
        StringWrapperClass output = cut.marshal(testInput);

        assertThat(output).isEqualTo(new StringWrapperClass(testInput));
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithASingleStringConstructorArg() throws Exception {
        StringWrapperBodyMarshaller<IntegerWrapperClass> cut = stringWrapperBodyMarshaller(IntegerWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithASingleConstructorArg() throws Exception {
        StringWrapperBodyMarshaller<TwoArgWrapperClass> cut = stringWrapperBodyMarshaller(TwoArgWrapperClass.class);

        cut.marshal("");
    }

    private <T> StringWrapperBodyMarshaller<T> stringWrapperBodyMarshaller(Class<T> clazz) {
        return new StringWrapperBodyMarshaller<T>(clazz);
    }

    private static class StringWrapperClass {
        private final String toWrap;

        public StringWrapperClass(String toWrap) {
            this.toWrap = toWrap;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            StringWrapperClass that = (StringWrapperClass) o;

            return !(toWrap != null ? !toWrap.equals(that.toWrap) : that.toWrap != null);
        }

        @Override
        public int hashCode() {
            return toWrap != null ? toWrap.hashCode() : 0;
        }
    }

    private static class IntegerWrapperClass {
        private IntegerWrapperClass(Integer one) {
        }
    }

    private static class TwoArgWrapperClass {
        private TwoArgWrapperClass(String one, String two) {
        }
    }
}
