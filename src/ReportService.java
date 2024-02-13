import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportService {
    InMemoryStorage storage = new InMemoryStorage();//Хранилище

    public void loadMonthReports() {
           for (int i = 1; i < 4; i++) {
            Integer month = i;
            String path = "./resources/m.20210" + i + ".csv";
            ArrayList<Item> items = loadMonthReport(path);
            storage.saveMonthReport(2021, i, items);// сохранил
        }
    }
    ArrayList<Item> loadMonthReport(String path){
        List<String> lines = readFileContents(path);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++){
            String line = lines.get(i);
            String[] rows = line.split(",");
            Item item = new Item(rows[0], Boolean.parseBoolean(rows[1]),
                    Integer.parseInt(rows[2]),
                    Long.parseLong(rows[3]));
            items.add(item);
        }
        return items;
    }

    public void printMonthReportInfo() {
        //System.out.println(storage.monthReports); //вывод в строку всех отчетов
        for (int i = 1; i < 4; i ++) {
            int month = i; // цикл по месяцам
            System.out.println("----------------------------------------------------");
            System.out.println("Отчет за  " + getMonthName(month) + " месяц");
            Item maxEarning = storage.getMaxEarning(month);
            System.out.println("Максимально доходный товар (услуга): " + maxEarning.name +
                    " сумма дохода от продаж: " + maxEarning.getTotal()+" рублей ");
            Item maxExpense = storage.getMaxExpense(month);
            System.out.println("Максимальная трата на приобретенный товар : " +
                    maxExpense.name + " сумма :" + maxExpense.getTotal() + " рулей ");
            System.out.println("----------------------------------------------------");
        }
    }

    private String getMonthName(int month) {
        String naimMonth = "";
        if (month == 1){
            naimMonth = "Январь";
        } else if (month == 2){
            naimMonth = "Февраль";
        } else if (month == 3) {
            naimMonth = "Март";
        }
            return naimMonth;
        }
   //}

    List<String> readFileContents(String path){
        try {
            return Files.readAllLines(Path.of(path));
        }   catch (IOException e){
            System.out.println("Невозможно прочитать файл с месячнымотчетом. Возможно файл не находится в папке");
            return Collections.emptyList();
        }
    }
}