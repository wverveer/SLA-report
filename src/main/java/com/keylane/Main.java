/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.keylane;

public class Main {
    public static void main(String[] args) throws Exception {
        SLAReportGenerator generator = new SLAReportGenerator();
        generator.generate(args[0],args[1]);
    }
}
