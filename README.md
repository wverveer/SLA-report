[![CI/CD](https://github.com/wverveer/SLA-report/actions/workflows/gradle.yml/badge.svg)](https://github.com/wverveer/SLA-report/actions/workflows/gradle.yml)
[![Coverage](.github/badges/jacoco.svg)](jacoco.svg)
[![GitHub Issues Open](https://github-basic-badges.herokuapp.com/issues/wverveer/SLA-report.svg)](https://github.com/wverveer/SLA-report/issues)

# Running 
Make sure the jar exist in "SLA-report/build/libs" from there start git bash. 
USe the following command (Windows): java -cp "./*" com.keylane.Main <inputFilePath> <outputFilePath>.
Example: java -cp "./*" com.keylane.Main C:/github/SLA-report/src/test/resources/input.xml output.html

# SLA-report
Application: SLA report
This application calculates, based on an input, the average duration per service of Axon. This is needed for our SLA report.

Input
The input contains the duration of several service calls of Axon. It should support CSV, JSON and XML files. The input files should always contain a list of the following three values:

The timestamp of the event
The service called
The duration of the event in milliseconds
Examples of each format are located in the src/test/resources directory. Note that all the examples have two services, but many more may be added in the future.

Output
This application should output the average of the durations per service. The output should either be a CSV or HTML file.

The CSV should look as follows:

Service;AverageDuration
PolicyService;123
FinancialService;123
The HTML should have more information, just like the reports from Gatling. It should state the number of calls per category: 0ms - 300ms, 300ms - 800ms, 800+. You can use the following template (not very pretty, can be improved):


<html lang="">
<head>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
    </script>
    <title>Template</title>
</head>
<body>

<h1>PolicyService</h1>
Average: 999ms.
<canvas id="myChart" style="width:100%;max-width:600px"></canvas>

<script>
var xValues = ["0-300ms", "300ms-800ms", "800ms +"];
var yValues = [1, 2, 3];
var barColors = ["red", "green","blue"];

new Chart("myChart", {
  type: "pie",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  }
});
</script>
</body>
</html>
Libraries
It is highly advised to use libraries for parsing the input files to Java objects. I suggest the following:

commons-csv for CSV files
jackson for JSON files
XML can be parsed using JAXB classes, which are part of the JDK.

Usage
The final result should be a single jar that can be run via the command line. The command looks like this:

java -cp SLA-report.jar com.keylane.Main <path-to-file>
To convince the business to use this application, you have to supply a code coverage report with a coverage of at least 95%.

Github project
I want you to create a Github project to work on this. This way you can:

Create tickets (called issues in Github)
Use a sort of kanban bord (called Projects in Github)
Use free CI tooling (Github actions, travis-ci, codecov, etc)
Review each other pull requests with automatic CI checks
Create documentation (wiki or Github pages)
We are going to work on this as if it is an actual application or library you publish to the outside world! Try to think about release notes, versioning, etc.

Note: make sure you have your CI configuration working before you start programming the entire application. This will help you a lot while programming. Also, if you configure something like code coverage beforehand, you can see which pull requests increase or decrease code coverage (you can use codecov.io or google how to do this with Github actions).

Note 2: it is ok if figuring this out takes a lot of time. You will learn a lot from this (configuring this the second time is considerably faster, trust me). This will also help you understand why Bamboo or TeamCity is so important and takes a lot of time to maintain.
