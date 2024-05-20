package com.ssafy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maximumPoolSize;

	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minimumIdle;

	@Value("${spring.datasource.hikari.connection-timeout}")
	private int connectionTimeout;

	@Value("${spring.datasource.hikari.idle-timeout}")
	private int idleTimeout;

	@Value("${spring.datasource.hikari.max-lifetime}")
	private int maxLifetime;

	@Value("${spring.datasource.hikari.connection-init-sql}")
	private String connectionInitSql;

	@Value("${spring.datasource.hikari.validation-timeout}")
	private int validationTimeout;

	@Value("${spring.datasource.hikari.auto-commit}")
	private boolean autoCommit;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.jdbc-url}")
	private String jdbcUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setMaximumPoolSize(maximumPoolSize);
		config.setMinimumIdle(minimumIdle);
		config.setConnectionTimeout(connectionTimeout);
		config.setIdleTimeout(idleTimeout);
		config.setMaxLifetime(maxLifetime);
		config.setConnectionInitSql(connectionInitSql);
		config.setValidationTimeout(validationTimeout);
		config.setAutoCommit(autoCommit);
		return new HikariDataSource(config);
	}
}
