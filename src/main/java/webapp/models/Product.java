package webapp.models;

public class Product {

  // Properties
  private int ProductId;
  private int CategoryId;
  private String ProductName;
  private String ProductDescription;
  private int ProductStock;
  private double ProductPrice;

  // Constructors
  public Product() {
  }

  public Product(int ProductId, String ProductName, int CategoryId, String ProductDescription, int ProductStock, double ProductPrice) {
      this.ProductId = ProductId;
      this.ProductName = ProductName;
      this.CategoryId = CategoryId;
      this.ProductDescription = ProductDescription;
      this.ProductStock = ProductStock;
      this.ProductPrice = ProductPrice;
  }

  // Accessor Methods
  public int getProductId()	{
		return this.ProductId;
	}

	public void setProductId(int ProductId)	{
		this.ProductId = ProductId;
  }
    
  public String getProductName() {
		return this.ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
  }

  public int getCategoryId() {
		return this.CategoryId;
	}

	public void setCategoryId(int CategoryId) {
	    this.CategoryId = CategoryId;
	}
    
  public String getProductDescription()	{
		return this.ProductDescription;
	}

	public void setProductDescription(String ProductDescription) {
		this.ProductDescription = ProductDescription;
  }
    
  public int getProductStock() {
		return this.ProductStock;
	}

	public void setProductStock(int ProductStock)	{
		this.ProductStock = ProductStock;
  }
    
  public double getProductPrice() {
		return this.ProductPrice;
	}

	public void setProductPrice(double ProductPrice) {
	    this.ProductPrice = ProductPrice;
  }

}