package edu.com.vegosbackend.mapper.article;

import edu.com.vegosbackend.controller.model.dto.ArticleDTO;
import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class ArticleMapper implements CustomMapper<Article, ArticleDTO> {
    private final ModelMapper mapper;

    @Override
    public Article convertToEntity(ArticleDTO value) {
        return mapper.map(value, Article.class);
    }

    @Override
    public ArticleDTO convertToDTO(Article value) {
        return mapper.map(value, ArticleDTO.class);
    }
}
