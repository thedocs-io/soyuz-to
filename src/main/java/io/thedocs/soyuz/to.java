package io.thedocs.soyuz;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 * Created on 30.11.15.
 */
public class to {

    private static final Logger log = LoggerFactory.getLogger(to.class);

    public static Integer Integer(int val) {
        return val;
    }

    public static Integer Integer(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof Integer) {
            return (Integer) val;
        } else {
            return Integer(val.toString(), null);
        }
    }

    public static Integer Integer(String val) {
        return Integer(val, null);
    }

    public static Integer Integer(Object val, Integer defaultValue) {
        return Integer(val, defaultValue, true);
    }

    public static Integer Integer(Object val, Integer defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doIntConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to int", e);
            }
        }

        return defaultValue;
    }

    public static Float Float(float val) {
        return val;
    }

    public static Float Float(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof Float) {
            return (Float) val;
        } else {
            return Float(val.toString(), null);
        }
    }


    public static Float Float(String val) {
        return Float(val, null);
    }


    public static Float Float(Object val, Float defaultValue) {
        return Float(val, defaultValue, true);
    }


    public static Float Float(Object val, Float defaultValue, boolean silent) {
        if (val != null) {
            try {
                if (val instanceof Float) {
                    return (Float) val;
                } else {
                    return doFloatConvert(val);
                }
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to float", e);
            }
        }

        return defaultValue;
    }

    public static Double Double(double val) {
        return val;
    }


    public static Double Double(Object val) {
        return Double(val, null);
    }


    public static Double Double(String val) {
        return Double(val, null);
    }


    public static Double Double(Object val, Double defaultValue) {
        return Double(val, defaultValue, true);
    }


    public static Double Double(Object val, Double defaultValue, boolean silent) {
        if (val != null) {
            try {
                if (val instanceof Double) {
                    return (Double) val;
                } else {
                    return doDoubleConvert(val);
                }
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to double", e);
            }
        }

        return defaultValue;
    }

    public static Long Long(long val) {
        return val;
    }


    public static Long Long(Object val) {
        return Long(val, null);
    }


    public static Long Long(String val) {
        return Long(val, null);
    }


    public static Long Long(Object val, Long defaultValue) {
        return Long(val, defaultValue, true);
    }


    public static Long Long(Object val, Long defaultValue, boolean silent) {
        if (val != null) {
            try {
                if (val instanceof Long) {
                    return (Long) val;
                } else {
                    return doLongConvert(val);
                }
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to long", e);
            }
        }

        return defaultValue;
    }

    public static Boolean Boolean(boolean val) {
        return val;
    }


    public static Boolean Boolean(String val) {
        return Boolean(val, null);
    }


    public static Boolean Boolean(String val, Boolean defaultValue) {
        return Boolean(val, defaultValue, true);
    }


    public static Boolean Boolean(Object val, Boolean defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doBooleanConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to boolean", e);
            }
        }

        return defaultValue;
    }


    public static String String(Object object) {
        return (object == null) ? null : object.toString();
    }


    public static String s(Object object) {
        return to.String(object);
    }

    public static String String(long longPrimitive) {
        return String.valueOf(longPrimitive);
    }

    public static String s(long longPrimitive) {
        return to.String(longPrimitive);
    }

    public static String String(int intPrimitive) {
        return String.valueOf(intPrimitive);
    }

    public static String s(int intPrimitive) {
        return to.String(intPrimitive);
    }

    public static String String(char charPrimitive) {
        return String.valueOf(charPrimitive);
    }

    public static String s(char charPrimitive) {
        return to.String(charPrimitive);
    }

    public static String String(boolean booleanPrimitive) {
        return String.valueOf(booleanPrimitive);
    }

    public static String s(boolean booleanPrimitive) {
        return to.String(booleanPrimitive);
    }

    public static String String(float floatPrimitive) {
        return String.valueOf(floatPrimitive);
    }

    public static String s(float floatPrimitive) {
        return to.String(floatPrimitive);
    }

    public static String String(double doublePrimitive) {
        return String.valueOf(doublePrimitive);
    }

    public static String s(double doublePrimitive) {
        return to.String(doublePrimitive);
    }

    /**
     * Replaces {} from {@code text} with {@code params}
     * e.g. to.s("Hello {}", "World") -&gt; Hello World
     */

    public static String String(String text, Object... params) {
        if (text == null) {
            return null;
        }

        char[] chars = text.toCharArray();
        int paramsSize = params.length;
        int paramsCurrent = 0;

        StringBuilder sb = new StringBuilder();
        char prev = '0';

        for (char c : chars) {
            if (c == '{') {
                prev = c;
            } else {
                boolean replaced = false;

                if (c == '}') {
                    if (prev == '{') {
                        if (paramsSize > paramsCurrent) {
                            sb.append(params[paramsCurrent]);
                            replaced = true;
                        }
                    }
                }

                if (replaced) {
                    paramsCurrent++;
                } else {
                    if (prev == '{') {
                        sb.append(prev);
                    }

                    sb.append(c);
                }

                prev = c;
            }
        }

        return sb.toString();
    }


    public static String s(String text, Object... params) {
        return to.String(text, params);
    }


    public static String String(String text, Collection params) {
        if (text == null) {
            return null;
        }

        return to.String(text, to.arr(params, Object.class));
    }


    public static String s(String text, Collection params) {
        return to.String(text, params);
    }


    public static String String(String text, Iterable params) {
        if (text == null) {
            return null;
        }

        return to.String(text, to.arr(params, Object.class));
    }


    public static String s(String text, Iterable params) {
        return to.String(text, params);
    }

    /**
     * Replaces {PARAM_KEY} from {@code text} with {@code params}
     * e.g. to.s("Hello {planet}", to.map("planet", "Earth")) -&gt; Hello Earth
     */

    public static String String(String text, Map<String, ?> params) {
        if (text == null) {
            return null;
        }

        for (Map.Entry<String, ?> e : params.entrySet()) {
            Object value = e.getValue();

            text = text.replace("{" + e.getKey() + "}", (value == null) ? "null" : value.toString());
        }

        return text;
    }


    public static String s(String text, Map<String, ?> params) {
        return to.String(text, params);
    }

    /**
     * Joins {@code iterable} with {@code separator}
     */

    public static String String(Iterable iterable, String separator) {
        if (iterable == null) {
            return null;
        }

        Iterator iterator = iterable.iterator();
        StringBuilder sb = new StringBuilder();

        if (iterator.hasNext()) {
            sb.append(iterator.next());
            while (iterator.hasNext()) {
                sb.append(separator).append(iterator.next());
            }
        }

        return sb.toString();
    }


    public static String s(Iterable iterable, String separator) {
        return to.String(iterable, separator);
    }

    // ARRAYS

    public static Object[] arr(Object... objects) {
        return objects;
    }

    public static Object[] arr(Iterable objects) {
        return to.arr(objects, Object.class);
    }

    public static <V> V[] arr(Iterable<V> objects, Class<V> clazz) {
        return to.arr(to.list(objects), clazz);
    }

    public static <V> V[] arr(Collection<V> objects, Class<V> clazz) {
        if (objects == null) {
            return (V[]) Array.newInstance(clazz, 0);
        } else {
            return objects.toArray((V[]) Array.newInstance(clazz, objects.size()));
        }
    }

    public static <V> Object[] arr(@Nullable Collection<V> objects, @Nullable Function<V, Object> mapper) {
        return to.arr(objects, Object.class, (mapper != null) ? mapper : r -> (Object) r);
    }

    public static <V, R> R[] arr(@Nullable Collection<V> objects, Class<R> clazz, Function<V, R> mapper) {
        if (objects == null) {
            return to.arr(null, clazz);
        }

        int i = 0;
        int size = objects.size();
        R[] answer = (R[]) Array.newInstance(clazz, size);

        for (V value : objects) {
            answer[i] = mapper.apply(value);
            i++;
        }

        return answer;
    }

    public static String[] arrOfStrings(@Nullable Collection<String> collection) {
        return (collection == null) ? new String[0] : collection.stream().toArray(String[]::new);
    }

    public static Integer[] arrOfIntegers(@Nullable Collection<? extends Number> collection) {
        return (collection == null) ? new Integer[0] : collection.stream().toArray(Integer[]::new);
    }

    public static Long[] arrOfLongs(@Nullable Collection<? extends Number> collection) {
        return (collection == null) ? new Long[0] : collection.stream().toArray(Long[]::new);
    }

    // MAPS

    public static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> map(K k1, V v1) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);
        a.put(k5, v5);

        return a;
    }

    public static Map<String, String> map(String... params) {
        return fillMapWithParams(new HashMap<String, String>(), params);
    }

    public static Map<String, String> map(Map<String, String> source, String... params) {
        return fillMapWithParams(new HashMap<>(source), params);
    }

    public static Map map(Object... params) {
        return fillMapWithParams(new HashMap(), params);
    }

    public static Map map(Map source, Object... params) {
        return fillMapWithParams(new HashMap(source), params);
    }

    public static <K, V, R> Map<K, R> map(Map<K, V> source, Function<Map.Entry<K, V>, Map<K, R>> mapper) {
        if (source == null) {
            return null;
        }

        Map<K, R> answer = new HashMap<>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            Map<K, R> entry = mapper.apply(e);

            if (entry != null) {
                answer.putAll(entry);
            }
        }

        return answer;
    }


    public static <K, V, R> Map<K, R> map(Map<K, V> source, BiFunction<K, V, R> mapper) {
        if (source == null) {
            return null;
        }

        Map<K, R> answer = new HashMap<>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            answer.put(e.getKey(), mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }


    public static <T, K> Map<K, T> map(Iterable<T> source, Function<T, K> keyFunction) {
        return map(source, keyFunction, (s) -> s);
    }


    public static <T, K, V> Map<K, V> map(Iterable<T> source, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        if (source == null) {
            return null;
        }

        Map<K, V> answer = new HashMap<K, V>();

        for (T e : source) {
            answer.put(keyFunction.apply(e), valueFunction.apply(e));
        }

        return answer;
    }

    public static <K, V> Map<K, V> linkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);
        a.put(k5, v5);

        return a;
    }

    public static Map<String, String> linkedHashMap(String... params) {
        return fillMapWithParams(new LinkedHashMap<String, String>(), params);
    }

    public static Map<String, String> linkedHashMap(Map<String, String> source, String... params) {
        return fillMapWithParams(new LinkedHashMap<>(source), params);
    }

    public static Map linkedHashMap(Object... params) {
        return fillMapWithParams(new LinkedHashMap(), params);
    }

    public static Map linkedHashMap(Map source, Object... params) {
        return fillMapWithParams(new LinkedHashMap(source), params);
    }

    public static <K, V, R> Map<K, R> linkedHashMap(Map<K, V> source, Function<Map.Entry<K, V>, Map<K, R>> mapper) {
        if (source == null) {
            return null;
        }

        Map<K, R> answer = new LinkedHashMap<K, R>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            Map<K, R> entry = mapper.apply(e);

            if (entry != null) {
                answer.putAll(entry);
            }
        }

        return answer;
    }


    public static <K, V, R> Map<K, R> linkedHashMap(Map<K, V> source, BiFunction<K, V, R> mapper) {
        if (source == null) {
            return null;
        }

        Map<K, R> answer = new LinkedHashMap<K, R>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            answer.put(e.getKey(), mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }


    public static <T, K> Map<K, T> linkedHashMap(Iterable<T> source, Function<T, K> keyFunction) {
        return linkedHashMap(source, keyFunction, (s) -> s);
    }


    public static <T, K, V> Map<K, V> linkedHashMap(Iterable<T> source, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        if (source == null) {
            return null;
        }

        Map<K, V> answer = new LinkedHashMap<K, V>();

        for (T e : source) {
            answer.put(keyFunction.apply(e), valueFunction.apply(e));
        }

        return answer;
    }

    private static <K, V> Map<K, V> fillMapWithParams(Map map, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Params number should be even");
        }

        for (int i = 0; i < params.length / 2; i++) {
            map.put(params[i * 2], params[i * 2 + 1]);
        }

        return map;
    }

    // STREAM


    public static <T> Stream<T> stream(T[] array) {
        return (array == null) ? null : Arrays.stream(array);
    }

    /**
     * http://stackoverflow.com/a/24511534
     */

    public static <T> Stream<T> stream(final Iterator<T> iterator) {
        if (iterator == null) {
            return null;
        }

        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }


    public static IntStream stream(int[] array) {
        return (array == null) ? null : Arrays.stream(array);
    }


    public static LongStream stream(long[] array) {
        return (array == null) ? null : Arrays.stream(array);
    }


    public static DoubleStream stream(double[] array) {
        return (array == null) ? null : Arrays.stream(array);
    }

    // COLLECTIONS

    public static <V> List<V> list() {
        return new ArrayList<V>();
    }


    public static <T> List<T> list(Iterator<T> iterator) {
        return list(iterator, 10);
    }

    /**
     * see org.apache.commons.collections.IteratorUtils.toList
     */

    public static <T> List<T> list(Iterator<T> iterator, int estimatedSize) {
        if (iterator == null) {
            return null;
        } else if (estimatedSize < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        } else {
            ArrayList<T> list = new ArrayList<T>(estimatedSize);

            while (iterator.hasNext()) {
                list.add(iterator.next());
            }

            return list;
        }
    }


    public static <T> List<T> list(T value) {
        if (value == null) return null;

        List<T> answer = new ArrayList<>();
        answer.add(value);
        return answer;
    }


    public static <T> List<T> list(T... value) {
        if (value == null) return null;

        List<T> answer = new ArrayList<>();
        Collections.addAll(answer, value);
        return answer;
    }


    public static <T> List<T> list(Iterable<T> objects) {
        if (objects == null) {
            return null;
        }

        List<T> answer = new ArrayList<>();

        for (T object : objects) {
            answer.add(object);
        }

        return answer;
    }


    public static <T, R> List<R> list(T[] values, Function<T, R> mapper) {
        if (values == null) return null;

        List<R> answer = new ArrayList<R>(values.length);

        for (T value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }


    public static <V> List<V> list(Collection<V> values) {
        if (values == null) {
            return null;
        } else if (values instanceof List) {
            return (List<V>) values;
        } else {
            List<V> answer = new ArrayList<V>();

            answer.addAll(values);

            return answer;
        }
    }


    public static <T, V> List<T> list(Collection<V> values, Function<V, T> mapper) {
        if (values == null) return null;

        List<T> answer = new ArrayList<T>(values.size());

        for (V value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }


    public static <K, V, R> List<R> list(Map<K, V> map, BiFunction<K, V, R> mapper) {
        if (map == null) return null;

        List<R> answer = new ArrayList<>(map.values().size());

        for (Map.Entry<K, V> e : map.entrySet()) {
            answer.add(mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }


    public static <T> Set<T> set(T value) {
        if (value == null) return null;

        Set<T> answer = new HashSet<T>();
        answer.add(value);
        return answer;
    }


    public static <T> Set<T> set(T... value) {
        if (value == null) return null;

        Set<T> answer = new HashSet<T>();
        Collections.addAll(answer, value);
        return answer;
    }


    public static <V> Set<V> set(Collection<V> values) {
        if (values == null) {
            return null;
        } else if (values instanceof Set) {
            return (Set<V>) values;
        } else {
            Set<V> answer = new HashSet<>();

            answer.addAll(values);

            return answer;
        }
    }


    public static <T> Set<T> set(Iterator<T> iterator) {
        if (iterator == null) {
            return null;
        } else {
            Set<T> set = new HashSet<>();

            while (iterator.hasNext()) {
                set.add(iterator.next());
            }

            return set;
        }
    }


    public static <V> Set<V> set(Iterable<V> values) {
        if (values == null) {
            return null;
        } else if (values instanceof Set) {
            return (Set<V>) values;
        } else {
            Set<V> answer = new HashSet<>();

            for (V value : values) {
                answer.add(value);
            }

            return answer;
        }
    }


    public static <T, V> Set<T> set(Collection<V> values, Function<V, T> mapper) {
        if (values == null) return null;

        Set<T> answer = new HashSet<T>();

        for (V value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }


    public static <K, V, R> Set<R> set(Map<K, V> map, BiFunction<K, V, R> mapper) {
        if (map == null) return null;

        Set<R> answer = new HashSet<>();

        for (Map.Entry<K, V> e : map.entrySet()) {
            answer.add(mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }


    public static <T> SortedSet<T> sortedSet(T value) {
        if (value == null) return null;

        SortedSet<T> answer = new TreeSet<T>();

        answer.add(value);

        return answer;
    }

    public static <T> SortedSet<T> sortedSet(T... value) {
        SortedSet<T> answer = new TreeSet<>();

        Collections.addAll(answer, value);

        return answer;
    }


    public static <T> SortedSet<T> sortedSet(Collection<T> values) {
        if (values == null) return null;

        return new TreeSet<>(values);
    }


    public static <T, V> SortedSet<V> sortedSet(Iterable<T> values, Function<T, V> mapper) {
        if (values == null) return null;

        SortedSet<V> answer = new TreeSet<>();

        for (T value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }

    // DATE
    public static Date date(java.sql.Date dateSql) {
        return (dateSql) == null ? null : new Date(dateSql.getTime());
    }

    public static Date date(LocalDate localDate) {
        //http://stackoverflow.com/a/22929420/716027
        return date(localDate, ZoneId.systemDefault());
    }

    public static Date date(LocalDate localDate, ZoneId zone) {
        return (localDate == null) ? null : date(localDate.atStartOfDay(zone));
    }

    public static Date date(LocalDateTime localDateTime) {
        //http://blog.progs.be/542/date-to-java-time
        return date(localDateTime, ZoneId.systemDefault());
    }

    public static Date date(Instant instant) {
        if (instant == null) {
            return null;
        } else {
            return new Date(instant.toEpochMilli());
        }
    }

    public static Date date(LocalDateTime localDateTime, ZoneId zone) {
        return (localDateTime == null) ? null : date(localDateTime.atZone(zone));
    }


    public static Date date(ZonedDateTime zonedDateTime) {
        return (zonedDateTime == null) ? null : Date.from(zonedDateTime.toInstant());
    }


    public static java.sql.Date sqlDate(Date date) {
        return (date == null) ? null : new java.sql.Date(date.getTime());
    }


    public static java.sql.Date sqlDate(LocalDate date) {
        return sqlDate(date(date));
    }


    public static java.sql.Date sqlDate(LocalDateTime dateTime) {
        return sqlDate(date(dateTime));
    }


    public static java.sql.Timestamp sqlTimestamp(Date date) {
        return (date == null) ? null : new java.sql.Timestamp(date.getTime());
    }


    public static java.sql.Timestamp sqlTimestamp(LocalDate date) {
        return sqlTimestamp(date(date));
    }


    public static java.sql.Timestamp sqlTimestamp(LocalDateTime dateTime) {
        return sqlTimestamp(date(dateTime));
    }

    public static LocalDateTime localDateTime(long millis) {
        //http://blog.progs.be/542/date-to-java-time
        return localDateTime(millis, ZoneId.systemDefault());
    }

    public static LocalDateTime localDateTime(long millis, ZoneId zone) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
    }


    public static LocalDateTime localDateTime(Date date) {
        //http://blog.progs.be/542/date-to-java-time
        return (date == null) ? null : localDateTime(date.getTime());
    }


    public static LocalDateTime localDateTime(Instant instant) {
        return localDateTime(date(instant));
    }


    public static LocalDate localDate(Date date) {
        return localDate(date, ZoneId.systemDefault());
    }


    public static LocalDate localDate(Instant instant) {
        return localDate(date(instant));
    }

    public static LocalDate localDate(Date date, ZoneId zone) {
        return (date == null) ? null : localDate(date.getTime(), zone);
    }

    public static LocalDate localDate(long millis) {
        return localDate(millis, ZoneId.systemDefault());
    }

    public static LocalDate localDate(long millis, ZoneId zone) {
        return localDateTime(millis, zone).toLocalDate();
    }

    public static LocalTime localTime(long millis) {
        return localTime(millis, ZoneId.systemDefault());
    }

    public static LocalTime localTime(long millis, ZoneId zone) {
        return localDateTime(millis, zone).toLocalTime();
    }


    public static ZonedDateTime zonedDateTime(Date date) {
        return zonedDateTime(date, null);
    }


    public static ZonedDateTime zonedDateTime(Date date, ZoneId zone) {
        if (date == null) {
            return null;
        } else {
            return ZonedDateTime.ofInstant(date.toInstant(), (zone != null) ? zone : ZoneId.systemDefault());
        }
    }

    public static ZonedDateTime zonedDateTime(LocalDateTime localDateTime) {
        return zonedDateTime(localDateTime, null);
    }

    public static ZonedDateTime zonedDateTime(LocalDateTime localDateTime, ZoneId zone) {
        if (localDateTime == null) {
            return null;
        } else {
            return localDateTime.atZone((zone != null) ? zone : ZoneId.systemDefault());
        }
    }

    public static Instant instant(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.toInstant();
        }
    }

    public static Instant instant(LocalDate localDate) {
        return instant(localDate, ZoneId.systemDefault());
    }

    public static Instant instant(LocalDate localDate, ZoneOffset zoneOffset) {
        if (localDate == null) {
            return null;
        } else {
            return instant(localDate.atStartOfDay(), zoneOffset);
        }
    }

    public static Instant instant(LocalDate localDate, ZoneId zoneId) {
        if (localDate == null) {
            return null;
        } else {
            return instant(localDate.atStartOfDay(), zoneId);
        }
    }

    public static Instant instant(LocalDateTime localDateTime) {
        return instant(localDateTime, ZoneId.systemDefault());
    }

    public static Instant instant(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        if (localDateTime == null) {
            return null;
        } else {
            return localDateTime.toInstant(zoneOffset);
        }
    }

    public static Instant instant(LocalDateTime localDateTime, ZoneId zoneId) {
        if (localDateTime == null) {
            return null;
        } else {
            return localDateTime.atZone(zoneId).toInstant();
        }
    }

    // URL
    public static URI uri(String url) {
        try {
            return (url == null) ? null : new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @SneakyThrows
    public static URI uriOrException(String url) {
        return new URI(url);
    }

    public static URL url(String url) {
        try {
            return (url == null) ? null : new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @SneakyThrows
    public static URL urlOrException(String url) {
        return new URL(url);
    }

    // FUNC
    public static <T, V> V nullOr(T object, Function<T, V> func) {
        return (object == null) ? null : func.apply(object);
    }

    public static <T> T nullOr(Object o, Supplier<T> supplier) {
        return (o == null) ? null : supplier.get();
    }

    public static <T> T or(@Nullable T o, Supplier<T> supplier) {
        return (o != null) ? o : supplier.get();
    }

    public static <T> T or(@Nullable T o, T otherwise) {
        return (o != null) ? o : otherwise;
    }

    public static String orDefault(@Nullable String o) {
        return (o != null) ? o : "";
    }

    public static int orDefault(@Nullable Integer o) {
        return (o != null) ? o : 0;
    }

    public static double orDefault(@Nullable Double o) {
        return (o != null) ? o : 0;
    }

    public static long orDefault(@Nullable Long o) {
        return (o != null) ? o : 0;
    }

    public static float orDefault(@Nullable Float o) {
        return (o != null) ? o : 0;
    }

    public static <T> Iterable<T> orDefault(@Nullable Iterable<T> o) {
        return (o != null) ? o : new ArrayList<>();
    }

    public static <T> Collection<T> orDefault(@Nullable Collection<T> o) {
        return (o != null) ? o : new ArrayList<>();
    }

    public static <T> List<T> orDefault(@Nullable List<T> o) {
        return (o != null) ? o : new ArrayList<>();
    }

    public static <T> Set<T> orDefault(@Nullable Set<T> o) {
        return (o != null) ? o : new HashSet<>();
    }

    public static <K, V> Map<K, V> orDefault(@Nullable Map<K, V> o) {
        return (o != null) ? o : new HashMap<>();
    }

    // THREAD
    public static Thread thread(Runnable runnable) {
        return to.thread(null, runnable);
    }

    public static Thread thread(String threadName, Runnable runnable) {
        return (threadName != null && !threadName.equals("")) ? new Thread(runnable, threadName) : new Thread(runnable);
    }

    public static Thread daemon(Runnable runnable) {
        return daemon(null, runnable);
    }

    public static Thread daemon(String threadName, Runnable runnable) {
        if (runnable == null) {
            return null;
        }

        Thread t = to.thread(threadName, runnable);
        t.setDaemon(true);

        return t;
    }

    private static Integer doIntConvert(BigDecimal val) {
        return val.intValue();
    }

    private static Integer doIntConvert(Long val) {
        return val.intValue();
    }

    private static Integer doIntConvert(Integer val) {
        return val;
    }

    private static Integer doIntConvert(Object val) {
        return Integer.parseInt(val.toString());
    }

    private static Long doLongConvert(BigDecimal val) {
        return val.longValue();
    }

    private static Long doLongConvert(Long val) {
        return val;
    }

    private static Long doLongConvert(Integer val) {
        return val.longValue();
    }

    private static Long doLongConvert(Object val) {
        return Long.parseLong(val.toString());
    }

    private static Float doFloatConvert(Object val) {
        return Float.parseFloat(val.toString());
    }

    private static Double doDoubleConvert(Object val) {
        return Double.parseDouble(val.toString());
    }

    private static Boolean doBooleanConvert(String val) {
        return Boolean.valueOf(val);
    }

    private static Boolean doBooleanConvert(Boolean val) {
        return val;
    }

    private static Boolean doBooleanConvert(Object val) {
        return Boolean.valueOf(val.toString());
    }

    public static class e {

        public static Parallel parallel() {
            return parallel(null);
        }

        public static Parallel parallel(ExecutorService pool) {
            return new Parallel(pool);
        }

        public static Thread daemonForever(long delayInMillis, Runnable runnable) {
            return daemonForever(null, delayInMillis, runnable);
        }

        public static Thread daemonForever(final String threadName, final long delayInMillis, final Runnable runnable) {
            return daemonForever(threadName, delayInMillis, runnable, 0);
        }

        public static Thread daemonForever(final String threadName, final long delayInMillis, final Runnable runnable, long startDelayInMillis) {
            Runnable forever = new Runnable() {
                @Override
                public void run() {
                    if (delayInMillis <= 0) {
                        log.warn("daemon.forever.stop: " + threadName + " : delayInMillis should be > 0");
                        return;
                    }

                    try {
                        if (startDelayInMillis > 0) {
                            Thread.sleep(startDelayInMillis);
                        }

                        while (true) {
                            runnable.run();

                            try {
                                Thread.sleep(delayInMillis);
                            } catch (InterruptedException e) {
                                //
                            }
                        }
                    } catch (Throwable e) {
                        log.error("daemon.forever.e: " + threadName, e);
                    }
                }
            };

            Thread t = (threadName != null && !threadName.equals("")) ? new Thread(forever, threadName) : new Thread(forever);
            t.setDaemon(true);

            return t;
        }
    }

    public static class Parallel {

        private static final ExecutorService POOL = Executors.newCachedThreadPool();

        private ExecutorService pool;
        private int threadsCount = -1;

        private Parallel() {
            this(null);
        }

        private Parallel(ExecutorService pool) {
            this.pool = (pool == null) ? POOL : pool;
        }

        public <T> List<T> list(Collection<T> items, Consumer<T> consumer) {
            return list(items, (i) -> {
                consumer.accept(i);

                return i;
            });
        }

        public <T, R> List<R> list(Collection<T> items, Function<T, R> func) {
            Semaphore semaphore = new Semaphore(getThreadsCountFor(items));

            List<Future<R>> futures = to.list(items, item -> {
                return pool.submit(() -> {
                    try {
                        semaphore.acquire();

                        return func.apply(item);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                });
            });

            return to.list(futures, f -> {
                try {
                    return f.get();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
        }

        private int getThreadsCountFor(Collection items) {
            return (threadsCount <= 0) ? items.size() : threadsCount;
        }
    }
}
