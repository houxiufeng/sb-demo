package com.example.demo.dao;

import com.example.demo.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * jdbcTemplate的实现方式
 */
@Repository
@Slf4j
public class ArticleJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //jdbcTemplate.update 适应于save update delete
    public void saveArticle(Article article) {
        String sql = "INSERT INTO article (author, title, content, create_time) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(), article.getContent(), article.getCreateTime());
    }

    public int deleteArticle(long id) {
        return jdbcTemplate.update("DELETE FROM article where id = ?", id);
    }

    public int updateById(Article article) {
        String sql = "UPDATE article SET author =?, title =?, content =?, create_time =? WHERE id = ?";
        return jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(), article.getContent(), article.getCreateTime(), article.getId());
    }

    public Article findById(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM article WHERE id =?",
                    new Object[]{id}, new BeanPropertyRowMapper<>(Article.class));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM article", new BeanPropertyRowMapper<>(Article.class));
    }
}
