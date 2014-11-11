package com.imit.codeformatter;

import java.io.*;
//import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
//import java.util.Scanner;

/**
 * Created by Alexey on 11.11.2014.
 */
public class CodeFormatter {

    public static void main(final String[] args) {

        if (args.length != 2) {
            System.err.println("The program uses:");
            System.err.println("CodeFormatter input_file output_file");
            System.exit(1);
        }

        RunFormatter cf = new RunFormatter();
        //FileWriter fw = new FileWriter();
        Reader fr = new FileReader();

        //File output = new File(args[1]);



        List<String> list = new ArrayList();

        try {
            list = fr.read(new BufferedReader(new java.io.FileReader((new java.io.File(args[0]))))); //new File(args[0]));
        } catch (IOException e) {
            System.out.println("Something wrong in file:" + args[0]);
        }

        List<String> readyCode = new ArrayList();
        for (String str : list) {
            readyCode.add(cf.modifiedString(str));
        }


        Writer fwr = new StringWriter(readyCode);

        //Writable javaFormattedCodeWriter = new FileWriter(formattedStrings);
        try {
            fwr.write(new BufferedWriter(new java.io.FileWriter(args[1])));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO add logger
        }

        /*Writer fwr = new FileWriter(readyCode);
        //Writable javaFormattedCodeWriter = new FileWriter(formattedStrings);
        try {
            fwr.write(new BufferedWriter(new java.io.FileWriter(args[1])));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO add logger
        }*/


        //fw.writeFile(readyCode, args);
    }

}
