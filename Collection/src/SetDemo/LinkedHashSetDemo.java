package SetDemo;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<Integer> set=new LinkedHashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(30);

        System.out.println(set);

    }
}
