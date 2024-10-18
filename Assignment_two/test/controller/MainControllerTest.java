package controller;

import customer.Customer;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MainControllerTest extends TestCase {

    MainController controller = new MainController("test/users_test.txt");

    public MainControllerTest() throws IOException {
    }

    @Test
    public void testWriteToFile() throws IOException {
        String path = "test/testFile.txt";
        String name = "Hasse";
        String persNr = "198502020255";
        controller.writeToFile(path, name, persNr);
        assertNotNull(controller.readWrite.readFile(path));
    }

    @Test
    public void testAddCustomer() {
        controller.addCustomer(new Customer(800502L,"Bosse", "1980-05-02"));
        List<Customer> customerList = controller.getCustomers();
        assertEquals(6, customerList.size());
    }

    @Test
    public void testGetCustomers() {
        List<Customer> customerList = controller.getCustomers();
        assertNotNull(customerList);
        assertEquals("Nahema Ninsson", customerList.getLast().getName());
        assertEquals(5, customerList.size());
    }

    @Test
    public void testDoesPersonExistLong() {
        assertNotNull(controller.doesPersonExistLong(7907281234L).getName());
        assertEquals("Jicky Juul", controller.doesPersonExistLong(9902149834L).getName());
        assertEquals("Nahema Ninsson", controller.doesPersonExistLong(7805211234L).getName());
    }

    @Test
    public void testDoesPersonExistString() {
        assertNotNull(controller.doesPersonExistString("Kadine Karlsson").getName());
        assertEquals("Jicky Juul", controller.doesPersonExistString( "Jicky Juul").getName());
        assertEquals("Nahema Ninsson", controller.doesPersonExistString("Nahema Ninsson").getName());
    }

    @Test
    public void testDoesPersonHaveValidAccount() {
        Customer klas = new Customer(4604151234L,"Bosse", String.valueOf(LocalDate.now().minusMonths(13)));
        assertFalse(controller.doesPersonHaveValidAccount(klas));
        klas.setLastPayedDate(LocalDate.now().minusMonths(3));
        assertTrue(controller.doesPersonHaveValidAccount(klas));
    }

    @Test
    public void testFormatData() {
        List<String> data = controller.getRawData();
        String[][] formattedData = controller.formatData(data);
        assertTrue(controller.formatData(data).length > 0);
        assertEquals(controller.formatData(data).length, (controller.getRawData().size() / 2));
        assertEquals("9902149834", formattedData[0][0]);
    }
}