package tasks.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TaskIOService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NewControllerTest {

    public static File savedTasksFile = new File("data/tasks.txt");
    private final ArrayTaskList savedTasksList = new ArrayTaskList();

    private TextField fieldTitle;
    private DatePicker datePickerStart;
    private TextField txtFieldTimeStart;
    private DatePicker datePickerEnd;
    private TextField txtFieldTimeEnd;
    private TextField fieldInterval;
    private CheckBox checkBoxActive;
    private CheckBox checkBoxRepeated;

    @Test
    void saveChanges() throws InterruptedException {
        Platform.startup(() -> {
            this.fieldTitle = new TextField();
            this.datePickerStart = new DatePicker();
            this.txtFieldTimeStart = new TextField();
            this.datePickerEnd = new DatePicker();
            this.txtFieldTimeEnd = new TextField();
            this.fieldInterval = new TextField();
            this.checkBoxActive = new CheckBox();
            this.checkBoxRepeated = new CheckBox();

            this.fieldTitle.setText("Task1");
            this.datePickerStart.setValue(LocalDate.of(2025, 1, 1));
            this.txtFieldTimeStart.setText("01:00");
            this.datePickerEnd.setValue(LocalDate.of(2025, 1, 2));
            this.txtFieldTimeEnd.setText("03:00");
            this.fieldInterval.setText("30");
            this.checkBoxActive.setSelected(true);
            this.checkBoxRepeated.setSelected(false);

            NewController newController = new NewController();
            newController.saveChanges();
        });


        if (savedTasksFile.length() != 0) {
            try {
                TaskIOService.readBinary(savedTasksList, savedTasksFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Task task = savedTasksList.getTask(savedTasksList.size() - 1);
        System.out.println("Saved task: " + task);
        System.out.println(savedTasksList.size());



        System.out.println(task);
    }
}