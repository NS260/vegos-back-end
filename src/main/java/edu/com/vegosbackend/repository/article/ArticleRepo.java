package edu.com.vegosbackend.repository.article;

import edu.com.vegosbackend.domain.main.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepo extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
}
