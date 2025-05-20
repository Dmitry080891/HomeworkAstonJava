import java.util.List;
import java.util.Scanner;


public class Service {
    private static final Scanner scanner = new Scanner(System.in);
    private static void listContacts() throws Exception {
        List<Contact> contacts = DatabaseManager.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("Контакты не найдены.");
            return;
        }
        System.out.println("Список контактов:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }


    private static void addContact() throws Exception {
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
        DatabaseManager.createContact(contact);
    }
    private static Contact findContact() throws Exception {
        System.out.print("Поиск по ID контакта: ");
        int id = Integer.parseInt(scanner.nextLine());

        Contact contact = DatabaseManager.getContactById(id);
        if (contact != null) {
            System.out.println(contact);
        } else {
            System.out.println("Контакт с ID " + id + " не найден.");
        }
        return contact;
    }

    private static void editContact() throws Exception {
        System.out.print("Введите ID контакта для редактирования: ");
        int id = Integer.parseInt(scanner.nextLine());

        Contact contact = DatabaseManager.getContactById(id);
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) contact.setFirstName(firstName);

        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) contact.setLastName(lastName);

        System.out.print("Телефон: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) contact.setPhone(phone);

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) contact.setEmail(email);

        System.out.print("Адрес: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) contact.setAddress(address);

        DatabaseManager.updateContact(contact);
    }

    private static void deleteContact() throws Exception {
        System.out.print("Введите ID контакта для удаления: ");
        int id = Integer.parseInt(scanner.nextLine());
        DatabaseManager.deleteContact(id);

    }

}
