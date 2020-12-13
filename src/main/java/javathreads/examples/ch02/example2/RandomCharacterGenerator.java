package javathreads.examples.ch02.example2;

import javathreads.examples.ch02.CharacterEventHandler;
import javathreads.examples.ch02.CharacterListener;
import javathreads.examples.ch02.CharacterSource;

import java.util.Random;

public class RandomCharacterGenerator extends Thread implements CharacterSource {
    static char[] chars;
    static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
    static {
        chars = charArray.toCharArray();
    }
    Random random;
    CharacterEventHandler handler;
    private volatile boolean done = false;

    public RandomCharacterGenerator() {
        random = new Random();
        handler = new CharacterEventHandler();
    }

    public int getPauseTime() {
        return (int) (Math.max(1000, 5000 * random.nextDouble()));
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
        handler.fireNewCharacter(this, chars[random.nextInt(chars.length)]);
    }

    @Override
    public void run() {
        while (!done) {
            nextCharacter();
            try {
                Thread.sleep(getPauseTime());
            } catch (InterruptedException ie) {
                return;
            }
        }
    }

    public void setDone() {
        this.done = true;
    }
}
