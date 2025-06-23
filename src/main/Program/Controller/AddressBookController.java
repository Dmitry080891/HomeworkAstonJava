package Controller;
/** Контролирует поток взаимодействия между моделью и видом, обрабатывая запросы пользователя. */
import Model.AddressBook;
import View.AddressBookView;

public class AddressBookController {
    private final AddressBook addressBook;
    private final AddressBookView view;

    public AddressBookController(AddressBook addressBook, AddressBookView view) {
        this.addressBook = addressBook;
        this.view = view;
    }
    /** В запущенном потоке выбор действия со списком */
    public void run() {
        boolean exit = false;
        while (!exit) {
            view.displayMenu();
            switch (view.selectOption()) {
                case 1:
                    addressBook.addPerson(view.inputNewContact());
                    break;
                case 2:
                    view.showPersons(addressBook.getPersons());
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор!");
            }
        }
    }
}
