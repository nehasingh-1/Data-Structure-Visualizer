import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Stack;

public class VisualizerPanel extends JPanel {
    private Stack<Integer> stack = new Stack<>();

    public VisualizerPanel() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 350;
        int y = 500;

        for (Integer value : stack) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 100, 30);
            g.setColor(Color.WHITE);
            g.drawString(value.toString(), x + 40, y + 20);
            y -= 40;
        }

        g.setColor(Color.BLACK);
        g.drawRect(x, y + 40, 100, stack.size() * 40);
    }

    public void push(int value) {
        stack.push(value);
        repaint();
    }

    public int pop() {
        if (!stack.isEmpty()) {
            int val = stack.pop();
            repaint();
            return val;
        }
        return -1; // or throw exception if preferred
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1; // or throw exception if preferred
    }
}
