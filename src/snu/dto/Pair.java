/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.dto;

/**
 * Classe utilizada para guardar par de valores
 *
 * @author Washington Luis
 * @param <First>
 * @param <Second>
 */
public class Pair<First, Second> implements Comparable<Pair<First, Second>> {

    private First first;
    private Second second;

    public Pair() {
    }

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public void setSecond(Second second) {
        this.second = second;
    }

    public First getFirst() {
        return first;
    }

    public Second getSecond() {
        return second;
    }

    public void set(First first, Second second) {
        setFirst(first);
        setSecond(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair pair = (Pair) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) {
            return false;
        }

        return !(second != null ? !second.equals(pair.second) : pair.second != null);
    }

    @Override
    public int compareTo(Pair<First, Second> p1) {
        if (null != p1) {
            if ((Double) (p1.getSecond()) == (Double) (this.getSecond())) {
                return 0;
            } else if ((Double) (p1.getSecond()) > (Double) (this.getSecond())) {
                return 1;
            } else if ((Double) (p1.getSecond()) < (Double) (this.getSecond())) {
                return -1;
            }
        }
        return (-1);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
