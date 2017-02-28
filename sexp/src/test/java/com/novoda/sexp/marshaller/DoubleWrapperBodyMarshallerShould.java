package com.novoda.sexp.marshaller;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DoubleWrapperBodyMarshallerShould {

    @Test
    public void marshalStringInput() throws Exception {
        DoubleWrapperBodyMarshaller<DoubleWrapperClass> cut = doubleWrapperBodyMarshaller(DoubleWrapperClass.class);

        DoubleWrapperClass output = cut.marshal("42.42d");

        assertThat(output).isEqualTo(new DoubleWrapperClass(42.42d));
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithADoubleConstructorArgString() throws Exception {
        DoubleWrapperBodyMarshaller<StringWrapperClass> cut = doubleWrapperBodyMarshaller(StringWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithADoubleConstructorArgInteger() throws Exception {
        DoubleWrapperBodyMarshaller<IntegerWrapperClass> cut = doubleWrapperBodyMarshaller(IntegerWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithADoubleConstructorArgLong() throws Exception {
        DoubleWrapperBodyMarshaller<LongWrapperClass> cut = doubleWrapperBodyMarshaller(LongWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithADoubleConstructorArgFloat() throws Exception {
        DoubleWrapperBodyMarshaller<FloatWrapperClass> cut = doubleWrapperBodyMarshaller(FloatWrapperClass.class);

        cut.marshal("");
    }


    @Test(expected = RuntimeException.class)
    public void onlyWorkForClassesWithASingleConstructorArg() throws Exception {
        DoubleWrapperBodyMarshaller<TwoArgWrapperClass> cut = doubleWrapperBodyMarshaller(TwoArgWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void failForClassesWithNoArgConstructor() throws Exception {
        DoubleWrapperBodyMarshaller<NoArgWrapperClass> cut = doubleWrapperBodyMarshaller(NoArgWrapperClass.class);

        cut.marshal("");
    }

    @Test(expected = RuntimeException.class)
    public void failForClassesWithPrivateConstructor() throws Exception {
        DoubleWrapperBodyMarshaller<PrivateConstructorWrapperClass> cut = doubleWrapperBodyMarshaller(
                PrivateConstructorWrapperClass.class
        );

        cut.marshal("1");
    }

    private <T> DoubleWrapperBodyMarshaller<T> doubleWrapperBodyMarshaller(Class<T> clazz) {
        return new DoubleWrapperBodyMarshaller<T>(clazz);
    }

    private static class DoubleWrapperClass {
        private final double toWrap;

        public DoubleWrapperClass(double toWrap) {
            this.toWrap = toWrap;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DoubleWrapperClass that = (DoubleWrapperClass) o;

            return Double.compare(that.toWrap, toWrap) == 0;

        }

        @Override
        public int hashCode() {
            long temp = Double.doubleToLongBits(toWrap);
            return (int) (temp ^ (temp >>> 32));
        }
    }

    private static class IntegerWrapperClass {
        private IntegerWrapperClass(int one) {
        }
    }

    private static class LongWrapperClass {
        private LongWrapperClass(long one) {
        }
    }

    private static class FloatWrapperClass {
        private FloatWrapperClass(float one) {
        }
    }

    private static class StringWrapperClass {
        private StringWrapperClass(String one) {
        }
    }

    private static class TwoArgWrapperClass {
        private TwoArgWrapperClass(double one, double two) {
        }
    }

    private static class NoArgWrapperClass {
        public NoArgWrapperClass() {
        }
    }

    private static class PrivateConstructorWrapperClass {
        private PrivateConstructorWrapperClass(double toWrap) {
        }
    }

}

