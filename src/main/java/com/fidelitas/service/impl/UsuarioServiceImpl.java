package com.fidelitas.service.impl;

import com.fidelitas.dao.UsuarioDao;
import com.fidelitas.domain.TipoUsuario;
import com.fidelitas.domain.Usuario;
import com.fidelitas.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;

    @Autowired
    public UsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuariosByTipo(TipoUsuario tipoUsuario) {
        var lista = usuarioDao.findAll();
        if (tipoUsuario == TipoUsuario.Estudiante) {
            lista.removeIf(u -> u.getTipoUsuario() == TipoUsuario.Estudiante);
        } else {
            lista.removeIf(u -> u.getTipoUsuario() == TipoUsuario.Administrador);
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }
}