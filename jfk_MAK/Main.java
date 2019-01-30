package jfk_MAK;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfk_MAK.Model.Engine.Nashorn;
import jfk_MAK.Model.Entity;
import jfk_MAK.Model.CsvFile;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/mainView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        System.out.println("Jakub K");
        CsvFile csvFile = new CsvFile();
        List<Entity> entities = csvFile.readFromCsvFile("clients.csv");

        Nashorn nashorn = new Nashorn(entities);

        nashorn.run("for each(e in entities) {\n" +
                "\tprint(e.name);\n" +
                "}"
        );
        nashorn.run("entities[0].name = 'Marek';");
        nashorn.run("for each(e in entities) {\n" +
                "\tprint(e.name);\n" +
                "}"
        );

        System.out.println(nashorn.run("dsfdf =sdf + sdfsdf ff"));
    }


    public static void main(String[] args) {
        launch(args);


    }
}
