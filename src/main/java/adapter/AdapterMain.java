package adapter;

import static adapter.DeliveryServiceFactory.ServiceType.*;

/**
 * Клиентский код — демонстрация паттерна Адаптер.
 */
public class AdapterMain {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       PATTERN: ADAPTER — Logistics       ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        String[] orders = {"ORDER-1", "ORDER-2", "ORDER-3"};

        // ── 1. Внутренняя служба ──────────────────────────────────────────
        System.out.println("--- [1] Internal Delivery Service ---");
        IInternalDeliveryService internal = DeliveryServiceFactory.create(INTERNAL);
        runDemo(internal, orders);

        // ── 2. Внешняя служба A через адаптер ────────────────────────────
        System.out.println("--- [2] External Service A (via AdapterA) ---");
        IInternalDeliveryService adapterA = DeliveryServiceFactory.create(EXTERNAL_A);
        runDemo(adapterA, orders);

        // ── 3. Внешняя служба B через адаптер ────────────────────────────
        System.out.println("--- [3] External Service B (via AdapterB) ---");
        IInternalDeliveryService adapterB = DeliveryServiceFactory.create(EXTERNAL_B);
        runDemo(adapterB, orders);

        // ── 4. Дополнительная служба C через адаптер (задание 2) ─────────
        System.out.println("--- [4] External Service C (via AdapterC) ---");
        IInternalDeliveryService adapterC = DeliveryServiceFactory.create(EXTERNAL_C);
        runDemo(adapterC, orders);

        // ── 5. Фабрика по строковому ключу (задание 3) ────────────────────
        System.out.println("--- [5] Factory by string key ---");
        for (String key : new String[]{"INTERNAL", "FAST", "BUDGET", "CARGO"}) {
            IInternalDeliveryService svc = DeliveryServiceFactory.create(key);
            System.out.printf("Key=%-8s | cost for ORDER-99 = $%.2f%n",
                    key, svc.calculateDeliveryCost("ORDER-99"));
        }
    }

    /** Утилита: доставить + статус + стоимость для набора заказов. */
    private static void runDemo(IInternalDeliveryService service, String[] orderIds) {
        for (String id : orderIds) {
            service.deliverOrder(id);
            System.out.printf("  Status: %-12s | Cost: $%.2f%n",
                    service.getDeliveryStatus(id),
                    service.calculateDeliveryCost(id));
        }
        System.out.println();
    }
}
