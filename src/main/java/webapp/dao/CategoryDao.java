package webapp.dao;

import java.util.List;
import webapp.models.Category;

// ProductDao Interface definition
public interface CategoryDao {

	// Return a list containing all the product objects
	public List<Category> findAll();

	// Return a product with matching id
	public Category findById(int id);

}