package com.visionarysoftwaresolutions.restaurant.nutrition

class Serving {
    String name
    double size
    NutritionalInformation nutrition
    
    static constraints = {
        name(blank:false)
        size(min:0.0d)
        nutrition(nullable:true)
    }
}
