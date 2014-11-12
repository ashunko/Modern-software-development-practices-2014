package com.imit.codeformatter;

import java.io.File;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RunFormatter {

    private StringBuilder sb = null;
    private boolean hasParenthesis = false;
    private boolean NewString = false;
    private boolean NewStringAtFile = false;
    private int tab = 0;
    private final int indentSize = 4;
    private final char symbolIndentation = ' ';

    public boolean nextSymbol(final String str, final char symbol) {
        for (char c : str.toCharArray()) {
            if (c == ' ') { } else {
                if (c == symbol) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean findParenthesis(final String s) {
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) != ' ') {
                if (s.charAt(i) == 'r') {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public String witchSymbol(final char c, final int index) {
        StringBuilder res = new StringBuilder();
        switch (c) {

            case '{': {
                if ((index != sb.length() - 1) && (sb.charAt(index + 1) == '}')) {
                    res.append(c);
                    break;
                } else {
                    if ((index > 1) && (sb.charAt(index - 1) != ' ')) {
                        res.append(' ');
                    }
                    res.append(c);
                    this.tab++;
                    if (sb.length() - 1 != index) {
                        res.append('\n');
                        NewString = true;
                        break;
                    }
                    break;
                }

            }

            case ';': {
                if (hasParenthesis) {
                    res.append(c);
                    if (sb.charAt(index + 1) != ' ') {
                        res.append(' ');
                    }
                    break;
                }
                if (index != (sb.length() - 1)) {
                    if (nextSymbol(sb.substring(index + 1), '}')) {
                        res.append(c).append('\n');
                        NewString = true;
                    } else {
                        res.append(c).append('\n');
                        NewString = true;
                    }
                    break;
                } else {
                    res.append(c);
                    break;
                }
            }

            case '}': {
                if ((index > 1) && (sb.charAt(index - 1) == '{')) {
                    res.append(c);
                    if (sb.length() - 1 != index) {
                        res.delete(0, res.length());
                        res.append("Down!Brace!");
                        NewString = true;
                        break;
                    }
                    break;
                } else {
                    this.tab--;
                    res.append("Down!Brace");
                    if (sb.length() - 1 != index) {
                        res.delete(0, res.length());
                        res.append("Down!Brace!N");
                        NewString = true;
                        break;
                    }
                }
                break;
            }

            case '(': {
                hasParenthesis = findParenthesis(sb.substring(0, index));
                if (index >= 1) {
                    if (sb.charAt(index - 1) != ' ') {
                        res.append(' ');
                    }
                }
                res.append(c);
                break;
            }

            case ')': {
                if (hasParenthesis) {
                    hasParenthesis = false;
                }
                res.append(c);
                if (sb.length() - 1 > index) {
                    if ((sb.charAt(index + 1) != ' ') && (sb.charAt(index + 1) != ';') &&
                            (sb.charAt(index + 1) != '{')) {
                        res.append(' ');
                    }
                }
                break;
            }

            case '+': {
                if (index > 0) {
                    if ((sb.charAt(index - 1) != ' ') && (sb.charAt(index + 1) != '+')) {
                        if (sb.charAt(index - 1) != '+') {
                            res.append(' ');
                        }
                    }
                }
                res.append(c);
                if (sb.length() - 1 > index) {
                    if ((sb.charAt(index + 1) != '=') && (sb.charAt(index + 1) != ' ') &&
                            (sb.charAt(index + 1) != '+')) {
                        res.append(' ');
                    }
                }
                break;
            }

            case '-': {
                if (index > 0) {
                    if ((sb.charAt(index - 1) != ' ') && (sb.charAt(index + 1) != '-')) {
                        if (sb.charAt(index - 1) != '-') {
                            res.append(' ');
                        }
                    }
                }
                res.append(c);
                if (sb.length() - 1 > index) {
                    if ((sb.charAt(index + 1) != '=') && (sb.charAt(index + 1) != ' ') &&
                            (sb.charAt(index + 1) != '-')) {
                        res.append(' ');
                    }
                }
                break;
            }

            default: {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String getTab() {
        char[] tabString = new char[this.tab * indentSize];
        Arrays.fill(tabString, symbolIndentation);
        return new String(tabString);
    }

    public String modifiedString(final String str) {

        if (str.isEmpty() || (str.trim().isEmpty())) {
            return str;
        }

        this.sb = new StringBuilder(str.trim());
        StringBuilder result = new StringBuilder();
        String for_help;

        NewStringAtFile = true;
        for (int i = 0; i < sb.length(); i++) {
            if (NewStringAtFile) {
                if ((sb.length() == 1) && (sb.charAt(i) == '}')) {
                    this.tab--;
                    result.append(getTab());
                    result.append(sb.charAt(i));
                } else {
                    result.append(getTab());
                    if (NewString) {
                        String tmp;
                        tmp = sb.substring(i, sb.length());
                        sb = new StringBuilder(tmp.trim());
                        i = 0;
                        result.append(getTab());
                    }
                    NewString = false;
                    char symbol = sb.charAt(i);
                    for_help = witchSymbol(symbol, i);
                    if (for_help.equals("Down!Brace")) {
                        result.delete(result.length() - indentSize, result.length());
                        result.append(symbol);
                    } else if (for_help.equals("Down!Brace!")) {
                        result.append(symbol + "\n");
                    } else if (for_help.equals("Down!Brace!N")) {
                        result.delete(result.length() - indentSize, result.length());
                        result.append(symbol + "\n");
                    } else {

                        result.append(for_help);
                    }
                }
                NewStringAtFile = false;
            } else {
                if (NewString) {
                    String tmp;
                    tmp = sb.substring(i, sb.length());
                    sb = new StringBuilder(tmp.trim());
                    i = 0;
                    result.append(getTab());
                }
                NewString = false;
                char symbol = sb.charAt(i);
                for_help = witchSymbol(symbol, i);
                if (for_help.equals("Down!Brace")) {
                    result.delete(result.length() - indentSize, result.length());
                    result.append(symbol);
                } else if (for_help.equals("Down!Brace!")) {
                    result.append(symbol + "\n");
                } else if (for_help.equals("Down!Brace!N")) {
                    result.delete(result.length() - indentSize, result.length());
                    result.append(symbol + "\n");
                } else {

                    result.append(for_help);
                }

            }

        }
        return result.toString();
    }

}
