import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class PeselTest {
    static Pesel p;

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    @BeforeAll
    static void init() {
        p = new Pesel();
    }

    @Test
    void shouldGenerateAndCheckPesel() {
        String number = p.generate();

        assertEquals(11, number.length());
        assertTrue(p.validate(number));
    }

    @Test
    void shouldValidateCorrectNumber() {
        String number = "12345678903";

        assertTrue(p.validate(number));
    }

    @Test
    void shouldNotValidateBadNumber() {
        String number = "12345678901";

        assertFalse(p.validate(number));
    }

    @Test
    void shouldExtractPrivateMethods() throws ClassNotFoundException {
        Class<?> peselClass = p.getClass();
        Method[] methods = peselClass.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);

        System.out.println(actualMethods);

        assertEquals(10, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("extractSex", "extractBirthDate")));
    }

    @Test
    void shouldCallPrivateMethods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String number = "12345678903";

        Class<?> peselClass = p.getClass();
        Method extractSexMethod = peselClass.getDeclaredMethod("extractSex", String.class);
        extractSexMethod.setAccessible(true);

        assertEquals("male", extractSexMethod.invoke(p, number));

        Method extractBirthDateMethod = peselClass.getDeclaredMethod("extractBirthDate", String.class);
        extractBirthDateMethod.setAccessible(true);

        Date d = (Date) extractBirthDateMethod.invoke(p, number);
        assertEquals(2020, d.getYear());
        assertEquals(10, d.getMonth());
        assertEquals(15, d.getDate());
    }
}