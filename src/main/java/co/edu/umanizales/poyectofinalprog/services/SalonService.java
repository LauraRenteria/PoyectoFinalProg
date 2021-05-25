package co.edu.umanizales.poyectofinalprog.services;

import co.edu.umanizales.poyectofinalprog.entity.Docente;
import co.edu.umanizales.poyectofinalprog.entity.Salon;
import co.edu.umanizales.poyectofinalprog.repository.DocenteDao;
import co.edu.umanizales.poyectofinalprog.repository.SalonDao;
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
public class SalonService implements ISalonService{

    @Autowired
    private SalonDao salonDao;


    @Override
    public Salon save(Salon salon) {
        return null;
    }

    @Override
    @Transactional
    public List<Salon> findAll(){
        return salonDao.findAll();
    }

    @Override
    public Page<Salon> findAll(Pageable pegeable) {
        return null;
    }


    @Override
    public Salon findById(Long id) {
        return null;
    }


    @Override
    public Page<Salon> filtrado(String nombre, int page) {
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
