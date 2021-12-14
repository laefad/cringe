package book_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book_service.model.Tag;
import book_service.service.TagService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(
    path = "tag"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagController {
    
    @Autowired
    TagService tagService;

    @GetMapping
    public Tag getTagById(
        @RequestParam long id
    ) {
        return tagService.getTagById(id);
    }

    @PostMapping
    public Tag saveTag(
        @RequestBody Tag tag
    ) {
        return tagService.saveTag(tag);
    }

    @DeleteMapping
    public Tag deleteTagById(
        @RequestParam long id
    ) {
        return tagService.deleteTagById(id);
    }
}
