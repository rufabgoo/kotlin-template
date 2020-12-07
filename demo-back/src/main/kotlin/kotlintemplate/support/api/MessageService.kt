package kotlintemplate.support.api

interface MessageService {
    fun getMessage(code: String): String

    fun getMessage(code: String, vararg args: String): String
}