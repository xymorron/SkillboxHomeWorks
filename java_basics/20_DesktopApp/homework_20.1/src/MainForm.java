import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.StringJoiner;

public class MainForm {
    private JPanel mainPanel;
    private JTextField nameField1;
    private JTextField nameField2;
    private JTextField nameField3;
    private JButton checkButton;
    private JLabel nameLabel1;
    private JLabel nameLabel2;
    private JLabel nameLabel3;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainForm() {
        checkButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkButton.getText().equals("Collapse")) {
                    String firstName = nameField1.getText();
                    String secondName = nameField2.getText();
                    String thirdName = nameField3.getText();
                    if (isValidName(firstName) && isValidName(secondName)) {
                        StringJoiner fullName = new StringJoiner(" ");
                        fullName.add(firstName).add(secondName);
                        if (isValidName(thirdName)) {
                            fullName.add(thirdName);
                        }
                        collapse(fullName.toString());
                    } else {
                        JOptionPane.showMessageDialog(
                                mainPanel,
                                "Необходимо заполнить поля Фамилия и Имя",
                                "Ошибка преобразования",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    String[] fullName = nameField1.getText().split("\\s+", 3);
                    int namePartsCount = fullName.length;
                    String firstName = fullName[0];
                    String secondName = namePartsCount > 1 ? fullName[1] : "";
                    String thirdName = namePartsCount > 2 ? fullName[2] : "";
                    if (isValidName(firstName) && isValidName(secondName)) {
                        expand(firstName, secondName, thirdName);
                    } else {
                        JOptionPane.showMessageDialog(
                                mainPanel,
                                "Необходимо указать фамилию и имя",
                                "Ошибка преобразования",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            private boolean isValidName(String name) {
                return !name.isBlank();
            }

            private void collapse(String fullName) {
                checkButton.setText("Expand");
                nameLabel1.setText("Ф.И.О.");
                nameField1.setText(fullName);
                nameField2.setVisible(false);
                nameField3.setVisible(false);
                nameLabel2.setVisible(false);
                nameLabel3.setVisible(false);
            }

            private void expand(String firstName, String secondName, String thirdName) {
                checkButton.setText("Collapse");
                nameLabel1.setText("Фамилия");
                nameField1.setText(firstName);
                nameField2.setText(secondName);
                nameField3.setText(thirdName);
                nameField2.setVisible(true);
                nameField3.setVisible(true);
                nameLabel2.setVisible(true);
                nameLabel3.setVisible(true);
            }
        });
    }
}
