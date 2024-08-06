package com.microservices.Services.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Services.persistence.entity.ServiceEntity;
import com.microservices.Services.persistence.repository.RepositoryServices;
import java.util.Optional;

@Service
public class FunctionService {
    @Autowired
    private RepositoryServices repositoryServices;
    public FunctionService (RepositoryServices repositoryServices){
        this.repositoryServices = repositoryServices;
    }
    public ServiceEntity findByName(String name){
        return repositoryServices.findByName(name);
    }
    public boolean existsByName(String name){
        return repositoryServices.existsByName(name);
    }
    public Optional<ServiceEntity> findById(Long id){
        return repositoryServices.findById(id);
    }
    public ServiceEntity save(ServiceEntity serviceEntity){
        return repositoryServices.save(serviceEntity);
    }
    public void delete(long id){
        repositoryServices.deleteById(id);
    }
    public Iterable<ServiceEntity> findAll(){
        return repositoryServices.findAll();
    }
    public void UpdateUser(long id, ServiceEntity serviceEntity){
        ServiceEntity service = repositoryServices.findById(id).get();
        service.setName(serviceEntity.getName());
        service.setPrice(serviceEntity.getPrice());
        service.setDescription(serviceEntity.getDescription());
        service.setAvailable(serviceEntity.getAvailable());
        service.setCategories(serviceEntity.getCategories());
        repositoryServices.save(service);
    }
    public Iterable<ServiceEntity> findByAvailable(boolean available){
        return repositoryServices.findByAvailable(available);
    }
    public Iterable<ServiceEntity> findByPrice(Double price){
        return repositoryServices.findByPrice(price);
    }
    public ServiceEntity findByPriceAndName(Double price, String name) {
       return repositoryServices.findByPriceAndName(price, name);
    }
    public Iterable<ServiceEntity> findByPriceAndNameAndAvailable(boolean available) {
        return repositoryServices.findByAvailable( available);
    }

    
}
