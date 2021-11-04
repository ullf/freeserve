package dev.freelance.freeserve.config;

import dev.freelance.freeserve.repository.ClientRepository;
import dev.freelance.freeserve.service.AbstractClientService;
import dev.freelance.freeserve.service.AbstractOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private ClientRepository clientRepository;

    public SecurityConfiguration(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(username -> clientRepository.loadUserByUsername(username));
    }
}
