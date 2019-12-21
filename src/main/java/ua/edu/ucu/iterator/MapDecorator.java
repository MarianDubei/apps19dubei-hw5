package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntUnaryOperator;

public class MapDecorator extends BaseDecorator {

    private IntUnaryOperator mapper;

    public MapDecorator(StreamIterator inputStreamIterator,
                        IntUnaryOperator inputMapper) {
        super(inputStreamIterator);
        mapper = inputMapper;
    }

    @Override
    public boolean hasNext() {
        return streamIterator.hasNext();
    }

    @Override
    public Integer next() {
        return mapper.apply(streamIterator.next());
    }

    public StreamIterator iteratorCopy() {
        return new MapDecorator(super.iteratorCopy(), mapper);
    }

}