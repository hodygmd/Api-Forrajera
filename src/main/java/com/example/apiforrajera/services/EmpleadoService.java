package com.example.apiforrajera.services;

import com.example.apiforrajera.dto.EmpleadoDto;
import com.example.apiforrajera.entities.Empleado;
import com.example.apiforrajera.repositories.EmpleadoRepository;
import com.example.apiforrajera.repositories.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository repository;
    @Autowired
    private PuestoRepository puestoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Empleado> getAllByStatus(){
        return repository.findAllByStatus();
    }
    public Empleado create(EmpleadoDto empleadoDto){
        String[] result= repository.findNombresByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (empleadoDto.getNombre().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Empleado %s already exists",result[i]));
            }
        }
        Empleado empleado=new Empleado();
        return getEmpleado(empleadoDto,empleado,false);
    }
    public Empleado update(String clave,EmpleadoDto empleadoDto){
        if(repository.findById(clave).isPresent()){
            Empleado empleado=repository.getReferenceById(clave);
            return getEmpleado(empleadoDto,empleado,true);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Empleado %s doesn´t exist",clave));
        }
    }
    public Empleado updatePassword(String clave,EmpleadoDto empleadoDto){
        if(repository.findById(clave).isPresent()){
            Empleado empleado=repository.getReferenceById(clave);
            return getEmpleadoPass(empleadoDto,empleado,true);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Empleado %s doesn´t exist",clave));
        }
    }
    public Empleado delete(String clave){
        if(repository.findById(clave).isPresent()){
            Empleado empleado=repository.getReferenceById(clave);
            empleado.setStatus((byte) 0);
            return repository.save(empleado);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Empleado %s doesn't exists",clave));
        }
    }
    public Empleado login(String username, String password) {
        Empleado empleado = repository.findEmpleadoByUsername(username);
        System.out.println(username+" "+password);
        if (empleado != null) {
            if (passwordEncoder.matches(password, empleado.getPassword())) {
                return empleado;
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
    private Empleado getEmpleado(EmpleadoDto empleadoDto,Empleado empleado,boolean flag) {
        empleado.setClave(empleadoDto.getClave());
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setUsername(empleadoDto.getUsername());
        if(!flag){
            empleado.setPassword(passwordEncoder.encode(empleadoDto.getPassword()));
        }
        empleado.setId_puesto(puestoRepository.findById(empleadoDto.getId_puesto()).get());
        empleado.setStatus(empleadoDto.getStatus());
        return repository.save(empleado);
    }
    private Empleado getEmpleadoPass(EmpleadoDto empleadoDto,Empleado empleado,boolean flag) {
        empleado.setPassword(passwordEncoder.encode(empleadoDto.getPassword()));
        return repository.save(empleado);
    }
}
