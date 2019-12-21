package ua.edu.ucu.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StreamIterator implements Iterator<Integer> {

    private int[] elements;
    private int idx;

    public StreamIterator(int ...inputElements) {

        elements = Arrays.copyOf(inputElements, inputElements.length);
        idx = 0;

    }

    @Override
    public boolean hasNext() {return elements.length >= idx + 1;}

    @Override
    public Integer next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements[idx++];

    }

    public StreamIterator iteratorCopy(){

        return new StreamIterator(Arrays.copyOf(elements, elements.length));

    }
}
