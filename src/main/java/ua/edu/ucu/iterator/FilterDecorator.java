package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntPredicate;

public class FilterDecorator extends BaseDecorator {

    private IntPredicate predicate;
    private Integer currInt;

    public FilterDecorator(StreamIterator inputStreamIterator,
                           IntPredicate inputPredicate) {
        super(inputStreamIterator);
        predicate = inputPredicate;
        updateCounts();
    }

    @Override
    public boolean hasNext() {
        return currInt != null;
    }

    private void updateCounts(){
        while (streamIterator.hasNext()){
            currInt = streamIterator.next();
            if (predicate.test(currInt)) {
                return;
            }
        }
        currInt = null;
    }

    @Override
    public Integer next() {

        Integer prevInt = currInt;
        updateCounts();
        return prevInt;

    }

    public StreamIterator iteratorCopy(){
        return new FilterDecorator(super.iteratorCopy(), predicate);
    }

}
