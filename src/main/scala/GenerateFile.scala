import config.ApplicationConfig
import model._
import util.{FileWriterJSON,FileReaderCSV}
import scala.util.{Failure, Success, Try}
object GenerateFile extends App {



  private def CsvFileToJsonFile = {
    val file: List[String] = Try(FileReaderCSV.readJsonFile(ApplicationConfig.config.getString("jsonpath"))) match {
      case Success(value) => value
      case Failure(exception) => List.empty
    }

    if (file.isEmpty) {
      FileWriterJSON.logErrorToFile(ApplicationConfig.config.getString("error_path"), "Couldn't read the CSV File")
    } else {
      FileWriterJSON.convertMembersToJsonString(FileWriterJSON.convertCsvToTrainers(file),
        ApplicationConfig.config.getString("csv_path"))
    }
  }
}

