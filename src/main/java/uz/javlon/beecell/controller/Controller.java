package uz.javlon.beecell.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uz.javlon.beecell.model.Provider;
import uz.javlon.beecell.model.Transaction;
import uz.javlon.beecell.utils.Utils;

import java.util.ArrayList;

public class Controller {

    public MenuItem menuClose;
    public MenuItem menuSave;
    public MenuItem menuOptions;
    public MenuItem menuQuit;
    public TextField phone;
    public TextField fieldAmount;
    public TextField fieldPayID;
    public Label status;
    public Button save;
    public Label id;
    //    public ComboBox<String> comboProvider;
    public TableView<Transaction> table;
    public Label leftStatus;
    public Label rightStatus;
    public Button btnDelete;

    ArrayList<Transaction> transactions = new ArrayList<Transaction>(0);

    public void menuNew(ActionEvent actionEvent) {

    }

    public void menuOpen(ActionEvent actionEvent) {

    }

    @FXML
    void initialize() {
//        assert comboProvider != null : "fx:id=\"combo\" was not injected: check your FXML file 'ComboboxExample.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected
//        comboProvider.getItems().clear();

//        for (Provider item : Provider.values()) {
//            comboProvider.getItems().add(item.name());
//        }

//        comboProvider.getSelectionModel().clearAndSelect(0);

        table.getColumns().add(newColumn("ID", 60, "id"));
        table.getColumns().add(newColumn("PAY_ID", 70, "payId"));
        table.getColumns().add(newColumn("Phone Number", 150, "phone"));
        table.getColumns().add(newColumn("Amount", 100, "amount"));
        table.getColumns().add(newColumn("Provider", 100, "providerString"));
        table.getColumns().add(newColumn("Status", 100, "status"));

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Transaction>() {
            @Override
            public void changed(ObservableValue<? extends Transaction> observableValue, Transaction transaction, Transaction transaction2) {
                id.setText(transaction2.getId());
                fieldAmount.setText(transaction2.getAmount());
                fieldPayID.setText(transaction2.getPayId());
                phone.setText(transaction2.getPhone());

                status.setText(transaction2.getStatus());
            }
        });

    }

    private TableColumn<Transaction, String> newColumn(String name, int prefAndMin, String fieldName) {
        TableColumn<Transaction, String> column = new TableColumn<Transaction, String>(name);
        column.setMinWidth(prefAndMin);
        column.setPrefWidth(prefAndMin);
        column.setMaxWidth(500);
        column.setResizable(false);
        column.setCellValueFactory(new PropertyValueFactory<Transaction, String>(fieldName));
        return column;
    }

    ObservableList<Transaction> data = FXCollections.observableArrayList(
    );

    public void saveOrUpdate(ActionEvent actionEvent) {

        String phoneNumber = phone.getText();
        if (Utils.validPhone(phoneNumber)) {
            saveTransaction(phoneNumber, fieldAmount.getText(), fieldPayID.getText());
        } else {
//            Tooltip tooltip = new Tooltip("Invalid phone number");
//            phone.setTooltip(tooltip);
            final Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Button button = new Button("OK");

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    dialogStage.close();
                }
            });

            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text("Invalid phone number\n"), button).
                    alignment(Pos.CENTER).padding(new Insets(15)).build()));
            dialogStage.show();
//            tooltip.show();
        }
    }

    private void saveTransaction(String phoneNumber, String amount, String payId) {
        Transaction transaction = new Transaction(phoneNumber, amount, payId);

        if (transaction.getProvider() == Provider.UCELL) {
            transaction.setPayId(payId);
        }

        data.add(transaction);

        addTransactionToTable();
    }

    private void addTransactionToTable() {
        table.setItems(data);
    }

    public void onDelete(ActionEvent actionEvent) {
        data.remove(table.getSelectionModel().getSelectedItem());
        table.setItems(data);
    }


}
