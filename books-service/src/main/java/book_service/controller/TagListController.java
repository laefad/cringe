package book_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book_service.model.TagList;
import book_service.service.TagListService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(
    path = "tagList"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagListController {
    
    @Autowired
    TagListService tagListService;

    @GetMapping
    public TagList getTagList (
        @RequestParam String username,
        @RequestParam long bookId
    ) {
        return tagListService.getTagListById(
            tagListService.createTagListId(username, bookId)
        );
    }

    @PostMapping
    public TagList createTagList (
        @RequestParam String username,
        @RequestParam long bookId
    ) {
        return tagListService.createTagList(username, bookId);
    }

    @PatchMapping(path = "/addTag")
    public TagList addTag (
        @RequestParam String username,
        @RequestParam long bookId,
        @RequestParam long tagId
    ) {
        return tagListService.addTagToTagList(username, bookId, tagId);
    }

    @PatchMapping(path = "/removeTag")
    public TagList removeTag (
        @RequestParam String username,
        @RequestParam long bookId,
        @RequestParam long tagId
    ) {
        return tagListService.removeTagFromTagList(username, bookId, tagId);
    }

    @DeleteMapping
    public TagList removeTagList (
        @RequestParam String username,
        @RequestParam long bookId
    ) {
        return tagListService.removeTagList(username, bookId);
    }

}
