import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(" === Task 1 ===");
//        a) Дан список Person {name, age}. Написать метод, который вернет Map/<Integer,List/>,
//        где ключ это возраст, значение - лист персон.
//        Подсказка: обратите внимание на коллектор Сollectors.groupingBy().
        Random random = new Random();
        Map<Integer, List<Person>> personsA = random
                .ints(4, 17, 77)
                .distinct()
                .mapToObj(i -> new Person("name" + i, i))
                .collect(Collectors.groupingBy(Person::getAge, Collectors.toList()));
        System.out.println(personsA);

        System.out.println(" === Task 2 ===");
//        b) Дан список Person {name, age}. Написать метод, который вернет Map/<String, Integer>,
//        где ключ это имя, значение сколько раз встретилось это имя.
//        Подсказка: обратите внимание на коллектор Сollectors.toMap().
        Map<String, Integer> personsB = random
                .ints(4, 17, 20)
                .mapToObj(i -> new Person("name-" + i, i))
                .peek(p -> System.out.println(p.getName()))
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
        System.out.println(personsB);

        System.out.println(" === Task 3 ===");
//        c) Дан список Person {name, age}. Написать метод, который вернет Map/<Boolean, List/>, где ключ
//        false - если Person моложе 18 лет, true - если уже есть 18. Значение, список соответствующих персон
//        Подсказка: обратите внимание на коллектор Сollectors.partitioningBy().

        Predicate<Person> isAdult = p -> p.getAge() > 18;
        Map<Boolean, List<Person>> personsC = random
                .ints(5, 16, 24)
                .mapToObj(i -> new Person("name" + i, i))
                .peek(p -> System.out.println(p.getAge()))
                .collect(Collectors.partitioningBy(isAdult));
        System.out.println(personsC);

        System.out.println(" === Task 4 ===");
        //  Дан список Person {name, age}. Написать метод, который вернет Map/<Integer,List/>, где ключ это возраст,
        //  значение - лист строк-имен персонов.
        //Подсказка: задача очень похожа на пункт a), на в качестве значений используется не лмст из Person, а лист ищ строк с именами.
        // Т.е. решение как пункт a), но Сollectors.groupingBy() вызываем с дополнительным параметром - коллектором Mapping
        Map<Integer, List<String>> personsD = random
                .ints(8, 16, 20)
                .mapToObj(i -> new Person("name" + i, i))
                .peek(p -> System.out.println(p.getAge()))
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println(personsD);
    }

    private static class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}