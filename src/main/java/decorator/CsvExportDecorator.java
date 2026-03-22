package decorator;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Декоратор: конвертирует отчёт в CSV-формат.
 */
public class CsvExportDecorator extends ReportDecorator {

    public CsvExportDecorator(IReport wrapped) {
        super(wrapped);
    }

    @Override
    public String generate() {
        String raw = super.generate();
        String csv = Arrays.stream(raw.split("\n"))
                .map(line -> Arrays.stream(line.split("\\|"))
                        .map(String::trim)
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));

        return "[Export: CSV]\n" + csv;
    }
}
