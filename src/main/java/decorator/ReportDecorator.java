package decorator;

/**
 * Абстрактный декоратор — хранит ссылку на базовый отчёт
 * и делегирует ему вызов generate().
 */
public abstract class ReportDecorator implements IReport {

    protected final IReport wrapped;

    public ReportDecorator(IReport wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String generate() {
        return wrapped.generate();
    }
}
