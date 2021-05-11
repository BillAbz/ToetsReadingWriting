import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageApp {
    public static void main(String[] args) {
        Path messagePath = Path.of("./FromBilalAbzToPearl/message.txt");
        Path animalPath = Path.of("./FromBilalAbzToPearl/animal.txt");

        try {
            if (!Files.exists(messagePath.getParent()))
                Files.createDirectory(messagePath.getParent());

            if (Files.notExists(messagePath))
                Files.createFile(messagePath);

            if (Files.notExists(animalPath))
                Files.createFile(animalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(messagePath));
            fileWriter.write("Everything is okay! I will word robot. :) \n");
            fileWriter.write("Iedereen denkt eraan de wereld te veranderen, maar niemand denkt eraan zichzelf te veranderen.");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(messagePath)))){
            String line=null;
            while ((line = reader.readLine())!=null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------ANIMAL PATH-------------");

        Rabbit rabbit = new Rabbit("Bony",false);


        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(animalPath.toString()))) {
            objectOutputStream.writeObject(rabbit);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(animalPath.toString()))) {
            String text = inputStream.readObject().toString();
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
