package com.visionarysoftwaresolutions.restaurant.nutrition

class NutritionalInformation  {

    double calories
    double caloriesFromFat
    double saturatedFat
    double transFat
    double cholesterol
    double sodium
    double totalCarbohydrate
    double dietaryFiber
    double sugars
    double protein
    Map vitamins = [:]
    
    static constraints = {
        calories(min:0.0d)
        caloriesFromFat(min:0.0d, validator: { cal, nutrit -> cal <= nutrit.calories })
        saturatedFat(min:0.0d)
        transFat(min:0.0d)
        cholesterol(min:0.0d)
        sodium(min:0.0d)
        totalCarbohydrate(min:0.0d)
        dietaryFiber(min:0.0d, validator: { fiber, nutrit -> fiber <= nutrit.totalCarbohydrate })
        sugars(min:0.0d, validator: { sug, nutrit -> sug <= nutrit.totalCarbohydrate })
        protein(min:0.0d)
        vitamins()
    }

    public Double calculateTotalFat(){ transFat + saturatedFat }
    
    NutritionalInformation plus(NutritionalInformation other){
        return new NutritionalInformation(
            calories: this.calories + other.calories,
            caloriesFromFat:  this.caloriesFromFat + other.caloriesFromFat,
            saturatedFat: this.saturatedFat + other.saturatedFat,
            transFat: this.transFat + other.transFat,
            cholesterol: this.cholesterol + other.cholesterol,
            sodium: this.sodium + other.sodium,
            totalCarbohydrate: this.totalCarbohydrate + other.totalCarbohydrate,
            dietaryFiber: this.dietaryFiber + other.dietaryFiber,
            sugars : this.sugars + other.sugars,
            protein: this.protein + other.protein,
            vitamins: this.vitamins + other.vitamins
        )
    }
}
