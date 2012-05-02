package com.visionarysoftwaresolutions.restaurant.menu

import com.visionarysoftwaresolutions.restaurant.nutrition.*

class FakeFactory {

    public static MenuItem createMenuItem(){
        NutritionalInformation nut = new NutritionalInformation(
            calories : 400,
            caloriesFromFat : 200,
            saturatedFat : 3,
            transFat : 2,
            cholesterol : 4,
            sodium : 5,
            totalCarbohydrate : 5,
            dietaryFiber : 3,
            sugars : 0,
            protein : 30)
        Serving serve = new Serving( name : "cup",
                                     size : 1,
                                     nutrition : nut
                                   )
        Ingredient ingrid = new Ingredient( name :"chicken", 
                                            amount : 40,
                                            unit : "cup"
                                          )
        Recipe helper = new Recipe(
            name : "Chicken flambee",
            preparationTime : "1 hour",
            preparationSteps : ["dethaw chicken", "flambee chicken"],
            ingredients : [ingrid],
            servings : [serve]
        )
        return new MenuItem(
                            name : "Krazy Karla's Chicken",
                            price : new BigDecimal(5.00),
                            recipe : helper
                          )
    }
}

