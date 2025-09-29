import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class LinkedListVisualizer extends JPanel {
    private ArrayList<Integer> nodes = new ArrayList<>();

    public LinkedListVisualizer() {
        nodes.add(10);
        nodes.add(20);
        nodes.add(30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 100;

        for (int i = 0; i < nodes.size(); i++) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 60, 40);
            g.setColor(Color.WHITE);
            g.drawString(nodes.get(i).toString(), x + 25, y + 25);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, 60, 40);

            if (i < nodes.size() - 1) {
                g.drawLine(x + 60, y + 20, x + 90, y + 20);
                g.drawLine(x + 85, y + 15, x + 90, y + 20);
                g.drawLine(x + 85, y + 25, x + 90, y + 20);
            }
            x += 100;
        }
    }

    public void insert(int value) {
        nodes.add(value);
        repaint();
    }

    public void remove() {
        if (!nodes.isEmpty()) {
            nodes.remove(nodes.size() - 1);
            repaint();
        }
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }
}
