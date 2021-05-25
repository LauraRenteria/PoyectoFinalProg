package co.edu.umanizales.poyectofinalprog.services;
import co.edu.umanizales.poyectofinalprog.entity.Estudiante;
import co.edu.umanizales.poyectofinalprog.entity.Materia;
import co.edu.umanizales.poyectofinalprog.repository.MateriaDao;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private MateriaDao materiaDao;


    @Override
    public Materia save(Materia materia) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MateriaDao> findAll(){
        return materiaDao.findAll();
    }

    @Override
    public Page<Materia> findAll(Pageable pegeable) {
        return null;
    }


    @Override
    public Materia findById(Long id) {
        return null;
    }


    @Override
    public Page<Materia> filtrado(String nombre, int page) {
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


