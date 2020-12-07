package kotlintemplate.db.postgres.enity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("example")
class Example(
        @Id
        @Column("id")
        val id: Long,

        @Column("name")
        val name: String
)