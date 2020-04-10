package com.czh.study.traitdemo


/**
  * trait 不能传参 ，类似与java的接口和抽象类的合并
  */
trait Read{
  def read(name:String): Unit ={
    println(s"name is $name read...")
  }
}

trait Listern{
  def listern(name:String): Unit ={
    println(s"name is $name listern...")
  }
}


class Person(xname:String,xage:Int) extends Read with Listern {
  val name = xname
  val age= xage
}



object Trait1 {

  def main(args: Array[String]): Unit = {
    val person = new Person("aaa",10)
    person.read("zhangsan")
    person.listern("lisi")
  }

}
