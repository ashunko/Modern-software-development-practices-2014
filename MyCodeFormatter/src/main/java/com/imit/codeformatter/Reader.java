package com.imit.codeformatter;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by Alexey on 11.11.2014.
 */
public interface Reader {
    List<String> read(BufferedReader in);
}
