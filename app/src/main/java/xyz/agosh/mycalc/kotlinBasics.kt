package xyz.agosh.mycalc


fun main() {


    /* Arrays */
    //3 ways of declaring and initializing array of integers in kotlin
        //val numbers:IntArray = intArrayOf(1,2,3,4,5,6)
        //val numbers = intArrayOf(1,2,3,4,5,6)
        val numbers = arrayOf(1,2,3,4,5,6)


    //print(numbers) //It will print the address of the array
    //print(numbers.contentToString()) //It will will print the array

    //Print the individual values of an array
    for(element in numbers){
        println("${element*22}")
    }





// MobilePhone("Android", "Samsung", "Galaxy S20 Ultra")
}






class MobilePhone(osName: String, brand: String, model: String){
    init{
        print("The phone has OS of $osName and the model is $brand $model")
    }
}