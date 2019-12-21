package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterator.FilterDecorator;
import ua.edu.ucu.iterator.FlatMapDecorator;
import ua.edu.ucu.iterator.MapDecorator;
import ua.edu.ucu.iterator.StreamIterator;

public class AsIntStream implements IntStream {

    private StreamIterator streamIterator;

    private AsIntStream(StreamIterator inputIterator) {
        streamIterator = inputIterator;
    }

    public static IntStream of(int... values) {

        StreamIterator itr = new StreamIterator(values);
        return new AsIntStream(itr);

    }

    // terminal methods
    @Override
    public Double average() { return ((double)sum() / count()); }

    @Override
    public Integer max() {
        isEmpty();
        return reduce(streamIterator.next(),
                (max, x) -> max = Math.max(x, max));
    }

    @Override
    public Integer min() {
        isEmpty();
        return reduce(streamIterator.next(),
                (min, x) -> min = Math.min(x, min)); }

    @Override
    public long count() { return toArray().length; }

    @Override
    public Integer sum() {
        isEmpty();
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public void forEach(IntConsumer action) {

        for (int number: toArray()){
            action.accept(number);
        }

    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {

        for (int number: toArray()){
            identity = op.apply(identity, number);
        }
        return identity;

    }

    @Override
    public int[] toArray() {

        int[] streamArray = new int[100];
        int idx = 0;

        while (streamIterator.hasNext()) {
            streamArray[idx] = streamIterator.next();
            idx += 1;
        }
        int[] resStreamArray = new int[idx];
        System.arraycopy(streamArray, 0, resStreamArray, 0, idx);
        streamIterator = new StreamIterator(resStreamArray);
        return resStreamArray;

    }

    // transitive methods
    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(
                new MapDecorator(streamIterator.iteratorCopy(), mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(
                new FlatMapDecorator(streamIterator.iteratorCopy(), func));
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(
                new FilterDecorator(streamIterator.iteratorCopy(), predicate));
    }

    public void isEmpty(){
        if (!streamIterator.hasNext()) {
            throw new IllegalArgumentException();
        }
    }
}
