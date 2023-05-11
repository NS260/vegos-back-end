package edu.com.vegosbackend.service.settings.modifiers.setters.article;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class ArticleSetter implements Setter<Article> {
    @Override
    public Article setValue(Article before, Article after) {
        after.setId(before.getId());
        after.setPublishedDate(before.getPublishedDate());
        after.setUser(before.getUser());
        after.setCategory(before.getCategory());
        after.setArticleType(before.getArticleType());
        if (after.getParts() == null) {
            after.setParts(before.getParts());
        }
        if (after.getName() == null) {
            after.setName(before.getName());
        }
        if (after.getPhotoUrl() == null) {
            after.setPhotoUrl(before.getPhotoUrl());
        }
        if (after.getRate() == 0) {
            after.setRate(before.getRate());
        }
        if (after.getUserComment() == null) {
            after.setUserComment(before.getUserComment());
        }
        return after;
    }
}
