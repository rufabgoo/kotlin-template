package kotlintemplate.config

import com.zaxxer.hikari.HikariDataSource
import kotlintemplate.support.HikariRetryableDataSource
import kotlintemplate.support.RetryableDataSource
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import javax.sql.DataSource

@Configuration
class AppConfig {

    @Bean
    fun dataSourceWrapper(): BeanPostProcessor = RetryableDataSourceBeanPostProcessor()

    @Order(Ordered.HIGHEST_PRECEDENCE)
    private class RetryableDataSourceBeanPostProcessor : BeanPostProcessor {
        @Throws(BeansException::class)
        override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
            return when (bean) {
                is HikariDataSource -> {
                    HikariRetryableDataSource(bean as DataSource)
                }
                is DataSource -> {
                    RetryableDataSource(bean)
                }
                else -> {
                    bean
                }
            }
        }

        @Throws(BeansException::class)
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
            return bean
        }
    }
}