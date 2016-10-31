
public class LetterBag {
	private static final String alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private int[] letterFrequencies;
	
	public LetterBag(){
		letterFrequencies=new int[alphabet.length()];
	}
	public void add(String letter){
		
		
		this.letterFrequencies[alphabet.indexOf(letter)]++;
	}
	public int getTotalWords(){
		int result=0;
		for(int i=0;i<alphabet.length();i++){
			result+=this.letterFrequencies[i];
		}
		return result;	
	}
	public int getNumUniqueWords(){
		int result=0;
		for(int i=0;i<alphabet.length();i++){
			if(this.letterFrequencies[i]>0)result++;
		}
		return result;
	}
	public int getNumOccurances(String letter){
		String lower=letter.toLowerCase();
		return this.letterFrequencies[alphabet.indexOf(letter)];
	}
	public String getMostFrequent(){
		String result=null;
		int max=0;;
		for(int i=0;i<alphabet.length();i++){
			if (this.letterFrequencies[i]>max){
				max=this.letterFrequencies[i];
				
				result=alphabet.substring(i,i+1);
				
			}
		}
		return result;
	}
	public void clear(){
		for(int i=0;i<this.alphabet.length();i++){
			this.letterFrequencies[i]=0;
		}
	}
	
}
