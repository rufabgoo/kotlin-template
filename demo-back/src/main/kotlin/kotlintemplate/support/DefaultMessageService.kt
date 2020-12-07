package kotlintemplate.support

import kotlintemplate.support.api.MessageService
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class DefaultMessageService(private val messageSource: ResourceBundleMessageSource) : MessageService {

    private val russian = Locale("ru", "RU")

    override fun getMessage(code: String) = messageSource.getMessage(code, null, russian)

    override fun getMessage(code: String, vararg args: String) = messageSource.getMessage(code, args, russian)

}