package Homework.HW_Generiks;

import java.util.Objects;

public class HW_Generics_Pair {
        public static void main(String[] args) {
           // Pair<Integer, String> pair = new Pair<>(1,"2");

            Pair<Integer, String> pair = Pair.of(398, "Hello");
            System.out.println(pair.getFirst());
            System.out.println(pair.getSecond());
            System.out.println(pair.equals(Pair.of(398, "Hello")));
            System.out.println(pair.equals(Pair.of(456, "World")));
        }
    }

    class Pair<T,U> {
        private T first;
        private U second;

        private Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public static <T, U> Pair<T, U> of(T first, U second) {
            return new Pair<>(first, second);
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }