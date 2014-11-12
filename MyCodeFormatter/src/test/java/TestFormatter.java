import com.imit.codeformatter.RunFormatter;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class TestFormatter {

    @Test
    public void testBraces() throws Exception {
        RunFormatter testFormat = new RunFormatter();
        String in = "public static void main (String[] args) {        for ()  {}if (345346 >= 15) {\n" +
                    "        return true;\n" +
                    "    }}\n";
        String answer = "public static void main (String[] args) {\n" +
                "    for ()  {}\n" +
                "    if (345346 >= 15) {\n" +
                "        return true;\n" +
                "    }\n" +
                "}";

        String out = testFormat.modifiedString(in);

        assertEquals(answer, out);
    }

    @Test
    public void testSemicolon() throws Exception {
        RunFormatter testFormat = new RunFormatter();
        String in = "public static void main (String[] args){String l = \"\";        if (345346 >= 15) {return true;}\n" +
                "    if (3 += 1) {\n" +
                "        int y = 5;return false;\t\t\t\t}\n" +
                "    for (int i =1;i >0;i-- )  {}\n" +
                "}";
        String answer = "public static void main (String[] args) {\n" +
                "    String l = \"\";\n" +
                "    if (345346 >= 15) {\n" +
                "        return true;\n" +
                "    }\n" +
                "    if (3 += 1) {\n" +
                "        int y = 5;\n" +
                "        return false;\n" +
                "    }\n" +
                "    for (int i =1; i >0; i-- )  {}\n" +
                "}";

        String out = testFormat.modifiedString(in);

        assertEquals(answer, out);
    }

    @Test
    public void testParentheses() throws Exception {
        RunFormatter testFormat = new RunFormatter();
        String in = "public static void main (String[] args) {\n" +
                "    Example ex = new Example();String l = ex.myExample (a);\n" +
                "    if (10 < 234) {\t\t\t\treturn true;\n" +
                "    }if(3 += 1){int y = 5;return false;\n" +
                "    }    System.out.println(ex.show);\n" +
                "}";
        String answer = "public static void main (String[] args) {\n" +
                "    Example ex = new Example ();\n" +
                "    String l = ex.myExample (a);\n" +
                "    if (10 < 234) {\n" +
                "        return true;\n" +
                "    }\n" +
                "    if (3 += 1) {\n" +
                "        int y = 5;\n" +
                "        return false;\n" +
                "    }\n" +
                "    System.out.println (ex.show);\n" +
                "}";

        String out = testFormat.modifiedString(in);

        assertEquals(answer, out);
    }

    @Test
    public void testSignsOperations() throws Exception {
        RunFormatter testFormat = new RunFormatter();
        String in = "public static void main (String[] args) {\n" +
                "    Example ex = new Example ();\n" +
                "    String l = ex.myExample (a);\n" +
                "\tif(4 -= 1){int x = 10-3;}    for (int i = 1; i > 0;i--)  {}\n" +
                "    if (3 += 1) {\n" +
                "        int y = 15 +5;}for (int i = 1; i > 0;i++)  {}}";
        String answer = "public static void main (String[] args) {\n" +
                "    Example ex = new Example ();\n" +
                "    String l = ex.myExample (a);\n" +
                "    if (4 -= 1) {\n" +
                "        int x = 10 - 3;\n" +
                "    }\n" +
                "    for (int i = 1; i > 0; i-- )  {}\n" +
                "    if (3 += 1) {\n" +
                "        int y = 15 + 5;\n" +
                "    }\n" +
                "    for (int i = 1; i > 0; i++ )  {}\n" +
                "}";

        String out = testFormat.modifiedString(in);

        assertEquals(answer, out);
    }
}
