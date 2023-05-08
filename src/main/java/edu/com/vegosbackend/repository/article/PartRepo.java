package edu.com.vegosbackend.repository.article;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.domain.main.article.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PartRepo extends JpaRepository<Part, Long> {

    List<Part> findAllByArticle(Article article);
}
