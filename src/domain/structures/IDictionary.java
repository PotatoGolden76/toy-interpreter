package domain.structures;

import java.util.Enumeration;

public interface IDictionary<k, V> {
        int size();
        boolean isEmpty();

        boolean isDefined(k key);
        Enumeration<k> keys();
        Enumeration<V> elements();
        V get(k key);
        void put(k key, V value) ;
        V remove(k key);

}
