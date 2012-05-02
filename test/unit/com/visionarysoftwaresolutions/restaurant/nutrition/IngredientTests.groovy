package com.visionarysoftwaresolutions.restaurant.nutrition

import grails.test.mixin.*
import org.junit.*

@TestFor(Ingredient)
class IngredientTests {
    Ingredient toTest

    void testValidIngredient() {
        Recipe recipe = new Recipe(name:"Lettuce Wraps", preparationSteps:["make them"])
        toTest= new Ingredient(name:'Lettuce', amount:5, unit:"cup", recipe:recipe)
        mockForConstraintsTests(Ingredient, [toTest])
        assert toTest.validate()
    }

    void testInvalidBlankNameIngredient(){
        toTest= new Ingredient(name:'', amount:5, unit:"cup")
        mockForConstraintsTests(Ingredient, [toTest])
        assert !toTest.validate()
        assert "blank" == toTest.errors['name']
    }
    
    void testInvalidNegativeAmountIngredient() {
        toTest = new Ingredient(name:"green eggs", amount:-5, unit:"cup")
        mockForConstraintsTests(Ingredient, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['amount']
    }

    void testInvalidUnitIngredient() {
        toTest = new Ingredient(name:"green eggs", amount:-5, unit:"shit")
        mockForConstraintsTests(Ingredient, [toTest])
        assert !toTest.validate()
        assert "inList" == toTest.errors['unit']
    }
}
