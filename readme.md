# soyuz-to
This library answers the question "How to convert `X` to `Y`?" How to convert `String` to `Long`? How to convert `Date` to `LocalDate`? How to convert `array` to `stream`? How to convert `List` to `Map`?
Most of the time we convert data from `X` type to `Y`. `soyuz-to` simplifies your life:

1. Simple way to convert `X` to `Y`
2. Simple way to init / convert `Map`, `List`, `Set`
3. \+ a little bit functional programming...

## to intro
Basic rule: `to.$requredType($sourceValue)`. E.g. we want to convert `java.util.Date now` to `java.time.LocalDate`:
```
LocalDate date = to.localDate(now)
```

## to convert simple
```java
Integer integer = to.Integer("5"); //5.7m views - https://stackoverflow.com/q/5585779/716027
Boolean aBoolean = to.Boolean("true"); //535k views - https://stackoverflow.com/q/1538755/716027
LocalDate localDate = to.localDate(new Date()); //374k views- https://stackoverflow.com/q/21242110/716027
Date date = to.date(LocalDate.now()); //322k views - https://stackoverflow.com/q/22929237/716027
Stream<String> stream = to.stream(new String[]{"1", "2"}); //59k views - https://stackoverflow.com/q/27888429/716027
```


## to convert functional
Use simple transformation functions to transform `Collections` / `Maps`:
```java
List<Car> cars = to.list(new Car(1, "bmw"), new Car(2, "lada"));
Set<String> carTitles = to.set(cars, Car::getTitle); //492k views https://stackoverflow.com/q/1429860/716027
Map<Integer, Car> carsById = to.map(cars, Car::getId); //356k views https://stackoverflow.com/q/4138364/716027
List<String> carTitlesFromMap = to.list(carsById, (k, v) -> v.getTitle()); //662k views https://stackoverflow.com/q/1026723/716027

@AllArgsConstructor
@Getter
@EqualsAndHashCode
private static class Car {
    private int id;
    private String title;
}
```

## to init
Simple way to objects:
```java
List<Integer> numbers = to.list(1, 2, 3); //1.2m views https://stackoverflow.com/q/13395114/716027
Set<String> strings = to.set("a", "b", "a"); //714k views https://stackoverflow.com/q/2041778/716027
Map<String, Integer> map = to.map("bmw", 4, "audi", 3, "lada", 5); //1.1m + 800k views https://stackoverflow.com/q/6802483/716027 + https://stackoverflow.com/q/507602/716027
```


## to null safe
Use `to.orDefault` to return values if it's not `null` or default value (`0`, `""`...) otherwise
```java
Map<String, Integer> carsCount = to.map("bmw", 3, "audi", 4);

int ladas = to.orDefault(carsCount.get("lada"));
```

## How to use
### Maven
```
<dependency>
    <groupId>io.thedocs</groupId>
    <artifactId>soyuz-to</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'io.thedocs:soyuz-to:1.0.1'
}
```

### Import to java class
```
import io.thedocs.soyuz.to;
```

## Dependencies
This library depends on `slf4j-api` and `com.google.code.findbugs:jsr305` (`javax.annotation.Nullable`)

## Other useful libraries
- [soyuz-is](https://github.com/thedocs-io/soyuz-is) - java thruthy checks (don't use != null check in your if statements)
- [soyuz-loge](https://github.com/thedocs-io/soyuz-loge) - slf4j wrapper which makes your logs cleaner and easier to read / find
- [soyuz-validator](https://github.com/thedocs-io/soyuz-validator) - bean fluent validator based on builder pattern

## License
MIT
