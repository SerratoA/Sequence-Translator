//Angel Serrato

package codontranslation;

import java.util.Scanner;

public class SequenceTranslator {
	static BSTImplement<String, String> bst = new BSTImplement<>();
	public static void codonTranslation(String args) { //param is the text file containing codon to amino acid translations
		//Fills the table with the codons and their acids
		StdIn.fromFile(args);
		while (!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			String[] words = line.split("\\s+"); //string[AAA, lysine]
			if (words == null) {
				return;
			}
			else {
				bst.put(words[0], words[1]);
			}
		}	
		StdOut.println();
	}
	
	//'process' method takes in a DNA Sequence string and converts the information into
	// 11 separate amino acid translations with the full amino acid name
	public static void process(String args) { //param is the DNA sequence
		//Process the file and print it out in format
			String aminoAcid = args; //aminoAcid in multiples of 3
			int start = 0;
			int end = 3;
			for (int i = 0; i < aminoAcid.length(); i += 3) { //go by indexes of 3, 
				String codon = aminoAcid.substring(start, end); //codon starts with 0,3
				StdOut.println(codon + " " + bst.get(codon));
				start += 3;
				end += 3;
			}
			StdOut.println();
		}
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in); 
		System.out.print("Enter your DNA sequence:");    
		String sequence = scanner.next(); 
		codonTranslation("codondata/codontoAminoacid.txt");
		process(sequence);
	}
}
