package decorator;

/**
 * Декоратор: симулирует экспорт отчёта в PDF.
 */
public class PdfExportDecorator extends ReportDecorator {

    public PdfExportDecorator(IReport wrapped) {
        super(wrapped);
    }

    @Override
    public String generate() {
        String raw = super.generate();
        // В реальном проекте здесь был бы вызов PDF-библиотеки (iText, Apache PDFBox и т.д.)
        return "[Export: PDF]\n"
                + "=== PDF Document Start ===\n"
                + raw
                + "\n=== PDF Document End ===";
    }
}
