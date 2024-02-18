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
          //  Integer month = i;
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
            naimMonth = "январь";
        } else if (month == 2){
            naimMonth = "февраль";
        } else if (month == 3) {
            naimMonth = "март";
        } else if (month == 4) {
            naimMonth = "апрель ";
        } else if (month == 5) {
            naimMonth = "май ";
        } else if (month == 6){
            naimMonth = "июнь";
        } else if (month == 7){
            naimMonth = "июль";
        } else if (month == 8){
            naimMonth = "август";
        } else if (month == 9){
            naimMonth = "сентябрь";
        } else if (month == 10){
            naimMonth = "октябрь";
        } else if (month == 11) {
            naimMonth = "ноябрь";
        } else if (month == 12) {
            naimMonth = "декабрь";
        }
        return naimMonth;
        }
    public void loadYearReports() {
        String path = "./resources/y.2021.csv";
        List<String> lines = readFileContents(path);
        ArrayList<MonthYear> monthYears = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++){
            int month = i;
            String line = lines.get(i);//01,1593150,false
            String[] rows = line.split(",");
            MonthYear monthYear = new MonthYear( Integer.parseInt(rows[0]),
                    Long.parseLong(rows[1]),
                    Boolean.parseBoolean(rows[2]));
            monthYears.add(monthYear);
            storage.saveYearReport(2021, monthYears);// сохранил
        }
    }

    List<String> readFileContents(String path){
        try {
            return Files.readAllLines(Path.of(path));
        }   catch (IOException e){
            System.out.println("Невозможно прочитать файл с месячнымотчетом. Возможно файл не находится в папке");
            return Collections.emptyList();
        }
   }

    public void printYearReports() {
        int year = 2021;
            System.out.println("----------------------------------------------------");
            System.out.println("             Отчет за " + year + " год");
            System.out.println("----------------------------------------------------");
            MonthYear maxMontYear = storage.getMaxMontYear(year);
            System.out.println("Максимальный доход, был  в "+ getMonthName(maxMontYear.month ) +
                    " месяце " + "  он состовил: - " + maxMontYear.amount + " рублей; ");
        System.out.println("--------");
        Integer averageExpenseAllMonthYear = storage.getAverageExpenseAllMonthYear(year);
            System.out.println("Средний расход за все месяцы, " + year + " года, составил:  " + averageExpenseAllMonthYear +
                    " рублей;");
        System.out.println("--------");
        Integer averageProfitAllMonthYear = storage.getAverageProfitAllMonthYear(year);
            System.out.println("Средняя прибыль за все месяцы," + year + " года составила:  " +
                    averageProfitAllMonthYear + " рублей;");
        System.out.println("--------");
        for(int i =1; i < 13; i++) {
               Integer month = i;
               System.out.println("Доход за " + getMonthName(month) +  " месяц: ");
               System.out.println(storage.yearReports); //вывод в строку всех отчетов
               System.out.println("Расход за " + getMonthName(month) +  " месяц: ");
               System.out.println(storage.yearReports); //вывод в строку всех отчетов
               System.out.println("--------");

        }

    }
}