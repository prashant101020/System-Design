package BehavorialDesignPattern;

import java.util.ArrayList;
import java.util.List;

//It defines an object (MEdiator) to encapsulate how a set of object interacts.
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
abstract class User{
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}
class chatRoom implements ChatMediator{
    private List<User> users;

    public chatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User user) {
        for(User u: users){
            if(u!=user){
                u.receive(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
class chatUser extends User{

    public chatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name+" sends: "+message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name+" receives: "+message);
    }
}
public class Mediator {
    public static void main(String[] args) {
        ChatMediator mediator = new chatRoom();

        User user1 = new chatUser(mediator, "Alice");
        User user2 = new chatUser(mediator, "Bob");
        User user3 = new chatUser(mediator, "Charlie");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        user1.send("Hello everyone!");
        user2.send("Hi Alice!");
        user3.send("Hey Alice and Bob!");
    }
}
