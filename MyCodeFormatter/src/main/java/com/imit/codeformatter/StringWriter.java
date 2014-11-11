package com.imit.codeformatter;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Alexey on 11.11.2014.
 */
public class StringWriter implements Writer{

    List<String> data = null;

    public StringWriter(List<String> list) {
        this.data = list;
    }

    @Override
    public void write(BufferedWriter bw) {
        if (bw == null) {
            throw new NullPointerException("out stream is null");
        }
        PrintWriter out = new PrintWriter(bw);
        out = new PrintWriter(bw);
        for (String s : data) {
            String[] sf = s.split("\n");
            for (String sw : sf) {
                out.print(sw);
                out.println();
            }
        }
        out.println();
        out.close();
    }

}