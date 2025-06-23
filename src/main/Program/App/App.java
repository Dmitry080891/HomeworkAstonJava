package App;

import Controller.AddressBookController;
import Model.AddressBook;
import View.AddressBookView;

public class App {


    public static void main(String[] args) {
        /** Создаем экземпляр модели */
        AddressBook model = new AddressBook();
        /** Создаем интерфейс пользователя */
        AddressBookView view = new AddressBookView();
        /** Связываем контроллер */
        AddressBookController controller = new AddressBookController(model, view);
/** Запускаем программу */
        controller.run();
    }
}