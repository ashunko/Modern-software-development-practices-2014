package com.imit.codeformatter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter implements Writer{

    List<String> data = null;

    public FileWriter(List<String> list) {
        this.data = list;
    }

    private static final Logger logger = Logger.getLogger(CodeFormatter.class);

    @Override
    public void write(BufferedWriter bw) {

        PropertyConfigurator.configure("src/main/resources/log4j.xml");

        if (bw == null) {
            logger.error("Out stream is null");
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