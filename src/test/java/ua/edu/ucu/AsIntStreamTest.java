package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;
import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testOf() {
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = intStream.toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testMax() {
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        long expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testForEach() {

        String expResult = "-10123";
        StringBuilder result = new StringBuilder();
        intStream.forEach(x -> result.append(x));
        assertEquals(expResult, result.toString());
    }

    @Test
    public void testReduce() {
        int expResult = 15;
        int result = intStream.reduce(0, (cnt, x) -> cnt += x*x);
        assertEquals(expResult, result);
    }

    @Test
    public void testToArray() {
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = intStream.toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMap() {
        int[] expResult = {-2, 0, 2, 4, 6};
        int[] result = intStream.map(x -> x * 2).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        int[] expResult = {-1, -2, 0, 0, 1, 2, 2, 4, 3, 6};
        int[] result = intStream.flatMap(x -> AsIntStream.of(x, x * 2)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFilter() {
        int[] expResult = {0, 2};
        int[] result = intStream.filter(x -> x % 2 == 0).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        IntStream emptyStream = AsIntStream.of();
        emptyStream.sum();
    }

    @Test
    public void testMultipleNonTerminals(){
        int[] expResult = {1, 4, 9};
        int[] result = intStream.filter(x -> x > 0).map(x -> x * x).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMultipleTerminals(){
        IntStream resultStream = intStream.filter(x -> x > 0).map(x -> x * x);
        int expResult = 14;
        int result = resultStream.sum();
        assertEquals(expResult, result);
        expResult = 3;
        long lresult = resultStream.count();
        assertEquals(expResult, lresult);
    }

    @Test
    public void testInitialStreamAfterNonTerminal(){
        IntStream resultStream = intStream.filter(x -> x > 0).map(x -> x * x);
        int expResult = 14;
        int result = resultStream.reduce(0, (sum, x) -> sum += x);;
        assertEquals(expResult, result);
        expResult = 5;
        result = intStream.reduce(0, (sum, x) -> sum += x);;
        assertEquals(expResult, result);
    }

//    @Test
//    public void test(){
//        int[] expResult = ;
//        int[] result = ;
//        assertArrayEquals(expResult, result);
//    }
//
//    @Test
//    public void test(){
//        int[] expResult = ;
//        int[] result = ;
//        assertArrayEquals(expResult, result);
//    }
}
