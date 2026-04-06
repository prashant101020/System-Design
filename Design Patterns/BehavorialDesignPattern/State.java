package BehavorialDesignPattern;


interface DocumentState{
void edit(Document context, String content);
void SubmitForReview(Document context);
void approve(Document context);
void reject(Document context);
void unpublish(Document context);
}

class DraftState implements DocumentState{

    @Override
    public void edit(Document context, String content) {
        System.out.println("Editing document in Draft state: "+content);
    }

    @Override
    public void SubmitForReview(Document context) {
        System.out.println("Submitting document for review");
        context.setState(new UnderReviewState());
    }

    @Override
    public void approve(Document context) {
        System.out.println("Cannot approve document in Draft state");
    }

    @Override
    public void reject(Document context) {
        System.out.println("Cannot reject document in Draft state");
    }

    @Override
    public void unpublish(Document context) {
        System.out.println("Cannot unpublish document in Draft state");
    }
}

class UnderReviewState implements DocumentState{

    @Override
    public void edit(Document context, String content) {
        System.out.println("Editing document in Under Review state: "+content);
    }

    @Override
    public void SubmitForReview(Document context) {
        System.out.println("Document is already under review");
    }

    @Override
    public void approve(Document context) {
        System.out.println("Approving document");
        context.setState(new publishState());
    }

    @Override
    public void reject(Document context) {
        System.out.println("Rejecting document");
        context.setState(new DraftState());
    }

    @Override
    public void unpublish(Document context) {
        System.out.println("Cannot unpublish document in Under Review state");
    }
}
class publishState implements DocumentState{

    @Override
    public void edit(Document context, String content) {
        System.out.println("Editing document in Published state: "+content);
    }

    @Override
    public void SubmitForReview(Document context) {
        System.out.println("Document is already published");
    }

    @Override
    public void approve(Document context) {
        System.out.println("Document is already approved");
    }

    @Override
    public void reject(Document context) {
        System.out.println("Cannot reject document in Published state");
    }

    @Override
    public void unpublish(Document context) {
        System.out.println("Unpublishing document");
        context.setState(new DraftState());
    }
}

class Document{
    private DocumentState state;
    private String content;

    public Document(){
        this.state=new DraftState();
    }
    public void setState(DocumentState state){
        this.state=state;
    }
    public void edit(String content){
        state.edit(this,content);
    }
    public void submitForReview() {
        state.SubmitForReview(this);
    }
    public void approve() {
        state.approve(this);
    }
    public void reject() {
        state.reject(this);
    }

}
public class State {
    public static void main(String[] args) {
        Document document=new Document();
        document.edit("Initial content");
        document.submitForReview();
        document.approve();
        document.edit("Updated content after approval");
        document.reject();
    }
}
