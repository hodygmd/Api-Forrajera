package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.UnidadMedidaDto;
import com.example.apiforrajera.entities.UnidadMedida;
import com.example.apiforrajera.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UnidadMedidaService {
    @Autowired
    private UnidadMedidaRepository repository;
    public List<UnidadMedida> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public UnidadMedida create(UnidadMedidaDto unidadMedidaDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (unidadMedidaDto.getUnidad().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Unidad %s already exists",result[i]));
            }
        }
        UnidadMedida unidadMedida=new UnidadMedida();
        return getCategoria(unidadMedidaDto,unidadMedida);
    }
    public UnidadMedida update(Integer id,UnidadMedidaDto unidadMedidaDto){
        if(repository.findById(id).isPresent()){
            UnidadMedida unidadMedida=repository.getReferenceById(id);
            return getCategoria(unidadMedidaDto,unidadMedida);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Unidad %s doesn´t exist",id));
        }
    }
    public UnidadMedida delete(Integer id){
        if(repository.findById(id).isPresent()){
            UnidadMedida unidadMedida=repository.getReferenceById(id);
            unidadMedida.setStatus((byte) 0);
            return repository.save(unidadMedida);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Unidad %s doesn't exists",id));
        }
    }
    private UnidadMedida getCategoria(UnidadMedidaDto unidadMedidaDto, UnidadMedida unidadMedida) {
        unidadMedida.setUnidad(unidadMedidaDto.getUnidad());
        unidadMedida.setStatus(unidadMedidaDto.getStatus());
        return repository.save(unidadMedida);
    }
}
