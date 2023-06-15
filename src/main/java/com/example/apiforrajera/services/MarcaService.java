package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.MrcFncnDto;
import com.example.apiforrajera.entities.Marca;
import com.example.apiforrajera.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository repository;
    public List<Marca> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Marca create(MrcFncnDto mrcFncnDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (mrcFncnDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Marca %s already exists",result[i]));
            }
        }
        Marca marca=new Marca();
        return getCategoria(mrcFncnDto,marca);
    }
    public Marca update(Integer id,MrcFncnDto mrcFncnDto){
        if(repository.findById(id).isPresent()){
            Marca marca=repository.getReferenceById(id);
            return getCategoria(mrcFncnDto,marca);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Marca %s doesn´t exist",id));
        }
    }
    public Marca delete(Integer id){
        if(repository.findById(id).isPresent()){
            Marca marca=repository.getReferenceById(id);
            marca.setStatus((byte) 0);
            return repository.save(marca);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Marca %s doesn't exists",id));
        }
    }
    private Marca getCategoria(MrcFncnDto mrcFncnDto,Marca marca) {
        marca.setNombre(mrcFncnDto.getNombre());
        marca.setDescripcion(mrcFncnDto.getDescripcion());
        marca.setStatus(mrcFncnDto.getStatus());
        return repository.save(marca);
    }
}
