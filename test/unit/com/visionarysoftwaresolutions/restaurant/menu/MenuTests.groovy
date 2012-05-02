package com.visionarysoftwaresolutions.restaurant.menu

import grails.test.mixin.*
import org.junit.*

import com.visionarysoftwaresolutions.restaurant.Restaurant

@TestFor(Menu)
class MenuTests {
    Menu toTest
    Restaurant restaurant = new Restaurant(name:"Nick's")
    
    void testValidMenu() {
        toTest = new Menu(name:"Breakfast", restaurant:restaurant)
        mockForConstraintsTests(Menu, [toTest])
        assert toTest.validate()
    }

    void testInvalidMenuNoRestaurant() {
        toTest = new Menu(name:"Breakfast")
        mockForConstraintsTests(Menu, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["restaurant"]
    }

    void testValidMenus() {
        toTest = new Menu(name:"Breakfast", restaurant:restaurant)
        Restaurant tst2 = new Restaurant(name:'Mookie')
        Menu tester = new Menu(name:"Breakfast", restaurant:tst2)
        mockForConstraintsTests(Menu, [toTest,tester])
        assert toTest.validate()
        assert tester.validate()
    }

    void testChildMenus() {
        Menu tester = new Menu(name:"Omlettes", restaurant:restaurant)
        toTest = new Menu(name:"Breakfast",
            subMenus:[tester],
            restaurant:restaurant)
        mockForConstraintsTests(Menu, [toTest,tester])
        assert toTest.validate()
        assert tester.validate()
    }
}
