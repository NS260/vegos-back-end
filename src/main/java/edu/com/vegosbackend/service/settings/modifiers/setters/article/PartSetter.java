package edu.com.vegosbackend.service.settings.modifiers.setters.article;

import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class PartSetter implements Setter<Part> {
    @Override
    public Part setValue(Part before, Part after) {
        after.setId(before.getId());
        after.setArticle(before.getArticle());
        if (after.getText() == null) {
            after.setText(before.getText());
        }
        if (after.getHeader() == null) {
            after.setHeader(before.getHeader());
        }
        return after;
    }
}
