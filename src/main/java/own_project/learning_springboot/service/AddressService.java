package own_project.learning_springboot.service;

import own_project.learning_springboot.entity.User;
import own_project.learning_springboot.model.AddressResponse;
import own_project.learning_springboot.model.CreateAddressRequest;
import own_project.learning_springboot.model.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressResponse create(User user, CreateAddressRequest request);

    AddressResponse get(User user, String contactId, String addressId);

    AddressResponse update(User user, UpdateAddressRequest request);

    void remove(User user, String contactId, String addressId);

    List<AddressResponse> list(User user, String contactId);

}
