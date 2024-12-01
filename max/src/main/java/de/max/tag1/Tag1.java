package de.max.tag1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Tag1 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new File("max/src/main/java/de/max/tag1/input.txt"))) {
            PriorityQueue<Integer> list = new PriorityQueue <>();
            PriorityQueue<Integer> list2 = new PriorityQueue <>();
            HashMap<Integer, Temp> map = new HashMap<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split("\\s+");
                if(map.containsKey(Integer.valueOf(words[0]))) {
                    ++(map.get(Integer.valueOf(words[0])).left);
                }else {
                    map.put(Integer.valueOf(words[0]), new Temp());
                    ++(map.get(Integer.valueOf(words[0])).left);
                }

                if(map.containsKey(Integer.valueOf(words[1]))) {
                    ++(map.get(Integer.valueOf(words[1])).right);
                }else {
                    map.put(Integer.valueOf(words[1]), new Temp());
                    ++(map.get(Integer.valueOf(words[1])).right);

                }

                list2.add(Integer.valueOf(words[0]));
                list.add(Integer.valueOf(words[1]));
            }
            var  iterator1  = list.iterator();
            var  iterator2  = list2.iterator();
            int sum = 0;
            AtomicInteger sum2 = new AtomicInteger();
            while(iterator1.hasNext()) {

                sum = sum + Math.abs(iterator1.next() - iterator2.next());
            }
            map.forEach((i,o) -> {
                for (int j = 0; j < o.left; j++) {
                    sum2.addAndGet(i * o.right);
                }
            });
            System.out.println(sum);
            System.out.println(sum2.get());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
