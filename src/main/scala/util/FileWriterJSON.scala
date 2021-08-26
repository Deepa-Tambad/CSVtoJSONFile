package util
import model.{Trainer,Trainers}
import java.io.PrintWriter
import scala.util.{Failure, Success, Try}

class FileWriterJSON {

  def logErrorFile(path: String, errorMessage: String): Unit = {
    Try(new PrintWriter(path)) match {
      case Success(value) => value.println(errorMessage)
        value.close()
      case Failure(exception) => println("Couldn't write the Error to the File")
    }
  }

  def convertCsvToTrainers(trainers: Trainers, output : String ): Unit = {
    val result = trainers.trainer match {
      case Nil =>
        println("No Data Found to write")
        "{}"
      case res @head => {
        trainers.toJson.prettyPrint
      }
    }
    Try(new PrintWriter(output)) match {
      case Success(value) => {
        value.println(result)
        value.close()
      }
      case Failure(exception) => {
        println("Couldn't write the Result to the File")
      }
    }
  }

}
