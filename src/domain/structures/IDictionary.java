package domain.structures;

import java.util.Enumeration;

public interface IDictionary<k, V> {
        public int size();
        public boolean isEmpty();

        public boolean isDefined(k key);
        public Enumeration<k> keys();
        public Enumeration<V> elements();
        public V get(k key);
        public V put(k key, V value) ;
        public V remove(k key);

}
