package com.imit.codeformatter;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;

public class StringWriter implements Writer{

    List<String> data = null;

    public StringWriter(List<String> list) {
        this.data = list;
    }

    @Override
    public void write(BufferedWriter bw) {

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
