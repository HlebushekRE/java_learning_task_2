import java.util.Arrays;

public class MyArrayList {
        // Константы для настройки размера
        private static final int DEFAULT_CAPACITY = 10;
        private static final int GROWTH_FACTOR = 2;

        // Основные поля
        private int[] array;
        private int size;
        private int capacity;

        // Конструктор по умолчанию
        public MyArrayList() {
            this(DEFAULT_CAPACITY);
        }

        // Конструктор с указанием начальной емкости
        public MyArrayList(int initialCapacity) {
            if (initialCapacity <= 0) {
                throw new IllegalArgumentException("Initial capacity must be positive");
            }
            this.capacity = initialCapacity;
            this.array = new int[capacity];
            this.size = 0;
        }

        // Добавление элемента в конец списка
        public void add(int element) {
            ensureCapacity();
            array[size] = element;
            size++;
        }

        // Добавление элемента по указанному индексу
        public void add(int index, int element) {
            checkIndex(index);
            ensureCapacity();
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = element;
            size++;
        }

        // Получение элемента по индексу
        public int get(int index) {
            checkIndex(index);
            return array[index];
        }

        // Удаление элемента по индексу
        public void remove(int index) {
            checkIndex(index);
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }

        // Добавление массива элементов
        public void addAll(int[] elements) {
            ensureCapacity(elements.length);
            for (int element : elements) {
                array[size++] = element;
            }
        }

        // Получение текущего размера списка
        public int size() {
            return size;
        }

        // Проверка индекса на корректность
        private void checkIndex(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
        }

        // Обеспечение достаточной емкости массива
        private void ensureCapacity() {
            ensureCapacity(1);
        }

        private void ensureCapacity(int minCapacity) {
            if (size + minCapacity > capacity) {
                int newCapacity = capacity * GROWTH_FACTOR;
                array = Arrays.copyOf(array, newCapacity);
                capacity = newCapacity;
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOf(array, size));
        }
}
