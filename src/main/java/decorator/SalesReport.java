package decorator;

/**
 * Базовый отчёт по продажам с фиктивными данными.
 */
public class SalesReport implements IReport {

    @Override
    public String generate() {
        return """
                === Sales Report ===
                2024-01-05 | Order #1001 | Laptop       | $1200.00
                2024-01-12 | Order #1002 | Phone        |  $800.00
                2024-02-03 | Order #1003 | Headphones   |  $150.00
                2024-02-18 | Order #1004 | Monitor      |  $450.00
                2024-03-07 | Order #1005 | Keyboard     |   $90.00
                """;
    }
}
