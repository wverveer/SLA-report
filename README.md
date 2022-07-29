[![CI/CD](https://github.com/wverveer/SLA-report/actions/workflows/gradle.yml/badge.svg)](https://github.com/wverveer/SLA-report/actions/workflows/gradle.yml)
[![Coverage](.github/badges/jacoco.svg)](.github/badges/jacoco.svg)
[![CodeKata Ranking](https://github-basic-badges.herokuapp.com/issues/wverveer/SLA-report.svg)](https://github.com/wverveer/SLA-report/issues)

# SLA-report
Application: SLA report
This application calculates, based on an input, the average duration per service of Axon. This is needed for our SLA report.

# Usage 
Make sure the jar exist "SLA-report.jar". In the same directory as the jar start git bash. 
Use the following command (Windows): java -cp "./*" com.keylane.SLAReportGenerator <inputFilePath> <outputFilePath>.
Example: java -cp "./*" com.keylane.SLAReportGenerator C:/github/SLA-report/src/test/resources/input.xml output.html

# Workflow
1. The entry point of the application is the Main method in SLAReportGenerator.
2. The application then uses a factory class to retrieve the reader mapped to the input file extension.
3. The same is done for the converter. 
   * If the input or output extension is not mapped, a null pointer exception will be thrown.
4. The reader opens the input file and converts the records to a list of SLARecord objects.
5. This list then gets passed to create the output.
6. Finally, a FileWriter will create the file and write the output in it.
   * If the output file already exists, then the file gets overwritten.
   * If at any point the program fails, an exception will be thrown and the process will stop.

# Supported input extensions
* .csv
* .json
* .xml

# Supported output extensions
* .html
* .csv

# Adding support for different extension
Support for new file extension can be added, through the ConvertFromSLARecord and ConvertToSLARecord interfaces for input and output files respectively.
Once the convert method has been implemented, the converter then needs to be mapped to the correct file extension in the ConverterFactory.

# Maintenance 
Currently, the code is maintained in a public repository in GitHub(https://github.com/wverveer/SLA-report).

## Dependencies
| Library                    | Version | For                       |
|----------------------------|---------|---------------------------|
| com.googlecode.json-simple | 1.1.1   | Reading json input files. |
| org.apache.commons         | 1.9.0   | Reading csv input files.  |
| org.junit.jupiter          | 5.8.2   | testing                   |
| org.mockito                | 4.6.1   | testing                   |

## Jacoco
Jacoco is used to check the code coverage. The gradle task jacocoTestCoverageVerification is used to generate the report.
This report is stored in build/reports/jacoco/test/jacocoTestReport.csv.



