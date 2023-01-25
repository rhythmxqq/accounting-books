package com.example.bookregister2;


import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookTest {

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }


    @Test
    public void getBookName() {
        Book book = new Book("1984","Джордж Оруэлл","Роман","Есть");
        assertEquals("1984", book.getBookName());
    }
    @Test
    public void getBookAuthor() {
        Book book = new Book("1984","Джордж Оруэлл","Роман","Есть");
        assertEquals("Джордж Оруэлл", book.getBookAuthor());
    }
    @Test
    public void getBookGenre() {
        Book book = new Book("1984","Джордж Оруэлл","Роман","Есть");
        assertEquals("Роман", book.getBookGenre());
    }
    @Test
    public void getBookPresence() {
        Book book = new Book("1984","Джордж Оруэлл","Роман","Есть");
        assertEquals("Есть", book.getBookPresence());
    }
}
