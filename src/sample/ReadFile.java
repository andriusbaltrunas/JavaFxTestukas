package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andriusbaltrunas on 10/16/2017.
 */
public class ReadFile {

    public List<Question> read(){
        List<Question> questions = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File("testas.txt")));
            String line;
            while ((line = bf.readLine()) != null){
                String[] items = line.split("\t");
                if(items.length == 5){
                    Question q = new Question(items[0], Integer.parseInt(items[4]), items[1], items[2], items[3]);
                    questions.add(q);
                }
            }
        }catch (IOException e){
            System.out.println("Nepavyko nuskaityti failo");
        }
        return questions;
    }
}
