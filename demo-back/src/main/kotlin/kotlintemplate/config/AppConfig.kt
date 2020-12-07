package kotlintemplate.config

import com.zaxxer.hikari.HikariDataSource
import kotlintemplate.support.HikariRetryableDataSource
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
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
            return HikariRetryableDataSource(bean as DataSource)
        }

        @Throws(BeansException::class)
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
            return bean
        }
    }

    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}