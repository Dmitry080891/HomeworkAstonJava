import java.util.Arrays;
import java.util.Comparator;
/**Массив с использованием дженериков */
public class MyArrayList<E> {
    /**Установление размера списка по умолчанию
     * Объявление поля класса
     * Инициализация переменной размера списка*/
    private static final int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int size;
    /** Конструктор класса*/
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    /**Метод для проверки наполненности массива,
     * если массив заполнен,
     * то содержимое массива копируется в новый массив и его размер увеличивается в 2 раза*/
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }
    /**Метод для проверки индекса массива, @param index индекс вставки
     * если индекс отрицательный либо превышает размер массива,
     * то будет выброшено исключение*/
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + "не соответствует размеру массива: " + size);
        }
    }

    /**
     * Медод добавляет элемент (по умолчанию в конец списка)
     * @param element значение для добавления
     * проверяет массив на заполненность
     * добавляет элемент (присвоение значения и увеличение размера массива)
     */
    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Медод добавляет элемент по указанному индексу
     * проверяет корректность добавляемого индекса
     * проверяет массив на заполненность
     * подготавливает место для нового элемента с помощью System.arraycopy();
     * перезаписывает значение у элемента с указанным индексом
     * увеличивает размер массива
     */
    public void addIndex(int index, E element) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Медод возвращает элемент по указанному индексу
     * проверяет корректность добавляемого индекса
     * возвращает значение элемента с указанным индексом
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * Удаляет элемент по индексу
     * @param index позиция удаляемого элемента
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс недопустим
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removed = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    /**
     * Метод очищает массив,
     * удаляет значения всех элементов
     * и размер массива приравнивает к 0
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Метод вернет размер массива
     */
    public int size() {
        return size;
    }
    /**
     * Метод вернет true,
     * если массив пуст
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**Статический вложенный класс быстрой сортировки.
     * Привязан к самому классу, а не к его экземпляру.
     * Можно создать экземпляр вложенного класса, не создавая промежуточный экземпляр внешнего класса.
     */
    public static class QuickSort {
        private QuickSort() {}

        /**
         * Сортирует массив с natural ordering
         * @param <E> тип элементов массива
         * @param array массив для сортировки
         * @param low начальный индекс
         * @param high конечный индекс
         */
        public static <E extends Comparable<? super E>> void sort(E[] array, int low, int high) {
            if (low < high) {
                int pivot = partition(array, low, high);
                sort(array, low, pivot - 1);
                sort(array, pivot + 1, high);
            }
        }

        /**
         * Сортирует массив с использованием компаратора
         * @param <E> тип элементов массива
         * @param array массив для сортировки
         * @param low начальный индекс
         * @param high конечный индекс
         * @param comparator компаратор для сравнения
         */
        public static <E> void sort(E[] array, int low, int high, Comparator<? super E> comparator) {
            if (low < high) {
                int pivot = partition(array, low, high, comparator);
                sort(array, low, pivot - 1, comparator);
                sort(array, pivot + 1, high, comparator);
            }
        }

        private static <E extends Comparable<? super E>> int partition(E[] array, int low, int high) {
            E pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (array[j].compareTo(pivot) <= 0) {
                    i++;
                    swap(array, i, j);
                }
            }

            swap(array, i + 1, high);
            return i + 1;
        }

        private static <E> int partition(E[] array, int low, int high, Comparator<? super E> comparator) {
            E pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (comparator.compare(array[j], pivot) <= 0) {
                    i++;
                    swap(array, i, j);
                }
            }

            swap(array, i + 1, high);
            return i + 1;
        }

        private static <E> void swap(E[] array, int i, int j) {
            E temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
