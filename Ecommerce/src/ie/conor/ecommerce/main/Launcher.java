/**
 *  @author Conor Brett (20079837) 
 * 
 * 
 */






package ie.conor.ecommerce.main;

import ie.conor.ecommerce.item.Product;
import ie.conor.ecommerce.view.EditProductController;
import ie.conor.ecommerce.view.LoginController;
import ie.conor.ecommerce.view.UserController;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;




import javafx.stage.Modality;
import javafx.stage.Stage;

public class Launcher extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of Product.
     */
   
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Conor's Retail Store");

        initRootLayout();

        initLogin();
    }

    
     // Initializes the root layout.
     
    public void initRootLayout() {
        try {
            // Loads root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("/ie/conor/ecommerce/view/RootLayout.fxml"));

            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void initLogin() {
        try {
            // Loads prodcut overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("/ie/conor/ecommerce/view/Login.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(productOverview);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void showAdminScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("/ie/conor/ecommerce/view/Admin.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(productOverview);

            // Give the controller access to the main app.
            UserController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void showUserScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("/ie/conor/ecommerce/view/User.fxml"));
            AnchorPane productOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(productOverview);

            // Give the controller access to the main app.
            UserController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showEditProduct(Product product) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("/ie/conor/ecommerce/view/EditProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditProductController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(product);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
 
    
}