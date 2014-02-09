package com.mm.jcli;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BasicCommandLineInterface {

	public Long basicCommand(String param) {
		Long result = System.currentTimeMillis();

		System.out.println("Basic command: " + param);

		return result;
	}

	@SuppressWarnings("static-access")
	public static void main(String... args) {
		int result = 0;

		Options options = new Options();
		Option param = OptionBuilder.hasArg().withArgName("param")
				.withDescription("Parameter param").create("param");

		Option help = new Option("help", "print this help instructions");

		options.addOption(param);
		options.addOption(help);

		CommandLineParser parser = new BasicParser();
		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("text-help", options);
			} else {
				String parameter = line.getOptionValue("param");

				if (parameter != null) {
					System.out.println(new BasicCommandLineInterface()
							.basicCommand(parameter));
				} else {
					System.out.println("Parameter not declared");
					HelpFormatter formatter = new HelpFormatter();
					formatter.printHelp("jcli", options);
					result = 1;
				}
			}
		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}

		System.exit(result);
	}
}