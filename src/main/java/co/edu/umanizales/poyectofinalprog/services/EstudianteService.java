package co.edu.umanizales.poyectofinalprog.services;

import co.edu.umanizales.poyectofinalprog.entity.Estudiante;
import co.edu.umanizales.poyectofinalprog.repository.EstudianteDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private EstudianteDao estudianteDao;

    @Override
    @Transactional
    public Estudiante save(Estudiante estudiante){
        if(estudiante.getNacimiento()!=null) {
            estudiante.setEdad(calcularEdad(estudiante));
        }
        return estudianteDao.save(estudiante);
    }

    @Override
    @Transactional
    public List<Estudiante> findAll(){
        return estudianteDao.findAll();
    }

    @Override
    public Page<Estudiante> findAll(Pageable pegeable) {
        return null;
    }

    @Override
    public List<Estudiante> porGrupo(Long grupo) {
        return null;
    }

    @Override
    public Page<Estudiante> porGrupoPage(Long grupo, int page) {
        return null;
    }

    @Override
    public Estudiante findById(Long id) {
        return null;
    }

    @Override
    public Integer calcularEdad(Estudiante estudiante) {
        return null;
    }

    @Override
    public Page<Estudiante> filtrado(String nombre, String id, int page) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Map<String, Object>> findAyudas(Long id) {
        return null;
    }
}

