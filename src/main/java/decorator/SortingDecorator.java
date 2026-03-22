package decorator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Декоратор: сортировка строк отчёта по заданному критерию.
 * Критерии: "date" (0-я колонка), "amount" (последняя колонка с $).
 */
public class SortingDecorator extends ReportDecorator {

    public enum SortBy { DATE, AMOUNT }

    private final SortBy sortBy;

    public SortingDecorator(IReport wrapped, SortBy sortBy) {
        super(wrapped);
        this.sortBy = sortBy;
    }

    @Override
    public String generate() {
        String raw = super.generate();
        String[] lines = raw.split("\n");

        // Отделяем заголовочные строки от данных
        StringBuilder headers = new StringBuilder();
        java.util.List<String> dataLines = new java.util.ArrayList<>();

        for (String line : lines) {
            if (line.isBlank() || line.startsWith("===") || line.startsWith("ID")
                    || line.startsWith("[")) {
                headers.append(line).append("\n");
            } else {
                dataLines.add(line);
            }
        }

        Comparator<String> comparator = switch (sortBy) {
            case DATE -> Comparator.comparing(l -> {
                try { return l.substring(0, 10).trim(); } catch (Exception e) { return ""; }
            });
            case AMOUNT -> Comparator.comparingDouble(l -> {
                try {
                    int idx = l.lastIndexOf('$');
                    return Double.parseDouble(l.substring(idx + 1).trim());
                } catch (Exception e) { return 0.0; }
            });
        };

        String sorted = dataLines.stream()
                .sorted(comparator)
                .collect(Collectors.joining("\n"));

        return headers + "[Sorted by: " + sortBy + "]\n" + sorted;
    }
}
