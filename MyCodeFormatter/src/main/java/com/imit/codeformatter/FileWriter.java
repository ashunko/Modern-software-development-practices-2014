package com.imit.codeformatter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * class write data from the file
 */
public class FileWriter implements Writer {

    private List<String> data = null;

    /**
     * construct of FileWriter class
     * @param list - list of ready code
     */
    public FileWriter(final List<String> list) {
        this.data = list;
    }

    private static final Logger logger = Logger.getLogger(CodeFormatter.class);

    /**
     * write data from the file
     * @param bufWriter - file buffer
     */
    @Override
    public void write(final BufferedWriter bufWriter) {

        PropertyConfigurator.configure("src/main/resources/log4j.xml");

        if (bufWriter == null) {
            logger.error("Out stream is null");
            throw new NullPointerException("out stream is null");
        }
        PrintWriter out = new PrintWriter(bufWriter);
        out = new PrintWriter(bufWriter);
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