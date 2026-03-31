package StructuralDesignPattern;

//The Bridge pattern is a design pattern used to decouple an abstraction from its implementation, allowing them to vary independently. It is often used when you want to separate the interface of a class from its implementation,
// so that the two can evolve separately without affecting each other.

interface Device{
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
}

class TV implements Device{
    private boolean on = false;
    private int volume = 30;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("TV is now ON");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("TV is now OFF");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        volume = percent;
        System.out.println("TV volume set to " + volume + "%");
    }
}

class Radio implements Device{
    private boolean on = false;
    private int volume = 50;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("Radio is now ON");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("Radio is now OFF");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        volume = percent;
        System.out.println("Radio volume set to " + volume + "%");
    }
}

abstract class Remote{
    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void togglePower(){
        if(device.isEnabled()){
            device.disable();
        }else{
            device.enable();
        }
    }

    public void volumeUp(){
        device.setVolume(device.getVolume() + 10);
    }

    public void volumeDown(){
        device.setVolume(device.getVolume() - 10);
    }
}

class BasicRemote extends Remote{
    public BasicRemote(Device device) {
        super(device);
    }
}

class AdvancedRemote extends Remote{
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute(){
        device.setVolume(0);
        System.out.println("Device muted");
    }
}
public class Bridge {
    public static void main(String[] args) {
        Device tv = new TV();
        Remote basicRemote = new BasicRemote(tv);
        basicRemote.togglePower();
        basicRemote.volumeUp();

        Device radio = new Radio();
        Remote advancedRemote = new AdvancedRemote(radio);
        advancedRemote.togglePower();
        advancedRemote.volumeUp();
        ((AdvancedRemote) advancedRemote).mute();
    }
}
