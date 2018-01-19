import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Boggle
{
	private static Map<String, Integer> dict = new HashMap<>();
	public static void readDictionary(String filename) throws FileNotFoundException
	{
		FileReader fr = new FileReader(filename);
		Scanner sc = new Scanner(fr);
		while(sc.hasNext())
		{
			dict.put(sc.nextLine().toLowerCase(), 1);
		}
		sc.close();
	}
	public static boolean isWord(String word)
	{
		return word.length() >=3 && dict.containsKey(word.toLowerCase());
	}
	public static List<String> foundWords = new ArrayList<>();
	public static void depthFirstSearch(int r, int c, String[][] swamp, String Path )
	{
		Path += swamp[r][c];
		if (isWord(Path) == false)
		{
			return;
		}
		if (isWord(Path))
		{
			if (!foundWords.contains(Path))
			foundWords.add(Path);
		}
		if (r != 0 && swamp[r-1][c].equals(swamp[r-1][c].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r-1, c, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (r != 0 && c!=swamp.length - 1 && swamp[r-1][c+1].equals(swamp[r-1][c+1].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r-1, c+1, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (c!=swamp.length - 1 && swamp[r][c+1].equals(swamp[r][c+1].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r, c+1, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (c!=swamp.length - 1 && r!=swamp.length - 1 && swamp[r+1][c+1].equals(swamp[r+1][c+1].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r+1, c+1, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (r!=swamp.length - 1 && swamp[r+1][c].equals(swamp[r+1][c].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r+1, c, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (c != 0 && r!=swamp.length - 1 && swamp[r+1][c-1].equals(swamp[r+1][c-1].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r+1, c-1, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (c != 0 && swamp[r][c-1].equals(swamp[r][c-1].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r, c-1, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
		if (c != 0 && r != 0 && swamp[r-1][c-1].equals(swamp[r-1][c-1].toLowerCase()))
		{
			swamp[r][c] = swamp[r][c].toUpperCase();
			depthFirstSearch( r-1, c-1, swamp, Path );
			swamp[r][c] = swamp[r][c].toLowerCase();
		}
	}
	public static void findWords(String board[][])
	{
		int M = board.length;
		int N = board[0].length;
		String str = "";
		for (int i=0; i<M; i++)
		{
			for (int j=0; j<N; j++)
			{
				depthFirstSearch(i, j, board, str);
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException
	{
		readDictionary(args[0]);
		FileReader fr = new FileReader(args[1]);
		Scanner sc = new Scanner(fr);
		int size = Integer.parseInt(sc.nextLine());
		String board[][] = new String[size][size];
		int i = 0;
		while(sc.hasNextLine())
		{
			String[] line = sc.nextLine().split("\\s+");
			board[i++] = line;
		}
		sc.close();
		findWords(board);
		Collections.sort(foundWords);
		for(String word : foundWords)
		System.out.println(word);
	}
}