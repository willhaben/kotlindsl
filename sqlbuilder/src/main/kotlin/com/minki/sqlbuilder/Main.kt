package com.minki.sqlbuilder

import com.minki.sqlbuilder.Order.SortVariant.*

fun main() {
    val query = SQLBuilder.query {
        select {
            +"column1"
            +"column2"
        }
        from("table1")
        where {
            or {
                "column3" eq 3
                "column4" eq null
            }
            "column5" eq 42
        }
        order {
            "column6" by ascending
            "column7" by descending
        }
    }

    println(query)
}