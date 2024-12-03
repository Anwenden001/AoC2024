package de.max.tag3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tag3 {
    public static void main(String[] args) {
        int sum = 0;
        int sum2 = 0;
        try(Scanner scanner = new Scanner(new File("max/src/main/java/de/max/tag3/input.txt"))) {
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                String line = scanner.next();
            sum += checkMut(line,"mul\\( *[0-9]+, *[0-9]+\\)", true);
            sum2 += checkMut(line,"don't\\(\\)|do\\(\\)|mul\\( *[0-9]+, *[0-9]+\\)", false);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sum);
        System.out.println(sum2);
    }

    public static String last = "o()";
    public static int checkMut(String string,String filter, boolean skip){
        Pattern pattern = Pattern.compile(filter);
        List<String> list = getArray(pattern, string);
        System.out.println(list.toString());
        int sum = 0;

        for(String s : list){
            System.out.println(s);
            if(Objects.equals(s, "o()") || Objects.equals(s, "on't()")){
                last = s;
                continue;
            }
            if (last.equals("o()") | skip){
            String[] split = s.split(",");
                if(split.length == 2){
                    System.out.println("test");
                  sum += mul(Integer.parseInt(split[0].replace("ul(", "")), Integer.parseInt(split[1].replace(")","")));
             }
            }
        }
        return sum;
    }



    public static List<String> getArray(Pattern tagMatcher, String str) {
        Matcher m = tagMatcher.matcher(str);
        List<String> l = new ArrayList<String>();
        while(m.find()) {
            String s = m.group(); //will give you "#computer"
            s = s.substring(1); // will give you just "computer"
            l.add(s);
        }
        return l;
    }
    public static int mul(int a, int b) {
        return a * b;
    }
}
