package kotlintemplate.support

class BusinessLogicException(message: String?, e: Exception?) : RuntimeException(message, e) {

    constructor(message: String) : this(message, null)

    constructor() : this(null, null)

}