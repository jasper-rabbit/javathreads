package javathreads.examples.ch02;

import javax.swing.*;
import java.awt.*;

public class CharacterDisplayCanvas extends JComponent implements CharacterListener {
    protected FontMetrics fm;
    protected char[] tmpChar = new char[1];
    protected int fontHeght;

    public CharacterDisplayCanvas() {
        setFont(new Font("Monospaced", Font.BOLD, 18));
        fm = Toolkit.getDefaultToolkit().getFontMetrics(getFont());
        fontHeght = fm.getHeight();
    }

    public CharacterDisplayCanvas(CharacterSource characterSource) {
        this();
        setCharacterSource(characterSource);
    }

    public void setCharacterSource(CharacterSource characterSource) {
        characterSource.addCharacterListener(this);
    }

    public Dimension preferredSize() {
        return new Dimension(fm.getMaxAscent() + 10, fm.getMaxAdvance() + 10);
    }

    protected synchronized void paintComponent(Graphics gc) {
        Dimension d = getSize();
        gc.clearRect(0, 0, d.width, d.height);
        if (tmpChar[0] == 0) {
            return;
        }
        int charWidth = fm.charWidth((int) tmpChar[0]);
        gc.drawChars(tmpChar, 0, 1, (d.width - charWidth) / 2, fontHeght);
    }

    @Override
    public synchronized void newCharacter(CharacterEvent characterEvent) {
        tmpChar[0] = (char) characterEvent.character;
        repaint();
    }
}
