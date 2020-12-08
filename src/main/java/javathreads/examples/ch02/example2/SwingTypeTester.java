package javathreads.examples.ch02.example2;

import javathreads.examples.ch02.CharacterDisplayCanvas;
import javathreads.examples.ch02.CharacterEventHandler;
import javathreads.examples.ch02.CharacterListener;
import javathreads.examples.ch02.CharacterSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingTypeTester extends JFrame implements CharacterSource {
    protected RandomCharacterGenerator producer;
    private CharacterDisplayCanvas displayCanvas;
    private CharacterDisplayCanvas feedbackCanvas;
    private JButton quitButton;
    private JButton startButton;
    private CharacterEventHandler handler;
    public SwingTypeTester() {
        initComponents();
    }

    private void initComponents() {
        handler = new CharacterEventHandler();
        displayCanvas = new CharacterDisplayCanvas();
        feedbackCanvas = new CharacterDisplayCanvas(this);
        quitButton = new JButton();
        startButton = new JButton();
        add(displayCanvas, BorderLayout.NORTH);
        add(feedbackCanvas, BorderLayout.CENTER);
        JPanel p = new JPanel();
        startButton.setText("Start");
        quitButton.setText("Quit");
        p.add(startButton);
        p.add(quitButton);
        add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });

        feedbackCanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != KeyEvent.CHAR_UNDEFINED) {
                    newCharacter(c);
                }
            }
        });

        startButton.addActionListener(e -> {
            producer = new RandomCharacterGenerator();
            displayCanvas.setCharacterSource(producer);
            producer.start();
            startButton.setEnabled(false);
            feedbackCanvas.setEnabled(true);
            feedbackCanvas.requestFocus();
        });
    }

    private void newCharacter(int c) {
        handler.fireNewCharacter(this, c);
    }

    private void quit() {
        System.exit(0);
    }

    @Override
    public void addCharacterListener(CharacterListener characterListener) {
        handler.addCharacterListener(characterListener);
    }

    @Override
    public void removeCharacterListener(CharacterListener characterListener) {
        handler.removeCharacterListener(characterListener);
    }

    @Override
    public void nextCharacter() {
        throw new IllegalStateException("We don't produce on demand");
    }

    public static void main(String[] args) {
        new SwingTypeTester().setVisible(true);
    }
}
