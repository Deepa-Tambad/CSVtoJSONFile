package util
import model.{Trainer,Trainers}
import java.io.PrintWriter
import scala.util.{Failure, Success, Try}

class FileWriterJSON {

  def logErrorFile(path: String, errorMessage: String): Unit = {
    Try(new PrintWriter(path)) match {
      case Success(value) => value.println(errorMessage)
        value.close()
      case Failure(exception) => println("Would not write the Error to File")
    }
  }

  def convertCsvToTrainers(trainers: Trainers, output : String ): Unit = {
    val solution = trainers.trainer match {
      case Nil =>
        println(" D ")
        "{}"
      case res @head => {
        trainers.toJson.prettyPrint
      }
    }
    Try(new PrintWriter(output)) match {
      case Success(value) => {
        value.println(solution)
        value.close()
      }
      case Failure(exception) => {
        println("Would not write the Result to File")
      }
    }
  }
}
