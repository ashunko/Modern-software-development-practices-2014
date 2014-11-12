package com.imit.codeformatter;

import java.io.BufferedReader;
import java.util.List;

public interface Reader {
    List<String> read(BufferedReader in);
}
