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

import webapp.dao.CategoryDao;
import webapp.models.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    // SQL for selecting Categorys
	private final String SELECT_SQL = "SELECT * FROM dbo.Category";
	private final String SELECT_SQL_BY_ID = "SELECT * FROM dbo.Category WHERE CategoryId = ?";

	/*
		SELECT * FROM dbo.Category JOIN (SELECT COUNT(ProductId) as catProducts from dbo.Product WHERE CategoryId = ?) 
	*/

    // Spring JdbcTempate helps with storing and retrieving data
	@Autowired
	private JdbcTemplate jdbcTemplate;

    // Implement findAll() which retrieves all Categorys from the DB
	// CategoryMapper() converts rows from the result into Category objects
	public List<Category> findAll() {
		
		// Use jdbcTemplate to execute query
		// Then use ProductMapper to crate a category object for each row in the query result
		return jdbcTemplate.query(SELECT_SQL, new CategoryMapper());
	}

    // Return a single Category matching id
	public Category findById(int id) {
		return jdbcTemplate.queryForObject(SELECT_SQL_BY_ID, new Object[] { id }, new CategoryMapper());
	}

}

// This class converts resultset rows (from the sql execution) into Java objects
class CategoryMapper implements RowMapper<Category> {
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category c = new Category();
		c.setCategoryId(rs.getInt("CategoryId"));
		c.setCategoryName(rs.getString("CategoryName"));
        c.setCategoryDescription(rs.getString("CategoryDescription"));
		return c;
	}

}
