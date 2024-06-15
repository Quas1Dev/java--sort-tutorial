package packt.java189fundamentals.ch03.main.bubble.simple;

import org.junit.Test;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.Assert;
import packet.java11.example.BubbleSort;
import packet.java11.example.Sortable;
import packet.java11.example.Swapper;

public class BubbleSortTest {

    @Test
    public void canSortStrings() {
        // Do everything but implement the Sortable interface *1
        var actualNames = new ArrayList(Arrays.asList("Wilson","Abraham", "Dagobert",
                "Johnson", "Wilkinson"));

        // Final sortable instance *2
        var names = new Sortable() {
            @Override
            public Object get(int i) {
                return actualNames.get(i);
            }

            @Override
            public int size() {
                return actualNames.size();
            }
        };

        // Implamentation of the Swapper class for the ArrayList
        class SwapActualNamesArrayElements implements Swapper {

            @Override
            public void swap(int i, int j) {
                final Object tmp = actualNames.get(i);
                actualNames.set(i, actualNames.get(j));
                actualNames.add(j, tmp);
            }

        }

        // Create an instance of a class that implements a Comparator
        // interface.
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                final String f = (String) o1;
                final String s = (String) o2;

                return f.compareTo(s);
            }

        };

        var sort = new BubbleSort();
        sort.setComparator(comparator);
        sort.setSwapper(new SwapActualNamesArrayElements());
        sort.sort(names);

        Assert.assertEquals(List.of(
                "Abraham", "Dagobert",
                "Johnson", "Wilkinson", "Wilson"
        ), actualNames);
    }
}

/*
1 - The object that actually "stores" the data and provides the
functionalities that allows us to access an item through its
index and get the size of the collection. The only thing it does not
is it doesn't implement the Sortable interface.

2 - This variable must not be modified; instead, it must be final
The final keyword is optional since Java 1.8.
This instantiate a collection that implements the Sortable
interface.

*/
