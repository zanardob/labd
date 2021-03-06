package controller;

import database.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SchemaDDLController implements Initializable {
    @FXML TextArea txaDDL;

    private DataManager dm;
    private ClipboardContent content;
    private final Clipboard clipboard = Clipboard.getSystemClipboard();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dm = new DataManager();
        content = new ClipboardContent();
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) txaDDL.getScene().getWindow();
        stage.close();
    }

    /**
     * Puts the text from the TextArea into the
     * system's clipboard for ease of access
     */
    public void copyCode(ActionEvent actionEvent) {
        content.clear();
        content.putString(txaDDL.getText());
        clipboard.setContent(content);
    }

    /**
     * Fills the TextArea with the schema DDL code
     */
    void fillTextArea(ActionEvent actionEvent){
        ArrayList<String> tableDDLs = dm.getTableDDL();

        // Clear the TextArea
        txaDDL.setText("");

        // For each table on the list, add its DDL to the TextArea
        for(String s : tableDDLs){
            txaDDL.appendText(s);
        }
    }
}
