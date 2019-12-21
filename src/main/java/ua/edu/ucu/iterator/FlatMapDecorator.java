package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntToIntStreamFunction;

public class FlatMapDecorator extends BaseDecorator {

    private IntToIntStreamFunction func;
    private int idx;
    private int[] subStream;

    public FlatMapDecorator(StreamIterator inputStreamIterator,
                            IntToIntStreamFunction inputFunc) {
        super(inputStreamIterator);
        func = inputFunc;
        idx = 0;
    }

    @Override
    public boolean hasNext() {
        return streamIterator.hasNext() || idx != 0;
    }

    @Override
    public Integer next() {
        if (idx == 0){
            subStream = func.applyAsIntStream(streamIterator.next()).toArray();
        }
        int subStreamElement = subStream[idx++];
        if (idx == subStream.length) {
            idx = 0;
        }
        return subStreamElement;
    }

    public StreamIterator iteratorCopy(){
        return new FlatMapDecorator(super.iteratorCopy(), func);
    }
}
