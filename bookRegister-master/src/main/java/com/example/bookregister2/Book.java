package com.example.bookregister2;

import javafx.beans.property.SimpleStringProperty;

public class Book {

    Integer bookID;
    private SimpleStringProperty bookName, bookAuthor, bookGenre, bookPresence;

    public Book(Integer bookID, String bookName, String bookAuthor, String bookGenre, String bookPresence){
        this.bookID = bookID;
        this.bookName = new SimpleStringProperty(bookName);
        this.bookAuthor = new SimpleStringProperty(bookAuthor);
        this.bookGenre = new SimpleStringProperty(bookGenre);
        this.bookPresence = new SimpleStringProperty(bookPresence);
    }

    public Book(String bookName, String bookAuthor, String bookGenre, String bookPresence){
        this.bookName = new SimpleStringProperty(bookName);
        this.bookAuthor = new SimpleStringProperty(bookAuthor);
        this.bookGenre = new SimpleStringProperty(bookGenre);
        this.bookPresence = new SimpleStringProperty(bookPresence);
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName.get();
    }

    public SimpleStringProperty bookNameProperty() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }

    public String getBookAuthor() {
        return bookAuthor.get();
    }

    public SimpleStringProperty bookAuthorProperty() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor.set(bookAuthor);
    }

    public String getBookGenre() {
        return bookGenre.get();
    }

    public SimpleStringProperty bookGenreProperty() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre.set(bookGenre);
    }

    public String getBookPresence() {
        return bookPresence.get();
    }

    public SimpleStringProperty bookPresenceProperty() {
        return bookPresence;
    }

    public void setBookPresence(String bookPresence) {
        this.bookPresence.set(bookPresence);
    }
}
