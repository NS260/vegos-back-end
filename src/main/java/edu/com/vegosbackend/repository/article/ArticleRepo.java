package edu.com.vegosbackend.repository.article;

import edu.com.vegosbackend.domain.main.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
}
