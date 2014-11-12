package com.imit.codeformatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader{

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
