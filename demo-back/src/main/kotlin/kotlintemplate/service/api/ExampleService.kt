package kotlintemplate.service.api

import kotlintemplate.db.postgres.enity.Example
import kotlintemplate.model.view.ExampleView
import reactor.core.publisher.Flux


interface ExampleService {

    fun findAll() : Flux<Example>
}