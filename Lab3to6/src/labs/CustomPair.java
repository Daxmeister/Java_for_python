package labs;

public class CustomPair<K, V> {
    private final K key;
    private final V value;

    public CustomPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomPair<?, ?> other = (CustomPair<?, ?>) obj;
        return key.equals(other.key) && value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return 31 * key.hashCode() + value.hashCode();
    }
}
