import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/address_book";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    /** проверка наличия простгрес скл драйвера */
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки PostgreSQL JDBC driver", e);
        }
    }
    /** Подключение к простгрес скл
     * URL — URL базы данных, к которой нужно подключиться.
     * USER — имя пользователя базы данных.
     * PASSWORD — пароль пользователя.  */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**  CRUD операции */
    /**  Создание контакта */
    public static void createContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (first_name, last_name, phone, email, address) VALUES (?, ?, ?, ?, ?)";
/** Connection — это корневой объект, «входная точка»
 * ко всем возможностям взаимодействия с СУБД (системой управления базами данных).
 *
 PreparedStatement — это параметризованный шаблон SQL-команды.
 Во время каждого выполнения в него подставляются постоянные значения. */
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getPhone());
            stmt.setString(4, contact.getEmail());
            stmt.setString(5, contact.getAddress());

            stmt.executeUpdate();
        }
    }
    /**  Получение всех контактов */
    public static List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY last_name, first_name";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             /** Полученные данные в виде таблици   */
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                contacts.add(contact);
            }
        }
        return contacts;
    }
    /**  Получение такта по айди */
    public static Contact getContactById(int id) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Contact(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("address")
                    );
                }
            }
        }
        return null;
    }
    /**  Изменение контактов
     * необходимо переназвать все параметры объекта  */
    public static void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, phone = ?, email = ?, address = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getPhone());
            stmt.setString(4, contact.getEmail());
            stmt.setString(5, contact.getAddress());
            stmt.setInt(6, contact.getId());

            stmt.executeUpdate();
        }
    }
    /**  Удаление контакта по айди  */
    public static void deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
