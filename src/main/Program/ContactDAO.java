import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
public class ContactDAO {
    /** Создаем SessionFactory из hibernate.cfg.xml */
    private static final SessionFactory sessionFactory;

    /** SessionFactory в Hibernate на языке Java — это компонент, который используется для создания
    сеансов (Session) работы с базой данных. Он служит фабрикой для создания экземпляров сеансов и
     управляет их жизненным циклом. */
    static {
        try {
            /**  Создать SessionFactory из файла конфигурации hibernate.cfg.xml  */
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        } catch (Throwable ex) {
            /** Обработка исключения ошибки инициализации*/
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    /** Создание нового контакта */
    public static void createContact(Contact contact) {
        /**Try-with-resources предназначенная для упрощения управления ресурсами, которые необходимо закрывать после использования.*/
        try (Session session = getSessionFactory().openSession()) {
            /** Метод beginTransaction() позволяет начать транзакцию и выполнить операции с базой данных внутри неё. */
            Transaction transaction = session.beginTransaction();
            session.save(contact);
            transaction.commit();
        }
    }
    /** Получение всего списка контактов */
    public static List<Contact> getAllContacts() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM Contact ORDER BY lastName, firstName", Contact.class).list();
        }
    }
    /** Получение контакта по Id */
    public static Contact getContactById(int id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Contact.class, id);
        }
    }
    /** Изменение контакта */
    public static void updateContact(Contact contact) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(contact);
            /** Сохранение изменений */
            transaction.commit();
        }
    }
    /** Удаление контакта по Id */
    public static void deleteContact(int id) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        }
}}
