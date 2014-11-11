package com.imit.codeformatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Alexey on 11.11.2014.
 */
public class FileWriter implements Writer{

    List<String> data = null;

    public FileWriter(List<String> list) {
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



/*    public void writeFile(final List<String> list, final String[] args) {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new BufferedWriter(new java.io.FileWriter(args[1])));
            for (String s : list) {
                String[] sf = s.split("\n");
                for (String sw : sf) {
                    out.print(sw);
                    out.println();
                }
            }
            out.println();
        } catch (IOException ex) {
            System.out.println("Something wrong with a writing to file" + args[1]);
        } finally {
            out.close();
        }
    }*/


