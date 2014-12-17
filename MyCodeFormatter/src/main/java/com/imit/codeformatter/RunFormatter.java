package com.imit.codeformatter;

import java.util.Arrays;

/**
 * class which leads code to a correct view
 */
public class RunFormatter {

    private StringBuilder stringBuild = null;
    private boolean hasParenthesis = false;
    private boolean newString = false;
    private boolean newStringAtFile = false;
    private int tab = 0;
    private final int indentSize = 4;
    private final char symbolIndentation = ' ';

    /**
     * next symbol in string
     * @param str - this line
     * @param symbol - character which looking
     * @return true if str contains symbol
     */
    public boolean nextSymbol(final String str, final char symbol) {
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                break;
            } else if (c == symbol) {
                return true;
            } else if ((c != ' ') && (c != symbol)) {
                return false;
            }
        }
        return false;
    }

    /**
     * find parenthesis in string
     * @param str - this string
     * @return true if str has parenthesis
     */
    public boolean findParenthesis(final String str) {
        for (int i = str.length() - 1; i > 0; i--) {

            if ((str.charAt(i) != ' ') && (str.charAt(i) == 'r')) {
                return true;
            } else if ((str.charAt(i) != ' ') && (str.charAt(i) != 'r')) {
                return false;
            }
        }
        return false;
    }

    /**
     * whitch symbol
     * @param symbol - this string
     * @return true if str has parenthesis
     */
    public String witchSymbol(final char symbol, final int index) {
        StringBuilder res = new StringBuilder();
        switch (symbol) {

            case '{':
                if ((index != stringBuild.length() - 1) && (stringBuild.charAt(index + 1) == '}')) {
                    res.append(symbol);
                    break;
                } else {
                    if ((index > 1) && (stringBuild.charAt(index - 1) != ' ')) {
                        res.append(' ');
                    }
                    res.append(symbol);
                    this.tab++;
                    if (stringBuild.length() - 1 != index) {
                        res.append('\n');
                        newString = true;
                        break;
                    }
                    break;
                }

            case ';':
                if (hasParenthesis) {
                    res.append(symbol);
                    if (stringBuild.charAt(index + 1) != ' ') {
                        res.append(' ');
                    }
                    break;
                }
                if (index != (stringBuild.length() - 1)) {
                    if (nextSymbol(stringBuild.substring(index + 1), '}')) {
                        res.append(symbol).append('\n');
                        newString = true;
                    } else {
                        res.append(symbol).append('\n');
                        newString = true;
                    }
                    break;
                } else {
                    res.append(symbol);
                    break;
                }

            case '}':
                if ((index > 1) && (stringBuild.charAt(index - 1) == '{')) {
                    res.append(symbol);
                    if (stringBuild.length() - 1 != index) {
                        res.delete(0, res.length());
                        res.append("Down!Brace!");
                        newString = true;
                        break;
                    }
                    break;
                } else {
                    this.tab--;
                    res.append("Down!Brace");
                    if (stringBuild.length() - 1 != index) {
                        res.delete(0, res.length());
                        res.append("Down!Brace!N");
                        newString = true;
                        break;
                    }
                }
                break;

            case '(':
                hasParenthesis = findParenthesis(stringBuild.substring(0, index));
                if (index >= 1) {
                    if (stringBuild.charAt(index - 1) != ' ') {
                        res.append(' ');
                    }
                }
                res.append(symbol);
                break;

            case ')':
                if (hasParenthesis) {
                    hasParenthesis = false;
                }
                res.append(symbol);
                if (stringBuild.length() - 1 > index) {
                    if ((stringBuild.charAt(index + 1) != ' ') && (stringBuild.charAt(index + 1) != ';') &&
                            (stringBuild.charAt(index + 1) != '{')) {
                        res.append(' ');
                    }
                }
                break;

            case '+':
                if (index > 0) {
                    if ((stringBuild.charAt(index - 1) != ' ') && (stringBuild.charAt(index + 1) != '+')) {
                        if (stringBuild.charAt(index - 1) != '+') {
                            res.append(' ');
                        }
                    }
                }
                res.append(symbol);
                if (stringBuild.length() - 1 > index) {
                    if ((stringBuild.charAt(index + 1) != '=') && (stringBuild.charAt(index + 1) != ' ') &&
                            (stringBuild.charAt(index + 1) != '+')) {
                        res.append(' ');
                    }
                }
                break;

            case '-':
                if (index > 0) {
                    if ((stringBuild.charAt(index - 1) != ' ') && (stringBuild.charAt(index + 1) != '-')) {
                        if (stringBuild.charAt(index - 1) != '-') {
                            res.append(' ');
                        }
                    }
                }
                res.append(symbol);
                if (stringBuild.length() - 1 > index) {
                    if ((stringBuild.charAt(index + 1) != '=') && (stringBuild.charAt(index + 1) != ' ') &&
                            (stringBuild.charAt(index + 1) != '-')) {
                        res.append(' ');
                    }
                }
                break;

            default:
                res.append(symbol);
        }
        return res.toString();
    }

    /**
     * setting the desired indentation from the edge
     * @return new string with the desired offset
     */
    public String getTab() {
        char[] tabString = new char[this.tab * indentSize];
        Arrays.fill(tabString, symbolIndentation);
        return new String(tabString);
    }

    /**
     * format string
     * @param str - this string
     * @return modified string
     */
    public String modifiedString(final String str) {

        if (str.isEmpty() || (str.trim().isEmpty())) {
            return str;
        }

        this.stringBuild = new StringBuilder(str.trim());
        StringBuilder result = new StringBuilder();
        String forHelp;

        newStringAtFile = true;
        for (int i = 0; i < stringBuild.length(); i++) {
            if (newStringAtFile) {
                if ((stringBuild.length() == 1) && (stringBuild.charAt(i) == '}')) {
                    this.tab--;
                    result.append(getTab());
                    result.append(stringBuild.charAt(i));
                } else {
                    result.append(getTab());
                    if (newString) {
                        String tmp;
                        tmp = stringBuild.substring(i, stringBuild.length());
                        stringBuild = new StringBuilder(tmp.trim());
                        i = 0;
                        result.append(getTab());
                    }
                    newString = false;
                    char symbol = stringBuild.charAt(i);
                    forHelp = witchSymbol(symbol, i);
                    if (forHelp.equals("Down!Brace")) {
                        result.delete(result.length() - indentSize, result.length());
                        result.append(symbol);
                    } else if (forHelp.equals("Down!Brace!")) {
                        result.append(symbol + "\n");
                    } else if (forHelp.equals("Down!Brace!N")) {
                        result.delete(result.length() - indentSize, result.length());
                        result.append(symbol + "\n");
                    } else {

                        result.append(forHelp);
                    }
                }
                newStringAtFile = false;
            } else {
                if (newString) {
                    String tmp;
                    tmp = stringBuild.substring(i, stringBuild.length());
                    stringBuild = new StringBuilder(tmp.trim());
                    i = 0;
                    result.append(getTab());
                }
                newString = false;
                char symbol = stringBuild.charAt(i);
                forHelp = witchSymbol(symbol, i);
                if (forHelp.equals("Down!Brace")) {
                    result.delete(result.length() - indentSize, result.length());
                    result.append(symbol);
                } else if (forHelp.equals("Down!Brace!")) {
                    result.append(symbol + "\n");
                } else if (forHelp.equals("Down!Brace!N")) {
                    result.delete(result.length() - indentSize, result.length());
                    result.append(symbol + "\n");
                } else {

                    result.append(forHelp);
                }

            }

        }
        return result.toString();
    }

}
