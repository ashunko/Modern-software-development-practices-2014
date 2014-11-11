package com.imit.codeformatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexey on 11.11.2014.
 */
public class FileReader implements Reader{

    /*    public List<String> readFile(final File file) throws IOException {
        List<String> list = new ArrayList();
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            list.add(scan.nextLine());
        }
        scan.close();
        return list;
    }*/

    @Override
    public List<String> read(BufferedReader in){
        List<String> list = new ArrayList();
        if (in == null) {
            //throw new NullPointerException("stream is null");
        } else {
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    list.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


}
