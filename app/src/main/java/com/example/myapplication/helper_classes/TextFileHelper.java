package com.example.myapplication.helper_classes;

import android.support.v4.app.FragmentActivity;

import com.example.myapplication.resolution.Resolution;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class TextFileHelper {
    public static void writeToFile(FragmentActivity fragmentActivity, ArrayList<Resolution> resolutions) {
        try {

            FileOutputStream file = fragmentActivity.openFileOutput("res.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(file);
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
