import java.util.Scanner;

public class BuhApplication {
    private Scanner scanner;
    private ReportService service;

    public void run(){
        scanner = new Scanner(System.in);
        service = new ReportService();
        String line = "";
        String year ="";


        while (!line.equals("6")){
            boolean read = true;
            printMenu();
            line = scanner.nextLine();
            if (line.isEmpty()){
                return;
            } else if (line.equals("1")) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Начинаем считывать все меясчные отчеты");
                System.out.println("~ ~ ~ ~");
                service.loadMonthReports();
                System.out.println("Месячные отчеты успешно считаны");
                System.out.println("--------------------------------------------------------------------------");
            } else if (line.equals("2")) {
                System.out.println("Укажите за какой год необходимо считать отчет");
                line = scanner.nextLine();
                if (line.equals("2021")) {
                    year = "2021";
                } else if (line.equals("2022")) {
                    year = "2022";
                } else{
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println("Для " + line + " года отчет не найден");
                    System.out.println("--------------------------------------------------------------------------");
                    read = false;
                }
                if (read == true) {
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println("Начинаем считывать годовой отчет ");
                    System.out.println("~ ~ ~ ~");
                    service.loadYearReports(year);
                    System.out.println("Отчет за " + year + "год успешно считан");
                    System.out.println("--------------------------------------------------------------------------");
                    System.out.println();
                }
            } else if (line.equals("3")) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Начинаем сверять отчеты ");
                service.checkMonthYearReports();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Сверка отчетов успешно завершена");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println();
            } else if (line.equals("4")) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Начинаем выводить информацию о всех месячных отчетах");
                System.out.println("--------------------------------------------------------------------------");
                service.printMonthReportInfo();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Вывод месячного отчета завершен");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println();
            } else if (line.equals("5")) {
                service.printYearReports();
                System.out.println("Вывод годового отчета завершен");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println();
            } else if (line.equals("6")) {
                System.out.println("Работа программы завершена ");
                return;
            } else {
                System.out.println("Введена неизвестная комманда, попробуйте снова: ");
            }
        }
    }

    private void printMenu(){
        System.out.println("Выбирите пункт меню: ");
        System.out.println("1 - считать все меясчные отчеты;");
        System.out.println("2 - считать годовой отчет;");
        System.out.println("3 - сверить отчеты;");
        System.out.println("4 - вывеси информацию о всех месячных отчтетах;");
        System.out.println("5 - вывести информацию о годовом отчете;");
        System.out.println("6 - нажмите  для завершения работы программы;");
    }
}
