package com.visionarysoftwaresolutions.restaurant.menu
import com.visionarysoftwaresolutions.restaurant.nutrition.*

class MenuItem {

    String name
    BigDecimal price
    Recipe recipe
    byte[] image
    byte[] barCode

    static constraints = {
        name(blank:false)
        price(min:new BigDecimal(0.0))
        recipe()
        image(nullable:true)
        barCode(nullable:true)
    }
    
    public NutritionalInformation calculateNutrition(){
        return recipe.calculateNutrition()
    }
}
