package com.minki.sqlbuilder

import com.minki.sqlbuilder.Order.SortMethod.*
import com.minki.sqlbuilder.SQLBuilder.query

fun main() {
    val query = query {
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
            order {  }
            "column6" by ascending
            "column7" by descending
        }
    }

    println(query)
}