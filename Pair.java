
public class Pair<T,I> {
    T key;
    I value;
    public Pair(T key, I value) {
        this.key = key;
        this.value = value;
    }
    public String toString() {
        return key + " : " + value;
    }

    public T getKey() {
        return key;
    }
    public I getValue() {
        return value;
    }
}
