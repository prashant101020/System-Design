package StructuralDesignPattern;

// Creating a Facade to simplify interactions with a complex subsystem
// A HomeTheaterFacade class that provides a simple interface to control various components of a home theater system
class Amplifier {
    public void on() {
        System.out.println("Amplifier is ON");
    }
    public void off() {
        System.out.println("Amplifier is OFF");
    }
    public void setVolume(int level) {
        System.out.println("Amplifier volume set to " + level);
    }
}

class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }
    public void off() {
        System.out.println("DVD Player is OFF");
    }
    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
}

class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }
    public void off() {
        System.out.println("Projector is OFF");
    }
    public void setInput(String source) {
        System.out.println("Projector input set to " + source);
    }
}

class SmartLights {
    public void on() {
        System.out.println("Smart Lights are ON");
    }
    public void off() {
        System.out.println("Smart Lights are OFF");
    }
    public void dim(int level) {
        System.out.println("Smart Lights dimmed to " + level + "%");
    }
}

class StreamingService {
    public void on() {
        System.out.println("Streaming Service is ON");
    }
    public void off() {
        System.out.println("Streaming Service is OFF");
    }
    public void play(String content) {
        System.out.println("Playing content: " + content);
    }
}

//Facade HomeTheaterFacade class that provides a simple interface to control the home theater system
class HomeTheaterFacade {
    private Amplifier amplifier;
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SmartLights smartLights;
    private StreamingService streamingService;

    public HomeTheaterFacade(Amplifier amplifier, DVDPlayer dvdPlayer, Projector projector, SmartLights smartLights, StreamingService streamingService) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.smartLights = smartLights;
        this.streamingService = streamingService;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        smartLights.on();
        smartLights.dim(50);
        projector.on();
        projector.setInput("DVD Player");
        amplifier.on();
        amplifier.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down the home theater...");
        smartLights.off();
        projector.off();
        amplifier.off();
        dvdPlayer.off();
    }
}
public class Facade {
    public static void main(String[] args) throws InterruptedException {
        Amplifier amplifier = new Amplifier();
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SmartLights smartLights = new SmartLights();
        StreamingService streamingService = new StreamingService();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, dvdPlayer, projector, smartLights, streamingService);

        homeTheater.watchMovie("Inception");
        System.out.println("\n--- Movie is playing ---\n");
        Thread.sleep(2000); // Simulate movie duration
        homeTheater.endMovie();
    }
}
