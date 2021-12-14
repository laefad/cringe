package book_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book_service.model.Tag;
import book_service.repository.TagRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagService {
    
    @Autowired
    TagRepository tagRepository;

    @Transactional(readOnly = true)
    public Tag getTagById(long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    public Tag deleteTagById(long id) {
        Tag tag = getTagById(id);

        if (tag == null)
            return null; // TODO add error

        tagRepository.delete(tag);
        return tag;
    }
}
