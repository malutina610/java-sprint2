import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class InMemoryStorage {
    LinkedHashMap<Integer, ArrayList<Item>> monthReports = new LinkedHashMap<>();
    LinkedHashMap<Integer, ArrayList<MonthYear>> yearReports = new LinkedHashMap<>();

    //LinkedHashMap<Integer, Integer> mapaMonthToCount = new LinkedHashMap<>();
//    LinkedHashMap<Integer, Integer> mapaMonthToCountExpence = new LinkedHashMap<>();

    public void saveMonthReport(int year, int month, ArrayList<Item> items) {
        monthReports.put(month, items);
    }

    public Item getMaxEarning(int month) {
        ArrayList<Item> items = monthReports.get(month);// делаю массив по месяцу??????? КАК?????
        Item max = null;
        long total = 0;
        for (Item item : items) {
            if (item.expense){
                continue;
            }
            if (item.getTotal() > total){
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }

    public Item getMaxExpense(int month) {
        ArrayList<Item> items = monthReports.get(month);
        Item max = null;
        long total = 0;
        for (Item item : items) {
            if (!item.expense){
                continue;
            }
            if (item.getTotal() > total){
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }
////////////////////////////////////////////////////////////////////////////////////////
    public int getSumMontExpense (int month) {
        ArrayList<Item> items = monthReports.get(month);
        Integer suma = 0;
        long total = 0;
        for (Item item : items) {
            if (item.expense){
                continue;
            }
            total = item.getTotal();
            suma = Math.toIntExact(suma + total);
            //mapaMonthToCount.put(month, suma);
        }
        return suma;
    }

    public int getSumMontExpenseFalse (int month) {
        ArrayList<Item> items = monthReports.get(month);
        Integer suma = 0;
        long total = 0;
        for (Item item : items) {
            if (!item.expense){
                continue;
            }
            total = item.getTotal();
            suma = Math.toIntExact(suma + total);
           // mapaMonthToCountExpence.put(month, suma);
        }
        return suma;
    }
///////////////////////////////////////////////////////////////////////////////
    public void saveYearReport(int year, ArrayList<MonthYear> monthYears ) {
        yearReports.put(year, monthYears);
    }
    public MonthYear getMaxMontYear(int year) {
        ArrayList<MonthYear> monthYears = yearReports.get(year);
        MonthYear max = null;
        long total = 0;
        for (MonthYear monthYear : monthYears) {
            if(!monthYear.expense){
                continue;
            }
            if (monthYear.amount > total){
                total = monthYear.amount;
                max = monthYear;
            }
        }
    return max;
    }

    public Integer getAverageExpenseAllMonthYear(int year) {
        ArrayList<MonthYear> monthYears = yearReports.get(year);
        Integer summ = 0;
        Integer average = 0;
        Integer expenseTrue = 0;
        for (MonthYear monthYear : monthYears) {
            if(!monthYear.expense){
                continue;
            }
            expenseTrue ++;
            summ = Math.toIntExact(summ + monthYear.amount);
            average = summ / expenseTrue;
        }
        return average;
    }

    public Integer getAverageProfitAllMonthYear(int year) {
        ArrayList<MonthYear> monthYears = yearReports.get(year);
        Integer sum = 0;
        Integer averagesum = 0;
        Integer expenseFalse = 0;
        for (MonthYear monthYear : monthYears) {
            if(monthYear.expense){
                continue;
            }
            expenseFalse ++;
            sum = Math.toIntExact(sum + monthYear.amount);
            averagesum = sum / expenseFalse;
        }
        return averagesum;
    }

    public Integer getProfitAllMonthYear(int i, int year) {
        ArrayList<MonthYear> monthYears = yearReports.get(year);
        Integer profit = 0;
        for (MonthYear monthYear : monthYears) {
            if(monthYear.month!=i){
                continue;
            }
            if(monthYear.expense){
                continue;
            }
            profit = Math.toIntExact(monthYear.amount);
        }
        return profit;
    }

    public Integer getExpenseAllMonthYear(int i, int year) {
        ArrayList<MonthYear> monthYears = yearReports.get(year);
        Integer expense = 0;
        for (MonthYear monthYear : monthYears){
            if(monthYear.month!=i){
                continue;
            }
            if (!monthYear.expense){
                continue;
            }
            expense = Math.toIntExact(monthYear.amount);
        }
        return expense;
    }
}