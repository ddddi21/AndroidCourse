package com.example.androidcourse

object CatRepository {
    var cats: List<Cat> = ArrayList<Cat>()

    fun getCatById(id:Int): Cat?{
        cats.forEach{
            if(it.id == id)
                return it
        }
        return null
    }

    fun getCat(): List<Cat>{
        cats = arrayListOf(           
            Cat(1,R.drawable.salam,"Два котика смотрят друг на друга", "Дружелюбный котик","он доволен", 7),
            Cat(4,R.drawable.grib,"Посмотрите что связала бабушка!", "Милый грибочек","кажется, ему комфортно", 1),
            Cat(2,R.drawable.catcookie,"У кого - то выходной, жаль, не у тебя", "Атдыхаем","just chill", 3),
            Cat(3,R.drawable.sadcatmath,"Похоже, кто - то делает математику", "Грустный котик","страдания", 2),
            Cat(5,R.drawable.cataftersee,"Эх, щас бы в море..", "Расстерянность","что - то явно не смущает", 4),
            Cat(9,R.drawable.afterbath,"С легким паром!", "Мокрый котик","just chill", 3),
            Cat(7,R.drawable.catlaptop,"Когда андроид студио опять залагало", "Программист, но не ты","опять?", 5),
            Cat(8,R.drawable.cloun,"Ну тут без комментариев..", "Каждый в душе","ha ha classic",4),
            Cat(6,R.drawable.niyaz,"Когда закончил прогать в 4 утра", "Типичный разраб","нервная система передает привет", 5)
            )
        return cats
    }

}