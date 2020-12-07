package kotlintemplate.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableR2dbcRepositories(basePackages = ["ru.rtech.nspk.matching.back.db.mysql.repository"])
@EnableTransactionManagement
class PostgresConfig(private val propertyConfig: PropertyConfig) {


}