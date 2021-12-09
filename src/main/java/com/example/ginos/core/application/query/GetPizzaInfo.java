//package com.example.ginos.core.application.query;
//
//import com.example.ginos.core.domain.Ingredient;
//import com.example.ginos.core.domain.Pizza;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class GetPizzaInfo {
//
//    private String pizzaName;
//    private final List<Ingredient> allIngredients = new ArrayList<>();
//    private Map<String, Pizza> allPizzas;
//
//    public GetPizzaInfo(String name){
//        this.pizzaName = name;
//    }
//
//
//    public Map<String, Pizza> getAllPizzas() {
//        initializeLists();
//        return allPizzas;
//    }
//
//    public List<Ingredient> getAllIngredients() {
//        initializeLists();
//        return allIngredients;
//    }
//
//    public Pizza getPizzaAllDetails(String name) throws Exception {
//        if (allPizzas.containsKey(name)){
//            return  allPizzas.get(name);
//        }
//        else throw new Exception("This pizza is not on our menu!");
//    }
//
//    public  List<Ingredient> getPizzaIngredients (String pizzaName) throws Exception {
//        initializeLists();
//        if (getAllPizzas().containsKey(pizzaName)){
//            return  getAllPizzas().get(pizzaName).getIngredients();
//        }
//        else throw new Exception("This pizza is not on our menu!");
//    }
//
//    private void initializeLists(){
//        allIngredients.add(new Ingredient("tomatensaus"));
//        allIngredients.add(new Ingredient("kaas"));
//        allIngredients.add(new Ingredient("salami"));
//        allIngredients.add(new Ingredient("ham"));
//        allIngredients.add(new Ingredient("champignons"));
//        allIngredients.add(new Ingredient("kebab"));
//
//        List<Ingredient> pepperoniPizzaIngredients  = new ArrayList<>();
//        pepperoniPizzaIngredients.add(new Ingredient("tomatensaus"));
//        pepperoniPizzaIngredients.add(new Ingredient("kaas"));
//        pepperoniPizzaIngredients.add(new Ingredient("salami"));
//
//        List<Ingredient> kebabPizzaIngredients  = new ArrayList<>();
//        kebabPizzaIngredients.add(new Ingredient("tomatensaus"));
//        kebabPizzaIngredients.add(new Ingredient("kaas"));
//        kebabPizzaIngredients.add(new Ingredient("kebab"));
//
//        List<Ingredient> ginosPizzaIngredients  = new ArrayList<>();
//        ginosPizzaIngredients.add(new Ingredient("tomatensaus"));
//        ginosPizzaIngredients.add(new Ingredient("kaas"));
//        ginosPizzaIngredients.add(new Ingredient("ham"));
//        ginosPizzaIngredients.add(new Ingredient("champignons"));
//
//        allPizzas.put("pepperoni", new Pizza("pepperoni", pepperoniPizzaIngredients,  8.99, 100) );
//        allPizzas.put("kebab", new Pizza("kebab", kebabPizzaIngredients,  8.99, 100) );
//        allPizzas.put("ginos", new Pizza("ginos", ginosPizzaIngredients,  8.99, 150 ));
//
//    }
//}
