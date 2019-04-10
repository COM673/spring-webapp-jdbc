package webapp.models;

public class Category {

    // Properties
    private int CategoryId;
    private String CategoryName;
    private String CategoryDescription;

    // Constructors
    public Category() {

    }

    public Category(int CategoryId, String CategoryName, String CategoryDescription) {
        this.CategoryId = CategoryId;
        this.CategoryName = CategoryName;
        this.CategoryDescription = CategoryDescription;
    }

    // Accessor Methods
    public int getCategoryId()	{
		return this.CategoryId;
	}

	  public void setCategoryId(int CategoryId)	{
		this.CategoryId = CategoryId;
    }
    
    public String getCategoryName()	{
		return this.CategoryName;
	}

	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
    }
    
    public String getCategoryDescription()	{
		return this.CategoryDescription;
	}

	public void setCategoryDescription(String CategoryDescription) {
		this.CategoryDescription = CategoryDescription;
    }
}