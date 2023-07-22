package com.example.lostandfound.repository;

import com.example.lostandfound.model.Category;
import com.example.lostandfound.model.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class JdbcItemRepository implements ItemRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcItemRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> findAll() {
        return jdbcTemplate.query("select * from item", itemRowMapper);
    }

    @Override
    public Item insert(Item item) {
        var update = jdbcTemplate.update("INSERT INTO item(item_id,item_name, category, place, description, created_at, updated_at)" +
                " VALUES (UUID_TO_BIN(:itemId), :itemName, :category, :place, :description, :createdAt, :updatedAt)", toParamMap(item));
        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        var update = jdbcTemplate.update(
                "UPDATE item SET item_name = :itemName, category = :category, place = :place, description = :description, created_at = :createdAt, updated_at = :updatedAt" +
                        " WHERE item_id = UUID_TO_BIN(:itemId)",
                toParamMap(item)
        );

        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return item;
    }
    @Override
    public Optional<Item> findById(UUID itemId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM item WHERE item_id = UUID_TO_BIN(:itemId)",
                            Collections.singletonMap("productId", itemId.toString().getBytes()), itemRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> findByName(String itemName) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM item WHERE product_name = :itemName",
                            Collections.singletonMap("itemName", itemName), itemRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findByCategory(Category category) {
        return jdbcTemplate.query(
                "SELECT * FROM item WHERE category = :category",
                Collections.singletonMap("category", category.toString()),
                itemRowMapper
        );
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM products", Collections.emptyMap());
    }

    private static final RowMapper<Item> itemRowMapper = (resultSet, i) -> {
        var itemId = toUUID(resultSet.getBytes("product_id"));
        var itemName = resultSet.getString("product_name");
        var category = Category.valueOf(resultSet.getString("category"));
        var place = resultSet.getString("place");
        var description = resultSet.getString("description");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Item(itemId, itemName, category, place, description, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Item item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("productId", item.getItemId().toString().getBytes());
        paramMap.put("productName", item.getItemName());
        paramMap.put("category", item.getCategory().toString());
        paramMap.put("place", item.getFoundPlace());
        paramMap.put("description", item.getDescription());
        paramMap.put("createdAt", item.getCreatedAt());
        paramMap.put("updatedAt", item.getUpdatedAt());
        return paramMap;
    }

    public static UUID toUUID(byte[] bytes) {
        var byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
