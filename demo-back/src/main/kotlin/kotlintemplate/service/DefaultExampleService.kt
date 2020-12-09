package kotlintemplate.service

import kotlintemplate.db.postgres.enity.Example
import kotlintemplate.db.postgres.repository.ExampleRepository
import kotlintemplate.model.view.ExampleView
import kotlintemplate.service.api.ExampleService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class DefaultExampleService(private val exampleRepository: ExampleRepository) : ExampleService {

    override fun findAll(): Flux<Example> {
        return exampleRepository.findAll();
    }
}