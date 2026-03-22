package adapter;

import java.util.logging.Logger;

/**
 * Адаптер для ExternalLogisticsServiceC (задание 2 — дополнительная служба).
 */
public class LogisticsAdapterC implements IInternalDeliveryService {

    private static final Logger log = Logger.getLogger(LogisticsAdapterC.class.getName());
    private final ExternalLogisticsServiceC service;

    public LogisticsAdapterC(ExternalLogisticsServiceC service) {
        this.service = service;
    }

    @Override
    public void deliverOrder(String orderId) {
        try {
            String cargoCode = "CARGO-" + orderId;
            int priority = 1; // стандартный приоритет
            log.info("[AdapterC] Adapting deliverOrder -> dispatchCargo(\"" + cargoCode + "\", " + priority + ")");
            boolean success = service.dispatchCargo(cargoCode, priority);
            if (!success) {
                log.warning("[AdapterC] Dispatch failed for order: " + orderId);
            }
        } catch (Exception e) {
            log.warning("[AdapterC] Error delivering order: " + e.getMessage());
        }
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        try {
            String cargoCode = "CARGO-" + orderId;
            String rawStatus = service.queryCargo(cargoCode);
            log.info("[AdapterC] queryCargo raw status: " + rawStatus);
            return rawStatus.contains("MOVING") ? "IN_TRANSIT" : "PROCESSING";
        } catch (Exception e) {
            log.warning("[AdapterC] Error getting status: " + e.getMessage());
            return "ERROR";
        }
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        String cargoCode = "CARGO-" + orderId;
        return service.quoteCargo(cargoCode);
    }
}
