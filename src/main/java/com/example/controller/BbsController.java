package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommnetRepository;

/**
 * 掲示板の操作.
 * @author tatsuro.miyazaki
 *
 */
@Controller
@RequestMapping("/ex-bbs")
public class BbsController {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommnetRepository commnetRepository;
	
	/**
	 * 全件検索して記事とコメントをビューへ反映.
	 * @param model 全件検索した結果
	 * @return 記事一覧へ遷移
	 */
	@RequestMapping("")
	public String index(Model model) {
//		model.addAttribute("articles", articleRepository.findAll());
		
		// 記事を全件検索する
		
		// articleListを拡張for文で回して１つずつarticleを取得
		
			// article.setCommentList(commnetRepository.findByArticled(対象記事のID));
		
		// model.addAttribute("articleList", articleList);
		// フォワード
		
		List<Article> articleList = articleRepository.findAll();
		
		for(Article article:articleList) {
			article.setCommentList(commnetRepository.findByArticled(article.getId()));
		}
		model.addAttribute("articleList", articleList);
		
//		ArrayList<List<Comment>> articleList = new ArrayList<>();
//		for(int i=1;i<=articleRepository.findAll().size();i++) {
//			Article article = new Article();
//			article.setCommentList(commnetRepository.findByArticled(i));
//			articleList.add(article.getCommentList());			
//		}
//		model.addAttribute("articleList", articleList);
//		System.out.println(articleRepository.findAll().size());
		
		return "articles";
	}
	
	@ModelAttribute
	public ArticleForm setUpArticleFrom() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentFrom() {
		return new CommentForm();
	}
	
	/**
	 * 投稿内容の追加.
	 * @param form 投稿者名と投稿内容
	 * @return 記事一覧へリダイレクト
	 */
	@RequestMapping("/bbs-add")
	public String showBbs(ArticleForm form) {
		//inserするときは情報をdomainから持ってくるようにしよう(BeanUtilsを使用しコピー)
		articleRepository.insert(form.getContributor(), form.getPostmessage());
		return "redirect:/ex-bbs";
	}
	
	/**
	 * コメントの投稿.
	 * @param form
	 * @return 記事一覧へリダイレクト
	 */
	@RequestMapping("/comment-add")
	public String postBbs(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setAtticleId(Integer.parseInt(form.getArticleId()));
		commnetRepository.insert(comment.getName(), comment.getContent(), comment.getAtticleId());
		return "redirect:/ex-bbs";
	}
	
	/**
	 * 記事を削除.
	 * @param form
	 * @return 記事一覧へリダイレクト
	 */
	@RequestMapping("/delete")
	public String delete(CommentForm form) {
		commnetRepository.deleteByArticleId(Integer.parseInt(form.getArticleId()));
		articleRepository.deleteById(Integer.parseInt(form.getArticleId()));
		return "redirect:/ex-bbs";
	}
}
