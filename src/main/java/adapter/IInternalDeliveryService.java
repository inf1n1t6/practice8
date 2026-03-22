package adapter;

/**
 * Единый интерфейс внутренней службы доставки.
 */
public interface IInternalDeliveryService {
    void deliverOrder(String orderId);
    String getDeliveryStatus(String orderId);
    double calculateDeliveryCost(String orderId);
}
