package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事一覧の操作.
 * @author tatsuro.miyazaki
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		return article;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 全件検索.
	 * @return 検索した結果を返す
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC;";
		List<Article> articles = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articles;
	}
	
	/**
	 * articlesテーブルに挿入.
	 * @param contributor 投稿者名
	 * @param postmessage 投稿内容
	 */
	public void insert(String contributor, String postmessage) {
		String sql ="INSERT INTO articles(name, content) VALUES(:contributor, :postmessage);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("contributor", contributor).addValue("postmessage", postmessage);
		template.update(sql, param);
	}
	
	/**
	 * 記事のメイン項目の削除.
	 * @param id ID
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
