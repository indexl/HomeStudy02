package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {

		articleService.writeArticle(title, body);

		int id = articleService.getLastArticleId();

		return articleService.getArticleById(id);

	}

	@GetMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}

	@GetMapping("/usr/article/showDetail")
	@ResponseBody
	public Object showDetail(int id) {

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		return foundArticle;
	}

	@GetMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		this.articleService.modifyArticle(id, title, body);

		return id + "번 글을 수정하였습니다.";
	}

	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		this.articleService.deleteArticle(id);

		return id + "번 글을 삭제하였습니다.";
	}
}
