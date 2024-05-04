package com.ufrotest.core.repositories;


import com.ufrotest.core.model.IEntityDTO;
import com.ufrotest.data.DataHandler;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public abstract class RepositoryABS<T extends IEntityDTO> {
    private final Class<T> type;
    private final DataHandler<T> dataHandler;
    private final String path;

    public String save(T DTO) {
        String serialized = dataHandler.jsonHandler().serialize(DTO);
        dataHandler.fileHandler().writeLine(path, serialized );
        return serialized;
    }

    public List<T> findAll(){
        return dataHandler.fileHandler().readAllLines(path).stream().map(json -> dataHandler.jsonHandler().deserialize(json, type)).toList();
    }

    public T findById(int id){
        return dataHandler.fileHandler().readAllLines(path).stream().map(json -> dataHandler.jsonHandler().deserialize(json, type)).filter(dto -> dto.id()==id ).findFirst().orElseThrow();
    }

    public boolean update(int id, T DTO){
        ArrayList<T> list = new ArrayList<>(findAll());
        list.set(list.indexOf(findById(id)), DTO);
        dataHandler.fileHandler().deleteFile(path);
        list.forEach(dto -> dataHandler.fileHandler().writeLine(path, dataHandler.jsonHandler().serialize(dto)));
        return true;
    }

    public boolean delete(T entity){
        return deleteById(entity.id());
    }

    public boolean deleteById(int id){
        ArrayList<T> list = new ArrayList<>(findAll());
        list.remove(findById(id));
        dataHandler.fileHandler().deleteFile(path);
        list.forEach(dto -> dataHandler.fileHandler().writeLine(path, dataHandler.jsonHandler().serialize(dto)));
        return true;
    }
}

