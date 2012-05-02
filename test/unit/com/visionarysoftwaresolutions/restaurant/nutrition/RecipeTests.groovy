package com.visionarysoftwaresolutions.restaurant.nutrition

import grails.test.mixin.*
import org.junit.*

@TestFor(Recipe)
class RecipeTests {
    Recipe toTest
    Ingredient helper = new Ingredient(name:"Chicken")
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
    Serving helper2 = new Serving(name:"cup", size:1, nutrition:nut)

    void testInvalidRecipeNullIngredients() {
        List preparation = ["Dethaw chicken", "Cook chicken", "serve chicken"]
        toTest= new Recipe(preparationSteps:preparation, servings:[helper2])
        mockForConstraintsTests(Recipe, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["ingredients"]
    }
    
    void testInvalidRecipeNullServings() {
        List preparation = ["Dethaw chicken", "Cook chicken", "serve chicken"]
        toTest= new Recipe(preparationSteps:preparation, ingredients:[helper])
        mockForConstraintsTests(Recipe, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["servings"]
    }

    void testInvalidRecipeNullPreparation() {
        toTest= new Recipe(ingredients:[helper], servings:[helper2])
        mockForConstraintsTests(Recipe, [toTest])
        assert !toTest.validate()
        assert "nullable" == toTest.errors["preparationSteps"]
    }

    void testValidRecipe() {
        List preparation = ["Dethaw chicken", "Cook chicken", "serve chicken"]
        toTest= new Recipe(name:"test",ingredients:[helper], servings:[helper2], preparationSteps:preparation)
        mockForConstraintsTests(Recipe, [toTest])
        assert toTest.validate()
    }
    
    void testCalculateNutritionForOneServing(){
        List preparation = ["Dethaw chicken", "Cook chicken", "serve chicken"]
        toTest= new Recipe(name:"test",ingredients:[helper], servings:[helper2], preparationSteps:preparation)
        def result = toTest.calculateNutrition()
        assert 400 == result.calories
        assert 30  == result.protein
    }
    
    void testCalculateNutritionForMultipleServings(){
        List preparation = ["Dethaw chicken", "Cook chicken", "serve chicken"]
        def nut2 = new NutritionalInformation(
            calories : 300,
            caloriesFromFat : 200,
            saturatedFat : 3,
            transFat : 2,
            cholesterol : 4,
            sodium : 5,
            totalCarbohydrate : 5,
            dietaryFiber : 3,
            sugars : 0,
            protein : 10)
        Serving another = new Serving(name:"oz", size:5, nutrition:nut2)
        toTest= new Recipe(name:"test",ingredients:[helper], servings:[helper2,another], preparationSteps:preparation)
        def result = toTest.calculateNutrition()
        assert 700 == result.calories
        assert 40  == result.protein
    }
}
