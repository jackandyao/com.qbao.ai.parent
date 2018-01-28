package com.qbao.ai.parse.utils;


public class Pair<K,V> {

    private K key;
    private V val;

    public Pair() {
    }
    
    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair p = (Pair) o;

       if(p.key!=null && p.val!=null && p.key.equals(this.key) && p.val.equals(this.val))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return key != null && val!=null  ? key.hashCode() + val.hashCode() : 0;
    }
     
    public K setKey(K key) {
        return this.key = key;
    }

    public V setValue(V val) {
        return this.val = val;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.val;
    }
    
    public String keyToString() {
        return this.key.toString();
    }

    public String valToString() {
        return this.val.toString();
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }
}
