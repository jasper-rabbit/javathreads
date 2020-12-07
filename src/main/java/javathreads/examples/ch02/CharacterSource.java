package javathreads.examples.ch02;

public interface CharacterSource {
    void addCharacterListener(CharacterListener characterListener);
    void removeCharacterListener(CharacterListener characterListener);
    void nextCharacter();
}
