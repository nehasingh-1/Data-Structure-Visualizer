import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel {
    private JComboBox<String> operationSelector;
    private JTextField inputField;
    private HoverButton executeButton;
    private JLabel statusLabel;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

        operationSelector = new JComboBox<>(new String[]{"Push", "Pop", "Peek", "IsEmpty"});
        inputField = new JTextField(6);
        executeButton = new HoverButton("Execute");
        statusLabel = new JLabel("Status: Ready");

        add(new JLabel("Operation: "));
        add(operationSelector);
        add(new JLabel("Value: "));
        add(inputField);
        add(executeButton);
        add(statusLabel);

        inputField.setEnabled(true);
        operationSelector.addActionListener(e -> {
            String op = (String) operationSelector.getSelectedItem();
            inputField.setEnabled("Push".equals(op));
            statusLabel.setText("Status: Ready");
        });
    }

    public void setStatus(String msg) {
        statusLabel.setText("Status: " + msg);
    }

    public void addExecuteListener(ActionListener l) {
        executeButton.addActionListener(l);
    }

    public String getSelectedOperation() {
        return (String) operationSelector.getSelectedItem();
    }

    public String getInputValue() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.setText("");
    }
}
