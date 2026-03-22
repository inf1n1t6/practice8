package adapter;

import java.util.logging.Logger;

/**
 * Адаптер для ExternalLogisticsServiceB.
 * Преобразует интерфейс B к IInternalDeliveryService.
 */
public class LogisticsAdapterB implements IInternalDeliveryService {

    private static final Logger log = Logger.getLogger(LogisticsAdapterB.class.getName());
    private final ExternalLogisticsServiceB service;

    public LogisticsAdapterB(ExternalLogisticsServiceB service) {
        this.service = service;
    }

    @Override
    public void deliverOrder(String orderId) {
        try {
            String packageInfo = "order=" + orderId + ";weight=2kg;priority=standard";
            log.info("[AdapterB] Adapting deliverOrder -> sendPackage(\"" + packageInfo + "\")");
            service.sendPackage(packageInfo);
        } catch (Exception e) {
            log.warning("[AdapterB] Error delivering order: " + e.getMessage());
        }
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        try {
            // Генерируем tracking code из orderId
            String trackingCode = orderId.toUpperCase().startsWith("A") ? "A-" + orderId : "B-" + orderId;
            String rawStatus = service.checkPackageStatus(trackingCode);
            log.info("[AdapterB] checkPackageStatus raw status: " + rawStatus);
            return switch (rawStatus) {
                case "SHIPPED"           -> "IN_TRANSIT";
                case "OUT_FOR_DELIVERY"  -> "IN_TRANSIT";
                default                  -> "PROCESSING";
            };
        } catch (Exception e) {
            log.warning("[AdapterB] Error getting status: " + e.getMessage());
            return "ERROR";
        }
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        String packageInfo = "order=" + orderId;
        return service.estimateCost(packageInfo);
    }
}
