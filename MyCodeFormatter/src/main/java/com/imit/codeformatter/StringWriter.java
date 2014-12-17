package com.imit.codeformatter;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * class write data from the string
 */
public class StringWriter implements Writer {

    private List<String> data = null;

    /**
     * construct of StringWriter class
     * @param list - list of ready code
     */
    public StringWriter(final List<String> list) {
        this.data = list;
    }

    /**
     * write data from the string
     * @param bufWriter - file buffer
     */
    @Override
    public void write(final BufferedWriter bufWriter) {

        PrintWriter out = new PrintWriter(bufWriter);
        out = new PrintWriter(bufWriter);
        for (String newStr : data) {
            String[] strMas = newStr.split("\n");
            for (String readyStr : strMas) {
                out.print(readyStr);
                out.println();
            }
        }
        out.println();
        out.close();
    }

}
