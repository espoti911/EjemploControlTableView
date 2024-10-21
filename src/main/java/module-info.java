module es.ieslosmontecillos.ejemplocontrolbasicotableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.ejemplocontrolbasicotableview to javafx.fxml;
    exports es.ieslosmontecillos.ejemplocontrolbasicotableview;
}