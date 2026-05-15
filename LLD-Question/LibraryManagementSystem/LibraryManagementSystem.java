import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum BookType{
    FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY
}
enum Status{
    LOANED, AVAILABLE,LOST
}
enum MemberStatus{
    ACTIVE, BLACKLISTED,SUSPENDED
}
enum Designation{
    LIBRARIAN, MEMEBER
}
abstract class Book{
    private int bookId;
    private String bookName;
    private String authorName;
    private BookType bookType;
    public Book(int bookId, String bookName, String authorName, BookType bookType){
        this.bookId=bookId;
        this.bookName=bookName;
        this.authorName=authorName;
        this.bookType=bookType;
    }
    public BookType getBookType() {return bookType;}
    public int bookId() {
        return bookId;
    }
    public String getBookName(){
        return bookName;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setBookId(int bookId){
        this.bookId=bookId;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}

class BookItem extends Book {
    Status bookStatus;

    public BookItem(int bookId, String bookName, String authorName, BookType bookType, Status bookStatus) {
        super(bookId, bookName, authorName, bookType);
        this.bookStatus=bookStatus;
    }

}

abstract class User{
    private int userId;
    private String userName;
    private Designation designation;
    public User(int userId, String userName, Designation designation) {
        this.userId = userId;
        this.userName = userName;
        this.designation=designation;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Designation getDesignation(){
        return designation;
    }
}
class Librarian extends User{
    int empId;

    public Librarian(int userId, String userName) {
        super(userId, userName,Designation.LIBRARIAN);
    }

}

class Member extends User{
    int libraryCardNumber;
    MemberStatus memberStatus;
   Member(int userId, String userName,int libraryCardNumber,MemberStatus memberStatus){
       super(userId,userName,Designation.MEMEBER);
       this.libraryCardNumber=libraryCardNumber;
       this.memberStatus=memberStatus;
   }
}
class library{
    List<Book> books;
    List<Member> members;
    List<Librarian> librarians;
    public library(List<Book> books, List<Member> members, List<Librarian> librarians) {
        this.books = books;
        this.members = members;
        this.librarians = librarians;
    }
    void addBook(Book book){
        books.add(book);
    }
    void removeBook(Book book){
        books.remove(book);
    }
    void addMember(Member member){
        members.add(member);
    }
    void removeMember(Member member){
        members.remove(member);
    }
    void addLibrarian(Librarian librarian){
        librarians.add(librarian);
    }
    void removeLibrarian(Librarian librarian){
        librarians.remove(librarian);
    }
}
interface Search{
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByType(BookType bookType);
}
class bookSearch implements Search{
    private Map<String, List<Book>> bookTitles;
    private Map<String, List<Book>> bookAuthors;
    private Map<String, List<Book>> bookTypes;
    public bookSearch(){
        bookTitles=new HashMap<>();
        bookAuthors=new HashMap<>();
        bookTypes=new HashMap<>();
    }
    void addBook(Book book){
        bookTitles.computeIfAbsent(book.getBookName(), k -> new java.util.ArrayList<>()).add(book);
        bookAuthors.computeIfAbsent(book.getAuthorName(), k -> new java.util.ArrayList<>()).add(book);
        bookTypes.computeIfAbsent(book.getBookType().name(), k -> new java.util.ArrayList<>()).add(book);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookTitles.getOrDefault(title, List.of());
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return bookAuthors.getOrDefault(author, List.of());
    }

    @Override
    public List<Book> searchByType(BookType bookType) {
        return bookTypes.getOrDefault(bookType.name(), List.of());
    }
}
interface PaymentProcess{
    void pay();
}
class CardsPayment implements PaymentProcess{
    @Override
    public void pay() {
        System.out.println("Processing card payment...");
    }
}
class UpiPayment implements PaymentProcess{
    @Override
    public void pay() {
        System.out.println("Processing card payment...");
    }
}
class BitCoinPayment implements PaymentProcess{
    @Override
    public void pay() {
        System.out.println("Processing card payment...");
    }
}
class IssueService{
    void processPayment(Member member, BookItem book,String type){

        if(type.equals("CARD")) {
            PaymentProcess paymentProcess = new CardsPayment();
            paymentProcess.pay();
            member.memberStatus = MemberStatus.ACTIVE;
            book.bookStatus = Status.LOANED;
        }
    }
    void issueBook(BookItem book, User user){
        if(book.bookStatus==Status.LOANED || book.bookStatus==Status.LOST){
            System.out.println("Can Not ISSUE the BOOK "+ book.getBookName()+" because it has been "+ book.bookStatus);
        }else{
            if(user.getDesignation()==Designation.LIBRARIAN){
                book.bookStatus=Status.LOANED;
            }else{
                Member member= (Member) user;
                if(member.memberStatus==MemberStatus.BLACKLISTED || member.memberStatus==MemberStatus.SUSPENDED){
                    System.out.println("YOU are "+ member.memberStatus);
                }else{
                    processPayment(member,book,"CARD");
                    System.out.println("BOOK "+ book.getBookName()+" ISSUED to "+ member.getUserName());

                }
            }
        }
    }
}
class LibraryManagementSystem{
    public static void  main(String[] args){
        BookItem book1=new BookItem(1,"The Great Gatsby","F. Scott Fitzgerald",BookType.FICTION,Status.AVAILABLE);
        BookItem book2=new BookItem(2,"A Brief History of Time","Stephen Hawking",BookType.SCIENCE,Status.AVAILABLE);
        Member member1=new Member(1,"Alice",12345,MemberStatus.ACTIVE);
        Librarian librarian1=new Librarian(2,"Bob");
        library library=new library(List.of(book1,book2),List.of(member1),List.of(librarian1));
        IssueService issueService=new IssueService();
        issueService.issueBook(book1,member1);
        issueService.issueBook(book2,librarian1);
        issueService.issueBook(book1,librarian1);
        issueService.issueBook(book2,member1);

        bookSearch bookSearch=new bookSearch();
        bookSearch.addBook(book1);
        bookSearch.addBook(book2);
        System.out.println("Search by Title: "+ bookSearch.searchByTitle("The Great Gatsby").get(0).getBookName());
        System.out.println("Search by Author: "+ bookSearch.searchByAuthor("Stephen Hawking").get(0).getBookName());
        System.out.println("Search by Type: "+ bookSearch.searchByType(BookType.FICTION).get(0).getBookName());
    }
}