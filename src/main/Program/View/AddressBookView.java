package View;
/** Отвечает за представление данных пользователю и взаимодействие с ним. */
import Model.Person;
import java.util.List;
import java.util.Scanner;

/** Отображение работы с контактами */
public class AddressBookView {
    Scanner scanner = new Scanner(System.in);
    /** Выбор действий */
    public void displayMenu() {
        System.out.println("Адресная книга:");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Показать контакты");
        System.out.println("3. Выход");
    }
    /** Распознавание введенного числа */
    public int selectOption() {
        return Integer.parseInt(scanner.nextLine());
    }
    /** Заполнение данных нового контакта */
    public Person inputNewContact() {
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();
        System.out.print("Номер телефона: ");
        String phoneNumber = scanner.nextLine();
        return new Person(firstName, lastName, phoneNumber);
    }
    /** Показать спиок контактов если есть записи */
    public void showPersons(List<Person> persons) {
        if (persons.isEmpty()) {
            System.out.println("Нет контактов.");
        } else {
            for (Person cont : persons) {
                System.out.printf("%s %s (%s)\n", cont.getFirstName(), cont.getLastName(), cont.getPhoneNumber());
            }
        }
    }
}
