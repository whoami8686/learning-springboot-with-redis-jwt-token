package own_project.learning_springboot.service;

import org.springframework.data.domain.Page;
import own_project.learning_springboot.entity.User;
import own_project.learning_springboot.model.ContactResponse;
import own_project.learning_springboot.model.CreateContactRequest;
import own_project.learning_springboot.model.SearchContactRequest;
import own_project.learning_springboot.model.UpdateContactRequest;

public interface ContactService {

    ContactResponse create(User user, CreateContactRequest request);

    ContactResponse get(User user, String id);

    ContactResponse update(User user, UpdateContactRequest request);

    void delete(User user, String contactId);

    Page<ContactResponse> search(User user, SearchContactRequest request);
}
