package com.imit.codeformatter;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class CodeFormatter {

    public static void main(final String[] args) {

        if (args.length != 2) {
            System.err.println("The program uses:");
            System.err.println("CodeFormatter input_file output_file");
            System.exit(1);
        }

        RunFormatter cf = new RunFormatter();
        Reader fr = new FileReader();

        File input = new File(args[0]);
        File output = new File(args[1]);



        List<String> list = new ArrayList();

        try {
            list = fr.read(new BufferedReader(new java.io.FileReader(input)));
        } catch (IOException e) {
            System.out.println("Something wrong in file:" + input);
        }

        List<String> readyCode = new ArrayList();
        for (String str : list) {
            readyCode.add(cf.modifiedString(str));
        }

        Writer fwr = new FileWriter(readyCode);

        try {
            fwr.write(new BufferedWriter(new java.io.FileWriter(output)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
