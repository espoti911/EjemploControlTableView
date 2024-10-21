package es.ieslosmontecillos.ejemplocontrolbasicotableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonTable extends Application {

    private TableView<Person> table;

    @Override
    public void start(Stage stage) throws IOException
    {
        //Creacion de la lista observable de personas
        final ObservableList<Person> data = FXCollections.observableArrayList(
                new Person("Jacob", "Smith", "jacob.smith@example.com"),
                new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                new Person("Ethan", "Williams", "ethan.williams@example.com"),
                new Person("Emma", "Jones", "emma.jones@example.com"),
                new Person("Michael", "Brown", "michael.brown@example.com")
        );

        //Creacion de la tabla
        table = new TableView<>();
        table.setEditable(true);

        //Columnas principales
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        firstNameCol.setMinWidth(100);
        lastNameCol.setMinWidth(100);
        emailCol.setMinWidth(200);

        /*
            Asignacion de las propiedades de Person a las columnas correspondientes
            Nota: las strings asignadas deben corresponder con el nombre de las variables property de la clase
         */
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Person, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setFirstName(t.getNewValue());
                });

        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Person, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setLastName(t.getNewValue());
                });

        emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        emailCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Person, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setEmail(t.getNewValue());
                });

        //Asignacion de columnas y datos a la tabla
        table.setItems(data);
        table.getColumns().setAll(firstNameCol, lastNameCol, emailCol);

        //Controles para añadir una persona
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());

        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()
            ));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });

        HBox hb = new HBox();

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

        //Creacion de la escena
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        vbox.getChildren().addAll(label, table, hb);

        Scene scene = new Scene(new Group(vbox), 450, 500);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}