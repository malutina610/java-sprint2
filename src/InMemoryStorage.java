import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InMemoryStorage {
    LinkedHashMap<Integer, ArrayList<Item>> monthReports = new LinkedHashMap<>();
    LinkedHashMap<Integer, ArrayList<MonthYear>> yearReports = new LinkedHashMap<>();


    public void saveMonthReport(int year, int month, ArrayList<Item> items) {
        monthReports.put(month, items);
    }

    public Item getMaxEarning(int month) {
        ArrayList<Item> items = monthReports.get(month);
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
}