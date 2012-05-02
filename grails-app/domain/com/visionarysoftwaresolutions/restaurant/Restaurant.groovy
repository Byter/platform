package com.visionarysoftwaresolutions.restaurant
import com.visionarysoftwaresolutions.location.Location

class Restaurant {

    String name
    String schedule
    Location location
    
    static constraints = {
        name(blank:false, unique:true)
        schedule()
        location()
    }
}
