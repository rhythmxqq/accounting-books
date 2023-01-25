package com.example.bookregister2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GUIApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApp.class.getResource("mainscreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 527);
        stage.setTitle("Реестр книг");

        //Установка иконки
        Image image = new Image(new File("iconLogo.png").toURI().toString());
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}