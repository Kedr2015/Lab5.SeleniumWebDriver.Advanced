package com.epam.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.kohsuke.args4j.Option;

public class Settings {
    @Option(name = "--testng", usage = "seth path to TestNG xml", required = true)
    public String testNgPath;

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
