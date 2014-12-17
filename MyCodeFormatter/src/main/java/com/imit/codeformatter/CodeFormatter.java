package com.imit.codeformatter;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * The main CodeFormatter class
 */
public class CodeFormatter {

    private static final Logger LOGGER = Logger.getLogger(CodeFormatter.class);

    /**
     * read input file
     * @param input - input file
     * @return list of lines of the file
     */
    public List<String> readInput(final File input) {

        List<String> list = new ArrayList();
        Reader fileReader = new FileReader();

        BufferedReader bufReader = null;
        try {
            LOGGER.info(String.format("Started to read the file: %s", input));
            bufReader = new BufferedReader(new java.io.FileReader(input));
            if (bufReader != null) {
                list = fileReader.read(bufReader);
            }
        } catch (IOException e) {
            LOGGER.error(String.format("Something wrong in file: %s", input), e);
        } finally {
            try {
                bufReader.close();
            } catch (IOException e) {
                LOGGER.error("Something wrong in stream: ", e);
            }
        }
        return list;
    }

    /**
     * formatting source code
     * @param inputCode - input code
     * @return list of lines of the ready code
     */
    public List<String> formatCode(final List<String> inputCode) {

        RunFormatter runFormatter = new RunFormatter();
        List<String> readyCode = new ArrayList();
        for (String str : inputCode) {
            readyCode.add(runFormatter.modifiedString(str));
        }
        return readyCode;
    }

    /**
     * write output file
     * @param readyCode - ready code
     * @param output - output file
     */
    public void writeOutput(final List<String> readyCode, final File output) {

        Writer fileWriter = new FileWriter(readyCode);

        try {
            fileWriter.write(new BufferedWriter(new java.io.FileWriter(output)));
        } catch (IOException e) {
            LOGGER.error(String.format("File %s recording failed", output), e);
        }

    }

    /**
     * The main function of CodeFormatter
     * @param args - arguments for the project
     */
    public static void main(final String[] args) {

        PropertyConfigurator.configure("src/main/resources/log4j.xml");

        if (args.length != 2) {
            LOGGER.error(String.format("The program uses: CodeFormatter input_file output_file"));
        }

        File input = new File(args[0]);
        File output = new File(args[1]);

        CodeFormatter codeFormatter = new CodeFormatter();
        List<String> inputCode = codeFormatter.readInput(input);
        List<String> readyCode = codeFormatter.formatCode(inputCode);
        codeFormatter.writeOutput(readyCode, output);

    }

}
