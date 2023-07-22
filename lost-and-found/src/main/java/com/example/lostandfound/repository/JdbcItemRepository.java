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
        String insertSql = new SqlBuilder
                .InsertBuilder()
                .insert("item")
                .columns("item_name", "category", "found_place", "description", "created_at", "updated_at")
                .values(":itemName", ":category", ":place", ":description", ":createdAt", ":updatedAt").build();

        var update = jdbcTemplate.update(insertSql, toParamMap(item));

        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        String updateSql = new SqlBuilder
                .UpdateBuilder()
                .update("item")
                .set("description = :description")
                .where("item_number = itemNumber")
                .build();

        var update = jdbcTemplate.update(updateSql, toParamMap(item));

        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return item;
    }

    private static final RowMapper<Item> itemRowMapper = (resultSet, i) -> {
        var itemName = resultSet.getString("item_name");
        var category = Category.valueOf(resultSet.getString("category"));
        var place = resultSet.getString("found_place");
        var description = resultSet.getString("description");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Item(itemName, category, place, description, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Item item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("itemName", item.getItemName());
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
