package co.cdmunoz.themoviedb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import co.cdmunoz.themoviedb.data.source.MoviesDBDataConverter;

import static org.junit.Assert.*;

public class ConverterUnitTest {

    @Test
    public void fromStringToList_isCorrect() {
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(12);
        expectedList.add(5);
        expectedList.add(21);

        MoviesDBDataConverter moviesDBDataConverter = new MoviesDBDataConverter();
        List<Integer> actualList = moviesDBDataConverter.fromStringToList("12, 5, 21");

        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0), actualList.get(0));
    }

    @Test
    public void fromStringToList_notCorrect() {
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(5);
        expectedList.add(21);

        MoviesDBDataConverter moviesDBDataConverter = new MoviesDBDataConverter();
        List<Integer> actualList = moviesDBDataConverter.fromStringToList("12, 5, 21");

        assertNotEquals(expectedList.size(), actualList.size());
        assertNotEquals(expectedList.get(0), actualList.get(0));
    }
}