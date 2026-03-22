package adapter;

/**
 * Дополнительная сторонняя служба C — уникальный интерфейс (задание 2).
 */
public class ExternalLogisticsServiceC {

    public boolean dispatchCargo(String cargoCode, int priority) {
        System.out.println("[ServiceC] Dispatching cargo=" + cargoCode + " priority=" + priority);
        return true;
    }

    public String queryCargo(String cargoCode) {
        return "C_STATUS_" + (cargoCode.hashCode() % 3 == 0 ? "MOVING" : "WAITING");
    }

    public double quoteCargo(String cargoCode) {
        return 15.00 + cargoCode.length();
    }
}
