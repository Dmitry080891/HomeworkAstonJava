import java.util.List;
import java.util.Scanner;

public class AddressBookApp {
    /** Сканер для ввода с клавиатуры */
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /** Сканер считывает данные с клавиатуры  */
        while (true) {
            /**Отобразится меню с номерами действий */
            printMenu();
            /**Считывание с клавиатуры номера действия */
            String choice = scanner.nextLine();

            try {
                /**Выбор пунктов из меню */
                switch (choice) {
                    case "1":
                        listContacts();
                        break;
                    case "2":
                        addContact();
                        break;
                    case "3":
                        viewContact();
                        break;
                    case "4":
                        editContact();
                        break;
                    case "5":
                        deleteContact();
                        break;
                    case "6":
                        System.out.println("Выход из программы");
                        ContactDAO.getSessionFactory().close();
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
                /**Обработка ошибки */
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
    /**Меню выбора действий в записной книжке */
    private static void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Список контактов");
        System.out.println("2. Добавить контакт");
        System.out.println("3. Просмотреть контакт");
        System.out.println("4. Редактировать контакт");
        System.out.println("5. Удалить контакт");
        System.out.println("6. Выход");
        System.out.print("Выберите действие: ");
    }
    /**Получение всего списка контактов */
    private static void listContacts() {
        List<Contact> contacts = ContactDAO.getAllContacts();
/**Обработка случая если контакты пусты */
        if (contacts.isEmpty()) {
            System.out.println("Контакты не найдены.");
            return;
        }

        System.out.println("Список контактов:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
    /**Добавление нового контакта */
    private static void addContact() {
        System.out.println("Добавление нового контакта");

        System.out.print("Имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();

        System.out.print("Телефон: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Адрес: ");
        String address = scanner.nextLine();

        Contact contact = new Contact(firstName, lastName, phone, email, address);
        ContactDAO.createContact(contact);

        System.out.println("Контакт успешно добавлен.");
    }
    /**Поиск контакта по ID*/
    private static void viewContact() {
        System.out.print("Введите ID контакта: ");
        int id = Integer.parseInt(scanner.nextLine());

        Contact contact = ContactDAO.getContactById(id);
        if (contact != null) {
            System.out.println("\nИнформация о контакте:");
            System.out.println(contact);
        } else {
            System.out.println("Контакт с ID " + id + " не найден.");
        }
    }
    /**Изменение контакта по ID*/
    private static void editContact() {
        System.out.print("Введите ID контакта для редактирования: ");
        int id = Integer.parseInt(scanner.nextLine());

        Contact contact = ContactDAO.getContactById(id);
        if (contact == null) {
            System.out.println("Контакт с ID " + id + " не найден.");
            return;
        }
        /**Вывод информации по контакту который хотим изменить*/
        System.out.println("Текущая информация:");
        System.out.println(contact);
        System.out.println("Введите новые данные (оставьте пустым, чтобы не изменять):");
/**Если информацию не ввести, то останутся старые данные*/
        System.out.print("Имя [" + contact.getFirstName() + "]: ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) contact.setFirstName(firstName);

        System.out.print("Фамилия [" + contact.getLastName() + "]: ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) contact.setLastName(lastName);

        System.out.print("Телефон [" + contact.getPhone() + "]: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) contact.setPhone(phone);

        System.out.print("Email [" + contact.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) contact.setEmail(email);

        System.out.print("Адрес [" + contact.getAddress() + "]: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) contact.setAddress(address);

        ContactDAO.updateContact(contact);
        System.out.println("Контакт успешно обновлен.");
    }
    /**Удаление контакта по ID*/
    private static void deleteContact() {
        System.out.print("\nВведите ID контакта для удаления: ");
        int id = Integer.parseInt(scanner.nextLine());
        /**Проверка контакта по ID о его существовании*/
        Contact contact = ContactDAO.getContactById(id);
        if (contact == null) {
            System.out.println("Контакт с ID " + id + " не найден.");
            return;
        }
        /**Подтверждение на удалении кнтакта*/
        System.out.println("Вы действительно хотите удалить контакт:");
        System.out.println(contact);
        System.out.print("Подтвердите удаление (y/n): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            ContactDAO.deleteContact(id);
            System.out.println("Контакт успешно удален.");
        } else {
            System.out.println("Удаление отменено.");
        }
    }
}
