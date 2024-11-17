import UseCases.Document;

public class App {
    public static void main(String[] args) throws Exception {
        Document ogDocument = new Document("Commercial proposal", "proposal content", "Nícolas Bassini");

        Document documentCopy = (Document) ogDocument.clone();
        documentCopy.title = "Revised proposal";

        System.out.println(ogDocument.toString());
        System.out.println(documentCopy.toString());
    }
}
