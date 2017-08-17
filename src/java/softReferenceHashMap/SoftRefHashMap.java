package softReferenceHashMap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftRefHashMap<K,V> {
  private HashMap<K, SoftReference<V>> map = new HashMap<>();
  
  public V put(K key, V value) {
     SoftReference<V> oldRef = map.put(key, new SoftReference<V>(value));

     if (oldRef == null) {
         return null;
     }

     V oldValue = oldRef.get();
     oldRef.clear();

     return oldValue;

  }

  public V get(K key) {
     SoftReference<V> ref = map.get(key);
     if (ref == null) {
         return null;
     }

     return ref.get();
  }

  public V remove(K key) {
     SoftReference<V> ref = map.get(key);
     if (ref == null) {
         return null;
     }

     V value = ref.get();
     ref.clear();

     return value;
  }

}
