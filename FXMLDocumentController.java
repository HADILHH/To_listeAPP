/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package hadil;

import static hadil.data.username;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    
    
       @FXML
    private AnchorPane home_form;
        @FXML
    private Label page_label;
    @FXML
    private Button home_btn;

    @FXML
    private Button myTasck_btn;

    @FXML
    private Button finished_btn;

    @FXML
    private Label home_NAP;

    @FXML
    private Label home_FP;

    @FXML
    private AnchorPane my_plans_form;

    @FXML
    private TextArea my_plans_plan;

    @FXML
    private DatePicker my_plans_startDate;

    @FXML
    private DatePicker my_plans_endDate;

    @FXML
    private Button my_plans_addBtn;

    @FXML
    private Button my_plans_updateBtn;

    @FXML
    private Button my_plans_calerBtn;

    @FXML
    private Button my_plans_deleteBtn;

    @FXML
    private TableView<planData> my_plans_tableView;

    @FXML
    private TableColumn<planData, String> my_plans_col_tasck;

    @FXML
    private TableColumn<planData, String> my_plans_col_startDate;

    @FXML
    private TableColumn<planData, String> my_plans_col_endDate;

    @FXML
    private TableColumn<planData, String> my_plans_col_createDate;

    @FXML
    private TableColumn<planData, String> my_plans_col_Satuts;

    @FXML
    private AnchorPane finishied_form;

    @FXML
    private TableView<?> finishied_tableView;

    @FXML
    private TableColumn<?, ?> finishied_col_idTasck;

    @FXML
    private TableColumn<?, ?> finishied_col_Tasck;

    @FXML
    private TableColumn<?, ?> finishied_col_startDate;

    @FXML
    private TableColumn<?, ?> finishied_col_endDate;

    @FXML
    private TableColumn<?, ?> finishied_col_createDate;

    @FXML
    private TableColumn<?, ?> finishied_col_satute;

    @FXML
    private ChoiceBox<?> finishied_satute;

    @FXML
    private TextField finishied_id_tasck;

    @FXML
     private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Alert alert;
    public void myPlansAddBtn() {

        connect = database.connectDB();

        try {

            if (my_plans_plan.getText().isEmpty() || my_plans_startDate.getValue() == null
                    || my_plans_endDate.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (my_plans_endDate.getValue().isBefore(my_plans_startDate.getValue())) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Date :3");
                    alert.showAndWait();
                } else {

                    String checkPlan = "SELECT plans FROM myplans WHERE plans = '"
                            + my_plans_plan.getText() + "'";

                    prepare = connect.prepareStatement(checkPlan);
                    result = prepare.executeQuery();

                    if (result.next()) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Plan " + my_plans_plan.getText() + " is already recorded");
                        alert.showAndWait();
                    } else {
                        String insertData = "INSERT INTO my plans (plan, startdate, enddate, dateCreated, planner, satute) "
                                + "VALUES(?,?,?,?,?,?)";

                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, my_plans_plan.getText());
                        prepare.setString(2, String.valueOf(my_plans_startDate.getValue()));
                        prepare.setString(3, String.valueOf(my_plans_endDate.getValue()));

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare.setString(4, String.valueOf(sqlDate));
                        prepare.setString(5, "Not Finish");
                        prepare.executeUpdate();

                        myPlansShowData();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<planData> myPlansDataList() {

        ObservableList<planData> listData = FXCollections.observableArrayList();
        
        String url="jdbc:mysql://localhost:3308/tasck";

        try {
           Connection connect =  DriverManager.getConnection(url,"root",""); // root is the default username while "" or empty or null is the default password
            Statement mystate = connect.createStatement();
           String myquery = "INSERT INTO hdl (username,password,date) VALUES ('Hadil','123','2024')";
           
          mystate.executeUpdate(myquery);
           
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
//
//         String selectData = "SELECT * FROM myplans WHERE planner = '"
//                + username.getText() + "'";
//
//        connect = database.connectDB();
//
//        try {
//
//            result = prepare.executeQuery();
//
//            planData pData;
//            while (result.next()) {
//                pData = new planData(result.getInt("id"),
//                        result.getString("plans"), result.getDate("startDate"),
//                        result.getDate("endDate"), result.getDate("dateCreated"),
//                        result.getString("status"), result.getString("planner"));
//
//                listData.add(pData);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return listData;
    }

private ObservableList<planData> myPlansListData;

    public void myPlansShowData() {
        myPlansListData = myPlansDataList();

        my_plans_col_tasck.setCellValueFactory(new PropertyValueFactory<>("plan"));
        my_plans_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        my_plans_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        my_plans_col_createDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        my_plans_col_Satuts.setCellValueFactory(new PropertyValueFactory<>("status"));

        my_plans_tableView.setItems(myPlansListData);
    }
    private int planID;

    public void myPlansSelectData() {

        planData pData = my_plans_tableView.getSelectionModel().getSelectedItem();
        int num = my_plans_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        planID = pData.getPlanID();

        my_plans_plan.setText(pData.getPlan());
        my_plans_startDate.setValue(LocalDate.parse(String.valueOf(pData.getStartDate())));
        my_plans_endDate.setValue(LocalDate.parse(String.valueOf(pData.getEndDate())));

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            my_plans_form.setVisible(false);
            finishied_form.setVisible(false);

            page_label.setText("HOME");

            
        } else if (event.getSource() == myTasck_btn) {
            home_form.setVisible(false);
            my_plans_form.setVisible(true);
            finishied_form.setVisible(false);


            page_label.setText("MY TASCKS");
        } else if (event.getSource() == finished_btn) {
            home_form.setVisible(false);
            my_plans_form.setVisible(false);
            finishied_form.setVisible(true);

            page_label.setText("FINISHED TASCKS");

        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        myPlansShowData();
    }    

    
    
}
