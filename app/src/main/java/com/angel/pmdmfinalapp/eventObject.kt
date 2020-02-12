package com.angel.pmdmfinalapp
import java.util.*

class eventObject(name: String, allDay: Boolean, since: Calendar?, to: Calendar?, color: Int, description: String?){

    var name: String
        get() = name
        set(value) { name = value }

    var allDay: Boolean = false
        get() = field
        set(value) { allDay = value }

    var since: Calendar?
        get() = since
        set(value) {since = value}

    var to: Calendar?
        get() = to
        set(value) {to = value}

    var color: Int
        get() = color
        set(value) { color = value }

    var description: String?
        get() = description
        set(value) {description = value}


    //Constructor
    init {
        this.name = name
        this.allDay = allDay
        this.since = since
        this.to = to
        this.color = color
        this.description = description
    }

    override fun toString(): String {
        return "{" +
                    "name: " + name +", /n" +
                    "allDay: " + allDay +", /n" +
                    "since: " + since +", /n" +
                    "to: " + to +", /n" +
                    "color: " + color +", /n" +
                    "description: " + description +", /n" +
                "}"
    }


}