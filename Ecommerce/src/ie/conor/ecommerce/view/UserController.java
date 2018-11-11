/**
 *  @author Conor Brett (20079837) 
 * 
 * 
 * 
 */




package ie.conor.ecommerce.view;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import ie.conor.ecommerce.date.DateUtil;
import ie.conor.ecommerce.item.Product;
import ie.conor.ecommerce.main.Launcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class UserController implements Initializable {
	@FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productPriceColumn = new TableColumn<>("Item#");
    @FXML
   int productTotalCount = 0; 		//Counts the amount of items in basket
    @FXML
    int cartTotal = 0;
   @FXML
   String productCaluclation = "";
    
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productDescriptionLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label subCategoryLabel;
    @FXML
    private Label productBestBeforeDateLabel;
    @FXML
    private Label cartNumberLabel;
    @FXML
    private Label totalPriceLabel;
    
    // Reference to the main application.
    private Launcher mainApp;
    
    
    
    
    
    ObservableList<Product> productData = FXCollections.observableArrayList();

    // Constructor
     
     public UserController() {
         // Add some sample data
     }

    // Returns Data as an observable list
     public ObservableList<Product> getProductData() {
         return productData;
     }

    
    

    
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Launcher mainApp) {
        this.mainApp = mainApp;

    }

  
    	
  @FXML
	private void  AddToCart()  {
		
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	productTable.getItems();
            
            productTotalCount++;
            cartNumberLabel.setText("" + productTotalCount);
            
            cartTotal = cartTotal + productTable.getItems().get(selectedIndex).getProductPrice();

            totalPriceLabel.setText("" + cartTotal);
       
        } 
        else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            
            alert.showAndWait();
        }
  }
  
  
  
  @FXML
  private void  Checkout()  {
		
   
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.initOwner(mainApp.getPrimaryStage());
          alert.setTitle("Checkout");
          alert.setHeaderText("Checkout Details");
          
          alert.setContentText("Thank you for shopping \n" + "Total Price: " +  cartTotal );
          totalPriceLabel.setText("" + 0);
          cartNumberLabel.setText("" + 0);
        
          
          alert.showAndWait();
      }

    
  
	@FXML
	private void handleBackToLogin() {
		 
		 mainApp.initLogin();
		 
		 
	 }
	

	
	public void initialize(URL location, ResourceBundle resources) {
		try {
    		loadProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    // Initialize the product table with the a column for Product name and the price.
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty()); //.getValue() gets productName and updates table
        productPriceColumn.setCellValueFactory(cellData ->cellData.getValue().productPriceProperty().asObject()); //.asObject gets an integer value
        
        productTable.setItems(getProductData());
        
        
        
        
    }
	

	
	private void showProductDetails(Product product) {
    	
        if (product != null) {
            // Fill the labels with info from the product object.
            productNameLabel.setText(product.getProductName());
            productDescriptionLabel.setText(product.getDescription());
            categoryLabel.setText(product.getCategory());
            productPriceLabel.setText(Integer.toString(product.getProductPrice()));
            subCategoryLabel.setText(product.getSubCategory());
            productBestBeforeDateLabel.setText(DateUtil.format(product.getBestBeforeDate()));
            
        } else {
            // Person is null, remove all the text.
        	 productNameLabel.setText("");
        	 productDescriptionLabel.setText("");
        	 categoryLabel.setText("");
        	 productPriceLabel.setText("");
        	 subCategoryLabel.setText("");
        	 productBestBeforeDateLabel.setText("");
        	  
        }
        
    }
    

    
    
    @FXML
    private void handleNewProduct() throws Exception {
    	
    	
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());

          
    	  
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showEditProduct(tempProduct);
        if (okClicked) {
        	getProductData().add(tempProduct);
        	saveProducts();
        }
    }
    
    

    /**
     * Called when Edit is clicked
     * @throws Exception 
     */
    @FXML
    private void handleEditProduct() throws Exception {
    	
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
    	 
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = mainApp.showEditProduct(selectedProduct);
            if (okClicked) {
                showProductDetails(selectedProduct);
                saveProducts();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            
            alert.showAndWait();
        }
    }

	
	
	@FXML
    private void handleDeleteProduct() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            
            alert.showAndWait();
        }
    }
    


	
	
    //XML
    

     public void saveProducts() throws Exception

     {

     XStream xstream = new XStream(new DomDriver());

     ObjectOutputStream out = xstream.createObjectOutputStream

     (new FileWriter("products.xml"));

     out.writeObject(productData);

     out.close();

     }

     @SuppressWarnings("unchecked")
	public void loadProducts() throws Exception

     {

     XStream xstream = new XStream(new DomDriver());

     ObjectInputStream is = xstream.createObjectInputStream

     (new FileReader("products.xml"));

     productData = (ObservableList<Product>) is.readObject();

     is.close();

     }


}