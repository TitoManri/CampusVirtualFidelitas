package com.fidelitas.service;

import com.fidelitas.domain.Personal;
import java.util.List;

public interface PersonalService {

    public List<Personal> getPersonal();

    public Personal getPersonalByCorreo(String correo);
}
