import java.util.*;

class Maze{
	
	static char[][] ar;
	static char[][] array;
	static Stack paths = new Stack();
	static Stack went = new Stack();
	
	public static void main(String args[]){
		setArray();
		solve();
	}
	
	public static void setArray(){
		Scanner input = new Scanner(System.in);
	
		// setting array size
		int r, c;
		System.out.print("Enter maze details : \n\tRows : ");
		r = input.nextInt();
		System.out.print("\tColumns : ");
		c = input.nextInt();
		ar = new char[r][c];
		
		// assigning user inputs to array elements
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				System.out.print("[" + i + "," + j + "] : ");
				ar[i][j] = input.next().charAt(0);
			}
			System.out.print("\n");
		}
	
		// resetting array with walls
		r = r + 2;
		c = c + 2;

		array = new char[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(i == 0 || i == r-1 || j == 0 || j == c-1){
					array[i][j] = '1';
				} else {
					array[i][j] = ar[i - 1][j - 1];
				}
			}
			System.out.print("\n");
		}
		
		// printing array
		System.out.print("Maze : \n");
		printArray(array);
	}
	
	public static void printArray(char[][] a){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				System.out.print(a[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public static void solve(){
		// finding location of 'm'
		boolean solving = true, stuck;
		int r = 0, c = 0, p = 0, q = 0;
		while(solving){
			stuck = true;
			for(int i = 0; i < array.length; i++){
					for(int j = 0; j < array[i].length; j++){
						if(array[i][j] == 'm'){
							r = i;
							c = j;
						}
					}
				}

			// checking for passages and storing in a stack
			if(array[r-1][c] == 'e' || array[r-1][c] == '0'){
				paths.push(r-1,c);
				stuck = false;
			}
			if(array[r+1][c] == 'e' || array[r+1][c] == '0'){
				paths.push(r+1,c);
				stuck = false;
			}
			if(array[r][c-1] == 'e' || array[r][c-1] == '0'){
				paths.push(r,c-1);
				stuck = false;
			}
			if(array[r][c+1] == 'e' || array[r][c+1] == '0'){
				paths.push(r,c+1);
				stuck = false;
			}
			
			if(stuck){
				// getting topmost path on the stack, went
				String s = "";
				if(!paths.isEmpty()){
					s = went.pop();
					p = Integer.parseInt(s.charAt(0) + "");
					q = Integer.parseInt(s.charAt(2) + "");
				
					// shifting 'm' and marking tried path
					array[r][c] = '.';
					array[p][q] = 'm';
					printArray(array);
				}
			} else {
				// getting topmost path on the stack
				String s = "";
				if(!paths.isEmpty()){
					s = paths.pop();
					p = Integer.parseInt(s.charAt(0) + "");
					q = Integer.parseInt(s.charAt(2) + "");
				
					// shifting 'm' and marking tried path
					if(array[p][q] == 'e'){
						solving = false;
					}
					array[r][c] = '.';
					array[p][q] = 'm';
					printArray(array);
					went.push(r, c);
				}
			}
		}
	}
}

