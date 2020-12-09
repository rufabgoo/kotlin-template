package kotlintemplate.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class PropertyConfig {


    @Value("\${spring.r2dbc.url}")
    lateinit var dbUrl : String

    @Value("\${spring.r2dbc.username}")
    lateinit var dbUsername : String

    @Value("\${spring.r2dbc.password}")
    lateinit var dbPassword : String
}