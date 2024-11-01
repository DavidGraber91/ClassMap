
package org.example.tala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String path;
        File file = new File("date\\sec");
        File dir = new File(("date"));
        if(file.exists())
            path = "log-in.fxml";
        else path = "sign_up.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));//
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        //stage.setResizable(false);


        Image image = new Image(getClass().getResourceAsStream("icons/Designer_icn.png"));
        stage.getIcons().add(image);
        stage.setTitle("כניסה");
        if(file.exists() || !dir.exists())
            stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}