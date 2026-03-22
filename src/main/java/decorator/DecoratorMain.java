package decorator;

import java.time.LocalDate;

/**
 * Клиентский код — демонстрация паттерна Декоратор.
 */
public class DecoratorMain {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       PATTERN: DECORATOR — Reports       ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── 1. Простой отчёт по продажам ─────────────────────────────────
        IReport salesReport = new SalesReport();
        System.out.println("--- [1] Raw Sales Report ---");
        System.out.println(salesReport.generate());

        // ── 2. Отчёт по продажам + фильтр по датам ────────────────────────
        IReport filtered = new DateFilterDecorator(salesReport,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31));
        System.out.println("--- [2] Sales Report filtered by January 2024 ---");
        System.out.println(filtered.generate());

        // ── 3. Отчёт + фильтр по датам + сортировка по сумме ─────────────
        IReport sortedByAmount = new SortingDecorator(
                new DateFilterDecorator(salesReport,
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 12, 31)),
                SortingDecorator.SortBy.AMOUNT);
        System.out.println("--- [3] Sales Report 2024 sorted by amount ---");
        System.out.println(sortedByAmount.generate());

        // ── 4. Отчёт + CSV экспорт ────────────────────────────────────────
        IReport csvReport = new CsvExportDecorator(salesReport);
        System.out.println("--- [4] Sales Report exported to CSV ---");
        System.out.println(csvReport.generate());

        // ── 5. Цепочка: фильтр суммы + сортировка + PDF экспорт ──────────
        IReport fullChain = new PdfExportDecorator(
                new SortingDecorator(
                        new AmountFilterDecorator(salesReport, 200.0),
                        SortingDecorator.SortBy.AMOUNT));
        System.out.println("--- [5] Sales Report: amount >= $200, sorted, PDF ---");
        System.out.println(fullChain.generate());

        // ── 6. UserReport + CSV ───────────────────────────────────────────
        IReport userCsv = new CsvExportDecorator(new UserReport());
        System.out.println("--- [6] User Report exported to CSV ---");
        System.out.println(userCsv.generate());
    }
}
