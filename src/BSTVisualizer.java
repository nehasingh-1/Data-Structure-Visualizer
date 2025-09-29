import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class BSTVisualizer extends JPanel {
    private class Node {
        int value;
        Node left, right;
        int x, y; // Coordinates for drawing

        Node(int val) {
            value = val;
            left = right = null;
        }
    }

    private Node root;
    private final int NODE_RADIUS = 20;
    private final int VERTICAL_GAP = 50;

    public BSTVisualizer() {
        root = null;
    }

    // Insert a value into BST
    public void insert(int val) {
        root = insertRec(root, val);
        repaint();
    }

    private Node insertRec(Node node, int val) {
        if (node == null)
            return new Node(val);

        if (val < node.value)
            node.left = insertRec(node.left, val);
        else if (val > node.value)
            node.right = insertRec(node.right, val);

        return node;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (root != null) {
            int startX = getWidth() / 2;
            int startY = 40;
            assignCoordinates(root, startX, startY, getWidth() / 4);
            drawTree(g, root);
        }
    }

    // Recursively assign coordinates for each node
    private void assignCoordinates(Node node, int x, int y, int horizontalGap) {
        if (node == null) return;
        node.x = x;
        node.y = y;

        if (node.left != null)
            assignCoordinates(node.left, x - horizontalGap, y + VERTICAL_GAP, horizontalGap / 2);

        if (node.right != null)
            assignCoordinates(node.right, x + horizontalGap, y + VERTICAL_GAP, horizontalGap / 2);
    }

    // Recursively draw nodes and edges
    private void drawTree(Graphics g, Node node) {
        if (node == null) return;

        g.setColor(Color.BLACK);

        if (node.left != null) {
            g.drawLine(node.x, node.y, node.left.x, node.left.y);
        }
        if (node.right != null) {
            g.drawLine(node.x, node.y, node.right.x, node.right.y);
        }

        g.setColor(Color.ORANGE);
        g.fillOval(node.x - NODE_RADIUS, node.y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        String valStr = Integer.toString(node.value);
        int strWidth = g.getFontMetrics().stringWidth(valStr);
        int strHeight = g.getFontMetrics().getHeight();

        g.drawString(valStr, node.x - strWidth / 2, node.y + strHeight / 4);

        drawTree(g, node.left);
        drawTree(g, node.right);
    }
}
