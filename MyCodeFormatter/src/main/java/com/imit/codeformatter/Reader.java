package com.imit.codeformatter;

import java.io.BufferedReader;
import java.util.List;

/**
 * interface for read text
 */
public interface Reader {
    List<String> read(BufferedReader in);
}
