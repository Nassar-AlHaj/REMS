package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.services.UsersDOAImp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AgentProfileController {

    @FXML
    private TableView<UserTableItem> agentTable;
    @FXML
    private TableColumn<UserTableItem, Integer> idColumn;
    @FXML
    private TableColumn<UserTableItem, String> usernameColumn;
    @FXML
    private TableColumn<UserTableItem, String> emailColumn;
    @FXML
    private TableColumn<UserTableItem, String> passwordColumn;
    @FXML
    private TableColumn<UserTableItem, HBox> actionColumn;

    private final UsersDOAImp userDao;
    private final ObservableList<UserTableItem> userTableItems;

    public AgentProfileController() {
        this.userDao = new UsersDOAImp();
        this.userTableItems = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        loadUsersFromDatabase();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        setupActionColumn();
        agentTable.setItems(userTableItems);
    }

    private void setupActionColumn() {
        actionColumn.setCellValueFactory(cellData -> {
            Button editButton = createIconButton("/com/rems/realestatemanagement/img/clarity_note-edit-solid.png", "Edit");
            editButton.setOnAction(event -> showEditDialog(cellData.getValue().getUser()));

            Button deleteButton = createIconButton("/com/rems/realestatemanagement/img/delete.png", "Delete");
            deleteButton.setOnAction(event -> deleteUser(cellData.getValue().getUser()));

            HBox actionBox = new HBox(10, editButton, deleteButton);
            actionBox.setAlignment(Pos.CENTER);
            return new SimpleObjectProperty<>(actionBox);
        });
    }

    private void loadUsersFromDatabase() {
        userTableItems.clear();
        var users = userDao.getAllAgents();
        if (users != null) {
            users.forEach(user -> userTableItems.add(new UserTableItem(user)));
        }
    }

    private Button createIconButton(String imagePath, String tooltip) {
        Button button = new Button();
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(24);
            imageView.setFitWidth(24);
            button.setGraphic(imageView);
            button.setTooltip(new Tooltip(tooltip));
            button.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: transparent;
                -fx-padding: 8px;
                -fx-cursor: hand;
                """);
            setupButtonHoverEffects(button);
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath + " - " + e.getMessage());
        }
        return button;
    }

    private void setupButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #f0f0f0;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent;"));
    }

    private void showEditDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/EditAgentDialog.fxml"));
            DialogPane dialogPane = loader.load();
            EditAgentDialogController controller = loader.getController();
            controller.setUser(user);

            Dialog<ButtonType> dialog = createDialog(dialogPane);
            handleEditDialogResult(dialog, user, controller);
        } catch (IOException e) {
            System.err.println("Error showing edit dialog: " + e.getMessage());
        }
    }

    private Dialog<ButtonType> createDialog(DialogPane dialogPane) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Edit Agent");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UTILITY);
        return dialog;
    }

    private void handleEditDialogResult(Dialog<ButtonType> dialog, User user, EditAgentDialogController controller) {
        dialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                updateUserData(user, controller);
                userDao.update(user);
                loadUsersFromDatabase();
            }
        });
    }

    private void updateUserData(User user, EditAgentDialogController controller) {
        user.setUsername(controller.getName());
        user.setEmail(controller.getEmail());
        String newPassword = controller.getPassword();
        if (newPassword != null) {
            user.setPassword(newPassword);
        }
    }

    private void deleteUser(User user) {
        Alert confirmDialog = createDeleteConfirmDialog();
        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                userDao.delete(user);
                loadUsersFromDatabase();
            }
        });
    }

    private Alert createDeleteConfirmDialog() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Delete");
        confirmDialog.setHeaderText("Delete Agent");
        confirmDialog.setContentText("Are you sure you want to delete this agent?");
        return confirmDialog;
    }

    @FXML
    private void goToAddAgentPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/addagent.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) agentTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error navigating to add agent page: " + e.getMessage());
        }
    }

    private static class UserTableItem {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty username;
        private final SimpleStringProperty email;
        private final SimpleStringProperty password;
        private final User user;

        public UserTableItem(User user) {
            this.user = user;
            this.id = new SimpleIntegerProperty(user.getId());
            this.username = new SimpleStringProperty(user.getUsername());
            this.email = new SimpleStringProperty(user.getEmail());
            this.password = new SimpleStringProperty(hashPassword(user.getPassword()));
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public SimpleStringProperty usernameProperty() {
            return username;
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public SimpleStringProperty passwordProperty() {
            return password;
        }

        public User getUser() {
            return user;
        }

        private String hashPassword(String password) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());
                BigInteger number = new BigInteger(1, hash);
                StringBuilder hashedText = new StringBuilder(number.toString(16));
                while (hashedText.length() < 64) {
                    hashedText.insert(0, "0");
                }
                return hashedText.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error hashing password", e);
            }
        }
    }
}
