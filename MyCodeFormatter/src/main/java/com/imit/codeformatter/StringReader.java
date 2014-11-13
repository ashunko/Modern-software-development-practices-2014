package com.imit.codeformatter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class read data from the string
 */
public class StringReader implements Reader {

    private static final Logger logger = Logger.getLogger(CodeFormatter.class);

    /**
     * read data from the string
     * @param in - string buffer
     */
    @Override
    public List<String> read(final BufferedReader in) {

        PropertyConfigurator.configure("src/main/resources/log4j.xml");

        List<String> list = new ArrayList();
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    list.add(line);
                }
            } catch (IOException e) {
                logger.error("Impossible to read the stream: ", e);
                e.printStackTrace();
            }
        return list;
    }

}
