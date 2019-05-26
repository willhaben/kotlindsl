package com.minki.sqlbuilder

/**
 * this example is taken from https://www.baeldung.com/kotlin-dsl with own modifications
 * this only generates a sql query string literal, it should only demonstrate the Kotlin dsl and should not be considered
 * as serious production usage
 */
@DslMarker
annotation class SqlMarker

@SqlMarker
abstract class Condition {

    fun and(init: Condition.() -> Unit) {
        addCondition(And().apply(init))
    }

    fun or(init: Condition.() -> Unit) {
        addCondition(Or().apply(init))
    }

    infix fun String.eq(value: Any?) {
        addCondition(Eq(this, value))
    }

    protected abstract fun addCondition(condition: Condition)
}

private open class CompositeCondition(private val sqlOperator: String) : Condition() {
    private val conditions = mutableListOf<Condition>()

    override fun addCondition(condition: Condition) {
        conditions += condition
    }

    override fun toString(): String {
        return if (conditions.size == 1) {
            conditions.first().toString()
        } else {
            conditions.joinToString(prefix = "(", postfix = ")", separator = " $sqlOperator ") {
                "$it"
            }
        }
    }
}

private class And : CompositeCondition("and")

private class Or : CompositeCondition("or")

private class Eq(private val column: String, private val value: Any?) : Condition() {

    init {
        if (value != null && value !is Number && value !is String) {
            throw IllegalArgumentException("Only <null>, numbers and strings values can be used in the 'where' clause")
        }
    }

    override fun addCondition(condition: Condition) {
        throw IllegalStateException("Can't add a nested condition to the sql 'eq'")
    }

    override fun toString(): String {
        return when (value) {
            null -> "$column is null"
            is String -> "$column = '$value'"
            else -> "$column = $value"
        }
    }
}

@SqlMarker
class Order {

    private class OrderPair(val column : String, val sortVariant : SortVariant) {
        override fun toString() = "$column $sortVariant"
    }

    fun clear() = orders.clear()

    private val orders = ArrayList<OrderPair>()

    sealed class SortVariant {
        object ascending : SortVariant(){
            override fun toString(): String {
                return "ascending"
            }
        }

        object descending : SortVariant(){
            override fun toString(): String {
                return "descending"
            }
        }
    }

    infix fun String.by(sortVariant : SortVariant) {
        orders.add(OrderPair(this, sortVariant))
    }

    override fun toString(): String {
        return if(orders.isEmpty()) {
            ""
        }else {
            " order by ${orders.joinToString(", ")}"
        }
    }
}

@SqlMarker
class Select {

    private val columns = mutableListOf<String>()

    fun clear() = columns.clear()

    operator fun String.unaryPlus() {
        columns.add(this)
    }

    override fun toString(): String {
        return if (columns.isEmpty()) {
            "*"
        } else {
            columns.joinToString(", ")
        }
    }
}

@SqlMarker
class SqlSelectBuilder {

    private val select = Select()
    private lateinit var table: String
    private var condition: Condition? = null
    private var order : Order = Order()

    fun select(init : Select.() -> Unit) {
        select.clear()
        select.apply(init)
    }

    fun from(table: String) {
        this.table = table
    }

    fun where(init: Condition.() -> Unit) {
        condition = And().apply(init)
    }

    fun order(init : Order.() -> Unit) {
        order.clear()
        order.init()
    }

    override fun toString(): String {
        val conditionString =
            if (condition == null) {
                ""
            } else {
                " where $condition"
            }

        return "select $select from $table$conditionString$order"
    }
}

@SqlMarker
object SQLBuilder {
    fun query(init: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
        val builder = SqlSelectBuilder()
        builder.init()
        init(builder)
        return builder
    }
}