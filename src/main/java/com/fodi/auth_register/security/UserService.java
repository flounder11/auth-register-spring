package com.fodi.auth_register.security;

import com.fodi.auth_register.models.Client;
import com.fodi.auth_register.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private ClientRepository clientRepository;
    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Client client = clientRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException(
                String.format("PhoneNumber %s not found", phoneNumber)
        ));
        return UserDetailsImpl.build(client);
    }
}
