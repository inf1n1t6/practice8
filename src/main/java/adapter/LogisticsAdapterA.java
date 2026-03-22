package adapter;

import java.util.logging.Logger;

/**
 * Адаптер для ExternalLogisticsServiceA.
 * Преобразует интерфейс A к IInternalDeliveryService.
 */
public class LogisticsAdapterA implements IInternalDeliveryService {

    private static final Logger log = Logger.getLogger(LogisticsAdapterA.class.getName());
    private final ExternalLogisticsServiceA service;

    public LogisticsAdapterA(ExternalLogisticsServiceA service) {
        this.service = service;
    }

    @Override
    public void deliverOrder(String orderId) {
        try {
            int itemId = Integer.parseInt(orderId.replaceAll("\\D", ""));
            log.info("[AdapterA] Adapting deliverOrder -> shipItem(" + itemId + ")");
            service.shipItem(itemId);
        } catch (NumberFormatException e) {
            log.warning("[AdapterA] Cannot parse orderId: " + orderId);
        }
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        try {
            int shipmentId = Integer.parseInt(orderId.replaceAll("\\D", ""));
            String rawStatus = service.trackShipment(shipmentId);
            log.info("[AdapterA] trackShipment raw status: " + rawStatus);
            // Нормализуем статус к внутреннему формату
            return switch (rawStatus) {
                case "ON_THE_WAY"  -> "IN_TRANSIT";
                case "ARRIVED"     -> "DELIVERED";
                default            -> "PROCESSING";
            };
        } catch (Exception e) {
            log.warning("[AdapterA] Error getting status: " + e.getMessage());
            return "ERROR";
        }
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        int itemId = Integer.parseInt(orderId.replaceAll("\\D", "0"));
        return service.getShippingRate(itemId);
    }
}
