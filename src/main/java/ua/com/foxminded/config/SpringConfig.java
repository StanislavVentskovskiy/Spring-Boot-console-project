package ua.com.foxminded.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.jdbc.Sql;
import javax.sql.DataSource;

@Configuration
@ComponentScan("ua.com.foxminded")
@Sql("/schema.sql")
@PropertySource("classpath:application.properties")
public class SpringConfig {
    @Value("${postgresql.driver}")
    private String driverName;
    @Value("${dburl}")
    private String dbUrl;
    @Value("${user}")
    private String dbUsername;
    @Value("${password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
