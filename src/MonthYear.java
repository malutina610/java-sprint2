public class MonthYear {
    Integer month;
    Long amount;
    boolean expense;

    @Override
    public String toString() {
        return "MonthYear{" +
                "month=" + month +
                ", amount=" + amount +
                ", expense=" + expense +
                '}';
    }
    public MonthYear (Integer month, Long amount, boolean expense) {
        this.month = month;
        this.amount = amount;//кол-во
        this.expense = expense;
    }
}

