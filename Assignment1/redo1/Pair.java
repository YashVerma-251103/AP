package Assignment1.redo1;

import java.util.Objects;

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;
    
    public Pair(){
        this.first = null;
        this.second = null;
    }

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    
    public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
        return new Pair<>(first, second);
    }

    public T1 getFirst() {
        return first;
    }
    
    public T2 getSecond() {
        return second;
    }
    
    public void setFirst(T1 first) {
        this.first = first;
    }
    
    public void setSecond(T2 second) {
        this.second = second;
    }
    
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
               Objects.equals(second, pair.second);
    }
    
}
