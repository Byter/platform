package com.visionarysoftwaresolutions.restaurant.menu

import grails.test.mixin.*
import org.junit.*

import com.visionarysoftwaresolutions.restaurant.nutrition.*

@TestFor(MenuItem)
class MenuItemTests {
    MenuItem toTest
    
    
    void testValidMenuItem() {
        toTest = FakeFactory.createMenuItem()
        mockForConstraintsTests(MenuItem, [toTest])
        assert toTest.validate()
    }

    void testInvalidMenuItemBlankName() {
        toTest = FakeFactory.createMenuItem()
        toTest.name = ""
        mockForConstraintsTests(MenuItem, [toTest])
        assert !toTest.validate()
        assert  "blank" == toTest.errors["name"]
    }

    void testInvalidMenuItemBlankRecipe() {
        toTest = new MenuItem(name:"Chicken flambee")
        mockForConstraintsTests(MenuItem, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["recipe"]
    }
    
    void testMenuItemCalculateNutrition(){
        toTest = FakeFactory.createMenuItem()
        NutritionalInformation result = toTest.calculateNutrition()
        assert 400 == result.calories
        assert 30  == result.protein
    }
}
