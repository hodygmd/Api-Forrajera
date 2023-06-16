package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.PresentacionDto;
import com.example.apiforrajera.entities.Presentacion;
import com.example.apiforrajera.repositories.PresentacionRepository;
import com.example.apiforrajera.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PresentacionService {
    @Autowired
    private PresentacionRepository repository;
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;
    public List<Presentacion> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Presentacion create(PresentacionDto presentacionDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (presentacionDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Presentacion %s already exists",result[i]));
            }
        }
        Presentacion presentacion=new Presentacion();
        return getPresentacion(presentacionDto,presentacion);
    }
    public Presentacion update(Integer id,PresentacionDto presentacionDto){
        if(repository.findById(id).isPresent()){
            Presentacion presentacion=repository.getReferenceById(id);
            return getPresentacion(presentacionDto,presentacion);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Presentacion %s doesn´t exist",id));
        }
    }
    public Presentacion delete(Integer id){
        if(repository.findById(id).isPresent()){
            Presentacion presentacion=repository.getReferenceById(id);
            presentacion.setStatus((byte) 0);
            return repository.save(presentacion);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Presentacion %s doesn't exists",id));
        }
    }

    private Presentacion getPresentacion(PresentacionDto presentacionDto, Presentacion presentacion) {
        presentacion.setNombre(presentacionDto.getNombre());
        presentacion.setDescripcion(presentacionDto.getDescripcion());
        presentacion.setId_unidad_medida(unidadMedidaRepository.findById(presentacionDto.getId_unidad_medida()).get());
        presentacion.setMedida(presentacionDto.getMedida());
        presentacion.setStatus(presentacionDto.getStatus());
        return repository.save(presentacion);
    }
}