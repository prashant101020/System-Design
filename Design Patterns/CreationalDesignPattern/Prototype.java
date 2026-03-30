package CreationalDesignPattern;

import java.util.ArrayList;
import java.util.List;

class RecipientList{
    private List<String> to;
    private List<String> cc;

    public RecipientList(List<String> to, List<String> cc) {
        this.to = to;
        this.cc = cc;
    }

    // A deep copy where new objects are being created for the lists.
    // no reference is being shared between the original and the copy
    //original change will not effect the copy and vice versa
    public RecipientList deepCopy() {
        return new RecipientList(new ArrayList<>(to), new ArrayList<>(cc));
    }
    public void addTo(String email) {
        to.add(email);
    }
    public void addCc(String email) {
        cc.add(email);
    }
    public String toString() {
        return "To: " + to.toString() + ", Cc: " + cc.toString();
    }
}

class EmailTemplate{
    private String subject;
    private String body;
    private RecipientList recipientList;

    public EmailTemplate(String subject, String body, RecipientList recipientList) {
        this.subject = subject;
        this.body = body;
        this.recipientList = recipientList;
    }

    // A shallow copy where the reference of the recipientList is being shared between the original and the copy.
    // original change will affect the copy and vice versa
    public EmailTemplate clone() {
        return new EmailTemplate(subject, body, recipientList.deepCopy());
    }
    public void setSubject  (String subject) {
        this.subject = subject;
    }
    public RecipientList getRecipientList () {
        return recipientList;
    }
    public void send() {
        System.out.println("Subject: " + subject + ", Body: " + body + ", Recipients: " + recipientList.toString());
    }

}
public class Prototype {
    public static void main(String[] args) {
    RecipientList baseRecipients = new RecipientList(List.of("my@abs.com","yours@abc.com"),List.of("who@abc.com"));
    EmailTemplate baseTemplate = new EmailTemplate("Hello","This is a template",baseRecipients);
    EmailTemplate marketingEmail = baseTemplate.clone();
    marketingEmail.setSubject("Special Offer!");
    marketingEmail.getRecipientList().addTo("pyss0206@gmail.com");

    EmailTemplate notificationEmail = baseTemplate.clone();
    notificationEmail.setSubject("System Alert");
    notificationEmail.getRecipientList().addTo("py227102@gmail.com");

    marketingEmail.send();
    notificationEmail.send();
    }

}
