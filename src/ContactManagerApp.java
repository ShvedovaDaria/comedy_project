import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactManagerApp extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private DefaultListModel<String> contactsModel;
    private JList<String> contactsList;

    public ContactManagerApp() {
        // Налаштування основного вікна програми
        setTitle("Contact Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Створення компонентів GUI
        nameField = new JTextField();
        phoneField = new JTextField();
        JButton addButton = new JButton("Add Contact");
        JButton deleteButton = new JButton("Delete Contact");
        JButton editButton = new JButton("Edit Contact");
        contactsModel = new DefaultListModel<>();
        contactsList = new JList<>(contactsModel);
        JScrollPane scrollPane = new JScrollPane(contactsList);

        // Додавання компонентів на основне вікно програми
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Обробники подій для кнопок
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        // Завантаження контактів при запуску програми
        loadContactsFromFile();
    }

    private void addContact() {
        // Отримання даних від користувача та додавання контакту до списку
        String name = nameField.getText();
        String phone = phoneField.getText();
        String contact = name + ": " + phone;
        contactsModel.addElement(contact);
        // Очистка полів після додавання контакту
        nameField.setText("");
        phoneField.setText("");
        // Збереження контактів у файл
        saveContactsToFile();
    }

    private void deleteContact() {
        int selectedIndex = contactsList.getSelectedIndex();
        if (selectedIndex != -1) {
            contactsModel.remove(selectedIndex);
            saveContactsToFile();
        }
    }

    private void editContact() {
        int selectedIndex = contactsList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedContact = contactsModel.getElementAt(selectedIndex);
            // Парсимо ім'я та номер телефону з обраного контакту
            String[] parts = selectedContact.split(": ");
            if (parts.length >= 2) { // Перевіряємо, чи масив має достатню довжину
                String oldName = parts[0];
                String oldPhone = parts[1];

                // Отримуємо нові дані з текстових полів
                String newName = nameField.getText();
                String newPhone = phoneField.getText();

                // Формуємо новий контакт
                String newContact = newName + ": " + newPhone;

                // Замінюємо старий контакт на новий у списку
                contactsModel.setElementAt(newContact, selectedIndex);

                // Очищаємо тексові поля
                nameField.setText("");
                phoneField.setText("");

                // Зберігаємо контакти у файл
                saveContactsToFile();
            } else {
                // Обробка помилки, якщо розділені дані недостатньо
                System.out.println("Недостатньо даних для редагування контакту");
            }
        }
    }


    private void loadContactsFromFile() {
        // Зчитування контактів з файлу
        // Логіка зчитування з файлу
    }

    private void saveContactsToFile() {
        // Збереження контактів у файл
        // Логіка збереження у файл
    }

    public static void main(String[] args) {
        // Запуск програми
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManagerApp().setVisible(true);
            }
        });
    }
}
