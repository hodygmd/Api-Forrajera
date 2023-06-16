package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.PuestoDto;
import com.example.apiforrajera.entities.Puesto;
import com.example.apiforrajera.repositories.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PuestoService {
    @Autowired
    private PuestoRepository repository;
    public List<Puesto> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Puesto create(PuestoDto puestoDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (puestoDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Puesto %s already exists",result[i]));
            }
        }
        Puesto puesto=new Puesto();
        return getPuesto(puestoDto,puesto);
    }
    public Puesto update(Integer id, PuestoDto puestoDto){
        if(repository.findById(id).isPresent()){
            Puesto puesto=repository.getReferenceById(id);
            return getPuesto(puestoDto,puesto);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Puesto %s doesn´t exist",id));
        }
    }
    public Puesto delete(Integer id){
        if(repository.findById(id).isPresent()){
            Puesto puesto=repository.getReferenceById(id);
            puesto.setStatus((byte) 0);
            return repository.save(puesto);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Puesto %s doesn't exists",id));
        }
    }
    private Puesto getPuesto(PuestoDto puestoDto,Puesto puesto){
        puesto.setNombre(puestoDto.getNombre());
        puesto.setStatus(puestoDto.getStatus());
        return repository.save(puesto);
    }
}
