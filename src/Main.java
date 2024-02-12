import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

    public class Main {
        public static void main (String[] args){
            System.out.println("Бухгалтерия, спринт 2");
            BuhApplication application = new BuhApplication();
            application.run();
        }

        List<String> readFileContents(String path){
            try{
                return Files.readAllLines(Path.of(path));
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчетом");
                return Collections.emptyList();
            }
        }
    }
