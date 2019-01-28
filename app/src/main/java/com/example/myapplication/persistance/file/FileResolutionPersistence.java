package com.example.myapplication.persistance.file;

import com.example.myapplication.models.Resolution;
import com.example.myapplication.persistance.ResolutionPersistance;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileResolutionPersistence implements ResolutionPersistance {

    private FileOutputStream fOut;

    public FileResolutionPersistence(FileOutputStream fOut) {
        this.fOut = fOut;
    }

    @Override
    public Resolution find(int id) {
        return null;
    }

    @Override
    public List<Resolution> findAll() {
        return null;
    }

    @Override
    public void save(Resolution r) {

    }

    @Override
    public void saveAll(List<Resolution> rList) {
        writeToFile(rList);
    }

    @Override
    public void update(Resolution r) {

    }

    @Override
    public void delete(int id) {

    }

    private void writeToFile(List<Resolution> resolutions) {
        try {

            OutputStreamWriter writer = new OutputStreamWriter(fOut);
            for (Resolution resolution : resolutions) {
                writer.write(resolution.getResolutionTitle() + "," +
                        resolution.getResolutionDescription() + "," + resolution.isCompleted() + "\n");
            }
            writer.flush();
            writer.close();
            //Toast.makeText(fragmentActivity, "User successfully added", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //Toast.makeText(fragmentActivity, "File couldn't be opened", Toast.LENGTH_SHORT).show();
        }
    }
}
