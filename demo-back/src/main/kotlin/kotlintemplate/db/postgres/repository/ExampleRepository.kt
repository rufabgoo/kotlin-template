package kotlintemplate.db.postgres.repository

import kotlintemplate.db.postgres.enity.Example
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ExampleRepository : ReactiveCrudRepository<Example, Long> {
}