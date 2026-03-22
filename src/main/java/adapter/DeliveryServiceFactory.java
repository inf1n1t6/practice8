package adapter;

/**
 * Фабрика для динамического выбора службы доставки.
 */
public class DeliveryServiceFactory {

    public enum ServiceType { INTERNAL, EXTERNAL_A, EXTERNAL_B, EXTERNAL_C }

    /**
     * Возвращает нужную службу доставки по типу.
     */
    public static IInternalDeliveryService create(ServiceType type) {
        return switch (type) {
            case INTERNAL   -> new InternalDeliveryService();
            case EXTERNAL_A -> new LogisticsAdapterA(new ExternalLogisticsServiceA());
            case EXTERNAL_B -> new LogisticsAdapterB(new ExternalLogisticsServiceB());
            case EXTERNAL_C -> new LogisticsAdapterC(new ExternalLogisticsServiceC());
        };
    }

    /**
     * Расширение фабрики: выбор по строковому ключу (задание 3).
     */
    public static IInternalDeliveryService create(String serviceKey) {
        return switch (serviceKey.toUpperCase()) {
            case "INTERNAL"    -> create(ServiceType.INTERNAL);
            case "A", "FAST"   -> create(ServiceType.EXTERNAL_A);
            case "B", "BUDGET" -> create(ServiceType.EXTERNAL_B);
            case "C", "CARGO"  -> create(ServiceType.EXTERNAL_C);
            default -> throw new IllegalArgumentException("Unknown service type: " + serviceKey);
        };
    }
}
