package kotlintemplate.web

import kotlintemplate.db.postgres.enity.Example
import kotlintemplate.service.api.ExampleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/example")
class ExampleController(private val exampleService: ExampleService) {

    @GetMapping("/all")
    fun all(): Flux<Example> {
        return exampleService.findAll()
    }
}