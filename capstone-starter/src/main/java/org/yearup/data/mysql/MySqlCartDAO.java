package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MySqlCartDAO extends MySqlDaoBase implements ShoppingCartDao {

    private ProductDao productDao;

    @Autowired
    public MySqlCartDAO(DataSource dataSource, ProductDao productDao) {
        super(dataSource);
        this.productDao = productDao;
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        String sql = """
                SELECT 
                    *
                FROM
                    shopping_cart
                WHERE
                    user_id = ?
                """;
        ShoppingCart cart = new ShoppingCart();
        BigDecimal total = BigDecimal.ZERO;
        Map<Integer, ShoppingCartItem> items = new HashMap<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, userId);
            ResultSet result =  statement.executeQuery();

            while(result.next()){
                int productId = result.getInt("product_id");
                int quantity = result.getInt("quantity");

                Product product = productDao.getById(productId);
                if(product != null){
                    ShoppingCartItem item = new ShoppingCartItem();
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    item.setDiscountPercent(BigDecimal.ZERO);

                    /*BigDecimal linetotal = item.getLineTotal();
                    total = total.add(linetotal);*/

                    items.put(productId, item);

                }
            }
            /*cart.getTotal();*/


        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        cart.setItems(items);
        return cart;
    }

    @Override
    public void addProduct(int userId, int productId) {
        String sql = """
                INSERT INTO shopping_cart (user_id, product_id, quantity)
                VALUES (?,?,1)
                ON DUPLICATE KEY 
                UPDATE quantity = quantity + 1
                """;

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.setInt(2,productId);
            statement.executeUpdate();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCart(int userId, int productId, ShoppingCartItem shoppingCartItem) {
        String sql = """
                UPDATE shopping_cart 
                SET quantity = ?
                WHERE 
                    user_id = ?,
                    product_id = ?
                """;

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,shoppingCartItem.getQuantity());
            statement.setInt(2,userId);
            statement.setInt(3,productId);

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCart(int userId) {
        String sql = """
                DELETE FROM shopping_cart
                WHERE user_id = ?
                """;

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,userId);
            statement.executeUpdate();
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
