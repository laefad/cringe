package book_service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import book_service.model.Tag;
import book_service.model.input.tag.TagCreateInput;
import book_service.service.TagService;

@Controller
public class TagResolver {

    @Autowired
    TagService tagService;

    @QueryMapping
    public Tag getTag(@Argument long tagId) {
        return tagService.getTagById(tagId);
    }

    @QueryMapping
    public List<Tag> getAllTags() {
        System.out.println("---------------------------------------------------");
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("---------------------------------------------------");
        System.out.println(token);
        System.out.println(token.getHeaders());
        System.out.println(token.getClaims());
        System.out.println(token.getTokenValue());
        System.out.println(token.getId());
        System.out.println(token.getSubject());
        System.out.println(token.getAudience());
        System.out.println("---------------------------------------------------");
        return tagService.getAll();
    }

    @MutationMapping
    public Tag deleteTag(@Argument long tagId) {
        return tagService.deleteTagById(tagId);
    }

    @MutationMapping
    public Tag createTag(@Argument TagCreateInput input) {
        Tag tag = Tag.builder()
                     .name(input.getName())
                     .build();
        
        return tagService.saveTag(tag);
    }

}
