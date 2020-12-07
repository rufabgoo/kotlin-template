package kotlintemplate.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class PropertyConfig {

    @Value("\${spring.datasource.hikari.minimumIdle}")
    var hikariMinimumIdle : Int = 5

    @Value("\${spring.datasource.hikari.maximumPoolSize}")
    var hikariMaximumPoolSize : Int = 20

    @Value("\${spring.datasource.hikari.idleTimeout}")
    var hikariIdleTimeout : Int = 30000

    @Value("\${spring.datasource.hikari.poolName}")
    lateinit var hikariPoolName : String

    @Value("\${spring.datasource.hikari.maxLifetime}")
    var hikariMaxLifetime : Int = 2000000

    @Value("\${spring.datasource.hikari.connectionTimeout}")
    var hikariConnectionTimeout : Int = 30000

    @Value("\${spring.r2dbc.url}")
    lateinit var dbUrl : String

    @Value("\${spring.r2dbc.username}")
    lateinit var dbUsername : String

    @Value("\${spring.r2dbc.password}")
    lateinit var dbPassword : String
}