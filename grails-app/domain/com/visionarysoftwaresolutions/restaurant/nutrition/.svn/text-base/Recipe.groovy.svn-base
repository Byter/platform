package com.visionarysoftwaresolutions.restaurant.nutrition

class Recipe {

    String name
    String preparationTime
    List preparationSteps
    static hasMany = [ingredients : Ingredient, servings : Serving]
    static constraints = {
        name(unique:true)
        preparationTime(nullable:true)
        ingredients(nullable:false)
        servings(nullable:false)
        preparationSteps(nullable:false)
    }
    
    public NutritionalInformation calculateNutrition(){
        return servings.sum { 
            it.nutrition 
        }
    }
}
