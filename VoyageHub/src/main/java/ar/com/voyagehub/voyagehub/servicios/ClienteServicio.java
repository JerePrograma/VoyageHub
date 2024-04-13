package ar.com.voyagehub.voyagehub.servicios;

import ar.com.voyagehub.voyagehub.entidades.Cliente;
import ar.com.voyagehub.voyagehub.repositorios.ClienteRepositorio;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServicio {
    @Autowired
    ClienteRepositorio clienteRepositorio;


}
