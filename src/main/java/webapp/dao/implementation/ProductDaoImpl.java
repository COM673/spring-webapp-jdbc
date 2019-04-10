package webapp.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import webapp.dao.ProductDao;
import webapp.models.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    // SQL statements for selecting products
	private final String SELECT_SQL = "SELECT * FROM dbo.Product";
	private final String SELECT_SQL_BY_ID = "SELECT * FROM dbo.Product WHERE ProductId = ?";
	private final String SELECT_SQL_BY_CAT_ID = "SELECT * FROM dbo.Product WHERE CategoryId = ?";
	private final String INSERT_SQL = "INSERT INTO Product(ProductName,CategoryId,ProductDescription,ProductStock,ProductPrice) values(?,?,?,?,?)";

    // Spring JdbcTempate helps with storing and retrieving data
	@Autowired
	private JdbcTemplate jdbcTemplate;

    // Implement findAll() which retrieves all products from the DB
    // ProductMapper() converts rows from the result into Product objects
	public List<Product> findAll() {
		// Use jdbcTemplate to execute query
		// Then use ProductMapper to crate a product object for each row in the query result
		return jdbcTemplate.query(SELECT_SQL, new ProductMapper());
	}

	// Return a single product matching id
	// Use jdbcTemplate to execute query
	// Then use ProductMapper to crate a product object for if the query ffound something
	public Product findById(int id) {
		return jdbcTemplate.queryForObject(SELECT_SQL_BY_ID, new Object[] { id }, new ProductMapper());
	}

	// return products for a category
	// Use jdbcTemplate to execute query
	// Then use ProductMapper to crate a product object for each row in the query result
	public List<Product> findByCategory(int id) {
		return jdbcTemplate.query(SELECT_SQL_BY_CAT_ID, new Object[] { id }, new ProductMapper());
	}

	// Create a new product
	public Product create(final Product product) {
		
		// A new Primary key (identity) value will be generated by the database on insert
		// This value is retrieved using KeyHolder
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			// Prepared statement replaces ? parameters with values
			// Create the statement and connect
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				// Set each parameter by index (of ? in SQL) and value 
				ps.setString(1, product.getProductName());
				ps.setInt(2, product.getCategoryId());
				ps.setString(3, product.getProductDescription());
				ps.setInt(4, product.getProductStock());
				ps.setDouble(5, product.getProductPrice());

				// return the completed statement
				return ps;
			}
		}, holder);

		// Get the new id and assign it to the new product object
		int newProductId = holder.getKey().intValue();
		product.setProductId(newProductId);

		// Return the newly created product
		return product;
	}

}

// This class converts resultset rows (from the sql execution) into Java objects
// 
class ProductMapper implements RowMapper<Product> {
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		// Declare a new Product object
		Product p = new Product();

		// Set product properties = values from query result
		p.setProductId(rs.getInt("ProductId"));
		p.setCategoryId(rs.getInt("CategoryId"));
		p.setProductName(rs.getString("ProductName"));
        p.setProductDescription(rs.getString("ProductDescription"));
        p.setProductStock(rs.getInt("ProductStock"));
		p.setProductPrice(rs.getDouble("ProductPrice"));
		return p;
	}

}
