import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Data Structure Algorithm Visualizer");
            frame.setSize(900, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            JPanel cards = new JPanel(new CardLayout());

            VisualizerPanel stackPanel = new VisualizerPanel();
            LinkedListVisualizer listPanel = new LinkedListVisualizer();
            BSTVisualizer bstPanel = new BSTVisualizer();

            cards.add(stackPanel, "Stack");
            cards.add(listPanel, "LinkedList");
            cards.add(bstPanel, "BST");

            frame.add(cards, BorderLayout.CENTER);

            ControlPanel controlPanel = new ControlPanel();

            JComboBox<String> dsSelector = new JComboBox<>(new String[]{"Stack", "LinkedList", "BST"});
            JPanel topControl = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
            topControl.add(new JLabel("Data Structure:"));
            topControl.add(dsSelector);

            JPanel southPanel = new JPanel(new BorderLayout());
            southPanel.add(topControl, BorderLayout.NORTH);
            southPanel.add(controlPanel, BorderLayout.CENTER);

            frame.add(southPanel, BorderLayout.SOUTH);

            CardLayout cardLayout = (CardLayout) cards.getLayout();
            cardLayout.show(cards, "Stack");

            dsSelector.addActionListener(e -> {
                String selected = (String) dsSelector.getSelectedItem();
                cardLayout.show(cards, selected);
                controlPanel.setStatus("Switched to " + selected);
            });

            controlPanel.addExecuteListener(e -> {
                String operation = controlPanel.getSelectedOperation();
                String valueText = controlPanel.getInputValue();
                String selectedDS = (String) dsSelector.getSelectedItem();

                if ("Stack".equals(selectedDS)) {
                    switch (operation) {
                        case "Push":
                            try {
                                int val = Integer.parseInt(valueText);
                                stackPanel.push(val);
                                controlPanel.setStatus("Pushed " + val);
                                controlPanel.clearInput();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Enter a valid integer");
                            }
                            break;
                        case "Pop":
                            if (!stackPanel.isEmpty()) {
                                int val = stackPanel.pop();
                                controlPanel.setStatus("Popped " + val);
                            } else {
                                controlPanel.setStatus("Stack is empty");
                            }
                            break;
                        case "Peek":
                            if (!stackPanel.isEmpty()) {
                                int top = stackPanel.peek();
                                controlPanel.setStatus("Top element is " + top);
                            } else {
                                controlPanel.setStatus("Stack is empty");
                            }
                            break;
                        case "IsEmpty":
                            controlPanel.setStatus("Stack is " + (stackPanel.isEmpty() ? "empty" : "not empty"));
                            break;
                    }
                } else if ("LinkedList".equals(selectedDS)) {
                    switch (operation) {
                        case "Push":
                        case "Insert":
                            try {
                                int val = Integer.parseInt(valueText);
                                listPanel.insert(val);
                                controlPanel.setStatus("Inserted " + val);
                                controlPanel.clearInput();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Enter a valid integer");
                            }
                            break;
                        case "Pop":
                        case "Remove":
                            if (!listPanel.isEmpty()) {
                                listPanel.remove();
                                controlPanel.setStatus("Removed last element");
                            } else {
                                controlPanel.setStatus("LinkedList is empty");
                            }
                            break;
                        default:
                            controlPanel.setStatus("Operation not supported on LinkedList");
                    }
                } else if ("BST".equals(selectedDS)) {
                    switch (operation) {
                        case "Push":
                        case "Insert":
                            try {
                                int val = Integer.parseInt(valueText);
                                bstPanel.insert(val);
                                controlPanel.setStatus("Inserted " + val + " into BST");
                                controlPanel.clearInput();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Enter a valid integer");
                            }
                            break;
                        default:
                            controlPanel.setStatus("Operation not supported on BST");
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}




