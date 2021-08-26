import config.ApplicationConfig
import model._
import util.{FileWriterJSON,FileReaderCSV}
import scala.util.{Failure, Success, Try}
object GenerateFile extends App {

  private def functionCsvToJson = {
    val fileContent: List[String] = Try(FileReader.readJsonFile(ApplicationConfig.config.getString("jsonpath"))) match {
      case Success(value) => value
      case Failure(exception) => List.empty
    }

    if (fileContent.isEmpty) {
      ResultFileWriter
        .logErrorToFile(ApplicationConfig.config.getString("error_path"),
          "Couldn't read the Json File")
    } else {
      FileWriterJSON.convertMembersToJsonString(FileWriterJSON.convertCsvToMembers(fileContent),
        ApplicationConfig.config.getString("csv_path"))
    }
  }


  private def functionCsvToJson = {
    val file: List[String] = Try(FileReaderCSV.readJsonFile(ApplicationConfig.config.getString("jsonpath"))) match {
      case Success(value) => value
      case Failure(exception) => List.empty
    }

    if (file.isEmpty) {
      FileWriterJSON.logErrorToFile(ApplicationConfig.config.getString("error_path"), "Couldn't read the CSV File")
    } else {
      FileWriterJSON.convertMembersToJsonString(FileFileWriterJSON.convertCsvToTrainers(file),
        ApplicationConfig.config.getString("csv_path"))
    }
  }

}

