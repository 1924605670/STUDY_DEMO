package com.czh.study.implicitdemo

/**
  * 隐式转换-函数
  */

class A(name:String){
  def fun1(name:String): Unit ={
    println(s"$name ...." )
  }

}

class B(xname:String){
  val name = xname
}

object ImplicitDemo2 {

  // 同一种隐式转换函数方法类型只能有一个，与方法名无关，于输入类型和输出类型相关

  // 定义隐式转换函数
  implicit def BToA(b:B): A ={
    new A(b.name)
  }

  def main(args: Array[String]): Unit = {
    val b = new B("zhangsan")
    b.fun1(b.name)

  }

}
