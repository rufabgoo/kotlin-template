package kotlintemplate.config

import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.time.Duration

@Configuration
@EnableR2dbcRepositories(basePackages = ["kotlintemplate.db.postgres.repository"])
@EnableTransactionManagement
class PostgresConfig(private val propertyConfig: PropertyConfig) : AbstractR2dbcConfiguration() {

    private val connectionFactoryOptions: ConnectionFactoryOptions by lazy {
        ConnectionFactoryOptions.parse(propertyConfig.dbUrl)
                .mutate()
                .option(ConnectionFactoryOptions.USER, propertyConfig.dbUsername)
                .option(ConnectionFactoryOptions.PASSWORD, propertyConfig.dbPassword)
                .build()
    }

    @Bean(destroyMethod = "dispose")
    override fun connectionFactory(): ConnectionFactory {
        val postgresConnectionFactory = ConnectionFactories.get(connectionFactoryOptions)

        val pool = Pool()
        val builder = ConnectionPoolConfiguration.builder(postgresConnectionFactory)
                .maxIdleTime(pool.maxIdleTime)
                .initialSize(pool.initialSize)
                .maxSize(pool.maxSize)
                .maxCreateConnectionTime(pool.maxCreateConnectionTime)
                .acquireRetry(pool.acquireRetry)
                .maxAcquireTime(pool.maxAcquireTime)
                .maxLifeTime(pool.maxLifeTime)
                .registerJmx(pool.registerJmx)

        if (pool.name != null) {
            builder.validationQuery(pool.name)
        }
        if (pool.validationQuery != null) {
            builder.validationQuery(pool.validationQuery)
        }

        return ConnectionPool(builder.build())
    }

    @Bean
    fun reactiveTransactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    data class Pool(
            val maxIdleTime: Duration = Duration.ofMinutes(30),
            val initialSize: Int = 10,
            val maxSize: Int = 10,
            val maxCreateConnectionTime: Duration = Duration.ZERO,
            val acquireRetry: Int = 1,
            val maxAcquireTime: Duration = Duration.ZERO,
            val maxLifeTime: Duration = Duration.ZERO,
            val registerJmx: Boolean = false,
            val name: String? = null,
            val validationQuery: String? = null
    )


}
