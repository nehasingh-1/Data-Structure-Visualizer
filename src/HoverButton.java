import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HoverButton extends JButton {
    private Color normalColor = new Color(70, 130, 180);
    private Color hoverColor = new Color(100, 160, 210);

    public HoverButton(String text) {
        super(text);
        setForeground(Color.WHITE);
        setBackground(normalColor);
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(true);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
            }
        });
    }
}
