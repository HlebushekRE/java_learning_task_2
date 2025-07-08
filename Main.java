import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тестирование MyHashSet:");
        MyHashSet set = new MyHashSet();
        set.add(5);
        set.add(8);
        set.add(15);
        set.add(22);
        System.out.println("Содержит 8: " + set.contains(8)); // true
        System.out.println("Содержит 5: " + set.contains(5)); // true
        System.out.println("Содержит 100: " + set.contains(100)); // false
        set.remove(8);
        System.out.println("После удаления 8:");
        System.out.println("Содержит 8: " + set.contains(8)); // false
        System.out.println("Содержит 5: " + set.contains(5)); // true
        System.out.println("Размер множества: " + set.size());


        System.out.println("Тестирование MyArrayList:");

        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println("Список после добавления: " + list);

        list.add(1, 10);
        System.out.println("После добавления 10 по индексу 1: " + list);

        list.addAll(new int[]{30, 40, 50});
        System.out.println("После добавления массива: " + list);

        System.out.println("Элемент по индексу 2: " + list.get(2));

        list.remove(2);
        System.out.println("После удаления элемента по индексу 2: " + list);
        System.out.println("Текущий размер списка: " + list.size());


        System.out.println("Задание 2");

        List<Student> students = Arrays.asList(
                new Student("Владимир", Arrays.asList(
                        new Book("Вишнёвый сад", 2024, 192),
                        new Book("Мастер и Маргарита", 2024, 480),
                        new Book("Отцы и дети", 1957, 200),
                        new Book("Вишнёвый сад", 2024, 192),
                        new Book("Щелкунчик и мышиный король", 2017, 192)
                )),
                new Student("Валерий", Arrays.asList(
                        new Book("Война и мир. Том 1", 2020, 448),
                        new Book("Война и мир. Том 2", 2025, 448),
                        new Book("Война и мир. Том 3", 2025, 448),
                        new Book("Война и мир. Том 4", 2025, 416),
                        new Book("Воскресение", 1899, 576)
                ))
        );
        students.stream()
                .peek(System.out::println) // Вывод студентов
                .flatMap(student -> student.getBooks().stream()) // Вывод списка книг для каждого студента + получить сами книги
                .distinct() // Оставить только уникальные книги
                .sorted((x1, x2) -> Integer.compare(x1.getPages(), x2.getPages())) // Отсортировать по кол-ву страниц
                .filter(book -> book.getYear() > 2000) // Фильтр книг, оставить только выпущенные после 2000 года
                .limit(3) // Ограничить стрим на 3 элементах
                .map(Book::getYear) // Получить из книг годы выпуска

                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска книги:" + year),
                        () -> System.out.println("Книга не найдена")
                );
    }
}