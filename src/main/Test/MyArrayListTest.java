import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<Integer> list;
   /**Создаём список для тестов*/
   @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }
    /**Добавлем 2 элемета и возвращем их по индексу*/
    @Test
    void addAndGet() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));

    }
    /**Добавляем 2 элемента
     * добавляем элемент по индексу существующего элемента
     * проверяем индекс последнего добавленного элемента
     * проверяем размер массива*/
    @Test
    void addIndexAndSize() {
        list.add(1);
        list.add(3);
        list.addIndex(1, 2);
        assertEquals(2, list.get(1));
        assertEquals(3, list.size());

    }
    /**Добавляем 3 элемента
     * удаляем элемент по индексу существующего элемента
     * проверяем размер массива
     * проверяем какой элемент находится на месте удаленного элемента*/
    @Test
    void remove() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));

    }
    /**Добавляем 2 элемента
     * добавляем элемент по индексу существующего элемента
     * проверяем индекс последнего добавленного элемента
     * проверяем размер массива*/
    @Test
    void clearAndIsEmpty() {
        list.add(1);
        list.add(2);
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

    }
    /**Тест вложенного класса быстрой сортировки*/
    @Nested
    class QuickSortTest {
        @Test
        void testSortIntegers() {
            Integer[] array = {5, 2, 9, 1, 5, 6};
            MyArrayList.QuickSort.sort(array, 0, array.length - 1);
            assertArrayEquals(new Integer[]{1, 2, 5, 5, 6, 9}, array);
        }

}}