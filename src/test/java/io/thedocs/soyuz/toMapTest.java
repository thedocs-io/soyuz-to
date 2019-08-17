package io.thedocs.soyuz;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class toMapTest {

    @Test
    public void shouldCreateEmptyMap() {
        Map<String, Integer> a = new HashMap<>();
        Map<String, Integer> b = to.map();

        assertEquals(a, b);
    }

    @Test
    public void shouldCreateSetWithSingleValue() {
        Map<String, Integer> a = ImmutableMap.of("a", 1);
        Map<String, Integer> b = to.map("a", 1);

        assertEquals(a, b);
    }

    @Test
    public void shouldCreateSetWithMultipleValues() {
        Map<String, Integer> a = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> b = to.map("a", 1, "b", 2, "c", 3);

        assertEquals(a, b);
    }

    @Test
    public void shouldApplyMapperToMapAndUserReturnedMapToCreateNewOne() {
        Map<String, Integer> source = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> expected = to.map("a", 2, "b", 4, "c", 6);

        assertEquals(expected, to.map(source, e -> to.map(e.getKey(), e.getValue() * 2)));
    }

    @Test
    public void shouldApplyMapperToMapAndTransformValues() {
        Map<String, Integer> source = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> expected = to.map("a", 2, "b", 4, "c", 6);

        assertEquals(expected, to.map(source, (k, v) -> v * 2));
    }

    @Test
    public void shouldTransformIterableToMapWithKeyFunction() {
        Iterable<Integer> iterable = to.list(1, 2, 3);
        Map<String, Integer> expected = to.map("1", 1, "2", 2, "3", 3);

        assertEquals(expected, to.map(iterable, to::s));
    }

    @Test
    public void shouldTransformIterableToMapWithKeyValueFunctions() {
        List<Integer> iterable = to.list(1, 2, 3);
        Map<String, Integer> expected = to.map("1", 2, "2", 4, "3", 6);

        assertEquals(expected, to.map(iterable, v -> to.s(v), v -> v * 2));
    }
}
