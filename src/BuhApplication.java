import java.util.Scanner;

public class BuhApplication {
    private Scanner scanner;
    private ReportService service;

    public void run(){
        scanner = new Scanner(System.in);
        service = new ReportService();
        String line = "";

        while (!line.equals("6")){
            printMenu();
            line = scanner.nextLine();
            if (line.isEmpty()){
                return;
            } else if (line.equals("1")) {
                System.out.println("Начинаем считывать все меясчные отчеты");
                service.loadMonthReports();
                System.out.println("Завершаем считывать все меясчные отчеты");
            } else if (line.equals("2")) {
                System.out.println("Начинаем считывать годовой отчет ");
                //service.loadMonthReports();
                System.out.println("Завершаем считывать годовой отчет");
            } else if (line.equals("3")) {
                System.out.println("Начинаем сверять отчеты ");
                //service.loadMonthReports();
                System.out.println("Завершаем сверять отчеты");
            } else if (line.equals("4")) {
                System.out.println("Начинаем выводить информацию о всех месячных отчетах");
                service.printMonthReportInfo();
                System.out.println("Завершаем выводить информацию о всех месячных отчетах");
            } else if (line.equals("5")) {
                System.out.println("Начинаем выводить информацию о годовом отчете");
                //  service.printMonthReports();
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
