package controller;

import customer.Customer;
import io.ReadWrite;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    List<Customer> customers = new ArrayList<>();
    ReadWrite readWrite;
    List<String> rawData;
    String[][] formattedData;
    public MainController(String path) throws IOException {
        this.readWrite = new ReadWrite();
        this.rawData = readWrite.readFile(path);
        this.formattedData = formatData(rawData);
        createCustomerList(formattedData);
    }

    public void writeToFile(String path, String name, String personNummer) throws IOException {
        StringBuilder output = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        output.append(name).append(",").append(personNummer).append(",").append(formatter.format(LocalDateTime.now())).append("\n");
        readWrite.writeToFile(path, output.toString());
    }

    private void createCustomerList(String[][] formattedData) {
        for (int i = 0; i < formattedData.length; i++) {
            addCustomer(new Customer(Long.parseLong(formattedData[i][0].trim()), formattedData[i][1].trim(), formattedData[i][2].trim()));
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer doesPersonExistLong(long personNr) {
        return customers.stream().
                filter(e -> e.getPersNr() == personNr).
                findFirst().
                orElse(null);
    }

    public Customer doesPersonExistString(String customerName) {
        return customers.stream().
                filter(c -> c.getName().equals(customerName)).
                findFirst().
                orElse(null);
    }

    public boolean doesPersonHaveValidAccount(Customer customer){
        return LocalDate.now().
                minusYears(1).
                isBefore(customer.getLastPayedDate());
    }

    public String[][] formatData(List<String> inputList){
        int columns = 3;
        String[][] formattedData = new String[inputList.size() / 2][columns];
        int counter = 0;
        StringBuilder customerRow = new StringBuilder();
        for(int i = 0; i < inputList.size(); i++){
            if (counter == 0){
                customerRow = new StringBuilder(inputList.get(i));
                counter++;
            } else if (counter == 1){
                customerRow.append(",").append(inputList.get(i));
                formattedData[i / 2] = customerRow.toString().split(",");
                counter = 0;
            }
        }
        return formattedData;
    }

    public List<String> getRawData() {
        return rawData;
    }

    public void setRawData(List<String> rawData) {
        this.rawData = rawData;
    }

    public String[][] getFormattedData() {
        return formattedData;
    }

    public void setFormattedData(String[][] formattedData) {
        this.formattedData = formattedData;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
