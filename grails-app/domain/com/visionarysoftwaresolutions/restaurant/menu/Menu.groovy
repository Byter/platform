package com.visionarysoftwaresolutions.restaurant.menu
import com.visionarysoftwaresolutions.restaurant.Restaurant

class Menu {

    String name
    String description

    static belongsTo = [restaurant: Restaurant]
    static hasMany = [ subMenus: Menu, items:MenuItem ]
    static constraints = {
        name(blank:false)
        description(nullable:true)
        subMenus()
        items()
    }
}
