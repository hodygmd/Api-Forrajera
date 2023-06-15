package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.CategoriaDto;
import com.example.apiforrajera.entities.Categoria;
import com.example.apiforrajera.repositories.CategoriaRepository;
import com.example.apiforrajera.repositories.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private FuncionRepository funcionRepository;
    public List<Categoria> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Categoria create(CategoriaDto categoriaDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (categoriaDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Categoria %s already exists",result[i]));
            }
        }
        Categoria categoria=new Categoria();
        return getCategoria(categoriaDto,categoria);
    }
    public Categoria update(Integer id,CategoriaDto categoriaDto){
        if(repository.findById(id).isPresent()){
            Categoria categoria=repository.getReferenceById(id);
            return getCategoria(categoriaDto,categoria);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Categoria %s doesn´t exist",id));
        }
    }
    public Categoria delete(Integer id){
        if(repository.findById(id).isPresent()){
            Categoria categoria=repository.getReferenceById(id);
            categoria.setStatus((byte) 0);
            return repository.save(categoria);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Categoria %s doesn't exists",id));
        }
    }
    private Categoria getCategoria(CategoriaDto categoriaDto, Categoria categoria) {
        categoria.setNombre(categoriaDto.getNombre());
        categoria.setId_funcion(funcionRepository.findById(categoriaDto.getId_funcion()).get());
        categoria.setStatus(categoriaDto.getStatus());
        return repository.save(categoria);
    }
}
