package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントの操作.
 * @author tatsuro.miyazaki
 *
 */
@Repository
public class CommnetRepository {
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setAtticleId(rs.getInt("article_id"));

		return comment;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 記事一覧のidからコメントを検索
	 * @param id 記事一覧に存在するid
	 * @return 記事に対応するコメント
	 */
	public List<Comment> findByArticled(int id){
		String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Comment> comment = template.query(sql, param, COMMENT_ROW_MAPPER);
		
		return comment;
	}
	
	/**
	 * コメントの追加.
	 * @param name 投稿者名
	 * @param content コメントの内容
	 * @param articleId どの記事に関連しているか
	 */
	public void insert(String name, String content ,int articleId) {
		String sql ="INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("content", content).addValue("articleId", articleId);
		template.update(sql, param);
	}
	
	/**
	 * コメントの消去.
	 * @param articleId どの記事に関連しているか
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
}
