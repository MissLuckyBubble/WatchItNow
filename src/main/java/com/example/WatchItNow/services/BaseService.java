package com.example.WatchItNow.services;

import com.example.WatchItNow.dto.BaseDTO;
import com.example.WatchItNow.models.MainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public abstract class BaseService <U extends MainModel> {
    protected abstract JpaRepository<U,Long> getRepo();
    public List<BaseDTO<U>> getAll(){
        List<U> entities = getRepo().findAll();
        return entities
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
    public BaseDTO<U> getBy(Long id){
        Optional<U> entity = getRepo().findById(id);
        if(entity.isPresent()){
            return convert(entity.get());
        }
        return null;
    }
    public BaseDTO<U> create(BaseDTO<U> baseDTO){
        U entity = convertDTOtoModel(baseDTO);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        U savedEntity = getRepo().save(entity);
        return convert(savedEntity);
    }
    protected abstract U convertDTOtoModel(BaseDTO<U> baseDTO);
    protected abstract BaseDTO<U> convert(U entity);

    public BaseDTO<U> update(BaseDTO<U> baseDTO){
        long id = baseDTO.getId();
        Optional<U> optionalEntity = getRepo().findById(id);
        if(optionalEntity.isPresent()){
            U entity = optionalEntity.get();
            updateEntity(entity ,baseDTO);
            return convert(getRepo().save(entity));
        }
        return null;
    }

    protected abstract void updateEntity(U entity, BaseDTO<U> dto);

    public boolean remove(long id) {
        Optional<U> optionalEntity = getRepo().findById(id);
        if(optionalEntity.isPresent()){
            getRepo().delete(optionalEntity.get());
            return true;
        }
        return false;
    }

}

