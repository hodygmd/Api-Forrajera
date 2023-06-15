package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.ProductoDto;
import com.example.apiforrajera.entities.Producto;
import com.example.apiforrajera.repositories.CategoriaRepository;
import com.example.apiforrajera.repositories.MarcaRepository;
import com.example.apiforrajera.repositories.PresentacionRepository;
import com.example.apiforrajera.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PresentacionRepository presentacionRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    public List<Producto> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Producto create(ProductoDto productoDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (productoDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Producto %s already exists",result[i]));
            }
        }
        Producto producto=new Producto();
        return getProducto(productoDto,producto);
    }
    public Producto update(String clave,ProductoDto productoDto){
        if(repository.findById(clave).isPresent()){
            Producto producto=repository.getReferenceById(clave);
            return getProducto(productoDto,producto);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Producto %s doesn´t exist",clave));
        }
    }
    public Producto delete(String clave){
        if(repository.findById(clave).isPresent()){
            Producto producto=repository.getReferenceById(clave);
            producto.setStatus((byte) 0);
            return repository.save(producto);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Producto %s doesn't exists",clave));
        }
    }

    private Producto getProducto(ProductoDto productoDto,Producto producto) {
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock_min(productoDto.getStock_min());
        producto.setStock_max(productoDto.getStock_max());
        producto.setExistencias(productoDto.getExistencias());
        producto.setId_categoria(categoriaRepository.findById(productoDto.getId_categoria()).get());
        producto.setId_presentacion(presentacionRepository.findById(productoDto.getId_presentacion()).get());
        producto.setId_marca(marcaRepository.findById(productoDto.getId_marca()).get());
        producto.setStatus(productoDto.getStatus());
        return repository.save(producto);
    }
}
