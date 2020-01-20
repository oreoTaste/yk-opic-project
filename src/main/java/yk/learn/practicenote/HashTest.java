package yk.learn.practicenote;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class HashTest {
  public static void main(String[] args) {

    // HashSet : add() & toArray()
    HashSet set = new HashSet();
    set.add("FirstValue");
    set.add("SecondValue");
    set.add("ThirdValue");

    System.out.println("<<1. HASHSET.ITERATOR>>");
    Iterator it = set.iterator();
    while (it.hasNext())
      System.out.println(it.next());

    System.out.println();

    System.out.println("<<2. HASHSET.TOARRAY>>");
    for (Object obj : set.toArray())
      System.out.println(obj);

    System.out.println("======================================");

    // HashMap : put() & keySet() & entrySet()
    HashMap hashmap = new HashMap();
    // Hashtable : put() & keySet() & entrySet()
    Hashtable hashtable = new Hashtable();

    hashmap.put("1", "FirstData");
    hashmap.put("2", "SecondData");
    hashmap.put("3", "ThirdData");
    hashmap.put(null, null);
    // hashtable.put(null, null);

    System.out.println("<<1. HASHMAP.KEYSET.ITERATOR == HASHTABLE>>");
    Iterator i = hashmap.keySet().iterator();
    while (i.hasNext()) {
      Object key = i.next();
      System.out.printf("%s : %s\n", key, hashmap.get(key));
    }

    System.out.println();
    System.out.println("<<2. HASHMAP.KEYSET == HASHTABLE>>");
    Set s1 = hashmap.keySet();
    for (Object v : s1)
      System.out.printf("%s : %s\n", v, hashmap.get(v));

    System.out.println();
    System.out.println("<<3. HASHMAP.VALUES == HASHTABLE>>");
    for (Object value : hashmap.values())
      System.out.println(value);

    System.out.println();
    System.out.println("<<4. HASHMAP.ENTRYSET == HASHTABLE>>");
    Set entrySet = hashmap.entrySet();
    for (Object obj : entrySet) {
      Entry entry = (Entry) obj;
      System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
    }

    // HashMap, Hashtable (which is a set) doesn't allow duplicated values.
    // Duplicated values are classified by equals() and hashCode() methods
  }
}
