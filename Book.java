import java.util.Objects;

public class Book{
    String name;
    int yearPublication;
    int numberPages;

    Book(String name, int yearPublication, int numberPages){
        this.name = name;
        this.yearPublication = yearPublication;
        this.numberPages = numberPages;
    }

    public String getName(){
        return name;
    }

    public int getYear(){
        return yearPublication;
    }

    public int getPages(){
        return numberPages;
    }

    @Override
    public String toString() {
        return "Название: " + name + ", Год выхода: " + yearPublication + ", Всего страниц: " + numberPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Проверка на саморавенство
        if (o == null || getClass() != o.getClass()) return false; // Проверка на null и тип

        Book book = (Book) o;

        // Сравнение всех значимых полей
        return yearPublication == book.yearPublication &&
                numberPages == book.numberPages &&
                name.equals(book.name);
    }

    @Override
    public int hashCode() {
        // Использование Objects.hash для генерации хеш-кода
        return Objects.hash(name, yearPublication, numberPages);
    }

}
