package kotlintemplate.support

import org.springframework.jdbc.datasource.AbstractDataSource
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource

open class RetryableDataSource(private val delegate: DataSource) : AbstractDataSource() {

    @Retryable(maxAttempts = 50, backoff = Backoff(multiplier = 2.3, maxDelay = 30000))
    @Throws(SQLException::class)
    override fun getConnection(): Connection? {
        return delegate.connection
    }

    @Retryable(maxAttempts = 50, backoff = Backoff(multiplier = 2.3, maxDelay = 30000))
    @Throws(SQLException::class)
    override fun getConnection(username: String?, password: String?): Connection? {
        return delegate.getConnection(username, password)
    }
}