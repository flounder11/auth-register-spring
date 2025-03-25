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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Username %s not found", username)
        ));
        return UserDetailsImpl.build(client);
    }
}
