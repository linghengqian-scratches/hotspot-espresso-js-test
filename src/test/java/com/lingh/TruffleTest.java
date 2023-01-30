package com.lingh;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

public class TruffleTest {
    @Test
    void testJs() {
        try (Context context = Context.create()) {
            Value result = context.eval("js", "40+2");
            assert result.asInt() == 42;
        }
    }
    @Test
    void testEspresso() {
        try (Context polyglot = Context.newBuilder().allowNativeAccess(true).build()) {
            Value java_lang_Math = polyglot.getBindings("java").getMember(Math.class.getName());
            double sqrt2 = java_lang_Math.invokeMember("sqrt", 2).asDouble();
            double pi = java_lang_Math.getMember("PI").asDouble();
            assert sqrt2 == 1.4142135623730951;
            assert pi == 3.141592653589793;
        }
    }
}
