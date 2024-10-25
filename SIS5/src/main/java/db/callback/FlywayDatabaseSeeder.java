package db.callback;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Event;
import org.flywaydb.core.api.callback.Context;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlywayDatabaseSeeder implements Callback {

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.AFTER_MIGRATE;
    }

    @Override
    public void handle(Event event, Context context) {
        try (var statement = context.getConnection().createStatement()) {
            var ADMIN_EMAIL = "superadmin@example.com";

            var checkQuery = "SELECT id FROM users WHERE email = '%s'"
                    .formatted(ADMIN_EMAIL);

            ResultSet resultSet = statement.executeQuery(checkQuery);

            // Проверка, есть ли результаты
            if (resultSet.next()) {
                // Пользователь уже существует, завершить выполнение
                return;
            }

            // Вставить "Super Admin" пользователя
            var sql = """
                    INSERT INTO users (email, name) VALUES
                    ('%s', 'Super Admin')
                    """.formatted(ADMIN_EMAIL);
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public String getCallbackName() {
        return FlywayDatabaseSeeder.class.getName();
    }
}



