package com.microservices.Services.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.Services.persistence.entity.CategoryEntity;
import com.microservices.Services.persistence.repository.RepositoryCategory;
import java.util.Optional;

@Service
public class ServiceCategory {
    @Autowired
    private RepositoryCategory repositoryCategory;
    public ServiceCategory (RepositoryCategory repositoryCategory){
        this.repositoryCategory = repositoryCategory;
    }
    public CategoryEntity findByName(String name){
        return repositoryCategory.findByName(name);
    }
    public CategoryEntity save(CategoryEntity categoryEntity){
        return repositoryCategory.save(categoryEntity);
    }
    public boolean existsByName(String name){
        return repositoryCategory.existsByName(name);
    }
    public Iterable<CategoryEntity> findAll(){
        return repositoryCategory.findAll();
    }
    public Optional<CategoryEntity> findById(Long id){
        return repositoryCategory.findById(id);
    }
    public void deleteById(Long id){
        repositoryCategory.deleteById(id);
    }
    public void deleteAll(){
        repositoryCategory.deleteAll();
    }
    public void updateCategory(Long id, CategoryEntity categoryEntity){
        CategoryEntity category = repositoryCategory.findById(id).get();
        category.setName(categoryEntity.getName());
        repositoryCategory.save(category);
    }
}
