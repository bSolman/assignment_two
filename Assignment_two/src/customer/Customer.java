package customer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Customer {
    private final String name;
    private final long persNr;
    private LocalDate lastPayedDate;

    public Customer(long persNr, String name, String date) {
        this.name = name;
        this.persNr = persNr;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         this.lastPayedDate = LocalDate.parse(date);
    }

    public String getName() {
        return name;
    }

    public long getPersNr() {
        return persNr;
    }

    public LocalDate getLastPayedDate() {
        return lastPayedDate;
    }

    public void setLastPayedDate(LocalDate lastPayedDate) {
        this.lastPayedDate = lastPayedDate;
    }
}
