package kotlintemplate.db.postgres.enity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("example.example_test")
data class Example(

        @Id
        @Column("id")
        var id: Long,

        @Column("name")
        var name: String
)