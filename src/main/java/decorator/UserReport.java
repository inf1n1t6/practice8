package decorator;

/**
 * Базовый отчёт по пользователям с фиктивными данными.
 */
public class UserReport implements IReport {

    @Override
    public String generate() {
        return """
                === User Report ===
                ID  | Name           | Email                   | Registered
                1   | Alice Johnson  | alice@example.com       | 2023-11-01
                2   | Bob Smith      | bob@example.com         | 2023-12-15
                3   | Carol White    | carol@example.com       | 2024-01-20
                4   | David Brown    | david@example.com       | 2024-02-10
                5   | Eva Green      | eva@example.com         | 2024-03-05
                """;
    }
}
