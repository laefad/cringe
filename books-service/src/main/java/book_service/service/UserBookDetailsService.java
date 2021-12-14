package book_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book_service.model.UserBookDetails;
import book_service.repository.UserBookDetailsRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBookDetailsService {
    @Autowired
    UserBookDetailsRepository userBookDetailsRepository;

    @Transactional(readOnly = true)
    public UserBookDetails getUserBookDetailsByUsername(String username) {
        return userBookDetailsRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public UserBookDetails createUserBookDetails(String username) {
        // TODO username exist check

        UserBookDetails userBookDetails = getUserBookDetailsByUsername(username);

        if (userBookDetails != null)
            return null; // TODO add error

        userBookDetails = UserBookDetails.builder().username(username).build();
        return userBookDetailsRepository.save(userBookDetails);
    }

    @Transactional
    public UserBookDetails removeByUsername(String username) {
        UserBookDetails userBookDetails = getUserBookDetailsByUsername(username);

        if (userBookDetails == null)
            return null; // TODO add error

        userBookDetailsRepository.delete(userBookDetails);
        return userBookDetails;
    }

    @Transactional
    public UserBookDetails getOrCreateByUsername(String username) {
        UserBookDetails userBookDetails = getUserBookDetailsByUsername(username);

        if (userBookDetails != null)
            return userBookDetails;

        userBookDetails = UserBookDetails.builder().username(username).build();
        return userBookDetailsRepository.save(userBookDetails);
    }

}
