package com.czh.study.implicitdemo

/**
  * 隐式转换-参数 当不传递参数的时候，需要隐式参数的方法会自动在作用域内寻找定义的隐式参数，无须传递
  */

object ImplicitDemo1 {

  def fun1(implicit name:String): Unit ={
    println(s"$name")
  }


  implicit val name = "zhangsan"

  def main(args: Array[String]): Unit = {
    fun1
  }

}
