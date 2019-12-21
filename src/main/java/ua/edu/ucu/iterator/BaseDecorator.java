package ua.edu.ucu.iterator;

class BaseDecorator extends StreamIterator {

    protected StreamIterator streamIterator;

    public BaseDecorator(StreamIterator inputStreamIterator) {
        streamIterator = inputStreamIterator;
    }

    @Override
    public boolean hasNext() {
        return streamIterator.hasNext();
    }

    @Override
    public Integer next() {
        return streamIterator.next();
    }

    public StreamIterator iteratorCopy() {
        return new BaseDecorator(streamIterator.iteratorCopy());
    }

}
