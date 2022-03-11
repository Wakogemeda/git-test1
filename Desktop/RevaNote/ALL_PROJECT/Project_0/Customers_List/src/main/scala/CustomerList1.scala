import java.util.Scanner
import scala.collection.mutable.ArrayBuffer
import java.util.Calendar
import java.sql.DriverManager
import java.sql.Connection
import java.io.PrintWriter
import java.io.File
object Bank {
  def main(arg: Array[String]): Unit = {
    val driver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/bankapp"
    val username = "root"
    val password = "Reva2021!@my"
    val log = new PrintWriter(new File("query.log"))
    var connection: Connection = null
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement()
    var scanner = new Scanner(System.in)
    var choice = 0
    while (choice != 5) {
      println("==============WELCOME TO OUR COMPANY!==================")
      println("========OUR COMPANY WORKING MONDAY TO FRIDAY===========")
      println("===========OUR WORKING TIME IS 8AM TO 5PM==============")
      
      println("Please Choose an option using number keys 1 - 5: ")
      println(
        "1: TO REGISTRATION\n2: TO REVOME REGISTRATION\n3: Print all CUSTOMER lists\n4: TO UPDATE INFORMATION\n5: EXIT"
      )
      try {
        choice = scanner.nextInt()

      } catch {
        case e: Exception => print("Exception thrown - Invalid input, please try again. ")
      }
      scanner.nextLine()
      if (choice == 1) {

        println("Please enter your information to register in the customer list:")
        println("Enter your full Name in single quote('') ")
        var fullName = scanner.nextLine()
        println("Enter your phone number")
        var phoneNumber = scanner.nextLong()
        println("Enter your check in time")
        var time = scanner.nextInt()
        //var str2 = ""
        //str2 += ("'"+fullName+"'")
        //statement.executeUpdate("create table customerList (id int NOT NULL AUTO_INCREMENT, fullName CHAR(255), phoneNumber Int, time Int, PRIMARY KEY (id));")
        statement.executeUpdate(
          "INSERT INTO customerList (fullName, phoneNumber, time) Values ("+ fullName +", " + phoneNumber + ", " + time + ")"
        )
        log.write(
          Calendar
            .getInstance()
            .getTimeInMillis + ": Executing - INSERT INTO customerList (fullName, phoneNumber, time) Values (" + fullName + ", " + phoneNumber + ", " + time + ")\n"
        )
        println("\n")
        println("\n---------------register successfully----------------")
        println("\n------------register transaction is end-------------")
        println("\n---------register transaction is end----------------")
        println("\n")
      } else if (choice == 2) {
        println("Please enter an id if you want remove from customer list:")
        var id = scanner.nextInt()
        statement.executeUpdate("DELETE FROM customerList WHERE id=(" + id + ")")
        log.write(
          Calendar
            .getInstance()
            .getTimeInMillis + "- Executing - DELETE FROM customerList WHERE id=(" + id + ")\n"
        )
        println("\n")
        println("\n---------------removed successfully----------------")
        println("\n------------removed transaction is end-------------")
        println("\n---------removed transaction is end----------------")
        println("\n")

      } else if (choice == 3) {
        val resultSet = statement.executeQuery("SELECT * FROM customerList")
        while (resultSet.next()) {
          print(resultSet.getString(1) + " " + resultSet.getString(2))
          println()
          log.write(
            Calendar
              .getInstance()
              .getTimeInMillis + ": Executing - SELECT * FROM customerList\n"
          )
          
        }
        println("\n")
          println("\n--------------Here your customer list--------")
          println("-------------transaction of is end-------------")
          println("----------transaction of is end----------------")
          println("\n")

      } else if (choice == 4) {
        println(
          "Please enter an id if you want to update your information:"
        )
        var id = scanner.nextInt()
        var str = ""
        var str1 = ""
        var str2 = ""
        scanner.nextLine()
        println("Please enter your correct name: ")
        var fullName = scanner.nextLine()
        println("Please enter your correct phone number:")
        var phoneNumber = scanner.nextLine()
        println("Please enter your correct checking in time:")
        var time = scanner.nextLine()
        str += ("'" + fullName + "'")
        str1 +=("'"+ phoneNumber +"'")
        str2 +=("'"+ time +"'")
        statement.executeUpdate(
          "update customerList set fullName = (" + str + ") where id =(" + id + ")"
        )
        statement.executeUpdate(
          "update customerList set phoneNumber = (" + str1 + ") where id =(" + id + ")"
        )
        statement.executeUpdate(
            "update customerList set time = (" + str2 + ") where id = (" + id + ")"
        )

        
        log.write(
          Calendar
            .getInstance()
            .getTimeInMillis + ": Executing - update customerList set fullName = (" + str + ") where id =(" + id + ")\n"
        )
        log.write(
          Calendar
            .getInstance()
            .getTimeInMillis + ": Executing - update customerList set phoneNumber = (" + str1 + ") where id =(" + id + ")\n"
        )
        log.write(
          Calendar
            .getInstance()
            .getTimeInMillis + ": Executing - update customerList set time = (" + str2 + ") where id =(" + id + ")\n"
        )
        println("\n")
        println("----------Your information is updated-----------")
        println("-------Your information is updated--------------")
        println("----Your information is updated-----------------")
        println("\n")

      } else if (choice == 5) {
        println("Your Regitrastion has been saved.")
        println("Thank you!")
        connection.close()
        log.close()
        println("---------HAVE A NICE DAY---------")

      } else {
        println("Please only enter numbers 1 - 5")

      }

    }

  }
}
