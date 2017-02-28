package com.novoda.sexp.marshaller;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LongWrapperBodyMarshallerShould {

    @Test
    public void marshalStringInput() throws Exception {
        LongWrapperBodyMarshaller<LongWrapperClass> cut = longWrapperBodyMarshaller(LongWrapperClass.class);

        LongWrapperClass output = cut.marshal(String.valueOf(Long.MAX_VALUE));

        assertThat(output).isEqualTo(new LongWrapperClass(Long.MAX_VALUE));
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithALongConstructorArgString() throws Exception {
        LongWrapperBodyMarshaller<StringWrapperClass> cut = longWrapperBodyMarshaller(StringWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithALongConstructorArgInteger() throws Exception {
        LongWrapperBodyMarshaller<IntegerWrapperClass> cut = longWrapperBodyMarshaller(IntegerWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithASingleConstructorArg() throws Exception {
        LongWrapperBodyMarshaller<TwoArgWrapperClass> cut = longWrapperBodyMarshaller(TwoArgWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void failForClassesWithNoArgConstructor() throws Exception {
        LongWrapperBodyMarshaller<NoArgWrapperClass> cut = longWrapperBodyMarshaller(NoArgWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void failForClassesWithPrivateConstructor() throws Exception {
        LongWrapperBodyMarshaller<PrivateConstructorWrapperClass> cut = longWrapperBodyMarshaller(
                PrivateConstructorWrapperClass.class
        );

        cut.marshal("1");
    }

    private <T> LongWrapperBodyMarshaller<T> longWrapperBodyMarshaller(Class<T> clazz) {
        return new LongWrapperBodyMarshaller<T>(clazz);
    }

    private static class LongWrapperClass {
        private final long toWrap;

        public LongWrapperClass(long toWrap) {
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

            LongWrapperClass that = (LongWrapperClass) o;

            return toWrap == that.toWrap;
        }

        @Override
        public int hashCode() {
            return (int) (toWrap ^ (toWrap >>> 32));
        }
    }

    private static class IntegerWrapperClass {
        private IntegerWrapperClass(int one) {
        }
    }

    private static class StringWrapperClass {
        private StringWrapperClass(String one) {
        }
    }

    private static class TwoArgWrapperClass {
        private TwoArgWrapperClass(long one, long two) {
        }
    }

    private static class NoArgWrapperClass {
        public NoArgWrapperClass() {
        }
    }

    private static class PrivateConstructorWrapperClass {
        private PrivateConstructorWrapperClass(long toWrap) {
        }
    }

}

