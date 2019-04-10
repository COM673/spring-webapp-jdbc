package webapp.dao;

import java.util.List;
import webapp.models.Product;

// ProductDao Interface definition
public interface ProductDao {

	// Return a list containing all the product objects
	public List<Product> findAll();

	// Return a product with matching id
	public Product findById(int id);

	// return a list of products in a category
	public List<Product> findByCategory(int id);

	// Create a new product
	public Product create(final Product product);

}