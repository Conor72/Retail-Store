/**
 *  @author Conor Brett (20079837) 
 * 
 * 	
 */


package ie.conor.ecommerce.view;

import ie.conor.ecommerce.date.DateUtil;
import ie.conor.ecommerce.item.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditProductController {

    @FXML
    private TextField productNameField;
    @FXML
    private TextField productDescriptionField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField subCategoryField;
    @FXML
    private TextField productBestBeforeDateField;


    private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of EditProduct.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * 
     * @param product
     */
    public void setPerson(Product product) {
        this.product = product;

        productNameField.setText(product.getProductName());
        productDescriptionField.setText(product.getDescription());
        productPriceField.setText(Integer.toString(product.getProductPrice()));
        categoryField.setText(product.getCategory());
        subCategoryField.setText(product.getSubCategory());
        productBestBeforeDateField.setText(DateUtil.format(product.getBestBeforeDate()));
        productBestBeforeDateField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked ok

     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            product.setProductName(productNameField.getText());
            product.setDescription(productDescriptionField.getText());
            product.setProductPrice(Integer.parseInt(productPriceField.getText()));
            product.setCategory(categoryField.getText());
            product.setCategory(categoryField.getText());
            product.setProductBestBeforeDate(DateUtil.parse(productBestBeforeDateField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks the cancel button
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (productNameField.getText() == null || productNameField.getText().length() == 0) {
            errorMessage += "Invalid product name!\n"; 
        }
        if (productDescriptionField.getText() == null || productDescriptionField.getText().length() == 0) {
            errorMessage += "Invalid description!\n"; 
        }
        if (productPriceField.getText() == null || productPriceField.getText().length() == 0) {
            errorMessage += "Invalid price!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(productPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid price (must be an integer)!\n"; 
            }
        }

        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "Invalid Category!\n"; 
        }
        
        if (subCategoryField.getText() == null || subCategoryField.getText().length() == 0) {
            errorMessage += "Invalid Sub-Category!\n"; 
        }

        if (productBestBeforeDateField.getText() == null || productBestBeforeDateField.getText().length() == 0) {
            errorMessage += "Invalid Best Before Date!\n";
        } else {
            if (!DateUtil.validDate(productBestBeforeDateField.getText())) {
                errorMessage += "Invalid Best Before Date. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}