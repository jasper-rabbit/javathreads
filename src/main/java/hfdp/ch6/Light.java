package hfdp.ch6;

/**
 * Receiver class
 */
public class Light {
    private String place;

    public Light() {
        this.place = "";
    }

    public Light(String place) {
        this.place = place;
    }

    public void on() {
        System.out.println(place + " Light is On");
    }

    public void off() {
        System.out.println(place + " Light is off");
    }
}
