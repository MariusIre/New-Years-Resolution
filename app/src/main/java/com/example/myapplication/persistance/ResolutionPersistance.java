package com.example.myapplication.persistance;

import com.example.myapplication.models.Resolution;

import java.util.List;

public interface ResolutionPersistance {

    Resolution find(int id);

    List<Resolution> findAll();

    void save(Resolution r);

    void saveAll(List<Resolution> rList);

    void update(Resolution r);

    void delete(int id);
}
