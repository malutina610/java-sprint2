import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportService {
    InMemoryStorage storage = new InMemoryStorage();//Хранилище

    private String getMonthName(int month){
        String [] arrayMonth = {"январь","февраль","март","апрель ","май ","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
        String naimMonth = arrayMonth [month];
        return naimMonth;
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Не возможно считать файл с отчетом, возможно файл не находится в папке");
            return Collections.emptyList();
        }
    }
    public void loadMonthReports(String year) {
        for (int i = 1; i < 4; i++) {
            String path = "./resources/m." + year + "0" + i + ".csv";
            ArrayList<Item> items = loadMonthReport(path);
            storage.saveMonthReport(i, items);// сохранил
        }
    }

    ArrayList<Item> loadMonthReport(String path) {
        List<String> lines = readFileContents(path);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
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
        for (int i = 1; i < 4; i++) {
            int month = i;
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Отчет за  " + getMonthName(month - 1) + " месяц");
            Item maxEarning = storage.getMaxEarning(month);
            System.out.println("Максимально доходный товар (услуга): " + maxEarning.name +
                    " сумма дохода от продаж: " + maxEarning.getTotal() + " рублей ");
            Item maxExpense = storage.getMaxExpense(month);
            System.out.println("Максимальная трата на приобретенный товар : " +
                    maxExpense.name + " сумма :" + maxExpense.getTotal() + " рулей ");
        }
    }

    public void loadYearReports(String year) {
        String path = "./resources/y." + year + ".csv";
        List<String> lines = readFileContents(path);
        ArrayList<MonthYear> monthYears = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);//01,1593150,false
            String[] rows = line.split(",");
            MonthYear monthYear = new MonthYear(Integer.parseInt(rows[0]),
                    Long.parseLong(rows[1]),
                    Boolean.parseBoolean(rows[2]));
            monthYears.add(monthYear);
            storage.saveYearReport( year, monthYears);// сохранил
        }
    }

    public void printYearReports(String year) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                       Отчет за " + year + " год");
        System.out.println("--------------------------------------------------------------------------");
        MonthYear maxMontYear = storage.getMaxMontYear(Integer.parseInt(year));
        System.out.println("Максимальный доход, был  в " + getMonthName(maxMontYear.month - 1) +
                " месяце " + "  он состовил: - " + maxMontYear.amount + " рублей; ");
        System.out.println("-");
        Integer averageExpenseAllMonthYear = storage.getAverageExpenseAllMonthYear(Integer.parseInt(year));
        System.out.println("Средний расход за все месяцы, " + year + " года, составил:  " + averageExpenseAllMonthYear +
                " рублей;");
        System.out.println("-");
        Integer averageProfitAllMonthYear = storage.getAverageProfitAllMonthYear(Integer.parseInt(year));
        System.out.println("Средняя прибыль за все месяцы," + year + " года составила:  " +
                averageProfitAllMonthYear + " рублей;");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                        Верстка годового отчета                           ");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 1; i < 13; i++) {
            Integer month = i;
            Integer profitAllMonthYear = storage.getProfitAllMonthYear(i, Integer.parseInt(year));
            if(profitAllMonthYear != 0){
                System.out.println("Доход за " + getMonthName(month - 1) + " месяц : " +
                        profitAllMonthYear + " рублей ");
                System.out.println("-");
            }
            Integer expenseAllMonthYear = storage.getExpenseAllMonthYear(i, Integer.parseInt(year));
            if(expenseAllMonthYear != 0){
                System.out.println("Расход за " + getMonthName(month - 1) + " месяц: " +
                        expenseAllMonthYear + " рублей");
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    public void checkMonthYearReports() {
        Integer year = 2021;
        Integer flag = 0;
        for (int i = 1; i < 4; i++) {
            int month = i;
            Integer amountMonthYear = storage.getProfitAllMonthYear(i, year);
            int sumMontExpense = storage.getSumMontExpense(month);
            if(amountMonthYear != sumMontExpense){
                flag ++;
                if(flag == 1){
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println("----------------   Протокол несоответствия отчетов     -------------------");
                    System.out.println("--------------------------------------------------------------------------");
                }
                System.out.println("В месячном отчете доход за " + getMonthName(month - 1) + " месяц : " +
                        sumMontExpense + " рублей ");
                System.out.println("В годовом отчете доход за " + getMonthName(month - 1) + " месяц : " +
                        amountMonthYear + " рублей ");
                System.out.println("-");
            }
            int sumMontExpenseFalse = storage.getSumMontExpenseFalse(month);
            Integer expenseAllMonthYear = storage.getExpenseAllMonthYear(i, year);
            if(sumMontExpenseFalse != expenseAllMonthYear ){
                flag ++;
                if(flag == 1){
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println("----------------   Протокол несоответствия отчетов     -------------------");
                    System.out.println("--------------------------------------------------------------------------");
                }
                System.out.println("В месячном отчете расход за " + getMonthName(month - 1) + " месяц : " +
                        sumMontExpenseFalse + " рублей ");
                System.out.println("В годовом отчете расход за " + getMonthName(month - 1) + " месяц : " +
                        expenseAllMonthYear + " рублей ");
                System.out.println("-");
            }
        }
    }
}