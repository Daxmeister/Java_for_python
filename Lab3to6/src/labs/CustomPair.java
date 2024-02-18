package labs;

import java.util.Objects;

public class CustomPair {
    private int number1;
    private int number2;

    public CustomPair(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int key1() {
        return number1;
    }

    public int key2() {
        return number2;
    }

    // Optional: Override equals, hashCode, and toString methods

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CustomPair CustomPair = (CustomPair) obj;
        return number1 == CustomPair.number1 && number2 == CustomPair.number2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number1, number2);
    }

    @Override
    public String toString() {
        return "CustomPair{" + "number1=" + number1 + ", number2=" + number2 + '}';
    }
}