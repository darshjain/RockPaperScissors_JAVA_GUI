import java.awt.Color;
import java.awt.Container;
import java.awt.event.*; 
import javax.swing.*;

public class RockPaperScissors {
	/* 
	 * 1 is rock
	 * 2 is paper
	 * 3 is scissors
	 */

	static int humanWon; // use for statistic
	static int win=0;
	static int total=0;
	static int tie=0;

	public static void main(String[] args){ // main
		gamePanel();// launch main game
		introductionPanel(); // launch instruction
	}

	private static void introductionPanel(){ // give the instruction to the game
		String text="Rock, Paper, Scissors!  This game is fairly simple.\nSimply pick your hands whenever you are ready.\nRock beats scissors, scissors beat paper\nand paper wrap the rock. Yes paper beats rock.";
		JOptionPane.showMessageDialog(null,text, "How to play!", 0, new ImageIcon(System.getProperty("user.dir")+"/image/5.gif"));
	}

	private static void gamePanel(){ // the main game panel

		JFrame frame = new JFrame("Rock, Scissors, Paper");  //the main frame of the game 

		Container panel = frame.getContentPane();  // creating a container panel, so we can place buttons where we pleased
		panel.setLayout(null); 

		String[] iconString= new String[3]; // creating icon string name so we can place the directory in with little effort
		int[] boundInt= new int[3]; // same idea

		for(int i=0; i<=2; i++){ // creating the condtions
			iconString[i]=System.getProperty("user.dir")+"/image/"+i+".jpg";
			boundInt[i]=40+150*i;
		}

		JButton b1 = new JButton (" ", new ImageIcon(iconString[0]));
		b1.setBackground(Color.white);
		b1.setBounds(40,boundInt[0],150,100);


		JButton b2 = new JButton (" ", new ImageIcon(iconString[1]));
		b2.setBackground(Color.white);
		b2.setBounds(40,boundInt[1],150,100);

		JButton b3 = new JButton (" ", new ImageIcon(iconString[2]));
		b3.setBackground(Color.white);
		b3.setBounds(40,boundInt[2],150,100);//creating three buttons

		JLabel l1 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/image/3.jpg"));
		l1.setBounds(300, 140, 128, 200);
		panel.add(l1);//creating a question button

		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		

		b1.addActionListener( //next three button will listen for which play pick and calculate the win in computeWinner

				new ActionListener() {
					public void actionPerformed( ActionEvent event ) {
						computeWinner(1);
					}
				}
		);

		b2.addActionListener(

				new ActionListener() {
					public void actionPerformed( ActionEvent event ) {
						computeWinner(2);
					}
				}
		);

		b3.addActionListener(

				new ActionListener() {
					public void actionPerformed( ActionEvent event ) {
						computeWinner(3);
					}
				}
		);


		frame.setSize(500, 500); 
		frame.setVisible(true); 
	}

	public static void computeWinner(int x){ // computing the winner
		int computerChoice=computerRandomChoice();
		int humanChoice=x;
		String text,text1="";
		String winningCombination= ""+Math.min(computerChoice, humanChoice)+Math.max(computerChoice, humanChoice);

		switch(Integer.parseInt(winningCombination)){

		case 12:
			text = "Paper wins!";
			if(humanChoice==2) humanWon=1;
			break;
		case 13:
			text = "Rock wins!";
			if(humanChoice==1) humanWon=1;
			break;
		case 23:
			text = "Scissors wins!";
			if(humanChoice==3) humanWon=1;
			break;
		default: text="It is a tie!";
		humanWon=2;
		tie=tie+1;
		}

		if(humanWon==1){
			text1="Human wins!  ";
			humanWon=0;
			win=win+1;
			total=total+1;
		}else if(humanWon==2){
			text1="It is a tie!  ";
			humanWon=0;		
		}else{
			text1="Computer wins!  ";
			total=total+1;

		}


		JFrame frame = new JFrame("Rock, Scissors, Paper"); 
		Container panel = frame.getContentPane(); 
		panel.setLayout(null); 


		JLabel l0 = new JLabel(text1+text);
		l0.setBounds(75, 10, 300, 35);
		panel.add(l0);


		//show the result in a new splash screen
		
		JLabel l1 = new JLabel("Human's Choice");
		l1.setBounds(40, 35, 150, 35);
		panel.add(l1);

		JLabel l2 = new JLabel("Computer's Choice");
		l2.setBounds(215, 35, 150, 35);
		panel.add(l2);

		JLabel l3 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/image/"+(humanChoice-1)+".jpg"));
		l3.setBounds(10, 100, 170, 60);
		panel.add(l3);

		JLabel l4 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/image/"+(computerChoice-1)+".jpg"));
		l4.setBounds(200, 100,170, 60);
		panel.add(l4);

		JLabel l5 = new JLabel("Win/Loss rate: " + win+"/"+total);
		l5.setBounds(125, 25, 150, 350);
		panel.add(l5);

		JLabel l6 = new JLabel("Tie: "+tie);
		l6.setBounds(125, 30, 125, 370);
		panel.add(l6);

		frame.setSize(400, 270); 
		frame.setVisible(true); 		



	}

	public static int computerRandomChoice(){// creating a random choice of rock paper or scissors by the computer
		int result=(int)(Math.random()*3)+1;		
		return result;
	}

}

