package io.thedocs.soyuz;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class toTest {

    @Test
    public void testListToArray() {
        List<String> source = to.list("hello", "world");
        String[] expected = new String[]{"hello", "world"};

        assertArrayEquals(expected, to.arr(source, String.class));
    }

    @Test
    public void testListToMap() {
        Car c1 = new Car(1, "lada");
        Car c2 = new Car(2, "bmw");

        List<Car> cars = to.list(c1, c2);
        Map<Integer, Car> carsByIds = to.map(c1.getId(), c1, c2.getId(), c2);

        assertEquals(carsByIds, to.map(cars, Car::getId));
    }

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    private static class Car {
        private int id;
        private String title;
    }
}
