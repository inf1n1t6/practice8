package decorator;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Дополнительный декоратор: фильтрация строк по минимальной сумме продажи.
 */
public class AmountFilterDecorator extends ReportDecorator {

    private final double minAmount;

    public AmountFilterDecorator(IReport wrapped, double minAmount) {
        super(wrapped);
        this.minAmount = minAmount;
    }

    @Override
    public String generate() {
        String raw = super.generate();
        String filtered = Arrays.stream(raw.split("\n"))
                .filter(line -> {
                    if (line.isBlank() || line.startsWith("===") || line.startsWith("ID")
                            || line.startsWith("[")) return true;
                    try {
                        int idx = line.lastIndexOf('$');
                        if (idx < 0) return true;
                        double amount = Double.parseDouble(line.substring(idx + 1).trim());
                        return amount >= minAmount;
                    } catch (Exception e) {
                        return true;
                    }
                })
                .collect(Collectors.joining("\n"));

        return "[AmountFilter: >= $" + minAmount + "]\n" + filtered;
    }
}
