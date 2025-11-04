package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.EmployeeProgress;
import bd.edu.seu.office_management.Service.EmpProgressService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProgressEmployeeController implements Initializable
{
 //field
    @FXML
    private TextField dateField;
    @FXML
    private TextField taskField;
    @FXML
    private TextField challengesField;
    @FXML
    private TextField skillUtilizedField;
    @FXML
    private TextField nextStepField;
    @FXML
    private TextField rateField;


    //table
    @FXML
    private TableView<EmployeeProgress> progressTable;

    @FXML
    private TableColumn<EmployeeProgress, String> challengesColumn;

    @FXML
    private TableColumn<EmployeeProgress, String> dateColumn;

    @FXML
    private TableColumn<EmployeeProgress, String> nextStepColumn;


    @FXML
    private TableColumn<EmployeeProgress, String> rateColumn;

    @FXML
    private TableColumn<EmployeeProgress, String> skillUtilizeColumn;

    @FXML
    private TableColumn<EmployeeProgress, String> taskColumn;

    //select update delete

    @FXML
    private Label DateLabel;
    @FXML
    private Label challengesLabel;
    @FXML
    private Label nextLabel;
    @FXML
    private Label rateLabel;
    @FXML
    private Label skillsLabel;
    @FXML
    private Label taskLabel;
    //table er data text field e anar jonno
    @FXML
    private TextField challengesSelect;
    @FXML
    private TextField dateSelect;
    @FXML
    private TextField nextSelect;
    @FXML
    private TextField rateSelect;
    @FXML
    private TextField skillsSelect;
    @FXML
    private TextField taskSelect;

    //////


 //save
 @FXML
 void save(ActionEvent event) {
     String date=dateField.getText();
     String task=taskField.getText();
     String challenges=challengesField.getText();
     String skill=skillUtilizedField.getText();
     String nextStep=nextStepField.getText();
     String rate=rateField.getText();

   //object

     EmployeeProgress employeeProgress=new EmployeeProgress(date,task,challenges,skill,nextStep,rate);
     HelloApplication.progress=employeeProgress;


     //service
     EmpProgressService empProgressService=new EmpProgressService();
     boolean isSaved= empProgressService.progress(employeeProgress);



     if(isSaved)
     {
         Alert alert=new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Success");
         alert.setContentText("Success");
         alert.showAndWait();

     }
     else {
         Alert alert=new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setContentText("Failed to go to payment scene");
         alert.showAndWait();


     }


 }


    ObservableList<EmployeeProgress> employeeProgressObservableList;  //bahire declare
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            challengesColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getChallenges()));
            taskColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getTaskCompleted()));
            dateColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getDate()));
            nextStepColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getNextSteps()));
            skillUtilizeColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getSkillUtilized()));
            rateColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getRate()));



            //ObservableList<EmployeeProgress> /////coz bahire already declared
            employeeProgressObservableList= FXCollections.observableArrayList();

        //from database
           EmpProgressService empProgressService=new EmpProgressService();
           List<EmployeeProgress> detailList=empProgressService.getProgress();
           employeeProgressObservableList.addAll(detailList);//////bahire declare
        //

            progressTable.setItems(employeeProgressObservableList);
        }




    //To Select
    EmployeeProgress selectProgress;
    @FXML
    public void select(MouseEvent event)
    {
        //delete
        selectProgress = progressTable.getSelectionModel().getSelectedItem();

        //update
        if (selectProgress !=null){
            DateLabel.setText(selectProgress.getDate());
            taskLabel.setText(selectProgress.getTaskCompleted());
            challengesLabel.setText(selectProgress.getChallenges());
            skillsLabel.setText(selectProgress.getSkillUtilized());
            nextLabel.setText(selectProgress.getNextSteps());
            rateLabel.setText(selectProgress.getRate());
            //
            dateSelect.setText(selectProgress.getDate());
            taskSelect.setText(selectProgress.getTaskCompleted());
            challengesSelect.setText(selectProgress.getChallenges());
            skillsSelect.setText(selectProgress.getSkillUtilized());
            nextSelect.setText(selectProgress.getNextSteps());
            rateSelect.setText(selectProgress.getRate());


        }


    }



    //delete
    @FXML
    public void delete(ActionEvent event)
    {


        if(selectProgress!=null){
            EmpProgressService empProgressService=new EmpProgressService();
            empProgressService.deleteInfo(selectProgress);

            employeeProgressObservableList.clear();
            employeeProgressObservableList.addAll(empProgressService.getProgress());


            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Success to delete");
            alert.showAndWait();

        }


    }


    //update
    @FXML
    public void update(ActionEvent event)
    {

        String date=dateSelect.getText();
        String task= taskSelect.getText();
        String challenges=challengesSelect.getText();
        String skill=skillsSelect.getText();
        String next= nextSelect.getText();
        String rate= rateSelect.getText();

        //System.err.println("Q: " + task);
        EmployeeProgress employeeProgress=new EmployeeProgress(task,challenges,skill,next,rate,date);

        EmpProgressService empProgressService=new EmpProgressService();
        boolean isUpdated= empProgressService.updateInfo(employeeProgress);

        if(isUpdated)
        {

            employeeProgressObservableList.clear();
            employeeProgressObservableList.addAll(empProgressService.getProgress());


            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Success to update");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to update");
            alert.showAndWait();
        }
    }



    @FXML
    void back(ActionEvent event) {
        HelloApplication.changeScene("employee_dash");

    }


    @FXML
    void home(ActionEvent event) {
        HelloApplication.changeScene("login");

    }


    }

