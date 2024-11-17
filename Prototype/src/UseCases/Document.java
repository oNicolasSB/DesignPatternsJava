package UseCases;

import Abstractions.Prototype;

public class Document implements Prototype {

    public String title;
    public String content;
    private String author;

    public Document(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Override
    public Prototype clone() {
        return new Document(this.title, this.content, this.author);
    }

    public String toString() {
        return "Title: " + this.title + "\n Content: " +
                this.content + "\n Author: " + this.author;
    }

}
