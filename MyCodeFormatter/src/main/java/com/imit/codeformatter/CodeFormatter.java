package com.imit.codeformatter;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CodeFormatter {

    private static final Logger logger = Logger.getLogger(CodeFormatter.class);

    public static void main(final String[] args) {

        PropertyConfigurator.configure("src/main/resources/log4j.xml");

        if (args.length != 2) {
            logger.error(String.format("The program uses: CodeFormatter input_file output_file"));
        }

        Reader fr = new FileReader();

        File input = new File(args[0]);
        File output = new File(args[1]);



        List<String> list = new ArrayList();

        BufferedReader br = null;
        try {
            logger.info(String.format("Started to read the file: %s", input));
            br = new BufferedReader(new java.io.FileReader(input));
            if(br != null) {
                list = fr.read(br);
            }
        } catch (IOException e) {
            logger.error(String.format("Something wrong in file: %s", input), e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("Something wrong in stream: ", e);
            }
        }

        RunFormatter cf = new RunFormatter();
        List<String> readyCode = new ArrayList();
        for (String str : list) {
            readyCode.add(cf.modifiedString(str));
        }

        Writer fwr = new FileWriter(readyCode);

        try {
            fwr.write(new BufferedWriter(new java.io.FileWriter(output)));
        } catch (IOException e) {
            logger.error(String.format("File %s recording failed", output), e);
        }
    }

}
