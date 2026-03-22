package adapter;

/**
 * Сторонняя логистическая служба B — другой интерфейс.
 */
public class ExternalLogisticsServiceB {

    public void sendPackage(String packageInfo) {
        System.out.println("[ServiceB] Sending package: " + packageInfo);
    }

    public String checkPackageStatus(String trackingCode) {
        if (trackingCode.startsWith("A")) return "SHIPPED";
        if (trackingCode.startsWith("B")) return "OUT_FOR_DELIVERY";
        return "UNKNOWN";
    }

    public double estimateCost(String packageInfo) {
        return 12.00 + packageInfo.length() * 0.05;
    }
}
