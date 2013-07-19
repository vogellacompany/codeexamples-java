package de.vogella.algorithms.sort.standardjava;
 
import java.util.Comparator;
 
public class MyIntComparable implements Comparator<Integer>{
 
    @Override
    public int compare(Integer o1, Integer o2) {
        return (o1>o2 ? -1 : (o1==o2 ? 0 : 1));
    }
}