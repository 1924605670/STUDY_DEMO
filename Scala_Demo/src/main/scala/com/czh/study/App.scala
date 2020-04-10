package com.czh.study

import java.util.Date

object App {

  def main(args: Array[String]): Unit = {
    //    print(111)
    //    fun("q","h","hello")


    // 偏应用函数
    //    val date = new Date()
    //    def fun1 = printLog(date, _: String)
    //
    //    fun1("aaaaa")
    //    Thread.sleep(2000)
    //    fun1("bbbb")
    //    Thread.sleep(2000)
    //    fun1("cccc")
    // 高阶函数1
    fun2(fun3, "aaaa")
    //高阶函数2
    val str = fun4("ssssss")("bbbb", 5)
    println(str)
  }

  /**
    * 可变长参数
    *
    * @param a
    */
  def fun(a: String*): Unit = {
    //    a.foreach(x => println(x))
    //    a.foreach(println(_))
    a.foreach(println)
  }

  /**
    * 偏应用函数
    */
  def printLog(date: Date, log: String): Unit = {
    println(s"$date    $log")
  }

  /**
    * 高阶函数-1 参数是函数
    */

  def fun2(f: (Int, Int) => Int, s: String): Unit = {
    val i = f(1, 2)
    println(s + i)
  }


  def fun3(a: Int, b: Int): Int = {
    a + b
  }


  /**
    * 高阶函数-2 返回参数是函数
    */
  def fun4(s: String): (String, Int) => String = {

    def fun5(a: String, b: Int): String = {
      a + "#" + b + "%" + s
    }

    fun5

  }


}
