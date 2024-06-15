package packet.java11.example;

import java.util.Comparator;

interface SortSupport {
    void setSwapper(Swapper swap);
    void setComparator(Comparator compare);
}
