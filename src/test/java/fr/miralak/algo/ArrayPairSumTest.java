package fr.miralak.algo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ArrayPairSumTest {

    private ArrayPairSum arrayPairSum = new ArrayPairSum();

    @Test
    public void shouldNotSortArrayIfLengthIsOne() {
        // Given
        int[] array = {1};

        // When
        int[] result = arrayPairSum.ascSortTable(array);

        // Then
        Assert.assertArrayEquals(new int[]{1}, result);
    }

    /**
     * {45, 12, 9, 6, 74, 12, 33, 5, 53, 44, 1}
     */
    @Test
    public void shouldSortArrayWithMultipleElements() {
        // Given
        int[] array = {45, 12, 9, 6, 74, 12, 33, 5, 53, 44, 1};

        // When
        int[] result = arrayPairSum.ascSortTable(array);

        // Then
        Assert.assertArrayEquals(new int[]{1, 5, 6, 9, 12, 12, 33, 44, 45, 53, 74}, result);
    }

    @Test
    public void shouldNotSortEmptyArray() {
        // Given
        int[] array = {};

        // When
        int[] result = arrayPairSum.ascSortTable(array);

        // Then
        Assert.assertArrayEquals(new int[]{}, result);
    }

    @Test
    public void shouldReturnNumberOfPairThatHasSum() {
        // Given
        int[] array = {10, 2, 9, 6, 14, 12, 30, 5, 8, 11, 1};
        int sum = 21;

        // When
        int result = arrayPairSum.pairSum(array, sum);

        // Then
        // possible pairs are {10,11} and {9,12}
        Assert.assertEquals(2, result);
    }

}