package BehavorialDesignPattern;

//It is a behavioral design pattern that shows how to access elements of a collectoion sequentially
//without exposing its underlying representation.

import java.util.ArrayList;
import java.util.List;

class Notification {
    private final String message;
    private final String type;
    private boolean isRead;

    public Notification(String message, String type, boolean isRead) {
        this.message = message;
        this.type = type;
        this.isRead = isRead;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
    public boolean isRead() {
        return isRead;
    }
    public void markAsRead() {
     this.isRead=true;
    }
    public String toString() {
        return "Notification{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
interface NotificationIterator {
    boolean hasNext();
    Notification next();
}

class NotificationCenter{
    private final List<Notification> notificationList= new ArrayList<>();
    public void add(Notification notification){
        notificationList.add(notification);
    }
   public Notification get(int index){
        return notificationList.get(index);
   }
    public int size(){
        return notificationList.size();
    }
    public NotificationIterator createIterator() {
        return new NotificationIteratorImpl(this);
    }
    public NotificationIterator createUnreadIterator() {
        return new UnreadNotificationIterator(this);
    }
    public NotificationIterator createFilteredIterator(String type) {
        return new FilteredNotificationIterator(this, type);
    }
}

class NotificationIteratorImpl implements NotificationIterator {
    private final NotificationCenter notificationCenter;
    private int currentIndex;


    public NotificationIteratorImpl(NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < notificationCenter.size();
    }

    @Override
    public Notification next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more notifications.");
        }
        return notificationCenter.get(currentIndex++);
    }
}

class FilteredNotificationIterator implements NotificationIterator {
    private final NotificationCenter notificationCenter;
    private int currentIndex;
    private final String type;

    public FilteredNotificationIterator(NotificationCenter notificationCenter, String type) {
        this.notificationCenter = notificationCenter;
        this.currentIndex = 0;
        this.type = type;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < notificationCenter.size()) {
            if (notificationCenter.get(currentIndex).getType().equals(type)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Notification next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more notifications of type: " + type);
        }
        return notificationCenter.get(currentIndex++);
    }
}
class UnreadNotificationIterator implements NotificationIterator {
    private final NotificationCenter notificationCenter;
    private int currentIndex;

    public UnreadNotificationIterator(NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < notificationCenter.size()) {
            if (!notificationCenter.get(currentIndex).isRead()) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Notification next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more unread notifications.");
        }
        return notificationCenter.get(currentIndex++);
    }
}
public class Iterator {
    public static void main(String[] args) {
        NotificationCenter notificationCenter = new NotificationCenter();
        notificationCenter.add(new Notification("You have a new message", "Message", false));
        notificationCenter.add(new Notification("Your order has been shipped", "Order", true));
        notificationCenter.add(new Notification("You have a new friend request", "FriendRequest", false));
        notificationCenter.add(new Notification("Your password was changed", "Security", true));

        System.out.println("All Notifications:");
        NotificationIterator allIterator = notificationCenter.createIterator();
        while (allIterator.hasNext()) {
            System.out.println(allIterator.next());
        }

        System.out.println("\nUnread Notifications:");
        NotificationIterator unreadIterator = notificationCenter.createUnreadIterator();
        while (unreadIterator.hasNext()) {
            System.out.println(unreadIterator.next());
        }

        System.out.println("\nMessage Notifications:");
        NotificationIterator messageIterator = notificationCenter.createFilteredIterator("Message");
        while (messageIterator.hasNext()) {
            System.out.println(messageIterator.next());
        }
    }
}
