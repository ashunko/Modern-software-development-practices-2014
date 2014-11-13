package com.imit.codeformatter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class read data from the file
 */
public class FileReader implements Reader {

    private static final Logger logger = Logger.getLogger(CodeFormatter.class);

    /**
     * read data from the file
     * @param in - file buffer
     */
    @Override
    public List<String> read(final BufferedReader in) {

        PropertyConfigurator.configure("src/main/resources/log4j.xml");

        List<String> list = new ArrayList();
        if (in == null) {
            logger.error("Stream is null");
            throw new NullPointerException("Stream is null");
        } else {
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    list.add(line);
                }
            } catch (IOException e) {
                logger.error("Impossible to read the stream: ", e);
                e.printStackTrace();
            }
        }
        return list;
    }
}
