public class Factory {
    public static void main(String[] args){
    EmailNotificationCreator email= new EmailNotificationCreator();
    email.send("Hi this mail is from Email");

    SmsNotificationCreator sms=new SmsNotificationCreator();
    sms.send("Hi this is SMS");
    }
}

interface Notification{
    void send(String message);
}

class EmailNotification implements Notification{
   public void send(String message){
        System.out.println("Sending Email Notification..."+message);
    }
}

class SMSNotification implements Notification{
    public void send(String Message){
        System.out.println("Sending SMS Notification..."+Message);
    }
}

abstract class NotificationCreator{
   abstract Notification createNotification();
    public void send(String message){
        Notification notification= createNotification();
        notification.send(message);
    }
}

class EmailNotificationCreator extends NotificationCreator{

    @Override
    Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsNotificationCreator extends NotificationCreator{
    @Override
    Notification createNotification(){
        return new SMSNotification();
    }
}