package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCartDAO extends MySqlDaoBase implements ShoppingCartDao {
    public MySqlCartDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        return null;
    }

    /*@Override
    public ShoppingCart getByUserId(int userId) {
        String sql = """
                SELECT 
                    sc.product_id,
                    sc.quantity,
                    p.name,
                    p.description,
                    p.category_id,
                    p.price,
                    p.color,
                    p.featured
                FROM
                    shopping_cart sc
                JOIN
                    products p
                ON
                    sc.product_id = p.product_id
                WHERE
                    sc.user_id = ?
                """;

        List<ShoppingCartItem> cartItems = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setColor(resultSet.getString("color"));
                product.setFeatured(resultSet.getBoolean("featured"));

                ShoppingCartItem item = new ShoppingCartItem();
                item.setProduct(product);
                item.setQuantity(resultSet.getInt("quantity"));
                item.setDiscountPercent(BigDecimal.ZERO);

                cartItems.add(item);
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return cartItems;
    }*/


}
