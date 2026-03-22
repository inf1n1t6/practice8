package adapter;

/**
 * Сторонняя логистическая служба A — свой интерфейс.
 */
public class ExternalLogisticsServiceA {

    public void shipItem(int itemId) {
        System.out.println("[ServiceA] Shipping item with id=" + itemId);
    }

    public String trackShipment(int shipmentId) {
        return switch (shipmentId % 3) {
            case 0 -> "ON_THE_WAY";
            case 1 -> "ARRIVED";
            default -> "PREPARING";
        };
    }

    public double getShippingRate(int itemId) {
        return 8.50 + itemId * 0.1;
    }
}
