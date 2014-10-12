package codeformatter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CodeFormatter {

    private StringBuilder sb = null;
    private boolean hasParenthesis = false;
    private boolean NewString = false;
    private boolean NewStringAtFile = false;
    private int tab = 0;
    private final int countSpaces = 4;

    public boolean nextSymbol(String str, char symbol) {
        for (char c : str.toCharArray()) {
            if (c == ' ') {
            } else {
                if (c == symbol) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean findParenthesis(String s) {
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

    public String witchSymbol(char c, int index) {
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
                res.append(c);
                break;
            }
            case ')': {
                if (hasParenthesis) {
                    hasParenthesis = false;
                }
                res.append(c);
                break;
            }

            default: {
                res.append(c);
            }
        }
        return res.toString();
    }

    public List<String> readFile (File file) throws IOException {
        List<String> list = new ArrayList<>();
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            list.add(scan.nextLine());
        }
        scan.close();
        return list;
    }

    public String getTab () {
        char[] tabString = new char[this.tab * countSpaces];
        Arrays.fill(tabString, ' ');
        return new String(tabString);
    }

    public String modifiedString (String str) {

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
                        result.delete(result.length() - 4, result.length());
                        result.append(symbol);
                    } else if (for_help.equals("Down!Brace!")) {
                        result.append(symbol + "\n");
                    } else if (for_help.equals("Down!Brace!N")) {
                        result.delete(result.length() - 4, result.length());
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
                    result.delete(result.length() - 4, result.length());
                    result.append(symbol);
                } else if (for_help.equals("Down!Brace!")) {
                    result.append(symbol + "\n");
                } else if (for_help.equals("Down!Brace!N")) {
                    result.delete(result.length() - 4, result.length());
                    result.append(symbol + "\n");
                } else {
                    
                    result.append(for_help);
                }
                
            }
            
        }
        return result.toString();
    }

    public void writeFile (List<String> list, String[] args) {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
            for (String s : list) {
                String[] sf = s.split("\n");
                for (String sw : sf) {
                    out.print(sw);
                    out.println();
                }
            }
            out.println();
        } catch (IOException ex) {
            System.out.println("Something wrong with a writing to file" + args[1]);
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) {

        CodeFormatter cf = new CodeFormatter();
        File output = new File(args[1]);

        if(args.length != 2) {
            System.err.println("The program uses:");
            System.err.println("CodeFormatter input_file output_file");
            System.exit(1);
        }

        List<String> list = new ArrayList<>();

        try {
            list = cf.readFile(new File(args[0]));
        } catch (IOException e) {
            System.out.println("Something wrong in file:" + args[0]);
        }

        List<String> readyCode = new ArrayList<>();
        for (String str : list) {
            readyCode.add(cf.modifiedString(str));
        }

        cf.writeFile(readyCode, args);
    }
}
