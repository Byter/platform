package com.visionarysoftwaresolutions.restaurant

import grails.test.mixin.*
import org.junit.*

import com.visionarysoftwaresolutions.restaurant.*
import com.visionarysoftwaresolutions.restaurant.menu.*
import com.visionarysoftwaresolutions.restaurant.nutrition.*
import com.visionarysoftwaresolutions.location.*

class CapriIntegrationTests extends GroovyTestCase {
    ContactInformation contact
    Location location
    StreetAddress address
    Restaurant restaurant
    
    protected void setUp() {
        super.setUp()
        contact = new ContactInformation(phone:"(480)882-0767",
             email:"capricafes@gmail.com").save(failOnError:true)
         address = new StreetAddress(address:"1726 E. Southern Ave",
            city:"Tempe",
            region:"AZ",
            postalCode:"85282",
            country:"USA").save(failOnError:true)
        location = new Location(latitude:33.393071,
            longitude:-111.910792,
            address:address,
            contact:contact
        ).save(failOnError:true)
        restaurant = new Restaurant(name:"Capri Cafe",
            schedule:"Sunday 	Closed Monday 	10:00 am – 7:00 pm Tuesday 	10:00 am – 7:00 pm Wednesday 	10:00 am – 7:00 pm Thursday 	10:00 am – 7:00 pm Friday 	10:00 am – 7:00 pm Saturday 	10:00 am – 3:00 pm",
            location:location
        ).save(failOnError:true)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSetupRestaurant() {
        assertTrue Restaurant.list().contains(restaurant)
    }

    private Menu createMenu(){
        Menu main = new Menu(name:"Main menu", restaurant:restaurant)
        Menu sandwich = new Menu(name:"Sandwich",
            description:"Our healthier choices start with Argentinean Miga's sandwiches, specials for the bread - natural, smooth, and thin - that enhances the taste of the fillings ",
            restaurant:restaurant)
        Menu special = new Menu(name:"Special", restaurant:restaurant)
        Menu empanadas = new Menu(name:"Empanadas",
            description:"Try a delicious and typical Argentianian food with fillings like:",
            restaurant:restaurant)
        Menu homemade = new Menu(name:"Homemade Special", restaurant:restaurant)
        Menu smoothies = new Menu(name:"Smoothies", restaurant:restaurant)
        Menu juices = new Menu(name:"Juices", restaurant:restaurant)
        Menu dessert = new Menu(name:"Sweet / Desserts", restaurant:restaurant)
        Menu coffee = new Menu(name:"Coffee", restaurant:restaurant)
        main.addToSubMenus(sandwich)
        main.addToSubMenus(special)
        main.addToSubMenus(empanadas)
        main.addToSubMenus(homemade)
        main.addToSubMenus(smoothies)
        main.addToSubMenus(juices)
        main.addToSubMenus(dessert)
        main.addToSubMenus(coffee)
        return main
    }
    
    void testBuildMenu(){
        Menu main = createMenu()
        main.save(failOnError:true)
        assert  main.subMenus.size() == 8
    }
    
    private Recipe createRecipe(){
        Recipe recipe = new Recipe(name:"Turkey with tomato, lettuce, eggs, and mayonnaise",
            preparationSteps:"combine all")
        Ingredient ingredient1 = new Ingredient(name:"turkey",
                                                amount:2.0,
                                                unit:"oz",
                                                recipe:recipe)
        Ingredient ingredient2 = new Ingredient(name:"tomato",
                                                amount:2,
                                                unit:"oz",
                                                recipe:recipe)
        Ingredient ingredient3 = new Ingredient(amount:2,
            unit:"oz",
            name:"lettuce",
            recipe:recipe
        )
        Ingredient ingredient4 = new Ingredient(amount:2,
            unit:"oz",
            name:"eggs",
            recipe:recipe
        )
        Ingredient ingredient5 = new Ingredient(amount:2,
            unit:"oz",
            name:"mayonnaise",
            recipe:recipe
        )
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
            protein : 30).save(flush:true)
        Serving helper2 = new Serving(name:"cup", size:1, nutrition:nut).save(flush:true)
        recipe.addToServings(helper2)
        recipe.addToIngredients(ingredient1)
        assertTrue ingredient1.validate()
        recipe.addToIngredients(ingredient2)
        assertTrue ingredient2.validate()
        recipe.addToIngredients(ingredient3)
        assertTrue ingredient3.validate()
        recipe.addToIngredients(ingredient4)
        assertTrue ingredient4.validate()
        recipe.addToIngredients(ingredient5)
        assertTrue ingredient5.validate()
        return recipe
    }
    void testBuildRecipes(){
        Recipe recipe = createRecipe()
        recipe.save(failOnError:true)
        assert Recipe.list().size() > 0
    }
    void testAddMenuItems(){
        def menu = createMenu() 
        menu.save(flush:true)
        def recipe = createRecipe()
        recipe.save(flush:true)
        Menu sandwich = Menu.findByName("Sandwich")
        assertNotNull sandwich
        MenuItem item = new MenuItem(
            name:"Turkey with tomato, lettuce, eggs, and mayonnaise",
            price:1.89,
            recipe:Recipe.findByName("Turkey with tomato, lettuce, eggs, and mayonnaise")
        ).save(failOnError:true)
        sandwich.addToItems(item)
        assert MenuItem.list().size() > 0
    }
}
