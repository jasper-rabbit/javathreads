package javathreads.examples.ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CharacterEventHandler {
    private List<CharacterListener> listeners = new ArrayList<>();

    public void addCharacterListener(CharacterListener characterListener) {
        listeners.add(characterListener);
    }

    public void removeCharacterListener(CharacterListener characterListener) {
        listeners.remove(characterListener);
    }

    public void fireNewCharacter(CharacterSource source, int character) {
        CharacterEvent characterEvent = new CharacterEvent(source, character);
        for (CharacterListener characterListener : listeners) {
            characterListener.newCharacter(characterEvent);
        }

    }
}
