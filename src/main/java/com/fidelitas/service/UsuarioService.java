package com.fidelitas.service;

import com.fidelitas.domain.TipoUsuario;
import com.fidelitas.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getUsuariosByTipo(TipoUsuario tipoUsuario);

    public Usuario getUsuario(Usuario usuario);
}
