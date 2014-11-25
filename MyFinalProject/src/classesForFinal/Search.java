package classesForFinal;

import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.*;

public class Search {

	public static void main(String[] args) throws IOException // Files.walkFileTree doesn't work without the throw
	{
		{

			Path startingDir = Paths.get(args[0]);
			String pattern = args[2];
			Finder testFind = new Finder(pattern);
			Files.walkFileTree(startingDir, testFind);
		}
	}

	public static class Finder extends SimpleFileVisitor<Path> {

		private static final FileVisitResult CONTINUE = null; // constant
																// variable from
																// SimpleVisitResult
		private final PathMatcher match;
		private int numMatches = 0; // record number of matches

		Finder(String test) {
			match = FileSystems.getDefault().getPathMatcher("glob:" + test);
		}

		void find(Path file) {
			Path name = file.getFileName();
			if (name != null && match.matches(name)) {
				numMatches++;
				System.out.println(file);
			}
		}

		void results() {
			System.out.println(numMatches + " Matches");
		}

		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			find(file);
			return CONTINUE;
		}

		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) {
			find(dir);
			return CONTINUE;
		}

	}
}
