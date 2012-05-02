package com.visionarysoftwaresolutions.restaurant.nutrition

import grails.test.mixin.*
import org.junit.*

@TestFor(NutritionalInformation)
class NutritionalInformationTests  {
    NutritionalInformation toTest
    
    void testInvalidNegativeCalories(){
        toTest= new NutritionalInformation(calories:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['calories']
    }

    void testInvalidNegativeCaloriesFromFat(){
        toTest= new NutritionalInformation(caloriesFromFat:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['caloriesFromFat']
    }

    void testInvalidTooBigCaloriesFromFat(){
        toTest= new NutritionalInformation(calories:100, caloriesFromFat:120)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "validator" == toTest.errors['caloriesFromFat']
    }

    void testInvalidNegativeSaturatedFat(){
        toTest= new NutritionalInformation(saturatedFat:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['saturatedFat']
    }

    void testInvalidNegativeTransFat(){
        toTest= new NutritionalInformation(transFat:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['transFat']
    }

    void testInvalidNegativeCholesterol(){
        toTest= new NutritionalInformation(cholesterol:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['cholesterol']
    }

    void testInvalidNegativeSodium(){
        toTest= new NutritionalInformation(sodium:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['sodium']
    }

    void testInvalidNegativeTotalCarbohydrate(){
        toTest= new NutritionalInformation(totalCarbohydrate:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['totalCarbohydrate']
    }

    void testInvalidNegativeDietaryFiber(){
        toTest= new NutritionalInformation(dietaryFiber:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['dietaryFiber']
    }

    void testInvalidTooBigDietaryFiber(){
        toTest= new NutritionalInformation(totalCarbohydrate:100, dietaryFiber:120)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "validator" == toTest.errors['dietaryFiber']
    }

    void testInvalidNegativeSugars(){
        toTest= new NutritionalInformation(sugars:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['sugars']
    }

    void testInvalidTooBigSugars(){
        toTest= new NutritionalInformation(totalCarbohydrate:100, sugars:120)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "validator" == toTest.errors['sugars']
    }

    void testInvalidNegativeProtein(){
        toTest= new NutritionalInformation(protein:-1)
        mockForConstraintsTests(NutritionalInformation, [toTest])
        assert !toTest.validate()
        assert "min" == toTest.errors['protein']
    }

    void testCalculateTotalFat(){
        toTest= new NutritionalInformation(saturatedFat:1, transFat:1)
        assert 2 == toTest.calculateTotalFat()
    }
    
    void testCombinedNutritionalInformation(){
        toTest= new NutritionalInformation(saturatedFat:1, transFat:1)
        def another = new NutritionalInformation(protein: 5, saturatedFat:4, transFat:3)
        def result = toTest + another
        assert 5 == result.protein
        assert 5 == result.saturatedFat
        assert 4 == result.transFat
    }
}
