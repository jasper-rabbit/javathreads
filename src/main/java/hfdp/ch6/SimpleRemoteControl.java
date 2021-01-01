package hfdp.ch6;

/**
 * Invoker Class
 */
public class SimpleRemoteControl {
    Command command;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed() {
        command.execute();
    }
}
