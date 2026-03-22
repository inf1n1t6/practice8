package decorator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Декоратор: фильтрация строк отчёта по диапазону дат (формат строки: YYYY-MM-DD | ...).
 */
public class DateFilterDecorator extends ReportDecorator {

    private final LocalDate from;
    private final LocalDate to;

    public DateFilterDecorator(IReport wrapped, LocalDate from, LocalDate to) {
        super(wrapped);
        this.from = from;
        this.to = to;
    }

    @Override
    public String generate() {
        String raw = super.generate();
        String[] lines = raw.split("\n");

        String filtered = Arrays.stream(lines)
                .filter(line -> {
                    // Заголовки и пустые строки пропускаем как есть
                    if (line.isBlank() || line.startsWith("===") || line.startsWith("ID")) return true;
                    // Ищем дату в начале строки
                    try {
                        LocalDate date = LocalDate.parse(line.substring(0, 10).trim());
                        return !date.isBefore(from) && !date.isAfter(to);
                    } catch (Exception e) {
                        return true; // строка без даты — оставляем
                    }
                })
                .collect(Collectors.joining("\n"));

        return "[DateFilter: " + from + " — " + to + "]\n" + filtered;
    }
}
