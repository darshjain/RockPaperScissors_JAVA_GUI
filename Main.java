import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		Game.panel_game();
		Game.panel_introduction();
	}
}

class Game {	
	 //1 is rock 2 is paper 3 is scissors

	static int score_human;
	static int score_win = 0;
	static int score_total = 0;
	static int score_tie = 0;

	public static void panel_introduction() { // give the instruction to the game
		String info_text = "Rock, Paper, Scissors!  This game is fairly simple.\nSimply pick your hands whenever you are ready.\nRock beats scissors, scissors beat paper\nand paper wrap the rock. Yes paper beats rock.";
		JOptionPane.showMessageDialog(null, info_text, "How to play!", 1);
	}

	public static void panel_game() {
		JFrame frame_main = new JFrame("Rock, Scissors, Paper");
		Container panel_main = frame_main.getContentPane();
		panel_main.setLayout(null);

		String[] icon_path = new String[3]; // creating icon string name so we can place the directory in with little
											// effort
		int[] icon_bound = new int[3]; // same idea

		for (int i = 0; i <= 2; i++) { // creating the condtions
			icon_path[i] = System.getProperty("user.dir") + "/Assets/images/" + i + ".png";
			icon_bound[i] = 40 + 250 * i;
		}

		JButton btn_rock = new JButton(" ", new ImageIcon(icon_path[0]));
		btn_rock.setBackground(Color.white);
		btn_rock.setBounds(40, icon_bound[0], 200, 250);

		JButton btn_paper = new JButton(" ", new ImageIcon(icon_path[1]));
		btn_paper.setBackground(Color.white);
		btn_paper.setBounds(icon_bound[1], 40, 200, 250);

		JButton btn_scissors = new JButton(" ", new ImageIcon(icon_path[2]));
		btn_scissors.setBackground(Color.white);
		btn_scissors.setBounds(icon_bound[2], 40, 200, 250);// creating three buttons

		panel_main.add(btn_rock);
		panel_main.add(btn_scissors);
		panel_main.add(btn_paper);
		btn_rock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				compute_winner(1);
			}
		});

		btn_paper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				compute_winner(2);
			}
		});

		btn_scissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				compute_winner(3);
			}
		});

		frame_main.setSize(800, 400);
		frame_main.setVisible(true);
	}

	public static void compute_winner(int choice_human) {
		int choice_computer = (int) (Math.random() * 3) + 1;
		String label_choice, label_winner = "";
		String combo_winner=""+Math.min(choice_computer, choice_human) + Math.max(choice_computer, choice_human);
		switch (Integer.parseInt(combo_winner)) {

			case 12:
				label_choice = "Paper wins!";
				if (choice_human == 2)
					score_human = 1;
				break;
			case 13:
				label_choice = "Rock wins!";
				if (choice_human == 1)
					score_human = 1;
				break;
			case 23:
				label_choice = "Scissors wins!";
				if (choice_human == 3)
					score_human = 1;
				break;
			default:
				label_choice = "It is a tie!";
				score_human = 2;
				score_tie += 1;
		}
		if (score_human == 1){ 
			label_winner = "   Human wins!";
			score_human = 0;
			score_win += 1;
			score_total += 1;
		} 
		else if (score_human == 2) {
			label_winner = "   Noone wins!";
			score_human = 0;
		} else {
			label_winner = "   Computer wins!";
			score_total += 1;
		}

		JFrame score_frame = new JFrame("Rock, Scissors, Paper");
		Container score_panel = score_frame.getContentPane();
		score_panel.setLayout(null);

		JLabel label_result = new JLabel(label_choice + label_winner);
		label_result.setBounds(150, 10, 300, 35);
		score_panel.add(label_result);

		JLabel label_title_human = new JLabel("Human's Choice");
		label_title_human.setBounds(50, 35, 150, 35);
		score_panel.add(label_title_human);

		JLabel label_title_computer = new JLabel("Computer's Choice");
		label_title_computer.setBounds(350, 35, 150, 35);
		score_panel.add(label_title_computer);

		JLabel image_human = new JLabel(new ImageIcon(System.getProperty("user.dir") + "/Assets/images/" + (choice_human - 1) + ".png"));
		image_human.setBounds(10, 100, 200, 250);
		score_panel.add(image_human);

		JLabel image_computer = new JLabel(
				new ImageIcon(System.getProperty("user.dir") + "/Assets/images/" + (choice_computer - 1) + "c.png"));
		image_computer.setBounds(300, 100, 200, 250);
		score_panel.add(image_computer);

		JLabel label_score1 = new JLabel("Win / Total : " + score_win + "/" + score_total);
		label_score1.setBounds(175, 200, 150, 350);
		score_panel.add(label_score1);

		JLabel label_score2 = new JLabel("Tie: " + score_tie);
		label_score2.setBounds(175, 210, 125, 370);
		score_panel.add(label_score2);

		score_frame.setSize(600, 450);
		score_frame.setVisible(true);
	}
}
