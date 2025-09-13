package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.step_definitions",
    plugin = {"pretty", "html:reports/cucumber-html-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
