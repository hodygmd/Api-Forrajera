package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.MrcFncnDto;
import com.example.apiforrajera.entities.Funcion;
import com.example.apiforrajera.repositories.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionService {
    @Autowired
    private FuncionRepository repository;
    public List<Funcion> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Funcion create(MrcFncnDto mrcFncnDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (mrcFncnDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Funcion %s already exists",result[i]));
            }
        }
        Funcion funcion=new Funcion();
        return getCategoria(mrcFncnDto,funcion);
    }
    public Funcion update(Integer id,MrcFncnDto mrcFncnDto){
        if(repository.findById(id).isPresent()){
            Funcion funcion=repository.getReferenceById(id);
            return getCategoria(mrcFncnDto,funcion);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Funcion %s doesn´t exist",id));
        }
    }
    public Funcion delete(Integer id){
        if(repository.findById(id).isPresent()){
            Funcion funcion=repository.getReferenceById(id);
            funcion.setStatus((byte) 0);
            return repository.save(funcion);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Funcion %s doesn't exists",id));
        }
    }
    private Funcion getCategoria(MrcFncnDto mrcFncnDto,Funcion funcion) {
        funcion.setNombre(mrcFncnDto.getNombre());
        funcion.setDescripcion(mrcFncnDto.getDescripcion());
        funcion.setStatus(mrcFncnDto.getStatus());
        return repository.save(funcion);
    }
}