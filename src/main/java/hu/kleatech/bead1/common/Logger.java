//@author Bozzay, Ádám
package hu.kleatech.bead1.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Function;

public class Logger {

	/**
	 The Logger class is singleton, you can access the only instance by this
	 variable. Use {@code import static} to make it more comfortable to use.
	 */
	public static final Logger LOGGER = new Logger();
	private FileWriter writer = null;
	private boolean logEverything = false;

	private Logger() {
	}

	/**
	 This method is used to make notes, not to log errors. The notes will be
	 written to the strout. It will only write to the log file if
	 {@link #logEverything logEverything()} is set to {@code true}.

	 @param s The note as a string
	 */
	public void log(String s) {
		System.out.println(s);
		if (logEverything) {
			write(s);
		}
	}

	/**
	 This method is used to make notes, not to log errors. The notes will be
	 written to the strout. It will only write to the log file if
	 {@link #logEverything logEverything()} is set to {@code true}.

	 @param s The note as a string
	 @param format An optional function to format the string
	 */
	public void log(String s, Function<String, String> format) {
		log(format.apply(s));
	}

	/**
	 This method is mostly used to log custom exceptions, or exceptions that are
	 self-explaining (via it's name or it's message). If you want to write out
	 your own message alongside it, you can either use
	 {@link #log(String, Exception) log(String s, Excepiton e}, or you can
	 customize the exception itself with
	 {@link #log(Exception, Function) log(Exception e, Function<Exception, Exception>
	 format).

	 @param e The exception to log
	 @return the same Exception that it was given as parameter {@code e}. With
	 this you can write something like this:
	 {@code throw (MyException) LOGGER.log(new MyException())} */
	@SuppressWarnings("CallToPrintStackTrace")
	public Exception log(Exception e) {
		write(Arrays.toString(e.getStackTrace()));
		return e;
	}

	/**
	 This method can be used if you want to customize the exception before
	 logging it.

	 @param e The exception to log
	 @param format The function to format the exception
	 @return the newly formatted Exception. With this you can write something
	 like this: {@code throw (MyException) LOGGER.log(new MyException())}
	 */
	public Exception log(Exception e, Function<Exception, Exception> format) {
		return log(format.apply(e));
	}

	/**
	 This method can be used if you want to write out a message alongside the
	 exception.

	 @param s The custom message (not confuse with {@code e.getMessage()})
	 @param e The exception to log
	 @return the newly formatted Exception. With this you can write something
	 like this: {@code throw (MyException) LOGGER.log(new MyException())}
	 */
	@SuppressWarnings("CallToPrintStackTrace")
	public Exception log(String s, Exception e) {
		write(s + '\n' + Arrays.toString(e.getStackTrace()));
		return e;
	}

	/**
	 You can use this method to further customize the logging. For example you
	 can note which developer's code have thrown the exception, so you can get
	 in touch with him if you get an exception.

	 @param s The custom message (not confuse with {@code e.getMessage()})
	 @param e The exception to log
	 @param strformat The function to format the string. Use
	 {@code Function.Identity}
	 if you don't want to set it.
	 @param excformat The function to customize the exception. Use
	 {@code Function.Identity} if you don't want to set it.
	 @return the newly formatted Exception. With this you can write something
	 like this: {@code throw (MyException) LOGGER.log(new MyException())}
	 */
	public Exception log(String s, Exception e, Function<String, String> strformat, Function<Exception, Exception> excformat) {
		return log(strformat.apply(s), excformat.apply(e));
	}

	/**
	 Simple method which prints out "breakpoint" to the strout.
	 */
	public void breakpoint() {
		System.out.println("breakpoint");
	}

	private void write(String s) {
		System.out.println(s);
		if (writer == null) {
			try {
				File file = new File("logs//" + System.currentTimeMillis() + "");
				file.getParentFile().mkdirs();
				writer = new FileWriter(file);
			}
			catch (IOException ex) {
				System.err.println("Log file can't be created");
			}
		}
		try {
			writer.write(LocalDateTime.now().toString() + '\n' + s + '\n');
			writer.flush();
		}
		catch (Exception e) {
			System.err.println("Log file can't be written: " + LocalDateTime.now().toString() + '\n' + s + '\n');
		}
	}

	/**
	 Since the Logger class write files, you should close the writer when
	 finished logging. If not closed, some logs can be missing from the file.
	 Also, you can use this method to split the logs. If you close a logfile,
	 another one will be opened when needed. Don't forget to close that one,
	 too.
	 */
	public void close() {
		try {
			if (writer != null)
				writer.close();
		}
		catch (IOException ex) {
			System.err.println("Logger didn't close, some logs can be missing.");
		}
	}

	/**
	 Normally the {@link #log(String) log(String s)} method only writes to the
	 stdout. If you also want to write these notes to the logfile, set this to
	 {@code true}.

	 @param flag Default false
	 */
	public void logEverything(boolean flag) {
		logEverything = flag;
	}
}
