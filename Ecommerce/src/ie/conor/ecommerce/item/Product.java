/**
 *  @author Conor Brett (20079837) 
 * 
 * 
 * 
 */



package ie.conor.ecommerce.item;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Product{

    private final StringProperty productName;
    private final StringProperty productDescription;
    private final IntegerProperty productPrice;
    private final StringProperty category;
    private final StringProperty subCategory;
    private final ObjectProperty<LocalDate> productBestBeforeDate;

    
    
    
    /**
     * Default constructor.
     */
    public Product() {
        this(null, 0);
    }
    

    
    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Product(String productName, int productPrice) {
        this.productName = new SimpleStringProperty(productName);
       
        // Some initial dummy data, just for convenient testing.
        this.productDescription = new SimpleStringProperty("Description:");
        this.productPrice = new SimpleIntegerProperty(productPrice);
        this.category = new SimpleStringProperty("Category:");
        this.subCategory = new SimpleStringProperty("Sub-Category:");
        this.productBestBeforeDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 2, 21));
    }
    
    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }
    
    public StringProperty productNameProperty() {
        return productName;
    }
   
    
    public String getDescription() {
        return productDescription.get();
    }

    public void setDescription(String productDescription) {
        this.productDescription.set(productDescription);
    }
    
    public StringProperty productDescriptionProperty() {
        return productDescription;
    }
    
    
    public int getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(int productPrice) {
        this.productPrice.set(productPrice);
    }
    
    public IntegerProperty productPriceProperty() {
        return productPrice;
    }
    
    
    
    

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
    
    public StringProperty categoryProperty() {
        return category;
    }

    
    
    

    public String getSubCategory() {
        return subCategory.get();
    }

    public void setSubCategory(String subCategory) {
        this.subCategory.set(subCategory);
    }
    
    public StringProperty subCategoryProperty() {
        return subCategory;
    }

    
    
    
    public LocalDate getBestBeforeDate() {
        return productBestBeforeDate.get();
    }

    public void setProductBestBeforeDate(LocalDate productBestBeforeDate) {
        this.productBestBeforeDate.set(productBestBeforeDate);
    }
    
    public ObjectProperty<LocalDate> productBestBeforeDateProperty() {
        return productBestBeforeDate;
    }
    
    
}