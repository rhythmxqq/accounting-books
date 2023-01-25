package com.example.bookregister2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreenController implements Initializable {

    //Конфигурация таблицы
    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, Integer> bookIDColumn;
    @FXML
    private TableColumn<Book, String> bookAuthorColumn;

    @FXML
    private TableColumn<Book, String> bookGenreColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> bookPresenceColumn;

    //Переменные, использующиеся для добавления книги
    @FXML
    private TextField textFieldAuthor;

    @FXML
    private TextField textFieldGenre;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPresenсe;

    //Строка поиска
    @FXML
    private TextField searchBar;

    //Создание списка книг
    ObservableList<Book> books = FXCollections.observableArrayList();

    //Инициализация класса контроллера
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String bookViewQuery = "SELECT bookID, bookName, bookAuthor, bookGenre, bookPresence FROM books";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(bookViewQuery);

            while (queryOutput.next()){

                Integer queryID = queryOutput.getInt("bookID");
                String queryName = queryOutput.getString("bookName");
                String queryAuthor = queryOutput.getString("bookAuthor");
                String queryGenre= queryOutput.getString("bookGenre");
                String queryPresence = queryOutput.getString("bookPresence");

                books.add(new Book(queryID, queryName, queryAuthor, queryGenre, queryPresence));

            }
        } catch (SQLException e) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        //Установка колонок в таблице
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        bookGenreColumn.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        bookPresenceColumn.setCellValueFactory(new PropertyValueFactory<>("bookPresence"));

        //Загрузка данных
        tableView.setItems(books);
        tableView.setEditable(true);
        bookNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bookAuthorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bookGenreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bookPresenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        search();

    }

    //Кнопка удаления
    @FXML
    void buttonDelete(ActionEvent event) {

        tableView.setItems(books);

        if(books.size() == 1) return;
        ObservableList<Book> selectedRows, allBooks;
        allBooks = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Book book : selectedRows) {

            String dbbook = "DELETE FROM books WHERE bookID = " + book.getBookID();
            DBEdit(dbbook);

            allBooks.remove(book);

        }
        search();
    }

    //Редактирование
    @FXML
    void onEditChangedAuthor(TableColumn.CellEditEvent<Book, String> bookStringCellEditEvent) {
        Book book = tableView.getSelectionModel().getSelectedItem();
        book.setBookAuthor(bookStringCellEditEvent.getNewValue());

        String dbbook = "UPDATE books SET bookAuthor = '" + book.getBookAuthor() + "' WHERE bookID = " + book.getBookID();

        DBEdit(dbbook);

    }

    @FXML
    void onEditChangedGenre(TableColumn.CellEditEvent<Book, String> bookStringCellEditEvent) {
        Book book = tableView.getSelectionModel().getSelectedItem();
        book.setBookGenre(bookStringCellEditEvent.getNewValue());

        String dbbook = "UPDATE books SET bookGenre = '" + book.getBookGenre() + "' WHERE bookID = " + book.getBookID();

        DBEdit(dbbook);
    }

    @FXML
    void onEditChangedName(TableColumn.CellEditEvent<Book, String> bookStringCellEditEvent) {
        Book book = tableView.getSelectionModel().getSelectedItem();
        book.setBookName(bookStringCellEditEvent.getNewValue());

        String dbbook = "UPDATE books SET bookName = '" + book.getBookName() + "' WHERE bookID = " + book.getBookID();

        DBEdit(dbbook);
    }

    @FXML
    void onEditChangedPresence(TableColumn.CellEditEvent<Book, String> bookStringCellEditEvent) {
        Book book = tableView.getSelectionModel().getSelectedItem();
        book.setBookPresence(bookStringCellEditEvent.getNewValue());

        String dbbook = "UPDATE books SET bookPresence = '" + book.getBookPresence() + "' WHERE bookID = " + book.getBookID();

        DBEdit(dbbook);
    }

    //Кнопка добавления в список
    @FXML
    public void buttonAdd(ActionEvent event) {

        if (textFieldName.getText().isEmpty() || textFieldAuthor.getText().isEmpty() || textFieldGenre.getText().isEmpty() || textFieldPresenсe.getText().isEmpty()){
            return;
        }
        Book newBook = new Book(books.get(books.size() - 1).getBookID() + 1,
                textFieldName.getText(),
                textFieldAuthor.getText(),
                textFieldGenre.getText(),
                textFieldPresenсe.getText());

        tableView.setItems(books);
        tableView.getItems().add(newBook);

        String dbbook = "INSERT books(bookID, bookName, bookAuthor, bookGenre, bookPresence) VALUES (" + (books.get(books.size() - 1).getBookID()) + ", '" + textFieldName.getText() + "', '" +  textFieldAuthor.getText()+ "', '" + textFieldGenre.getText()+ "', '" + textFieldPresenсe.getText() +"')";

        DBEdit(dbbook);

        search();
    }

    //Метод для работы с базой данных
    public void DBEdit(String s){
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getDBConnection();

        try {
            Statement statement = connectDB.createStatement();
            int rows = statement.executeUpdate(s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Метод для работы строки поиска
    public void search(){
        FilteredList<Book> filteredData = new FilteredList<>(books, book -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Book -> {
                //Если значение поиска отсутствует, то отобразит все записи или любые записи, которые есть на данный момент
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (Book.getBookName().toLowerCase().contains(searchKeyword)) {
                    return true; //Найдено совпадение в bookName
                } else if (Book.getBookAuthor().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Book.getBookGenre().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Book.getBookPresence().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else
                    return false; //Совпадения не найдены
            });
        });

        SortedList<Book> sortedData = new SortedList<>(filteredData);

        //Связывает отсортированный вариант с TableView
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        //Применяет отсортированные данные в TableView
        tableView.setItems(sortedData);
    }
}