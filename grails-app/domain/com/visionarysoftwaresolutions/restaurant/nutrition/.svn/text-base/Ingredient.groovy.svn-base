package com.visionarysoftwaresolutions.restaurant.nutrition

class Ingredient {

    String name
    Double amount
    String unit
    
    static belongsTo = [recipe: Recipe]
    
    static constraints = {
        name(blank:false)
        amount(min:0.0d)
        unit(inList:["whole","g", "oz", "cup", "teaspoon", "tablespoon", "lb"])
    }
}
