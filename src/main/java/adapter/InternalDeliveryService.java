package adapter;

import java.util.logging.Logger;

/**
 * Собственная внутренняя служба доставки.
 */
public class InternalDeliveryService implements IInternalDeliveryService {

    private static final Logger log = Logger.getLogger(InternalDeliveryService.class.getName());

    @Override
    public void deliverOrder(String orderId) {
        log.info("[InternalDelivery] Delivering order: " + orderId);
        System.out.println("[InternalDelivery] Order " + orderId + " dispatched via internal fleet.");
    }

    @Override
    public String getDeliveryStatus(String orderId) {
        // Симуляция: статус зависит от последней цифры orderId
        int last = Character.getNumericValue(orderId.charAt(orderId.length() - 1));
        return switch (last % 3) {
            case 0 -> "IN_TRANSIT";
            case 1 -> "DELIVERED";
            default -> "PROCESSING";
        };
    }

    @Override
    public double calculateDeliveryCost(String orderId) {
        return 5.99;
    }
}
