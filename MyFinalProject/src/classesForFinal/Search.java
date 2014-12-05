package classesForFinal;

import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.*;

public class Search {

	public static void main(String[] args) throws IOException
	{
		{			
			Search t = new Search();
			t.startSearch("Java*");
			
			String pattern = ("*.java");
			PathMatcher matcher =
			    FileSystems.getDefault().getPathMatcher("glob:" + pattern);
			System.out.println(matcher);
		}
	}
	
	public PathMatcher testingShit(String pat)
	{
		PathMatcher matcher =
			    FileSystems.getDefault().getPathMatcher("glob:" + pat);
		return matcher;
	}
	public void startSearch(String toFind) throws IOException // Files.walkFileTree doesn't work without the throw
	{
			Path startingDir = Paths.get("C:\\Users\\smithm19\\My Documents");
			String pattern = toFind;
			Finder testFind = new Finder(pattern);
			Files.walkFileTree(startingDir, testFind);
	}

	public static class Finder extends SimpleFileVisitor<Path> {

		private final FileVisitResult CONTINUE = null; // constant
																// variable from
																// SimpleVisitResult
		private PathMatcher match;
		private int numMatches = 0; // record number of matches

		Finder(String test) {
			match = FileSystems.getDefault().getPathMatcher("glob:" + test);
		}

		Path find(Path file) {
			Path name = file.getFileName();
			if (name != null && match.matches(name)) {
				numMatches++;
				System.out.println(file);
				return file;
			}
			return null;
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
