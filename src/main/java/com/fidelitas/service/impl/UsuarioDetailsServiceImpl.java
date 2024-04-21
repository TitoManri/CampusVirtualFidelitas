/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.service.impl;


import com.fidelitas.dao.UserDao;
import com.fidelitas.domain.Rol;
import com.fidelitas.domain.Usuario;
import com.fidelitas.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl
       implements UsuarioDetailsService, UserDetailsService {
    
    @Autowired
    private UserDao usuarioDao;
            
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {
        
        //Se busca el usuario en la tabla de usuarios de la BD
        Usuario usuario = usuarioDao.findByCorreo(correo);
        
        //Se valida si el usuariose encontro
        if (usuario==null) {
            //No encontro el usuario
            throw new UsernameNotFoundException(correo);
        }
                
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        return new User(usuario.getCorreo(),usuario.getContrasena(),roles);
    }
    
}
